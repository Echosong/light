<template>
    <div class="app-container">
        <el-form :inline="true" v-show="showSearch" class="demo-form-inline">
            <el-form-item label="名称">
                <el-input v-model="p.name" placeholder="模糊查询"></el-input>
            </el-form-item>
            <el-form-item style="min-width: 0px">
                <el-button type="primary" icon="Search" @click="f5();">查询</el-button>
                <el-button icon="Refresh" plain @click="reset">重置</el-button>
            </el-form-item>
        </el-form>

        <FunNavigation @f5="f5" @showFn="showSearch = !showSearch">
            <el-button type="success" icon="Plus" plain @click="add">增加</el-button>
            <el-button type="info" icon="Sort" plain @click="toggleExpandAll">展开/折叠</el-button>
        </FunNavigation>

        <el-table v-if="refreshTable" :header-cell-style="tableHeaderCellStyle"  :default-expand-all="isExpandAll" :data="list" style="width: 100%"  row-key="id"
                  :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
            <el-table-column  width="160" label="id" >
                <template  #default="scope">
                    <span>{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="导航名称" >
                <template  #default="scope">
                    <div class="flexsAliCen">
                        <!-- <component style="width: 16px;height: 16px;" class="xxx" :is="scope.row.icon"></component> -->
                        <span style="margin-left: 6px;">{{ scope.row.name }}</span>
                    </div>
                </template>
            </el-table-column>

            <!-- <el-table-column label="别名">
                <template  #default="scope">
                    <span>{{ scope.row.url }}</span>
                </template>
            </el-table-column> -->
            <el-table-column label="权限码" >
                <template  #default="scope">
                    <span>{{ scope.row.perms }}</span>
                </template>
            </el-table-column>
            <el-table-column label="描述">
                <template  #default="scope">
                    <span>{{ scope.row.description }}</span>
                </template>
            </el-table-column>
            <el-table-column width="280"  label="操作">
                <template  #default="scope">
                    <el-button  link @click="addFromPop(scope.row,{parentId:scope.row.id,id:0})" :icon="Plus"  type="primary" size="small">添加子级</el-button>
                    <el-button  link @click="addFromPop(scope.row,{parentId:scope.row.parentId,id:scope.row.id})" :icon="Edit"  type="warning" size="small">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <AddPermission @onsuccess="listFn" ref="addPermissionRef"></AddPermission>

</template>
<script setup>

import { Edit, Plus } from '@element-plus/icons-vue';
import {nextTick, onMounted, ref} from 'vue'
import AddPermission from './components/addPermission.vue';

import { handleTree } from '@/utils';
import sa from "@/utils/sa";
import FunNavigation from "@/components/funNavigation/funNavigation.vue";

const params = {pageSize: 10, page: 1, total: 0, name: ''}
const p = ref(JSON.parse(JSON.stringify(params)))
const showSearch = ref(true);
const isExpandAll = ref(false);
const refreshTable = ref(true);

onMounted(() => {
    listFn()
})
const addPermissionRef = ref(null)
function addFromPop(obj,editObj) {
    addPermissionRef.value.dialogVisible = true
    if (obj) addPermissionRef.value.addFormFn(obj,editObj)
}
const list = ref([])
async function listFn() {
    const { data } = await sa.get('/permission/all')
    list.value = handleTree(data)
}

function reset() {
    p.value = JSON.parse(JSON.stringify(params))
}
async function toggleExpandAll(){
    refreshTable.value = false;
    isExpandAll.value =!isExpandAll.value
    await nextTick()
    refreshTable.value = true;
}

</script>

<style>
.tableBackground {
    background: red;
}
</style>
