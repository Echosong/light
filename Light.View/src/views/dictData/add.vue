<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="字典编码："  prop="dictCode" >
    <el-input v-model="m.dictCode"></el-input>
</el-form-item>
<el-form-item label="字典排序："  prop="dictSort" >
    <el-input v-model="m.dictSort"></el-input>
</el-form-item>
<el-form-item label="字典标签："  prop="dictLabel" >
    <el-input v-model="m.dictLabel"></el-input>
</el-form-item>
<el-form-item label="字典键值："  prop="dictValue" >
    <el-input v-model="m.dictValue"></el-input>
</el-form-item>
<el-form-item label="字典类型："  prop="dictType" >
    <el-input v-model="m.dictType"></el-input>
</el-form-item>
<el-form-item label="样式属性："  prop="cssClass" >
    <el-input v-model="m.cssClass"></el-input>
</el-form-item>
<el-form-item label="表格字典样式："  prop="listClass" >
    <el-input v-model="m.listClass"></el-input>
</el-form-item>
<el-form-item label="是否默认："  prop="defaultFlg" >
    <el-input v-model="m.defaultFlg"></el-input>
</el-form-item>
<el-form-item label="状态">
     <input-enum enumName="dicTypeStateEnum" v-model="m.status" ></input-enum>
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
const rules = {dictCode:[],
dictSort:[],
dictLabel:[],
dictValue:[],
dictType:[],
cssClass:[],
listClass:[],
defaultFlg:[],
status:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 字典数据";
        m.value = data;
    } else {
        m.value = {dictCode:0,
dictSort:0,
dictLabel:'',
dictValue:'',
dictType:'',
cssClass:'',
listClass:'',
defaultFlg:0,
status:0}
        title.value = "添加 字典数据";
    }
}

//提交字典数据信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            
            await sa.post("/dictData/save", m.value);
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