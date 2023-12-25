<template>
    <div class="app-container">
        <FunNavigation @addFrom="addFromPop({},{parentId:0,id:0})" @refreshFn="listFn"></FunNavigation>

        <el-table header-cell-class-name="tableBackground" :data="list" style="width: 100%" border row-key="id" 
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
                    <el-button v-if="scope.row.type == 1 && permission('add')" @click="addFromPop(scope.row,{parentId:scope.row.id,id:0})" :icon="Plus"  type="primary" size="small">添加子级</el-button>
                    <el-button  v-if="permission('edit')"  @click="addFromPop(scope.row,{parentId:scope.row.parentId,id:scope.row.id})" :icon="Edit"  type="warning" size="small">修改</el-button>
                    <el-popconfirm  v-if="permission('delete')" @confirm="deleteFn(scope.row.id)" title="您确定要删除这个菜单吗？">
                        <template #reference>
                            <el-button :icon="Delete"  type="danger" size="small">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <AddPermission @onsuccess="listFn" ref="addPermissionRef"></AddPermission>
   
</template>
<script>
export default {
    name: "permissionList"
}
</script>
<script setup>

import { permissionAll,permissionDelete } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { Edit, Delete, Plus } from '@element-plus/icons-vue';
import { onMounted, ref } from 'vue'
import AddPermission from './components/addPermission.vue';

import { handleTree } from '@/utils';
import { permission } from '@/hooks/permission';

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
    const { data } = await permissionAll()
    list.value = handleTree(data)
}
async function deleteFn(id) {
    await permissionDelete({
        id
    })
    listFn()

}

</script>

<style>
.tableBackground {
    background: red;
}
</style>
  