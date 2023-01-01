<style scoped>
</style>

<template>
    <el-dialog v-if="m" :title="title" :visible.sync="isShow" width="550px" top="10vh" :append-to-body="true"
        :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog">
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                    label-width="90px">
                    <el-form-item label="配置字段名：" prop="key">
                        <el-input v-model="m.key"></el-input>
                    </el-form-item>
                    <el-form-item label="配置说明：" prop="name">
                        <el-input v-model="m.name"></el-input>
                    </el-form-item>
                    <el-form-item label="配置分组：" prop="group">
                        <el-input v-model="m.group"></el-input>
                    </el-form-item>
                    <el-form-item label="配置值：" prop="value">
                        <el-input v-model="m.value"></el-input>
                    </el-form-item>
                    <el-form-item label="类型：" prop="type">
                        <el-input v-model="m.type"></el-input>
                    </el-form-item>
                    <el-form-item label="描述：" prop="description">
                        <el-input v-model="m.description"></el-input>
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
//import inputEnum from "../../sa-resources/com-view/input-enum.vue";
export default {
    //components: { inputEnum },
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
                this.title = "修改 系统配置";
                this.m = data;
            } else {
                this.m = {
                    key: '',
                    name: '',
                    group: 0,
                    value: '',
                    type: 0,
                    description: ''
                }
                this.title = "添加 系统配置";
            }
        },
        success(response, file, fileList) {
            console.log(response, file, fileList);
        },

        //提交系统配置信息
        ok: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.sa.post("/config/save", this.m).then((res) => {
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