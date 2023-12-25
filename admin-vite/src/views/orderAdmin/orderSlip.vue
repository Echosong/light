<template>
    <div class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.productName" placeholder="请输入产品名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.identityno" placeholder="请输入证件号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.companyName" placeholder="请输入保险公司" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.enterpriseNameHolder" placeholder="请输入投保人" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.enterpriseName" placeholder="请输入被保险人" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.polyno" placeholder="请输入保单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.orderno" placeholder="请输入订单号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.outorderno" placeholder="请输入外部订单号" />
            </el-form-item>
            <!-- <el-form-item>
                <el-input v-model="formInline.insuredname" placeholder="请输入雇员姓名" />
            </el-form-item> -->

            <el-form-item>
                <el-select clearable v-model="formInline.polystatus" placeholder="请选择保单状态">
                    <el-option v-for="(item, index) of g_statusmark['PolyStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="出保开始时间" end-placeholder="出保结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="change1Fn" start-placeholder="生效开始时间" end-placeholder="生效结束时间" v-model="date1"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="backeQueryPolyReportFn()">导出报表</el-button>

                <el-button type="warning" :icon="Download" @click="backeQueryPolyDetailFn()">导出明细</el-button>

                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>

        </el-form>
        <FunNavigation :isAdd="false" @addFrom="$router.push({ name: 'userAdd' })" @showFn="show = !show"
            @refreshFn="listFn"></FunNavigation>
        <el-table border :data="list" stripe style="width: 100%">
            <el-table-column width="80" prop="userName" label="用户名" />
            <el-table-column width="80" prop="parentName" label="上级用户" />
            <el-table-column width="120" prop="orderno" label="订单号" />
            <el-table-column width="120" prop="polyno" label="保单号" />
            <el-table-column width="120" prop="originalorderno" label="原订单号" />
            <el-table-column width="140" prop="companyName" label="保险公司" />
            <el-table-column width="140" prop="productName" label="产品名称" />
            <el-table-column width="100" prop="enterpriseNameHolder" label="投保人" />
            <el-table-column width="100" prop="enterpriseName" label="被保险人" />
            <el-table-column width="70" prop="insuredname" label="人员姓名" />
            <el-table-column width="90" prop="identityno" label="证件号" />
            <el-table-column width="70" prop="polyprice" label="保费" />
            <!-- <el-table-column width="80" prop="polypayprice" label="支付金额" /> -->
            <el-table-column width="100" prop="polyStatusName" label="保单状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.polyStatusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column width="90" prop="addtime" label="添加时间" />
            <el-table-column width="90" prop="dotime" label="出单时间" />
            <el-table-column width="90" prop="polystartdate" label="生效日" />
            <el-table-column width="90" prop="polyenddate" label="终止日" />
            <el-table-column width="90" prop="outorderno" label="外部订单号" />
            <!-- <el-table-column width="160" prop="telephone" label="手机号" /> -->
            <el-table-column width="60" fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button @click="$router.push({ name: 'orderDet', query: { orderno: scope.row.orderno } })" link
                        type="primary" size="small">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class=" pages">
            <el-pagination background v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
                layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
</template>
<script>
export default {
    name: "orderSlip"
}
</script>
<script setup>

import { backQueryPolyInfoList, queryUserLevelList, backeQueryPolyReport,queryPolyReport } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { statusmark } from '@/pinia/modules/statusmark';
import { parseTime, urlMontage } from '@/utils';
import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref, computed } from 'vue'
import {ElMessage} from "element-plus";
import { exportExcel } from '@/utils/excel'
onMounted(() => {
    listFn()
})
const g_statusmark = computed(() => statusmark().g_statusmark);

const date = ref([])
date.value = [new Date().getTime() - (30 * 24 * 60 * 60 * 1000), new Date().getTime()]
const date1 = ref([])
const show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    addBeginDate: new Date().getTime() - (30 * 24 * 60 * 60 * 1000),
    addEndDate: new Date().getTime(),
    username: '',
    productName: '',
    companyName: '',
    enterpriseNameHolder: '',
    enterpriseName: '',
    polyno: '',
    orderno: '',
    outorderno: '',
    insuredname: '',
    identityno: '',
    polystatus: '',
    polySDate: '',
    polyEDate: '',
})

formInline.addBeginDate = parseTime(date.value[0], '{y}-{m}-{d}')
formInline.addEndDate = parseTime(date.value[1], '{y}-{m}-{d}')

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
let isFlg = true;

async function backeQueryPolyDetailFn() {
    if(isFlg) {
        isFlg = false;
        const {data} = await queryPolyReport({
            ...formInline,
            pageSize: 100000
        })
        isFlg = true;
        window.open(data, '_blank');
    }else{
        ElMessage.info('请不要重复导出');
    }
}

async function backeQueryPolyReportFn() {
    if(isFlg) {
        isFlg = false;
        const {data} = await backeQueryPolyReport({
            ...formInline,
            pageSize: 100000
        })
        window.open(data, '_blank');
    }else{
        ElMessage.info('报表不要重复导出');
    }

}

async function listFn() {
    const { data } = await backQueryPolyInfoList({
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

const change1Fn = (val) => {

    if (val) {
        formInline.polySDate = parseTime(val[0].getTime(), '{y}-{m}-{d}')
        formInline.polyEDate = parseTime(val[1].getTime(), '{y}-{m}-{d}')
    } else {
        formInline.polySDate = ''
        formInline.polyEDate = ''
    }
}

const onSubmit = () => {
    formInline.pageNum = 1
    listFn()
}

const resetting = () => {
    Object.assign(formInline, {
        pageSize: 10,
        pageNum: 1,
        addBeginDate: '',
        addEndDate: '',
        username: '',
        productName: '',
        companyName: '',
        enterpriseNameHolder: '',
        enterpriseName: '',
        polyno: '',
        orderno: '',
        outorderno: '',
        insuredname: '',
        identityno: '',
        polystatus: '',
        polySDate: '',
        polyEDate: '',
    })
    date.value = [new Date().getTime() - (30 * 24 * 60 * 60 * 1000), new Date().getTime()]
    date1.value = ''
}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data

</script>

<style></style>
  