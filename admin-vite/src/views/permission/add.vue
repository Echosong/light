<template>
    <Dialog v-model="isShow" :title="title" maxHeight="90%">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
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

//create_editor
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
        title.value = "修改 权限";
        //replace_file
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
}

//提交权限信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            //replace_editor
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