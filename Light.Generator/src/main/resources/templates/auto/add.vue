<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            #{el-form-item}#
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
//import_file
//create_editor
const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {//rule_fields
}
const sa = inject('sa')
const ruleForm = ref();
const  query = ref({});

function open(data, parmas)  {
    isShow.value = true;
    if (data) {
        title.value = "修改 #{tableInfo}#";
        m.value = data;
    } else {
        let mdata  = //data_init
        query.value = parmas || {};
        m.value ={...mdata, ...parmas}
        title.value = "添加 #{tableInfo}#";
    }
}

//提交#{tableInfo}#信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            //replace_editor
            await sa.post("/#{EntityName}#/save", m.value);
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