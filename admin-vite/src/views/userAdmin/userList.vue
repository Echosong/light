<template>
    <div class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="formInline.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.companyName" placeholder="请输入公司名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.parentname" placeholder="请输入上级账号" />
            </el-form-item>
            <el-form-item>
                <el-input v-model.number="formInline.mobile" placeholder="请输入手机号" />
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.usertype" placeholder="请选择用户类型">
                    <el-option label="个人" value="0" />
                    <el-option label="企业" value="1" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.userlevelid" placeholder="请选择用户级别">
                    <el-option v-for="(item, index) of levelList" :key="index" :label="item.levelname" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.status" placeholder="请选择状态">
                    <el-option v-for="(item, index) of g_statusmark['UserStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="修改开始时间" end-placeholder="修改结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="primary" icon="Download" @click="exportExcelFormServe">导出报表</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>

            </el-form-item>
        </el-form>

        <FunNavigation @addFrom="$router.push({ name: 'userAdd' })" @showFn="show = !show" @refreshFn="listFn"></FunNavigation>

        <el-table header-cell-class-name="tableBackground" :data="list" stripe style="width: 100%">
            <el-table-column prop="username" label="用户名" />
            <el-table-column prop="parentname" label="上级帐号" />
            <el-table-column prop="mobile" label="手机号" />
            <el-table-column prop="userTypeName" label="用户类型" />
            <el-table-column prop="levelName" label="用户级别" />
            <el-table-column prop="availablebalance" label="可用余额" />
            <el-table-column prop="companyName" label="公司名称" />
            <el-table-column width="120" prop="statusName" label="状态">
                <template #default="scope">
                    <el-tag>{{ scope.row.statusName }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column width="180" prop="addtime" label="添加时间">
                <template #default="scope">
                    {{ parseTime(new Date(scope.row.addtime).getTime(), '{y}-{m}-{d}') }}
                </template>
            </el-table-column>
            <el-table-column fixed="right" prop="address" label="操作">
                <template #default="scope">
                    <el-button v-if="permission('edit')" @click="$router.push({ name: 'userAdd', query: { id: scope.row.id } })"
                        link type="primary" size="small">编辑</el-button>
                    <!-- <el-button link type="primary" size="small">详情</el-button> -->
                    <el-button v-if="permission('withhold')"
                        @click="$router.push({ name: 'withhold', query: { id: scope.row.id } })" link type="primary"
                        size="small">代扣代充</el-button>
                    <el-button v-if="permission('productGroup')" @click="prodFn(scope.row.id,scope.row.parentname,scope.row.username)" link type="primary"
                        size="small">所属产品组</el-button>
                    <el-button @click="AllLocaFn(scope.row.id)" v-if="permission('allocation') && scope.row.usertype == 1"
                        link type="primary" size="small">角色</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pages">
            <el-pagination background v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
                layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>
    </div>
    <ProdGroupsPop ref="ProdGroupsPopRef"></ProdGroupsPop>
    <AddAllocation ref="AddAllocationRef"></AddAllocation>
</template>
<script>
export default {
    name: "userList"
}
</script>
<script setup>

import { backQueryUsersList, queryUserLevelList } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { statusmark } from '@/pinia/modules/statusmark';
import { parseTime } from '@/utils';
import { Search, Refresh } from '@element-plus/icons-vue';
import { onMounted, reactive, ref,computed } from 'vue'
import ProdGroupsPop from './components/prodGroupsPop.vue';
import AddAllocation from './components/addAllocation.vue';
import { permission } from '@/hooks/permission';
import { exportExcel } from '@/utils/excel';
const ProdGroupsPopRef = ref()
const AddAllocationRef = ref()
onMounted(() => {
    listFn()
})

function prodFn(id,parentname,username) {
    ProdGroupsPopRef.value.dialogVisible = true
    ProdGroupsPopRef.value.createUnrefFn(id,parentname || username)
}

function AllLocaFn(id) {
    AddAllocationRef.value.dialogVisible = true
    AddAllocationRef.value.createUnrefFn(id)
}

const g_statusmark = computed(() => statusmark().g_statusmark);

const date = ref('')
const id = ref(0)
const show = ref(true)
const formInline = reactive({
    companyName: "",
    pageSize: 10,
    pageNum: 1,
    username: '',
    parentname: '',
    mobile: '',
    usertype: '',
    userlevelid: '',
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


async function listFn() {
    const { data } = await backQueryUsersList({
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
    formInline.mobile = ''
    formInline.beginDate = ''
    formInline.endDate = ''
    date.value = ''
    formInline.userlevelid = ''
    formInline.usertype = ''
    formInline.username = ''
    formInline.status = ''
    formInline.parentname = ''
    formInline.companyName = ''
}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data


async function exportList() {
    const { data } = await backQueryUsersList({
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
            parentname: item.parentname,
            mobile: item.mobile,
            userTypeName: item.userTypeName,
            levelName: item.levelName,
            availablebalance: item.availablebalance,
            companyName: item.companyName,
            statusName: item.statusName,
            addtime: parseTime(new Date(item.addtime).getTime(), '{y}-{m}-{d}'),
        }
    })
    exportExcel(dataList, '用户列表', 'ExcelUserTitle', 'sheetName');
}
</script>

<style>.tableBackground {
    background: red;
}</style>
  