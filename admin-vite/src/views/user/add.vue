<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="550px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
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
                    <el-form-item label="身份证正面：" prop="frontCard">
                        <el-input v-model="m.frontCard"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证反面：" prop="reverseCard">
                        <el-input v-model="m.reverseCard"></el-input>
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
const ruleForm = ref();
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

function open(data) {
    isShow.value = true;
    console.log("进入了新增页面")
    if (data) {
        this.title = "修改 用户";
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
            roleId: 1,
            reverseCard: ''
        }
        title.value = "添加 用户";
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



//提交用户信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            sa.post("/user/create", m.value).then((res) => {
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