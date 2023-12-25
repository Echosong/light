<template>
    <div class="app-container">
        <FunNavigation @addFrom="addFromPop"  @refreshFn="listFn"></FunNavigation>
        <el-table header-cell-class-name="tableBackground" :data="list" stripe style="width: 100%">
            <el-table-column width="80" prop="id" label="id" />
            <el-table-column prop="name" label="角色名" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="createTime" label="添加时间" />
            <el-table-column width="310" fixed="right" prop="address" label="操作">
                <template  #default="scope">
                    <el-button  v-if="permission('allocation')"  @click="editRolePermissionPop(scope.row)" :icon="Finished" type="success" size="small">权限分配</el-button>
                    <el-button  v-if="permission('edit')"   @click="addFromPop(scope.row)" :icon="Edit"  type="warning" size="small">修改</el-button>
                    <el-popconfirm  v-if="permission('delete')"  @confirm="deleteFn(scope.row.id)" title="您确定要删除这个角色吗？">
                        <template #reference>
                            <el-button :icon="Delete"  type="danger" size="small">删除</el-button>
                        </template>
                </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </div>
<AddRole @onsuccess="listFn" ref="addRoleRef"></AddRole>
<EditRolePermission  @onsuccess="listFn" ref="editRolePermissionRef"></EditRolePermission>
</template>
<script>
export default {
  name: "roleList"
}
</script>
<script setup>

import { roleList,roleDelete } from '@/api/api';
import FunNavigation from '@/components/funNavigation/funNavigation.vue';
import { Edit, Finished,Delete } from '@element-plus/icons-vue';
import { onMounted, ref } from 'vue'
import AddRole from './components/addRole.vue';
import EditRolePermission from './components/editRolePermission.vue';
import { permission } from '@/hooks/permission';
const editRolePermissionRef = ref(null)

onMounted(() => {
    listFn()
})

const addRoleRef = ref(null)

function addFromPop(obj){
    addRoleRef.value.dialogVisible = true
    if(obj) addRoleRef.value.addFormFn(obj)
}

function editRolePermissionPop(obj){
    editRolePermissionRef.value.dialogVisible = true
    if(obj) editRolePermissionRef.value.addFormFn(obj)
}


const list = ref([])
async function listFn() {
    const { data } = await roleList()
    list.value = data
}
async function deleteFn(id){
    await roleDelete({
        id
    })
    listFn()

}

</script>

<style>
.tableBackground{
    background:red;
}
</style>
  