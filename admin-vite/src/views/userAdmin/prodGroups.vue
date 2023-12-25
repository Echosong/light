<template>
    <div  class="app-container">
        <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item >
                <el-input v-model="formInline.groupname" placeholder="请输入产品组名称" />
            </el-form-item>
            <el-form-item>
                <el-input v-model="formInline.username" placeholder="请输入创建人" />
            </el-form-item>
            <el-form-item>
                <el-select  clearable v-model="formInline.userlevelid" placeholder="请选择所属用户级别">
                    <el-option v-for="(item,index) of levelList" :key="index" :label="item.levelname" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-date-picker @change="changeFn" start-placeholder="修改开始时间" end-placeholder="修改结束时间" v-model="date"
                    type="daterange" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">搜索</el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">重置</el-button>
            </el-form-item>
        </el-form>
    
        <FunNavigation @addFrom="$router.push({name:'prodGroupsAdd'})" @showFn="show = !show" @refreshFn="listFn"></FunNavigation>
        <el-table :data="list" stripe style="width: 100%">
            <el-table-column prop="groupname" label="产品组名称" />
            <el-table-column prop="levelName" label="所属用户级别" />
            <el-table-column prop="username" label="创建人" />
            <el-table-column width="180" prop="updatetime" label="修改时间">
        <template #default="scope">
          {{ parseTime(new Date(scope.row.updatetime).getTime(), '{y}-{m}-{d}') }}
        </template>
      </el-table-column>
            <el-table-column  width="80" prop="displayorder" label="排序" />
            <el-table-column width="80" fixed="right" prop="address" label="操作">
                <template  #default="scope">
                    <el-button v-if="permission('edit')" @click="$router.push({name:'prodGroupsAdd',query:{productgroupid:scope.row.productgroupid}})" link type="primary" size="small">编辑</el-button>
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
  name: "userList"
}
</script>
<script setup>

import { backQueryAllList,queryUserLevelList,} from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { parseTime } from '@/utils';
import { Search, Refresh } from '@element-plus/icons-vue';
import { onMounted, reactive, ref } from 'vue'
import { permission } from '@/hooks/permission';
onMounted(() => {
    listFn()
})

const  date = ref('')
const  show = ref(true)
const formInline = reactive({
    pageSize: 10,
    pageNum: 1,
    groupname:'',
    userlevelid:'',
    username:'',
    beginDate:'',
    endDate:''
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
    const { data } = await backQueryAllList({
        ...formInline
    })
    list.value = data.dataList
    total.value = data.total
}
const changeFn = (val)=>{
    if(val){
        formInline.beginDate = parseTime(val[0].getTime(),'{y}-{m}-{d}')
    formInline.endDate = parseTime(val[1].getTime(),'{y}-{m}-{d}')
    }else{
        formInline.beginDate = ''
    formInline.endDate = ''
    }
    
}

const onSubmit = () => {
    formInline.pageNum = 1
    listFn()
}

const resetting = ()=>{
    formInline.groupname = '' 
    formInline.username = '' 
    formInline.beginDate = ''
    formInline.endDate = ''
    date.value = ''
    formInline.userlevelid = ''
}

const levelList = ref([])
let {data} = await queryUserLevelList({})
levelList.value = data

</script>

<style></style>
  