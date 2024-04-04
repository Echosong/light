<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="订单日期："  prop="orderTime" v-if="!query.orderTime">
   <el-date-picker v-model="m.orderTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="订单日期"></el-date-picker>
</el-form-item>
<el-form-item label="客户名称"  prop="customerName" v-if="!query.customerName" >
    <el-input v-model="m.customerName"></el-input>
</el-form-item>
<el-form-item label="渠道名称"  prop="channelName" v-if="!query.channelName" >
    <el-input v-model="m.channelName"></el-input>
</el-form-item>
<el-form-item label="保险公司"  prop="companyName" v-if="!query.companyName" >
    <el-input v-model="m.companyName"></el-input>
</el-form-item>
<el-form-item label="方案"  prop="scheme" v-if="!query.scheme" >
    <el-input v-model="m.scheme"></el-input>
</el-form-item>
<el-form-item label="渠道等级"  prop="grade" v-if="!query.grade" >
    <el-input v-model="m.grade"></el-input>
</el-form-item>
<el-form-item label="伤残比例"  prop="accidentRate" v-if="!query.accidentRate" >
    <el-input v-model="m.accidentRate"></el-input>
</el-form-item>
<el-form-item label="三类人数"  prop="threeClass" v-if="!query.threeClass" >
    <el-input v-model="m.threeClass"></el-input>
</el-form-item>
<el-form-item label="三类售价"  prop="threeClassPrice" v-if="!query.threeClassPrice" >
    <el-input v-model="m.threeClassPrice"></el-input>
</el-form-item>
<el-form-item label="三类渠道价"  prop="threeClassChannelPrice" v-if="!query.threeClassChannelPrice" >
    <el-input v-model="m.threeClassChannelPrice"></el-input>
</el-form-item>
<el-form-item label="四类人数"  prop="fourClass" v-if="!query.fourClass" >
    <el-input v-model="m.fourClass"></el-input>
</el-form-item>
<el-form-item label="四类渠道价"  prop="fourClassChannelPrice" v-if="!query.fourClassChannelPrice" >
    <el-input v-model="m.fourClassChannelPrice"></el-input>
</el-form-item>
<el-form-item label="四类售价"  prop="fourClassPrice" v-if="!query.fourClassPrice" >
    <el-input v-model="m.fourClassPrice"></el-input>
</el-form-item>
<el-form-item label="五类人数"  prop="fiveClass" v-if="!query.fiveClass" >
    <el-input v-model="m.fiveClass"></el-input>
</el-form-item>
<el-form-item label="五类渠道价"  prop="fiveClassChannelPrice" v-if="!query.fiveClassChannelPrice" >
    <el-input v-model="m.fiveClassChannelPrice"></el-input>
</el-form-item>
<el-form-item label="五类售价"  prop="fiveClassPrice" v-if="!query.fiveClassPrice" >
    <el-input v-model="m.fiveClassPrice"></el-input>
</el-form-item>
<el-form-item label="运营人"  prop="operation" v-if="!query.operation" >
    <el-input v-model="m.operation"></el-input>
</el-form-item>
<el-form-item label="返利总额"  prop="totalRebate" v-if="!query.totalRebate" >
    <el-input v-model="m.totalRebate"></el-input>
</el-form-item>
<el-form-item label="利润"  prop="profit" v-if="!query.profit" >
    <el-input v-model="m.profit"></el-input>
</el-form-item>
<el-form-item label="回本日期："  prop="backTime" v-if="!query.backTime">
   <el-date-picker v-model="m.backTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="回本日期"></el-date-picker>
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
const rules = {orderTime:[],
customerName:[],
channelName:[],
companyName:[],
scheme:[],
grade:[],
accidentRate:[],
threeClass:[],
threeClassPrice:[],
threeClassChannelPrice:[],
fourClass:[],
fourClassChannelPrice:[],
fourClassPrice:[],
fiveClass:[],
fiveClassChannelPrice:[],
fiveClassPrice:[],
operation:[],
totalRebate:[],
profit:[],
backTime:[],
}
const sa = inject('sa')
const ruleForm = ref();
const  query = ref({});

async function open(data, parmas)  {
    isShow.value = true;
    if (data) {
        title.value = "修改 业绩数据";
        let one = await sa.get("/order/find/"+data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata  = {orderTime:'',
customerName:'',
channelName:'',
companyName:'',
scheme:'',
grade:'',
accidentRate:'',
threeClass:0,
threeClassPrice:'',
threeClassChannelPrice:'',
fourClass:0,
fourClassChannelPrice:'',
fourClassPrice:'',
fiveClass:0,
fiveClassChannelPrice:'',
fiveClassPrice:'',
operation:'',
totalRebate:'',
profit:'',
backTime:''}
        query.value = parmas || {};
        m.value ={...mdata, ...parmas}
        title.value = "添加 业绩数据";
    }
}

//提交业绩数据信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            
            await sa.post("/order/save", m.value);
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