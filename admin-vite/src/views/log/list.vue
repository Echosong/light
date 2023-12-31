<template>
    <div class="app-container">
        <!-- 参数栏 -->
        <el-form :inline="true" v-show="showSearch" class="demo-form-inline">
            <el-form-item label="用户">
                <el-input v-model="p.username" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item label="描述">
                <el-input v-model="p.description" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item label="日志类型">
                <el-input v-model="p.logType" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item style="min-width: 0px">
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
                <el-button icon="Refresh" plain @click="reset">重置</el-button>
            </el-form-item>
        </el-form>


        <FunNavigation @f5="f5" @showFn="showSearch = !showSearch">
            <el-button type="success" icon="Plus" plain @click="add">增加</el-button>
            <el-button type="warning" icon="Download" plain @click="exportFile">导出</el-button>
        </FunNavigation>

        <!-- <div class="c-title">数据列表</div> -->
        <el-table :data="dataList" v-loading="loading" @sort-change="shortChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="用户" prop="username"></el-table-column>
            <el-table-column label="请求ip" prop="requestIp"></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
            <el-table-column label="描述" prop="description"></el-table-column>
            <el-table-column label="浏览器" :show-overflow-tooltip="true" prop="browser"></el-table-column>
            <el-table-column label="请求耗时" prop="time"></el-table-column>
            <el-table-column label="方法名" prop="method"></el-table-column>
            <el-table-column label="参数" :show-overflow-tooltip="true" prop="params"></el-table-column>
            <el-table-column label="日志类型" prop="logType"></el-table-column>
            <el-table-column prop="address" label="操作" width="120px" fixed="right">
                <template #default="s">
                    <!--注意这里  v-permission="" 表示 任意权限，如果需要控制权限补充里面内容，比如 user-delete 然后权限表里面加相关权限，并且用户角色设置有关联权限-->
                    <el-button link v-permission="" type="primary" @click="update(s.row)">修改</el-button>
                    <el-button link v-permission="" type="danger" @click="del(s.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <Pagination :p="p" @f5="f5"></Pagination>
    </div>
    <!-- 增改组件 -->
    <add-or-update ref="addUpdate"></add-or-update>
</template>

<script setup>
import addOrUpdate from './add.vue';
import {inject, ref, onMounted} from "vue";
import Pagination from "@/components/file/Pagination.vue";
import FunNavigation from "@/components/funNavigation/funNavigation.vue";

const params = {pageSize: 10, page: 1, total: 0, username: '', description: '', logType: ''}
const p = ref(JSON.parse(JSON.stringify(params)))
const dataList = ref([]);
const sa = inject('sa')
const addUpdate = ref()
const loading = ref(false)
const showSearch = ref(true)
onMounted(() => {
    f5()
})

// 数据刷新
async function f5() {
    loading.value = true
    const {data} = await sa.put("/log/listPage", p.value);
    loading.value = false
    dataList.value = data.content.map((item) => {
        return item;
    });
    p.value.total = data.totalElements;
}

function exportFile() {
    sa.download("/log/export", p.value)
}

function reset() {
    p.value = JSON.parse(JSON.stringify(params))
}

// 删除
function del(data) {
    sa.confirm('是否删除，此操作不可撤销', async function () {
        let res = await sa.delete("/log/delete/" + data.id);
        console.log(res)
        sa.arrayDelete(dataList.value, data);
        sa.ok(res.message);
    }.bind(this));
}

function shortChange(e) {
    console.log('排序接受', e)
    p.value.direction = e.order === 'ascending';
    p.value.sortCol = e.prop;
    f5();
}

async function updateSwitch(row) {
    await sa.post('/log//save', row);
    sa.ok("更新成功", true)
}

//更新
function update(row) {
    console.log(addUpdate.value)
    addUpdate.value.open(row);
}

//添加
function add() {
    addUpdate.value.open(0);
}

defineExpose({
    f5
})
</script>

<style>
</style>
