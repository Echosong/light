<script setup>
// import axiosReq from '@/utils/axiosReq.js'
import { ref, onMounted } from 'vue'
import tinymce from 'tinymce/tinymce' //tinymce默认hidden，不引入不显示
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/themes/silver/theme' // 主题文件
import 'tinymce/icons/default'
import 'tinymce/models/dom'
// tinymce插件可按自己的需要进行导入
// 更多插件参考：https://www.tiny.cloud/docs/plugins/
import 'tinymce/plugins/image' // 插入上传图片插件
import 'tinymce/plugins/importcss' //图片工具
import 'tinymce/plugins/media' // 插入视频插件
import 'tinymce/plugins/table' // 插入表格插件
import 'tinymce/plugins/lists' // 列表插件
import 'tinymce/plugins/charmap' // 特殊字符
import 'tinymce/plugins/wordcount' // 字数统计插件
import 'tinymce/plugins/codesample' // 插入代码
import 'tinymce/plugins/code' // 查看源码
import 'tinymce/plugins/fullscreen' //全屏
import 'tinymce/plugins/link' //
import 'tinymce/plugins/preview' // 预览
import 'tinymce/plugins/template' //插入模板
import 'tinymce/plugins/save' // 保存
import 'tinymce/plugins/searchreplace' //查询替换
import 'tinymce/plugins/pagebreak' //分页
import 'tinymce/plugins/insertdatetime' //时间插入
import { upLoadfile } from '@/api/api';
import { urlMontage } from '@/utils'
const emit = defineEmits(["change"])
const props = defineProps({
    value: {
        type: String,
        default: ''
    },
    disabled: {
        type: Boolean,
        default: false
    },

    plugins: {
        type: [String, Array],
        default: 'lists image  wordcount save preview'
        // 插件需要import进来
        // default: 'wordcount visualchars visualblocks toc textpattern template tabfocus spellchecker searchreplace save quickbars print preview paste pagebreak noneditable nonbreaking media insertdatetime importcss imagetools image hr help fullscreen fullpage directionality codesample code charmap link code table lists advlist anchor autolink autoresize autosave'
    },
    toolbar: {
        type: [String, Array],
        default:
            'undo redo |  formatselect | bold italic underline strikethrough forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent blockquote image codesample code removeformat preview'
        // default:
        //   "formats undo redo paste print fontsizeselect fontselect template fullpage|wordcount ltr rtl visualchars visualblocks toc spellchecker searchreplace|save preview pagebreak nonbreaking|media image|outdent indent aligncenter alignleft alignright alignjustify lineheight  underline quicklink h2 h3 blockquote numlist bullist table removeformat forecolor backcolor bold italic  strikethrough hr charmap link insertdatetime|subscript superscript cut codesample code |anchor preview fullscreen|help",
    }
})

const onchange = async (file, success) => {
    let formData = new FormData();
    formData.append("file", file, file.name);
    let res = await upLoadfile(formData);
    success(urlMontage(res.data.fileurl));
}

const init = {
    // width: 720,
    height: 400,
    language_url: '/langs/zh-Hans.js',
    language: 'zh-Hans',
    // 皮肤：这里引入的是public下的资源文件
    skin_url: '/tinymce/skins/ui/oxide',
    // skin_url: 'tinymce/skins/ui/oxide-dark',//黑色系
    content_css: '/tinymce/skins/content/default/content.css', //内容区域css样式
    // images_file_types: "jpg,svg,webp",
    // images_upload_url: "xxxxxxxxxxxxx",//系统默认配置的自动上传路径，需替换为真实路径测试
    plugins: props.plugins,
    toolbar: props.toolbar,
    autoresize_min_height:'600px',
    branding: false,
    placeholder: "在这里输入文字",
    content_style: "p {margin: 0;}",
    // 隐藏菜单栏
    menubar: false,
    // 是否显示底部状态栏
    statusbar: true,
    // convert_urls: false,
    // 初始化完成
    init_instance_callback: (editor) => {
        console.log('初始化完成：', editor)
    },
    // file_picker_callback: function (callback, value, meta) {
    //     console.log(meta.filetype)
    //     if (meta.filetype == "media") {
    //         var input = document.createElement("input");
    //         input.setAttribute("type", "file");
    //         input.click();
    //         input.onchange = function () {
    //             var file = this.files[0];
    //             onchange(file, (res) => {
    //                 callback(res, {
    //                     title: file.name,
    //                 });
    //             });
    //         };
    //     }
    // },
    images_upload_handler: (blobInfo, progress) =>
        new Promise(async (resolve, reject) => {
            let formData = new FormData();
            formData.append("file", blobInfo.blob());
            let res = await upLoadfile(formData);
            resolve(urlMontage(res.data.fileurl));
        }),
    init_instance_callback: (editor) => {
        // console.log(`回调----`)
        // editor.on("input", (e) => {
        //   // console.log('文本框input触发')
        //   this.$emit("input", e.target.innerHTML);
        //   console.log(e.target.innerHTML)
        // });
        editor.on("change", (e) => {
            // console.log('文本框change触发')
            emit("change", e.level.content);
        });
    },
}
let textContent = ref(props.value)
// 组件挂载完毕
onMounted(() => {
    tinymce.init({})
})

// 添加相关的事件,https://github.com/tinymce/tinymce-vueevents
const clear = () => {
    textContent = ''
}

const setContent = (value) => {
    textContent.value = value
}
const getContent = () => {
    return textContent.value
}
defineExpose({ setContent, getContent })



</script>
<template>
    <editor style="width: 100%;" v-model="textContent" :init="init" :disabled="disabled"></editor>
</template>