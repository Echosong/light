<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="550px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="配置字段名："  prop="key" >
            <el-input v-model="m.key"></el-input>
          </el-form-item>
<el-form-item label="配置说明："  prop="name" >
            <el-input v-model="m.name"></el-input>
          </el-form-item>
<el-form-item label="配置分组："  prop="group" >
            <el-input v-model="m.group"></el-input>
          </el-form-item>
<el-form-item label="配置值："  prop="value" >
            <el-input v-model="m.value"></el-input>
          </el-form-item>
<el-form-item label="类型："  prop="type" >
            <el-input v-model="m.type"></el-input>
          </el-form-item>
<el-form-item label="描述："  prop="description" >
            <el-input v-model="m.description"></el-input>
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
const rules = {key:[],
name:[],
group:[{pattern:/^\d{0,}$/, message:'配置分组必须为数字类型', trigger: 'blur'},],
value:[],
type:[{pattern:/^\d{0,}$/, message:'类型必须为数字类型', trigger: 'blur'},],
description:[{pattern:/\s{2,}/, message:'', trigger: 'blur'}],
}
const sa = inject('sa')
const ruleForm = ref();
function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 系统配置";
        
        m.value = data;
    } else {
        m.value = {key:'',
name:'',
group:0,
value:'',
type:0,
description:''}
        title.value = "添加 系统配置";
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



//提交系统配置信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            
             sa.post("/config/save", m.value).then((res) => {
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