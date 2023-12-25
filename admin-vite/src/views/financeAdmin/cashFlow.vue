<template>
    <div class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.batchno" placeholder="请输入外部交易号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.serialno" placeholder="请输入流水号" />
            </el-form-item>

            <el-form-item>
                <el-select  clearable v-model="formInline.tradetypethird" placeholder="交易类型">
                    <el-option v-for="(item, index) of levelList" :key="index" :label="item.text" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.financialtype" placeholder="财务类型">
                    <el-option label="资金流入" value="1" />
                    <el-option label="资金流出" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="交易开始时间" end-placeholder="交易结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>

            <el-form-item>
                <el-select  clearable v-model="formInline.isTotal" placeholder="是否汇总">
                    <el-option label="否" value="1" />
                    <el-option label="是" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelFormServe">导出报表</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>

        <FunNavigation :isAdd="false" @addFrom="$router.push({ name: 'userAdd' })" @showFn="show = !show" @refreshFn="listFn">
        </FunNavigation>

        <el-table :header-cell-style="{ color: '#515a6e' }" :data="list" stripe style="width: 100%">
            <el-table-column prop="username" label="用户名" />
            <el-table-column width="200" prop="batchno" label="流水号" />
            <el-table-column prop="financialtypeName" label="财务类型" />
            <el-table-column width="120" prop="tradeName" label="交易类型" />
            <el-table-column width="80" prop="amout" label="交易金额" />
            <el-table-column prop="serialno" label="外部交易号" />
            <el-table-column prop="tradetime" label="交易时间" />
            <el-table-column prop="addtime" label="添加时间" />
            <el-table-column width="460" prop="remark" label="备注" />
        </el-table>
        <div class="pages">

            <el-pagination background v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
                layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
</template>
<script>
export default {
    name: "userList"
}
</script>
<script setup>

import { queryMatchTradeTypeList, capitalflowQueryMatchTradeTypeList } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { statusmark } from '@/pinia/modules/statusmark';
import { parseTime } from '@/utils';
import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref ,computed} from 'vue'
import { exportExcel } from '@/utils/excel'
const g_statusmark = computed(() => statusmark().g_statusmark);
onMounted(() => {
    listFn()
})


const date = ref('')
const show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    beginDate: '',
    endDate: '',
    username: '',
    tradetypethird: '',
    financialtype: '',
    serialno: '',
    batchno: '',
    isTotal: '',
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
    const { data } = await queryMatchTradeTypeList({
        ...formInline
    })
    list.value = data.dataList
    total.value = data.total
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
    formInline.financialtype = ''
    date.value = ''
    formInline.tradetypethird = ''
    formInline.username = ''
    formInline.isTotal = ''
}

const levelList = ref([])
let { data } = await capitalflowQueryMatchTradeTypeList({})
levelList.value = data


async function exportList() {
    const { data } = await queryMatchTradeTypeList({
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
            batchno: item.batchno,
            financialtypeName: item.financialtypeName,
            tradeName: item.tradeName,
            amout: item.amout,
            serialno: item.serialno,
            tradetime: item.tradetime,
            addtime: item.addtime,
        }
    })
    exportExcel(dataList, '资金流水', 'ExcelCashFlowTitle', 'sheetName');
}


</script>

<style></style>
  