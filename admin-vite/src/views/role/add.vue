<template>
    <Dialog v-model="isShow" :title="title" maxHeight="200px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="客户名称："  prop="name" >
            <el-input v-model="m.name"></el-input>
          </el-form-item>
<el-form-item label="描述："  prop="description" >
            <el-input v-model="m.description"></el-input>
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
const rules = {name:[ {required: true, message: '请输入客户名称', trigger: 'blur' },],
description:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 所属客户";
        //replace_file
        m.value = data;
    } else {
        m.value = {name:'',
description:''}
        title.value = "添加 所属客户";
    }
}

//提交所属客户信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            //replace_editor
            sa.post("/role/save", m.value).then((res) => {
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