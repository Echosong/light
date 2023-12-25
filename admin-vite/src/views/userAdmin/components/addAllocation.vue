<template>
    <el-dialog
      v-model="dialogVisible"
      title="绑定角色"
      width="30%"
    >
  <el-select  style="width: 100%;" multiple v-model="formLabelAlign.roleId" filterable placeholder="请选择角色">
    <el-option
      v-for="item in list"
      :key="item.id"
      :label="item.name"
      :value="item.id"
    />
  </el-select>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitForm">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </template>
  
  <script  setup>
  import { listByUser,saveUserRole,roleList } from '@/api/api';
import { ref,reactive,  } from 'vue'
  import { ElMessage } from 'element-plus'



function createUnrefFn(id){
    formLabelAlign.id = id
    listFn(id)
    roleListFn()
}

  const dialogVisible = ref(false)
const formLabelAlign = reactive({
  roleId:[],
  id:0
})

const submitForm = async () => {
    formLabelAlign.roleId = formLabelAlign.roleId.map(item=>{
        return {
            userId:formLabelAlign.id,
            roleId:item,
        }
    })
          await saveUserRole(formLabelAlign.roleId)
          ElMessage.success('保存成功')
          dialogVisible.value = false

}
  async function listFn(id){
   let {data:list} = await listByUser({
        id,
    })
    console.log(list)
    formLabelAlign.roleId = list.map(item=>{
        return item.id
    })
    // Object.assign(formLabelAlign,list)
}
const list = ref([])
async function roleListFn() {
    const { data } = await roleList()
    list.value = data
}
   //把数据导出
   defineExpose({
    dialogVisible,
    createUnrefFn
})

  </script>
  <style scoped>
  .dialog-footer button:first-child {
    margin-right: 10px;
  }
  </style>