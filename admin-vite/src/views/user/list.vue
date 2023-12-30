<template>
    <div class="app-container">
        <!-- 参数栏 -->
        <el-form :inline="true" size="small" class="demo-form-inline">
            <el-form-item label="账号：">
                <el-input v-model="p.username" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item label="姓名：">
                <el-input v-model="p.name" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item style="min-width: 0px">
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
                <el-button type="success" icon="Plus" plain @click="add">增加</el-button>
            </el-form-item>
        </el-form>
        <!-- <div class="c-title">数据列表</div> -->
        <el-table :data="dataList" header-cell-class-name="tableBackground" @sort-change="shortChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="账号" prop="username"></el-table-column>
            <el-table-column label="性别" sortable prop="sexEnum"></el-table-column>
            <el-table-column label="身高" prop="height"></el-table-column>
            <el-table-column label="体重" prop="weight"></el-table-column>
            <el-table-column label="昵称" prop="nick"></el-table-column>
            <el-table-column label="注册方式" prop="typeEnum"></el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="状态">
                <template #default="s">
                    <ElSwitch v-model="s.row.state" @change="updateSwitch(s.row)"></ElSwitch>
                </template>
            </el-table-column>

            <el-table-column label="邮箱" prop="email"></el-table-column>
            <el-table-column label="注册ip" prop="regIp"></el-table-column>
            <el-table-column label="登录ip" prop="loginIp"></el-table-column>
            <el-table-column prop="address" label="操作" width="120px" fixed="right">
                <template #default="s">
                    <el-button link type="primary" @click="update(s.row)">修改</el-button>
                    <el-button link type="danger" @click="del(s.row)">删除</el-button>
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
import ElSwitch from "@/components/ESwitch/ESwitch.vue";
import Preview from "@/components/file/preview.vue";

const p = ref({pageSize: 10, page: 1, total: 0, username: '', name: ''})
const dataList = ref([]);
const sa = inject('sa')
const addUpdate = ref()
onMounted(() => {
    f5()
})

// 数据刷新
async function f5() {
    const {data} = await sa.put("/user/listPage", p.value);
    dataList.value = data.content.map((item) => {
        return item;
    });
    p.value.total = data.totalElements;
}

// 删除
function del(data) {
    sa.confirm('是否删除，此操作不可撤销', async function () {
        let res = await sa.delete("/user/delete/" + data.id);
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
    await sa.post('/user/save', row);
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
