<template>
    <Dialog v-model="isShow" :title="title" maxHeight="480px" style="width: 950px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px" style="width: 920px;" :inline="true">
            <el-form-item label="订单日期" prop="orderTime" v-if="!query.orderTime">
                <el-date-picker style="width: 300px" v-model="m.orderTime" type="date"
                                value-format="YYYY-MM-DD HH:mm:ss" placeholder="订单日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="业绩归属" prop="owner" v-if="!query.owner">
                <el-date-picker style="width: 300px" v-model="m.owner" type="date" value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="业绩归属"></el-date-picker>
            </el-form-item>
            <el-form-item label="客户名称" prop="customerName" v-if="!query.customerName">
                <el-input v-model="m.customerName" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="渠道名称" prop="channelName" v-if="!query.channelName">
                <el-select v-model="m.channelName" filterable @change="selectChannel" style="width: 300px">
                    <el-option v-for="item in channelNames" :key="item.channelName" :value="item.channelName"
                               :label="item.channelName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="保险公司" prop="companyName" v-if="!query.companyName">
                <el-select v-model="m.companyName" filterable @change="selectCompany" style="width: 300px">
                    <el-option v-for="item in companyNames" :key="item.companyName" :value="item.companyName"
                               :label="item.companyName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="方案" prop="scheme" v-if="!query.scheme">
                <el-select v-model="m.scheme" @change="selectScheme" filterable style="width: 300px">
                    <el-option v-for="item in schemes" :key="item" :value="item" :label="item">
                    </el-option>
                </el-select>
            </el-form-item>
            <div style="margin-bottom: 10px; margin-left: 50px; margin-right: 50px;">
                <el-descriptions direction="vertical" v-if="channel.id" :column="4" border>
                    <el-descriptions-item label="伤残比例">{{ channel.accidentRate }}%</el-descriptions-item>
                    <el-descriptions-item label="三类渠道价">{{ channel.threeClassChannelPrice }}</el-descriptions-item>
                    <el-descriptions-item label="四类渠道价">{{ channel.fourClassChannelPrice }}</el-descriptions-item>
                    <el-descriptions-item label="五类渠道价">{{ channel.fiveClassChannelPrice }}</el-descriptions-item>
                </el-descriptions>
            </div>

            <el-form-item label="三类人数" prop="threeClass" v-if="!query.threeClass">
                <el-input v-model="m.threeClass" style="width: 300px;" placeholder="三类人数"></el-input>
            </el-form-item>
            <el-form-item label="三类售价" prop="threeClass" v-if="!query.threeClass">
                <el-input v-model="m.threeClassPrice" placeholder="三类售价" style="width: 300px"></el-input>
            </el-form-item>
            <el-form-item label="四类人数" prop="fourClass" v-if="!query.fourClass">
                <el-input v-model="m.fourClass" style="width: 300px;" placeholder="四类人数"></el-input>
            </el-form-item>

            <el-form-item label="四类售价" prop="fourClass" v-if="!query.fourClass">

                <el-input v-model="m.fourClassPrice" placeholder="四类售价" style="width: 300px"></el-input>
            </el-form-item>

            <el-form-item label="五类人数" prop="fiveClass" v-if="!query.fiveClass">
                <el-input v-model="m.fiveClass" style="width: 300px;" placeholder="五类人数"></el-input>
            </el-form-item>

            <el-form-item label="五类售价" prop="fiveClass" v-if="!query.fiveClass">
                <el-input v-model="m.fiveClassPrice" placeholder="五类售价" style=" width: 300px"></el-input>
            </el-form-item>

            <el-form-item label="回本日期" prop="backTime" v-if="!query.backTime">
                <el-date-picker v-model="m.backTime" type="date" value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="回本日期" style="width: 300px"></el-date-picker>
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
import selectData from '@/components/SelectData/index.vue'

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    orderTime: [],
    owner: [],
    customerName: [],
    channelName: [],
    companyName: [],
    scheme: [],
    grade: [],
    accidentRate: [],
    threeClass: [],
    threeClassPrice: [],
    threeClassChannelPrice: [],
    fourClass: [],
    fourClassChannelPrice: [],
    fourClassPrice: [],
    fiveClass: [],
    fiveClassChannelPrice: [],
    fiveClassPrice: [],
    operation: [],
    totalRebate: [],
    profit: [],
    backTime: [],
}
const sa = inject('sa')
const ruleForm = ref();
const query = ref({});
const channel = ref({})

async function open(data, parmas) {
    isShow.value = true;
    if (data) {
        title.value = "修改业绩数据";
        let one = await sa.get("/order/find/" + data.id);
        m.value = one.data;
        let channelData = await sa.get("/channel/find/" + one.data.channelId);
        channel.value = channelData.data;

    } else {
        let mdata = {
            orderTime: '',
            owner: '',
            customerName: '',
            channelName: '',
            companyName: '',
            scheme: '',
            grade: '',
            accidentRate: '',
            threeClass: '',
            threeClassPrice: '',
            threeClassChannelPrice: '',
            fourClass: '',
            fourClassChannelPrice: '',
            fourClassPrice: '',
            fiveClass: '',
            fiveClassChannelPrice: '',
            fiveClassPrice: '',
            operation: '',
            totalRebate: '',
            profit: '',
            backTime: ''
        }
        query.value = parmas || {};
        m.value = {...mdata, ...parmas}
        title.value = "添加业绩数据";
    }
    channelData();
}

const channelNames = ref([])
const companyNames = ref([]);
const schemes = ref([]);

async function channelData() {
    let {data} = await sa.get("/channel/listChannelName");
    channelNames.value = data;
}

async function selectScheme() {
    let {data} = await sa.post("/channel/getChannel",
        {
            channelName: m.value.channelName,
            companyName: m.value.companyName,
            scheme: m.value.scheme
        }
    );
    m.value.channelId = data.id;
    m.value.accidentRate = data.accidentRate;
    channel.value = data;
}

function selectChannel() {
    console.log("选择渠道")
    let model = m.value;
    //循环 channelNames 过滤等于model.channelName的数据
    channelNames.value.forEach(item => {
        if (item.channelName === model.channelName) {
            companyNames.value = item.companyNames;
        }
    })
    console.log("公司信息", channelNames, companyNames, model.channelName, model.companyName)
}

function selectCompany() {
    let model = m.value;
    //循环 companyNames 过滤等于model.companyName的数据
    companyNames.value.filter(item => {
        if (item.companyName === model.companyName) {
            schemes.value = item.schemes;
        }
    })
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