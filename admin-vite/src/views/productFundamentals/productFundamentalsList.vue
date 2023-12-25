<template>
    <div class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.clausename" placeholder="请输入条款名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.insCompanyName" placeholder="请输入保险公司" />
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.instypeid" placeholder="请选择险种">
                    <el-option v-for="(item, index) of instypeList" :key="index" :label="item.instypename"
                        :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.clausetypeid" placeholder="请选择条款类型">
                    <el-option v-for="(item, index) of clausetypeLists" :key="index" :label="item.typename"
                        :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.benefitclassid" placeholder="请选择保障利益分类">
                    <el-option v-for="(item, index) of queryAllList" :key="index" :label="item.benefitclassname"
                        :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.status" placeholder="请选择状态">
                    <el-option label="启用" value="1" />
                    <el-option label="禁用" value="0" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="开始创建时间" end-placeholder="结束创建时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" :icon="Download" @click="exportExcelFormServe">导出报表</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>
        <FunNavigation @addFrom="$router.push({ name: 'productFundamentalsAdd' })" @showFn="show = !show"
            @refreshFn="listFn">
        </FunNavigation>

        <el-table :data="list" stripe style="width: 100%">
            <el-table-column prop="insTypeName" label="险种" />
            <el-table-column width="220" prop="clausename" label="条款名称" />
            <el-table-column prop="clauseTypeName" label="条款类型" />
            <el-table-column prop="clauseversion" label="条款版本" />
            <el-table-column width="200" prop="benefitClassName" label="保障利益分类" />
            <el-table-column width="140" prop="benefitno" label="内部编号" />
            <el-table-column prop="recordno" label="备案号" />
            <el-table-column prop="displayorder" label="排序" />
            <el-table-column width="80" prop="statusName" label="状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.statusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column width="100" prop="updatetime" label="修改时间">
                <template #default="scope">
                    {{ parseTime(new Date(scope.row.updatetime).getTime(), '{y}-{m}-{d}') }}
                </template>
            </el-table-column>
            <el-table-column fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button v-if="permission('edit')"
                        @click="$router.push({ name: 'productFundamentalsAdd', query: { id: scope.row.id } })" link
                        type="primary" size="small">编辑</el-button>
                        <el-popconfirm @confirm="delFn(scope.row.id)" title="您确定要删除这条数据吗？">
                        <template #reference>
                            <el-button v-if="permission('delete')" link type="primary" size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
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
    name: "productFundamentals"
}
</script>
<script setup>
import { permission } from '@/hooks/permission';
import { backQueryBenefitsList,benefitsDelete, benefitclassQueryAllList, instypeQueryAllList, clausetypeQueryAllList } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { parseTime } from '@/utils';
import { Search, Refresh, Download } from '@element-plus/icons-vue';
import { onMounted, reactive, ref } from 'vue'
import { exportExcel } from '@/utils/excel';
import { ElMessage } from 'element-plus';

onMounted(() => {
    listFn()
})


const date = ref('')
const show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    instypeid: '',
    clausename: '',
    insCompanyName: "",
    clausetypeid: '',
    benefitclassid: '',
    status: '',
    beginDate: '',
    endDate: '',

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
async function delFn(id) {
    let { data } = await benefitsDelete({
        id
    })
     ElMessage.success('删除成功！')
    listFn()
}

async function listFn() {
    const { data } = await backQueryBenefitsList({
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
    formInline.instypeid = ''
    formInline.clausename = ''
    formInline.clausetypeid = ''
    formInline.benefitclassid = ''
    formInline.status = ''
    formInline.beginDate = ''
    formInline.insCompanyName = ''
    formInline.endDate = ''
    date.value = ''
}


const queryAllList = ref([])
let { data: AllList } = await benefitclassQueryAllList({})
queryAllList.value = AllList


const instypeList = ref([])
let { data: insTypeLists } = await instypeQueryAllList({})
instypeList.value = insTypeLists

const clausetypeList = ref([])
let { data: clausetypeLists } = await clausetypeQueryAllList({})
clausetypeList.value = clausetypeLists


async function exportList() {
    const { data } = await backQueryBenefitsList({
        ...formInline,
        pageSize: 10000
    })
    return data.dataList
}
async function exportExcelFormServe() {
    let list = await exportList()
    let dataList = list.map(item => {
        return {
            insTypeName: item.insTypeName,
            companyName: item.companyName,
            clausename: item.clausename,
            clauseTypeName: item.clauseTypeName,
            clauseversion: item.clauseversion,
            benefitClassName: item.benefitClassName,
            benefitdetailname: item.detailsList.map(items => items.benefitdetailname).join(","),
            dutyno: item.detailsList.map(items => items.dutyno).join(","),
            benefitdescription: item.detailsList.map(items => items.benefitdescription).join(","),
            benefitno: item.benefitno,
            recordno: item.recordno,
            displayorder: item.displayorder,
            statusName: item.statusName,
            updatetime: parseTime(new Date(item.updatetime).getTime(), '{y}-{m}-{d}'),
        }
    })
    exportExcel(dataList, '保障利益管理', 'ExcelFundamentalsTitle', 'sheetName');
}


</script>

<style></style>
  