<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="550px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="图标">
<el-upload
  class="upload-demo"
  :action="sa.cfg.api_url + '/file/upload'"
  :multiple="false"
  :data="{ fileType: 2, params: '' }"  :limit="10"
    :on-success="success_ico"   :before-remove="remove_ico"  :file-list="m.icoFile">
  <el-button size="mini" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">上传图标</div>
</el-upload></el-form-item>
<el-form-item label="文件名">
<el-upload
  class="upload-demo"
  :action="sa.cfg.api_url + '/file/upload'"
  :multiple="false"
  :data="{ fileType: 2, params: '' }"  :limit="10"
  list-type="picture-card" 
   :on-success="success_fileName"   :before-remove="remove_fileName"  :file-list="m.fileNameFile">
  <el-button size="mini" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">上传文件名</div>
</el-upload></el-form-item>
<el-form-item label="文件扩展名："  prop="extend" >
            <el-input v-model="m.extend"></el-input>
          </el-form-item>
<el-form-item label="文件存储路径："  prop="filePath" >
            <el-input v-model="m.filePath"></el-input>
          </el-form-item>
<el-form-item label="文件类型">
                    <input-enum
                        enumName="fileTypeEnum"
                        v-model="m.fileType"
                    ></input-enum>
                </el-form-item>
<el-form-item label="文件大小："  prop="fileSize" >
            <el-input v-model="m.fileSize"></el-input>
          </el-form-item>
<el-form-item label="相对路径："  prop="urlPath" >
            <el-input v-model="m.urlPath"></el-input>
          </el-form-item>
<el-form-item label="文件唯一标识："  prop="uuid" >
            <el-input v-model="m.uuid"></el-input>
          </el-form-item>
                    <el-form-item>
                        <span class="c-label">&emsp;</span>
                        <el-button type="primary" icon="el-icon-plus" size="small" @click="ok($parent)">确定
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </el-dialog>
</template>

<script setup>

import {inject, ref} from "vue";

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {ico:[],
fileName:[ {required: true, message: '请输入文件名', trigger: 'blur' },],
extend:[],
filePath:[],
fileType:[],
fileSize:[],
urlPath:[],
uuid:[],
}
const sa = inject('sa')
const ruleForm = ref();
function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 文件管理";
        data.icoFile = JSON.parse(data.ico);
        m.value = data;
    } else {
        m.value = {ico:'',
icoFile:[],
fileName:'',
fileNameFile:[],
extend:'',
filePath:'',
fileType:0,
fileSize:0,
urlPath:'',
uuid:''}
        title.value = "添加 文件管理";
    }
    //create_editor
}

/*create_editor
create_editor: function (editName) {
    this.$nextTick(function () {
        let editor = new E(this.$refs[editName]);
        editor.customConfig.debug = true; // debug模式
        editor.customConfig.uploadFileName = 'file'; // 图片流name
        editor.customConfig.withCredentials = true; // 跨域携带cookie
        editor.customConfig.uploadImgShowBase64 = true   	// 使用 base64 保存图片
        editor.customConfig.onchange = (html) => {	// 创建监听，实时传入
            this.m[editName] = html;
        }
        editor.create();		// 创建编辑器
        editor.txt.html(this.m[editName]);	// 为编辑器赋值
    });
},
 create_editor*/

success_ico(response, file, fileList) {
          if(response.code != 200){
            this.sa.error(response.message);
            return;
          }
          if(!this.m.icoFile){
            this.m.icoFile = [];
          }          this.m.icoFile.push(response.data);
          console.log(fileList);
        },
 remove_ico(file, fileList){
    this.m.icoFile = fileList;
},

//提交文件管理信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
                   this.m.ico =JSON.stringify(this.m.icoFile.map(item=>{
              let a = {};
              a.name = item.name;
              a.url = item.url;
              return a;       }));
             sa.post("/file/save", m.value).then((res) => {
                parent.f5();
                isShow.value = false;
            });
        } else {
            console.log("error submit!!");
            return false;
        }
    });
}
defineExpose({
    open
})

</script>