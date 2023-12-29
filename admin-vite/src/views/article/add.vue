<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="标题：" prop="title">
                <el-input v-model="m.title"></el-input>
            </el-form-item>
            <el-form-item label="封面图片">
                <FileImage @onremove="m.imgUrl = ''" @onSuccess="m.imgUrl = $event" :file="m.imgUrl"/>
            </el-form-item>
            <el-form-item label="类型">
                <input-enum enumName="articleTypeEnum" v-model="m.type"></input-enum>
            </el-form-item>
            <el-form-item label="内容">
                <Tinymce ref="content" v-model="m.content"></Tinymce>
            </el-form-item>
            <el-form-item label="附件">
                <File @onremove="m.fileUrl = ''" @onSuccess="m.fileUrl = $event" :file="m.fileUrl"/>
            </el-form-item>
            <el-form-item label="项目id：" prop="projectId">
                <el-input v-model="m.projectId"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="ok($parent)">
                    确定
                </el-button>
                <el-button @click="isShow = false">取消</el-button>
            </span>
        </template>
    </Dialog>
</template>

<script setup>
import Dialog from "@/components/dialog/index.vue";
import {inject, ref} from "vue";
import File from "@/components/file/file.vue";
import Tinymce from '@/components/Tinymce/Tinymce.vue';
import FileImage from "@/components/file/fileImage.vue";
import InputEnum from "@/components/enum/InputEnum.vue";

const content = ref()
const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    title: [],
    imgUrl: [],
    type: [],
    content: [],
    fileUrl: [],
    projectId: [],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 新闻";
        m.value = data;
    } else {
        m.value = {
            title: '',
            imgUrl: '',
            type: 0,
            content: '',
            fileUrl: '',
            projectId: 0
        }
        title.value = "添加 新闻";
    }
}

//提交新闻信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            let contentText = content.value.getContent()
            if (contentText) {
                m.value.content = contentText
            }
            await sa.post("/article/save", m.value);
            parent.f5();
            isShow.value = false;
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