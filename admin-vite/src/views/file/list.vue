<template>
    <div class="app-container">
        <!-- 参数栏 -->
        <el-form :inline="true" size="small" class="demo-form-inline">
            <el-form-item label="文件名：">
                <el-input v-model="p.fileName" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item label="文件类型">
                <input-enum
                    enumName="fileTypeEnum"
                    v-model="p.fileType"
                ></input-enum>
            </el-form-item>
            <el-form-item style="min-width: 0px">
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
                <el-button type="success" icon="Plus" @click="add">增加</el-button>
            </el-form-item>
        </el-form>
        <!-- <div class="c-title">数据列表</div> -->
        <el-table :data="dataList" header-cell-class-name="tableBackground" @sort-change="shortChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="图标">
                <template #default="s">
                    <el-link v-for="item in (s.row.icoFile)" :key="item.name" :href="item.url" type="primary">
                        {{ item.name }}
                    </el-link>
                </template>
            </el-table-column>
            <el-table-column label="文件名" prop="fileName"></el-table-column>
            <el-table-column label="文件扩展名" prop="extend"></el-table-column>
            <el-table-column label="文件存储路径" prop="filePath"></el-table-column>
            <el-table-column label="文件类型" prop="fileTypeEnum"></el-table-column>
            <el-table-column label="文件大小" prop="fileSize"></el-table-column>
            <el-table-column label="相对路径" prop="urlPath"></el-table-column>
            <el-table-column label="文件唯一标识" prop="uuid"></el-table-column>
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

const p = ref({pageSize: 10, page: 1, fileName: '', fileType: ''})
;
const dataCount = ref(0);
const dataList = ref([]);
const sa = inject('sa')
const addUpdate = ref()
onMounted(() => {
    f5()
})

// 数据刷新
function f5() {
    sa.put("/file/listPage", p.value).then(res => {
        dataList.value = res.data.content.map((item) => {
            try {
                item.icoFile = JSON.parse(item.ico);
            } catch (e) {
                item.icoFile = [{name: '咱无', url: ''}]
            }
            return item;
        });
        dataCount.value = res.data.totalElements;
    });
}

// 删除
function del(data) {
    sa.confirm('是否删除，此操作不可撤销', function () {
        sa.delete("/file/delete/" + data.id).then(res => {
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
