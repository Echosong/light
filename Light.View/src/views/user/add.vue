<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="账号" prop="username" v-if="!query.username">
                <el-input v-model="m.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" v-if="!query.password">
                <el-input v-model="m.password"></el-input>
            </el-form-item>
            <el-form-item label="性别" v-if="!query.sex">
                <input-enum enumName="userSexEnum" v-model="m.sex"></input-enum>
            </el-form-item>
            <el-form-item label="工号" prop="code" v-if="!query.code">
                <el-input v-model="m.code"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name" v-if="!query.name">
                <el-input v-model="m.name"></el-input>
            </el-form-item>
            <el-form-item label="状态" v-if="!query.state">
                <el-switch v-model="m.state"></el-switch>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" v-if="!query.email">
                <el-input v-model="m.email"></el-input>
            </el-form-item>
            <el-form-item label="注册ip" prop="regIp" v-if="!query.regIp">
                <el-input v-model="m.regIp"></el-input>
            </el-form-item>
            <el-form-item label="登录ip" prop="loginIp" v-if="!query.loginIp">
                <el-input v-model="m.loginIp"></el-input>
            </el-form-item>

            <el-form-item label="角色" v-if="!query.roleId">
                <select-data v-model="m.roleId" routeName="role"></select-data>
            </el-form-item>
            <el-form-item label="个人简介" v-if="!query.info">
                <el-input type="textarea" rows="2" placeholder="个人简介" v-model="m.info"></el-input>
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
import InputEnum from "@/components/enum/InputEnum.vue";
import selectData from '@/components/SelectData/index.vue'

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    username: [],
    password: [{min: 6, max: 2147483647, message: '密码必须大于等于6位', trigger: 'blur'},],
    sex: [{pattern: /^\d{0,}$/, message: '性别必须为数字类型', trigger: 'blur'},],
    code: [],
    name: [{min: 2, max: 2147483647, message: '姓名必须大于两个字符', trigger: 'blur'},],
    state: [],
    email: [],
    regIp: [],
    loginIp: [],
    info: [],
}
const sa = inject('sa')
const ruleForm = ref();
const query = ref({});

async function open(data, parmas) {
    isShow.value = true;
    if (data) {
        title.value = "修改 用户";
        let one = await sa.get("/user/find/" + data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata = {
            username: '',
            password: '',
            sex: 0,
            code: '',
            name: '',
            state: 0,
            email: '',
            regIp: '',
            loginIp: '',
            info: ''
        }
        query.value = parmas || {};
        m.value = {...mdata, ...parmas}
        title.value = "添加 用户";
    }
}

//提交用户信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {

            await sa.post("/user/save", m.value);
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