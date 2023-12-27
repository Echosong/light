<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="550px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="权限名称："  prop="name" >
            <el-input v-model="m.name"></el-input>
          </el-form-item>
<el-form-item label="权限描述："  prop="description" >
            <el-input v-model="m.description"></el-input>
          </el-form-item>
<el-form-item label="访问路径："  prop="url" >
            <el-input v-model="m.url"></el-input>
          </el-form-item>
<el-form-item label="权限标识："  prop="perms" >
            <el-input v-model="m.perms"></el-input>
          </el-form-item>
<el-form-item label="父级id："  prop="parentId" >
            <el-input v-model="m.parentId"></el-input>
          </el-form-item>
<el-form-item label="类型："  prop="type" >
            <el-input v-model="m.type"></el-input>
          </el-form-item>
<el-form-item label="排序："  prop="sort" >
            <el-input v-model="m.sort"></el-input>
          </el-form-item>
<el-form-item label="图标："  prop="icon" >
            <el-input v-model="m.icon"></el-input>
          </el-form-item>
<el-form-item label="是否展现 1 展现 0 不展现："  prop="show" >
            <el-input v-model="m.show"></el-input>
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
const rules = {name:[],
description:[],
url:[],
perms:[],
parentId:[{pattern:/^\d{0,}$/, message:'父级id必须为数字类型', trigger: 'blur'},],
type:[{pattern:/^\d{0,}$/, message:'类型必须为数字类型', trigger: 'blur'},],
sort:[],
icon:[],
show:[],
}
const sa = inject('sa')
const ruleForm = ref();
function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 权限";
        
        m.value = data;
    } else {
        m.value = {name:'',
description:'',
url:'',
perms:'',
parentId:0,
type:0,
sort:0,
icon:'',
show:0}
        title.value = "添加 权限";
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



//提交权限信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            
             sa.post("/permission/save", m.value).then((res) => {
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