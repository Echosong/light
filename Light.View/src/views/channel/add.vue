<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px" >
            <el-form-item label="渠道名" prop="channelName" v-if="!query.channelName">
                <el-input v-model="m.channelName"></el-input>
            </el-form-item>
            <el-form-item label="保险公司" prop="companyName" v-if="!query.companyName">
                <el-input v-model="m.companyName"></el-input>
            </el-form-item>
            <el-form-item label="渠道等级" prop="grade" v-if="!query.grade">
                <el-input v-model="m.grade"></el-input>
            </el-form-item>
            <el-form-item label="渠道方案" prop="scheme" v-if="!query.scheme">
                <el-input v-model="m.scheme"></el-input>
            </el-form-item>
            <el-form-item label="伤残比例" prop="accidentRate" v-if="!query.accidentRate">
                <el-input v-model="m.accidentRate"></el-input>
            </el-form-item>
            <el-form-item label="成本价" prop="costPrice" v-if="!query.costPrice">
                <el-input v-model="m.costPrice"></el-input>
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


const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    channelName: [],
    companyName: [],
    grade: [],
    scheme: [],
    accidentRate: [],
    costPrice: [],
}
const sa = inject('sa')
const ruleForm = ref();
const query = ref({});

async function open(data, parmas) {
    isShow.value = true;
    if (data) {
        title.value = "修改 渠道信息表";
        let one = await sa.get("/channel/find/" + data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata = {
            channelName: '',
            companyName: '',
            grade: '',
            scheme: '',
            accidentRate: '',
            costPrice: ''
        }
        query.value = parmas || {};
        m.value = {...mdata, ...parmas}
        title.value = "添加 渠道信息表";
    }
}

//提交渠道信息表信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {

            await sa.post("/channel/save", m.value);
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