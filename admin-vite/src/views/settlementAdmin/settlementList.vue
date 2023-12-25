<template>
    <div class="app-container">
        <el-form v-show="show" label-width="96" :inline="true" :model="formInline" class="demo-form-inline">
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
                <el-input v-model="formInline.productName" placeholder="请输入产品名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.orderno" placeholder="请输入订单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.identityno" placeholder="请输入出险人证件号" />
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
                <el-select  clearable v-model="formInline.claimsstatus" placeholder="请选择订单状态">
                    <el-option v-for="(item, index) of g_statusmark['ClaimsStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="下单开始时间" end-placeholder="下单结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelFormServe">导出报表</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>
        <FunNavigation :isAdd="false" @addFrom="$router.push({ name: 'userAdd' })" @showFn="show = !show"
            @refreshFn="listFn"></FunNavigation>

        <el-table :data="list" stripe style="width: 100%">
            <el-table-column width="120" prop="username" label="用户名" />
            <el-table-column width="140" prop="polyno" label="保单号" />
            <el-table-column width="180" prop="companyName" label="保险公司" />
            <el-table-column width="160" prop="productName" label="产品名称" />
            <el-table-column width="160" prop="enterpriseNameHolder" label="投保企业" />
            <el-table-column width="160" prop="enterpriseName" label="出险企业" />
            <el-table-column width="100" prop="insuredname" label="出险人姓名" />
            <el-table-column width="160" prop="identityno" label="出险人证件号" />
            <el-table-column width="100" prop="polystartdate" label="起保日期" />
            <el-table-column width="100" prop="polyenddate" label="保险止期" />
            <el-table-column width="100" prop="accidenttime" label="出险时间" />
            <el-table-column width="100" prop="reportdate" label="提交时间" />
            <el-table-column width="80" prop="claimsmoney" label="理赔估损" />
            <el-table-column width="80" prop="claimspaymoney" label="理赔实付" />
            <el-table-column width="80" prop="claimsStatusName" label="理赔状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.claimsStatusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column width="60" fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button v-if="scope.row.claimsno"
                        @click="$router.push({ name: 'settlementDet', query: { claimsno: scope.row.claimsno, orderno: scope.row.orderno } })"
                        link type="primary" size="small">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class=" pages">
            <el-pagination v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
                layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
</template>
<script>
export default {
    name: "settlementList"
}
</script>
<script setup>

import { backQueryClaimsList, queryUserLevelList } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { statusmark } from '@/pinia/modules/statusmark';
import { parseTime } from '@/utils';

import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref,computed } from 'vue'
import { exportExcel } from '@/utils/excel';
const g_statusmark = computed(() => statusmark().g_statusmark);

onMounted(() => {
    listFn()
})

function prodFn(id) {
    ProdGroupsPopRef.value.dialogVisible = true
    ProdGroupsPopRef.value.createUnrefFn(id)
}

const date = ref('')
const show = ref(true)
const formInline = reactive({
    identityno:"",
    pageSize: 10,
    pageNum: 1,
    beginDate: '',
    endDate: '',
    username: "",
    enterpriseNameHolder: "",
    enterpriseName: "",
    productName: "",
    orderno: "",
    voucherno: "",
    outorderno: "",
    companyName: "",
    claimsstatus: "",
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


async function listFn() {
    const { data } = await backQueryClaimsList({
        ...formInline
    })
    list.value = data.dataList
    total.value = data.total
}
const changeFn = (val) => {

    if (val) {
        formInline.addBeginDate = parseTime(val[0].getTime(), '{y}-{m}-{d}')
        formInline.addEndDate = parseTime(val[1].getTime(), '{y}-{m}-{d}')
    } else {
        formInline.addBeginDate = ''
        formInline.addEndDate = ''
    }
}

const onSubmit = () => {
    formInline.pageNum = 1
    listFn()
}

const resetting = () => {
    
    formInline.identityno = ''
    formInline.beginDate = ''
    formInline.endDate = ''
    formInline.username = ''
    formInline.enterpriseNameHolder = ''
    formInline.enterpriseName = ''
    formInline.productName = ''
    formInline.orderno = ''
    formInline.voucherno = ''
    formInline.outorderno = ''
    formInline.companyName = ''
    formInline.claimsstatus = ''
    date.value = ''
}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data

async function exportList() {
    const { data } = await backQueryClaimsList({
        ...formInline,
        pageSize: 10000
    })
    return data.dataList
}
async function exportExcelFormServe() {
    let list = await exportList()

    let dataList = list.map(item => {
        return {
            username: item.username,
            parentName: item.parentName,
            polyno: item.polyno,
            companyName: item.companyName,
            productName: item.productName,
            productplan: item.productplan,//计划
            enterpriseNameHolder: item.enterpriseNameHolder,
            enterpriseName: item.enterpriseName,
            insuredname: item.insuredname,
            identityno: item.identityno,
            classname: item.classname,//工种名称
            grade: item.grade,//职业等级
            polystartdate: item.polystartdate,
            polyenddate: item.polyenddate,
            accidenttime: item.accidenttime,
            reportdate: item.reportdate,
            claimsmoney: item.claimsmoney,
            claimspaymoney: item.claimspaymoney,
            claimsStatusName: item.claimsStatusName,
        }
    })
    exportExcel(dataList, '理赔查询', 'ExcelSettlementTitle', 'sheetName');
}
</script>

<style></style>
  