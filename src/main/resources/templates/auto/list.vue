<template>
    <div class="app-container">
        <!-- 参数栏 -->
        <el-form :inline="true" size="small" class="demo-form-inline">
            #{el-form-item}#
            <el-form-item style="min-width: 0px">
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
                <el-button type="success" icon="Plus" @click="add">增加</el-button>
            </el-form-item>
        </el-form>
        <!-- <div class="c-title">数据列表</div> -->
        <el-table :data="dataList" header-cell-class-name="tableBackground" @sort-change="shortChange">
            #{el-table-column}#
            <el-table-column prop="address" label="操作" width="220px">
                <template #default="s">
                    <el-button link class="c-btn" type="primary" icon="el-icon-edit" @click="update(s.row)">修改
                    </el-button>
                    <el-button link class="c-btn" type="danger" icon="el-icon-delete" @click="del(s.row)">删除
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
//importFiles

const p = ref(#{queryPageParams}#)
const dataCount = ref(0);
const dataList = ref([]);
const sa = inject('sa')
const addUpdate = ref()
onMounted(() => {
    f5()
})

// 数据刷新
async function f5() {
    const {data} = await sa.put("/#{EntityName}#/listPage", p.value);
    dataList.value = data.content.map((item) => {
        return item;
    });
    dataCount.value = data.totalElements;
}

// 删除
function del(data) {
    sa.confirm('是否删除，此操作不可撤销', async function () {
        let res = await sa.delete("/#{EntityName}#/delete/" + data.id);
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
