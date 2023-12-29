<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="账号：" prop="username">
                <el-input v-model="m.username"></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password">
                <el-input v-model="m.password"></el-input>
            </el-form-item>
            <el-form-item label="性别：" prop="sex">
                <el-input v-model="m.sex"></el-input>
            </el-form-item>
            <el-form-item label="身高：" prop="height">
                <el-input v-model="m.height"></el-input>
            </el-form-item>
            <el-form-item label="体重：" prop="weight">
                <el-input v-model="m.weight"></el-input>
            </el-form-item>
            <el-form-item label="昵称：" prop="nick">
                <el-input v-model="m.nick"></el-input>
            </el-form-item>
            <el-form-item label="注册方式">
                <input-enum
                    enumName="userRegTypeEnum"
                    v-model="m.type"
                ></input-enum>
            </el-form-item>
            <el-form-item label="姓名：" prop="name">
                <el-input v-model="m.name"></el-input>
            </el-form-item>
            <el-form-item label="状态默认开启">
                <input-enum
                    enumName="userStateEnum"
                    v-model="m.state"
                ></input-enum>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
                <el-input v-model="m.email"></el-input>
            </el-form-item>
            <el-form-item label="注册ip：" prop="regIp">
                <el-input v-model="m.regIp"></el-input>
            </el-form-item>
            <el-form-item label="登录ip：" prop="loginIp">
                <el-input v-model="m.loginIp"></el-input>
            </el-form-item>
            <el-form-item label="身份证正面">
                <FileImage @onremove="m.frontCard = ''" @onSuccess="m.frontCard = $event" :file="m.frontCard"/>
            </el-form-item>
            <el-form-item label="身份证反面">
                <FileImage @onremove="m.reverseCard = ''" @onSuccess="m.reverseCard = $event" :file="m.reverseCard"/>
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
import FileImage from "@/components/file/fileImage.vue";
import InputEnum from "@/components/enum/InputEnum.vue";

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    username: [{required: true, message: '请输入账号', trigger: 'blur'}, {
        pattern: /\d{11}/,
        message: '',
        trigger: 'blur'
    }],
    password: [{min: 6, max: 2147483647, message: '密码必须大于等于6位', trigger: 'blur'},],
    sex: [{pattern: /^\d{0,}$/, message: '性别必须为数字类型', trigger: 'blur'},],
    height: [{pattern: /^\d{0,}$/, message: '身高必须为数字类型', trigger: 'blur'},],
    weight: [],
    nick: [],
    type: [],
    name: [{min: 2, max: 2147483647, message: '姓名必须大于两个字符', trigger: 'blur'},],
    state: [],
    email: [{type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}],
    regIp: [],
    loginIp: [],
    frontCard: [],
    reverseCard: [],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 用户";
        m.value = data;
    } else {
        m.value = {
            username: '',
            password: '',
            sex: 0,
            height: 0,
            weight: 0,
            nick: '',
            type: '',
            name: '',
            state: 0,
            email: '',
            regIp: '',
            loginIp: '',
            frontCard: '',
            reverseCard: ''
        }
        title.value = "添加 用户";
    }
}

//提交用户信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {

            sa.post("/user/save", m.value).then((res) => {
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