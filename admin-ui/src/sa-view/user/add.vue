<style scoped>
</style>

<template>
    <el-dialog v-if="m" :title="title" :visible.sync="isShow" width="80%" top="10vh" :append-to-body="true"
        :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog">
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                    label-width="90px">
                    <el-form-item label="账号：" prop="username">
                        <el-input v-model="m.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码：" prop="password">
                        <el-input v-model="m.password"></el-input>
                    </el-form-item>
                    <el-form-item label="性别：" prop="sex">
                        <el-input v-model="m.sex"></el-input>
                    </el-form-item>
                    <el-form-item label="姓名：" prop="name">
                        <el-input v-model="m.name"></el-input>
                    </el-form-item>
                    <el-form-item label="状态默认开启">
                        <input-enum enumName="userStateEnum" v-model="m.state"></input-enum>
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
                    <el-form-item>
                        <span class="c-label">&emsp;</span>
                        <el-button type="primary" icon="el-icon-plus" size="mini" @click="ok('ruleForm')">确定</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </el-dialog>
</template>

<script>
import inputEnum from "../../sa-resources/com-view/input-enum.vue";
export default {
    components: { inputEnum },
    props: ["params"],
    data() {
        return {
            m: {},
            title: "",
            isShow: false,
            rules: {},
            fileList: [],
        }
    },
    methods: {
        open: function (data) {
            this.isShow = true;
            if (data) {
                this.title = "修改 用户";
                this.m = data;
            } else {
                this.m = {
                    username: '',
                    password: '',
                    sex: 0,
                    name: '',
                    email: '',
                    regIp: '',
                    loginIp: ''
                }
                this.title = "添加 用户";
            }
        },
        success(response, file, fileList) {
            console.log(response, file, fileList);
        },

        //提交用户信息
        ok: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.sa.post("/user/save", this.m).then((res) => {
                        console.log(res);
                        this.$parent.f5();
                        this.isShow = false;
                    });
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        }
    },
    created() {
    },
};
</script>