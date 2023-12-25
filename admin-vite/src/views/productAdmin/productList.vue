<template>
    <div class="app-container">
        <el-form v-show="show" label-width="140" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.companyname" placeholder="请输入保险公司" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.productname" placeholder="请输入产品全称" />
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.instypeid" placeholder="请选择归属险种">
                    <el-option v-for="(item, index) of instypeList" :key="index" :label="item.instypename"
                        :value="item.id" />
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-select  clearable v-model="formInline.producttypeid" placeholder="请选择产品分类">
                    <el-option v-for="(item, index) of queryAllList" :key="index" :label="item.producttypename"
                        :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.status" placeholder="请选择状态">
                    <el-option v-for="(item, index) of g_statusmark['ProductStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="修改开始时间" end-placeholder="修改结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelFormServe">导出报表</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>

        <FunNavigation @addFrom="$router.push({ name: 'productAdd' })" @showFn="show = !show" @refreshFn="listFn">
        </FunNavigation>

        <el-table :data="list" stripe style="width: 100%">
            <el-table-column width="160" prop="companyName" label="保险公司" />
            <el-table-column width="90" prop="insTypeName" label="归属险种" />
            <el-table-column width="120" prop="productTypeName" label="产品分类" />
            <el-table-column width="80" prop="insperiod" label="保险期限" />
            <el-table-column width="180" prop="productcode" label="产品代码" />
            <el-table-column prop="productname" label="产品全称" />
            <el-table-column width="120" prop="productversion" label="产品版本号" />
            <el-table-column width="90" prop="suitablecrowd" label="适合人群" />
            <el-table-column width="80" prop="productStatusName" label="状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.productStatusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column width="100" prop="updatetime" label="修改时间">
                <template #default="scope">
                    {{ parseTime(new Date(scope.row.updatetime).getTime(), '{y}-{m}-{d}') }}
                </template>
            </el-table-column>
            <el-table-column width="100" fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button v-if="permission('edit')"
                        @click="$router.push({ name: 'productAdd', query: { id: scope.row.id } })" link type="primary"
                        size="small">编辑</el-button>
                    <el-button v-if="permission('add')" @click="copyFn(scope.row.id)" link type="primary"
                        size="small">拷贝</el-button>
                    <el-popconfirm @confirm="delFn(scope.row.id)" title="您确定要删除这条拷贝数据吗？">
                        <template #reference>
                            <el-button v-if="scope.row.sourceId" link type="primary" size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
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
    name: "productFundamentals"
}
</script>
<script setup>

import { backQueryProductsList, productDelete, productCopy, queryProducttypeList, instypeQueryAllList, } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { parseTime } from '@/utils';
import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref,computed } from 'vue'
import { statusmark } from '@/pinia/modules/statusmark';

import { permission } from '@/hooks/permission';
import { exportExcel } from '@/utils/excel';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
const router = useRouter()
onMounted(() => {
    listFn()
})

const g_statusmark = computed(() => statusmark().g_statusmark);

const date = ref('')
const show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    companyname: "",
    instypeid: "",
    producttypeid: "",
    insproductcode: "",
    productcode: "",
    productname: "",
    productshortname: "",
    productversion: "",
    status: "",
    beginDate: "",
    endDate: "",

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
    const { data } = await backQueryProductsList({
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
    Object.assign(formInline, {
        pageSize: 10,
        pageNum: 1,
        companyname: "",
        instypeid: "",
        producttypeid: "",
        insproductcode: "",
        productcode: "",
        productname: "",
        productshortname: "",
        productversion: "",
        status: "",
        beginDate: "",
        endDate: "",
    })
    date.value = ''
}

async function copyFn(productId) {
    let { data } = await productCopy({
        productId
    })
    ElMessage.success('拷贝成功！')
    listFn()
    // router.push({ name: 'productAdd', query: { id: data.id } })
}

const queryAllList = ref([])
let { data: AllList } = await queryProducttypeList({})
queryAllList.value = AllList


const instypeList = ref([])
let { data: insTypeLists } = await instypeQueryAllList({})
instypeList.value = insTypeLists

async function exportList() {
    const { data } = await backQueryProductsList({
        ...formInline,
        pageSize: 10000
    })
    return data.dataList
}

async function delFn(productId) {
    let { data } = await productDelete({
        productId
    })
    ElMessage.success('删除成功！')
    listFn()
}
async function exportExcelFormServe() {
    let list = await exportList()

    let dataList = list.map(item => {
        return {
            companyName: item.companyName,
            insTypeName: item.insTypeName,
            productTypeName: item.productTypeName,
            insproductcode: item.insproductcode,
            insperiod: item.insperiod,
            productcode: item.productcode,
            productname: item.productname,
            productshortname: item.productshortname,
            productversion: item.productversion,
            suitablecrowd: item.suitablecrowd,
            productStatusName: item.productStatusName,
            updatetime: parseTime(new Date(item.updatetime).getTime(), '{y}-{m}-{d}'),
        }
    })
    exportExcel(dataList, '保险产品', 'ExcelProductTitle', 'sheetName');
}
</script>

<style></style>
  