<template>
    <el-dialog v-model="dialogVisible" title="权限分配" width="50%" draggable>

        <el-table default-expand-all max-height="400px" @selection-change="selectAllFn" header-cell-class-name="tableBackground" :data="list" style="width: 100%" border row-key="id" 
            :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
            <el-table-column label="导航名称" >
                <template  #default="scope">
                    <svg-icon class="icon" :name=" scope.row.icon" />
                    <span>{{ scope.row.name }}</span>
                </template>
            </el-table-column>

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

            <el-table-column width="55" label="选择">
                <template #default="scope">
                <el-checkbox @change="change(scope.row)" v-model="scope.row.checked"></el-checkbox>
            </template>
            </el-table-column>

        </el-table>

        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="submitForm(ruleFormRef)">
                    确定
                </el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, reactive, inject } from 'vue'
import { ElMessage } from 'element-plus'
import { handleTree } from '@/utils';
const emit = defineEmits(['onsuccess'])

const ruleFormRef = ref(null)
const sa = inject('sa')
const dialogVisible = ref(false)
const formLabelAlign = reactive({
    roleId: 0,
    arr:[]
})

function change(item){
    if(item.children){
        item.children.map(items=>{
            items.checked = item.checked
            change(items)
        })
    }
}
const addFormFn = (obj) => {
    formLabelAlign.roleId = obj.id
    listFn()
    
}

//把数据导出
defineExpose({
    addFormFn,
    dialogVisible,
})

const list = ref([])
async function listFn() {
    const { data } = await sa.get('/permission/all', {});
    listByRoleFn(data)
}
const submitForm = async () => {
    formLabelAlign.arr = []
    listSelect(list.value)
    if(formLabelAlign.arr.length === 0){
        ElMessage.success('请最少选择一个权限')
        return 
    }
    await sa.put(`/permission/updateRolePermissions/${formLabelAlign.roleId}`,formLabelAlign.arr)
    ElMessage.success('保存成功！')
    dialogVisible.value = false
    emit('onsuccess')
}
async function listByRoleFn(arr){
    let {data} = await sa.get(`/permission/listByRole/${formLabelAlign.roleId}`, {roleId:formLabelAlign.roleId})
    data.map(item=>{
        arr.map(items=>{
            if(item.id == items.id){
                items.checked = true
            }
        })
    })
    list.value = handleTree(arr)
    console.log(list)
}


function listSelect(obj){
    obj.map(item=>{
        if(item.checked){
            formLabelAlign.arr.push(item.id)
            if(item.children){
                listSelect(item.children)
            }
        }
    })
}


</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>
