<template>
    <div class="app-container">
        <!-- 参数栏 -->
        <el-form :inline="true" size="small" class="demo-form-inline">
            <el-form-item>
                <el-input v-model="p.username"  placeholder="账号" />
            </el-form-item>
            <el-form-item style="min-width: 0px">
                <el-button type="success" icon="Plus" @click="add">增加</el-button>
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
            </el-form-item>
        </el-form>
        <!-- <div class="c-title">数据列表</div> -->
        <el-table header-cell-class-name="tableBackground" :data="dataList"  @sort-change="shortChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="账号" prop="username"></el-table-column>
            <el-table-column label="性别" prop="sex"></el-table-column>
            <el-table-column label="身高" prop="height"></el-table-column>
            <el-table-column label="体重" prop="weight"></el-table-column>
            <el-table-column label="昵称" prop="nick"></el-table-column>
            <el-table-column label="注册方式" prop="typeEnum"></el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="状态" prop="stateEnum"></el-table-column>
            <el-table-column label="邮箱" prop="email"></el-table-column>
            <el-table-column label="注册ip" prop="regIp"></el-table-column>
            <el-table-column label="登录ip" prop="loginIp"></el-table-column>
            <el-table-column prop="address" label="操作" width="220px">
                <template #default="s">
                    <el-button class="c-btn" link type="primary" icon="el-icon-edit" @click="update(s.row)">修改
                    </el-button>
                    <el-button class="c-btn" link type="danger" icon="el-icon-delete" @click="del(s.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="pages">
            <el-pagination
                background
                layout="total, prev, pager, next, sizes, jumper"
                v-model:current-page="p.page"
                v-model:page-size="p.pageSize"
                :total="dataCount"
                :page-sizes="[1, 10, 20, 30, 40, 50, 100]"
                @current-change="f5(true)"
                @size-change="f5(true)">
            </el-pagination>
        </div>
    </div>
    <!-- 增改组件 -->
    <add-or-update ref="addUpdate"></add-or-update>
</template>

<script setup>
import addOrUpdate from './add.vue';
import {inject, ref, onMounted} from "vue";

const p = ref({pageSize: 10, page: 1, username:''});
const dataCount = ref(0);
const dataList = ref([]);
const sa = inject('sa')
const addUpdate = ref()
onMounted(() => {
    f5()
})

// 数据刷新
function f5() {
    sa.put("/user/listPage", p.value).then(res => {
        dataList.value = res.data.content.map((item) => {
            return item;
        });
        dataCount.value = res.data.totalElements;
    });
}

// 删除
function del(data) {
    sa.confirm('是否删除，此操作不可撤销', function () {
        sa.delete("/user/delete/" + data.id).then(res => {
            console.log(res)
            sa.arrayDelete(dataList.value, data);
            sa.ok(res.message);
        });
    }.bind(this));
}

function shortChange(e) {
    console.log('排序接受', e)
    p.value.direction = e.order === 'ascending';
    p.value.sortCol = e.prop;
    f5();
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
