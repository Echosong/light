<template>
    <Dialog v-model="isShow" :title="title" maxHeight="80%">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="请求ip："  prop="requestIp" >
            <el-input v-model="m.requestIp"></el-input>
          </el-form-item>
<el-form-item label="地址："  prop="address" >
            <el-input v-model="m.address"></el-input>
          </el-form-item>
<el-form-item label="描述："  prop="description" >
            <el-input v-model="m.description"></el-input>
          </el-form-item>
<el-form-item label="浏览器">
<el-input type="textarea"  rows="2" placeholder="浏览器"  v-model="m.browser"></el-input></el-form-item>
<el-form-item label="请求耗时："  prop="time" >
            <el-input v-model="m.time"></el-input>
          </el-form-item>
<el-form-item label="方法名："  prop="method" >
            <el-input v-model="m.method"></el-input>
          </el-form-item>
<el-form-item label="参数">
<el-input type="textarea"  rows="2" placeholder="参数"  v-model="m.params"></el-input></el-form-item>
<el-form-item label="日志类型："  prop="logType" >
            <el-input v-model="m.logType"></el-input>
          </el-form-item>
<el-form-item label="异常详情">
<el-input type="textarea"  rows="2" placeholder="异常详情"  v-model="m.exceptionDetail"></el-input></el-form-item>
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


const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {requestIp:[],
address:[],
description:[],
browser:[],
time:[],
method:[],
params:[],
logType:[],
exceptionDetail:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 日志";
        m.value = data;
    } else {
        m.value = {requestIp:'',
address:'',
description:'',
browser:'',
time:0,
method:'',
params:'',
logType:'',
exceptionDetail:0}
        title.value = "添加 日志";
    }
}

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