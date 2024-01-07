<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="字典名称："  prop="dictName" >
    <el-input v-model="m.dictName"></el-input>
</el-form-item>
<el-form-item label="字典类型："  prop="dictType" >
    <el-input v-model="m.dictType"></el-input>
</el-form-item>
<el-form-item label="状态">
     <input-enum enumName="dicTypeStateEnum" v-model="m.state" ></input-enum>
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

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {dictName:[],
dictType:[],
state:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 字典类型";
        m.value = data;
    } else {
        m.value = {dictName:'',
dictType:'',
state:0}
        title.value = "添加 字典类型";
    }
}

//提交字典类型信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            
            await sa.post("/dictType/save", m.value);
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