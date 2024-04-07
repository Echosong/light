<template>
    <div class="app-container">
        <el-form :inline="true"  class="demo-form-inline">
            <el-form-item style="min-width: 0px">
                <el-button type="success" icon="Plus" plain @click="addFromPop({},{parentId:0,id:0})">增加</el-button>
            </el-form-item>
        </el-form>
        <el-table :header-cell-style="tableHeaderCellStyle" :data="list"  style="width: 100%">
            <el-table-column width="80" prop="id" label="id" />
            <el-table-column prop="name" label="角色名" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="createTime" label="添加时间" />
            <el-table-column width="310" fixed="right" prop="address" label="操作">
                <template  #default="scope">
                    <el-button link   @click="editRolePermissionPop(scope.row)" :icon="Finished" type="success" size="small">权限分配</el-button>
                    <el-button  link    @click="addFromPop(scope.row)" :icon="Edit"  type="warning" size="small">修改</el-button>
<!--                    <el-popconfirm    @confirm="deleteFn(scope.row.id)" title="您确定要删除这个角色吗？">
                        <template #reference>
                            <el-button link :icon="Delete"  type="danger" size="small">删除</el-button>
                        </template>
                    </el-popconfirm>-->
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

import { Edit, Finished,Delete } from '@element-plus/icons-vue';
import { onMounted, ref ,inject} from 'vue'
import AddRole from './components/addRole.vue';
import EditRolePermission from './components/editRolePermission.vue';
import { permission } from '@/hooks/permission';
const editRolePermissionRef = ref(null)

onMounted(() => {
    listFn()
})

const addRoleRef = ref(null)
const sa = inject('sa')

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
    const { data } = await sa.put('/role/list')
    list.value = data
}
async function deleteFn(id){
    sa.delete("/role/delete/" + id).then(res => {
        console.log(res)
        sa.arrayDelete(dataList.value, data);
        sa.ok(res.message);
    });
}

</script>

<style>
.tableBackground{
    background:red;
}
</style>
