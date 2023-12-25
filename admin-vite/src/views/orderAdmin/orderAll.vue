<template>
    <div class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.enterpriseNameHolder" placeholder="请输入投保人" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.enterpriseName" placeholder="请输入被保险人" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.identityno" placeholder="请输入身份证号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.productName" placeholder="请输入产品名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.orderno" placeholder="请输入订单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.voucherno" placeholder="请输入保单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.outorderno" placeholder="请输入外部订单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.companyName" placeholder="请输入保险公司" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.originalorderno" placeholder="请输入原订单号" />
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.orderstatus" placeholder="请选择订单状态">
                    <el-option v-for="(item, index) of g_statusmark['OrderStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="下单开始时间" end-placeholder="下单结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelFormServe()">导出报表</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelPolyinvoice()">发票导出</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>
        <FunNavigation :isAdd="false" @addFrom="$router.push({ name: 'userAdd' })" @showFn="show = !show"
            @refreshFn="listFn"></FunNavigation>

        <el-table :data="list" stripe style="width: 100%">
            <el-table-column width="120" prop="username" label="用户名" />
            <el-table-column width="120" prop="parentName" label="上级用户" />
            <el-table-column width="180" prop="orderno" label="订单号" />
            <el-table-column width="200" prop="voucherno" label="保单/批单号" >
                <template #default="scope">
                   {{ scope.row.voucherno + (scope.row.sharePoliyNo ? "," + scope.row.sharePoliyNo:'') }}
                </template>
            </el-table-column>
            <el-table-column width="200" prop="originalorderno" label="原订单号" />
            <el-table-column width="180" prop="companyName" label="保险公司" />
            <el-table-column width="200" prop="productName" label="产品名称" />
            <el-table-column width="200" prop="enterpriseNameHolder" label="投保人" />
            <el-table-column width="200" prop="enterpriseName" label="被保险人" />
            <el-table-column width="80" prop="amount" label="总保费" />
            <!-- <el-table-column width="80" prop="payamount" label="支付金额" /> -->
            <el-table-column width="80" prop="polycount" label="人数" />
            <el-table-column width="100" prop="orderStatusName" label="订单状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.orderStatusName }}</el-tag>
                    <el-tag v-if="scope.row.shareStatus">{{ scope.row.shareStatus }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column width="160" prop="outorderno" label="外部订单号" />
            <el-table-column width="160" prop="addtime" label="下单时间" />
            <el-table-column width="80" fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button
                        v-if="(scope.row.extendinfo5.includes('邮件') || scope.row.underwritingdesc.includes('邮件发送成功')) && permission('callbacks')"
                        @click = "callbackFn(scope.row)"
                        link type="primary" size="small">强制回调</el-button>

                    <el-button @click="$router.push({ name: 'orderDet', query: { orderno: scope.row.orderno } })" link
                        type="primary" size="small">详情</el-button>

                    <el-button v-if="scope.row.shareSend" @click="shareSend(scope.row)" link type="primary" size="small">
                        {{ scope.row.shareSend }}</el-button>

                    <el-button v-if="scope.row.shareCancel" @click="cancel(scope.row)" link type="primary" size="small">
                        {{ scope.row.shareCancel }}</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class=" pages">
            <el-pagination background v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
                layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
    <Callback @onSuccess="onSuccess" ref="callbackRef" ></Callback>
</template>
<script>
export default {
    name: "orderAll"
}
</script>
<script setup>

import {
    backQueryOrdersList,
    queryUserLevelList,
    exportPolyinvoice,
    shareCorrect,
    shareCancel,
    shareInsureNo
} from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { statusmark } from '@/pinia/modules/statusmark';
import { parseTime, urlMontage } from '@/utils';
import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref,computed } from 'vue'
import { exportExcel } from '@/utils/excel'
import { permission } from '@/hooks/permission';
import Callback from './components/callback.vue';
import {ElMessage} from "element-plus";
const callbackRef = ref()
onMounted(() => {
    listFn()
})

// function prodFn(id) {
//     ProdGroupsPopRef.value.dialogVisible = true
//     ProdGroupsPopRef.value.createUnrefFn(id)
// }

const g_statusmark = computed(() => statusmark().g_statusmark);

const date = ref([new Date().getTime() - (30 * 24 * 60 * 60 * 1000),new
 Date().getTime()])
const show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    beginDate: parseTime(new Date().getTime() - (30 * 24 * 60 * 60 * 1000), '{y}-{m}-{d}'),
    endDate: parseTime(new Date().getTime(), '{y}-{m}-{d}'),
    username: "",
    enterpriseNameHolder: "",
    enterpriseName: "",
    productName: "",
    originalorderno:"",
    orderno: "",
    voucherno: "",
    outorderno: "",
    companyName: "",
    orderstatus: "",
    insuredname: "",
    identityno: ""
})

const list = ref([])
const total = ref(0)
const handleSizeChange = (val) => {
    formInline.pageSize = val
    listFn()
}

const handleCurrentChange = (val) => {
    formInline.pageNum = val
    listFn()
}

function onSuccess(){
    listFn()
}

async function listFn() {
    const { data } = await backQueryOrdersList({
        ...formInline
    })
    let dataList = data.dataList;
    dataList.forEach(item => {

        if(item.extendinfo5 && item.extendinfo5.length>5){
            try {
                let jsonObj = JSON.parse(item.extendinfo5);
                item.sharePoliyNo = jsonObj.policyNo;
                item.shareStatus = jsonObj.status;
            }catch (e) {}
        }
        //做一个是否要共保的判断
        if(item.productName.includes('太平') ){
            if(parseInt(item.orderstatus) === 3 && !item.sharePoliyNo){
                item.shareSend = '共保同步'
            }
            if(parseInt(item.orderstatus) === 5 && (!item.shareStatus|| !item.shareStatus.includes('退保'))){
                item.shareCancel = '共保退保'
            }
        }
      //  item.orderStatusName = g_statusmark.value[item.orderStatus]
    })
    console.log(555555555555, dataList)
    list.value = dataList;

    total.value = data.total
}

function callbackFn(item){
    callbackRef.value.dialogVisible = true
    callbackRef.value.addFrom(item)

}

async function shareSend(item){
    let  ret;
    if(item.originalorderno){
        //调用批改 shareCorrect api.js下方法
        ret  = await shareCorrect({orderno:item.orderno});
    }else{
        ret = await shareInsureNo({orderno:item.orderno});
    }
    ElMessage.success(ret.data || ret.msg);
    listFn();
}

async function cancel(item){
    let ret = await shareCancel({orderno:item.orderno});
    ElMessage.success(ret.data || ret.msg);
    listFn();
}

async function exportList() {
    const { data } = await backQueryOrdersList({
        ...formInline,
        pageSize: 10000
    })
    return data.dataList
}
const changeFn = (val) => {

    if (val) {
        formInline.beginDate = parseTime(val[0].getTime(), '{y}-{m}-{d}')
        formInline.endDate = parseTime(val[1].getTime(), '{y}-{m}-{d}')
    } else {
        formInline.beginDate = ''
        formInline.endDate = ''
    }
}

const onSubmit = () => {
    formInline.pageNum = 1
    listFn()
}

const resetting = () => {
    formInline.beginDate = ''
    formInline.endDate = ''
    formInline.username = ''
    formInline.enterpriseNameHolder = ''
    formInline.enterpriseName = ''
    formInline.productName = ''
    formInline.originalorderno = ''
    formInline.orderno = ''
    formInline.voucherno = ''
    formInline.outorderno = ''
    formInline.companyName = ''
    formInline.orderstatus = ''
    formInline.insuredname = ''
    formInline.identityno = ''
    date.value = ''
}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data

async function exportExcelFormServe() {
    let list = await exportList()
    let dataList = list.map(item => {
        return {
            username: item.username,
            parentName: item.parentName,
            orderno: item.orderno,
            voucherno: item.voucherno,
            originalorderno: item.originalorderno,
            companyName: item.companyName,
            productName: item.productName,
            enterpriseNameHolder: item.enterpriseNameHolder,
            enterpriseName: item.enterpriseName,
            amount: item.amount,
            payamount: item.payamount,
            polycount: item.polycount,
            orderStatusName: item.orderStatusName,
            addtime: item.addtime,
            orderstartdate: item.orderstartdate,
            orderenddate: item.orderenddate,
            outorderno: item.outorderno
        }
    })
    exportExcel(dataList, '订单数据', 'ExcelOrderTitle', 'sheetName');
}
async function exportExcelPolyinvoice() {
    let { data } = await exportPolyinvoice({
        startTime:formInline.beginDate,
        endTime:formInline.endDate
    })
    window.open(urlMontage(data))

}
</script>

<style></style>
  