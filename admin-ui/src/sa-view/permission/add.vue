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
                    <el-form-item label="权限名称：" prop="name">
                        <el-input v-model="m.name"></el-input>
                    </el-form-item>
                    <el-form-item label="权限描述：" prop="description">
                        <el-input v-model="m.description"></el-input>
                    </el-form-item>
                    <el-form-item label="访问路径：" prop="url">
                        <el-input v-model="m.url"></el-input>
                    </el-form-item>
                    <el-form-item label="权限标识：" prop="perms">
                        <el-input v-model="m.perms"></el-input>
                    </el-form-item>
                    <el-form-item label="父级id：" prop="parentId">
                        <el-input v-model="m.parentId"></el-input>
                    </el-form-item>
                    <el-form-item label="类型：" prop="type">
                        <el-input v-model="m.type"></el-input>
                    </el-form-item>
                    <el-form-item label="排序：" prop="sort">
                        <el-input v-model="m.sort"></el-input>
                    </el-form-item>
                    <el-form-item label="图标：" prop="icon">
                        <el-input v-model="m.icon"></el-input>
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
                this.title = "修改 权限";
                this.m = data;
            } else {
                this.m = {
                    name: '',
                    description: '',
                    url: '',
                    perms: '',
                    parentId: 0,
                    type: 0,
                    sort: 0,
                    icon: ''
                }
                this.title = "添加 权限";
            }
        },
        success(response, file, fileList) {
            console.log(response, file, fileList);
        },

        //提交权限信息
        ok: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.sa.post("/permission/save", this.m).then((res) => {
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