<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="550px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="请求ip：" prop="requestIp">
                        <el-input v-model="m.requestIp"></el-input>
                    </el-form-item>
                    <el-form-item label="地址：" prop="address">
                        <el-input v-model="m.address"></el-input>
                    </el-form-item>
                    <el-form-item label="描述：" prop="description">
                        <el-input v-model="m.description"></el-input>
                    </el-form-item>
                    <el-form-item label="浏览器">
                        <el-input type="textarea" rows="2" placeholder="浏览器" v-model="m.browser"></el-input>
                    </el-form-item>
                    <el-form-item label="请求耗时：" prop="time">
                        <el-input v-model="m.time"></el-input>
                    </el-form-item>
                    <el-form-item label="方法名：" prop="method">
                        <el-input v-model="m.method"></el-input>
                    </el-form-item>
                    <el-form-item label="参数">
                        <el-input type="textarea" rows="2" placeholder="参数" v-model="m.params"></el-input>
                    </el-form-item>
                    <el-form-item label="日志类型：" prop="logType">
                        <el-input v-model="m.logType"></el-input>
                    </el-form-item>
                    <el-form-item label="异常详情：" prop="exceptionDetail">
                        <el-input v-model="m.exceptionDetail"></el-input>
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
const rules = {
    requestIp: [],
    address: [],
    description: [],
    browser: [],
    time: [],
    method: [],
    params: [],
    logType: [],
    exceptionDetail: [],
}
const sa = inject('sa')
const ruleForm = ref();

function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 日志";

        m.value = data;
    } else {
        m.value = {
            requestIp: '',
            address: '',
            description: '',
            browser: '',
            time: 0,
            method: '',
            params: '',
            logType: '',
            exceptionDetail: 0
        }
        title.value = "添加 日志";
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



//提交日志信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {

            sa.post("/log/save", m.value).then((res) => {
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