<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="80%" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="标题：" prop="title">
                        <el-input v-model="m.title"></el-input>
                    </el-form-item>
                    <el-form-item label="类型">
                        <input-enum
                            enumName="articleTypeEnum"
                            v-model="m.type"
                        ></input-enum>
                    </el-form-item>
                    <el-form-item label="内容">
                        <Tinymce ref="content" v-model="m.content"></Tinymce>
                    </el-form-item>
                    <el-form-item label="项目id：" prop="projectId">
                        <el-input v-model="m.projectId"></el-input>
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

import Tinymce from '@/components/Tinymce/Tinymce.vue';

const content = ref()

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    title: [],
    type: [],
    content: [],
    projectId: [],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 新闻";

        m.value = data;
    } else {
        m.value = {
            title: '',
            type: 0,
            content: '',
            projectId: 0
        }
        title.value = "添加 新闻";
    }
}


//提交新闻信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {

            m.value.content = content.value.getContent()
            sa.post("/article/save", m.value).then((res) => {
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