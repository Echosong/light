<template>
    <el-dialog
      v-model="dialogVisible"
      title="所属产品组"
      width="30%"
    >
  <el-select  style="width: 100%;" multiple v-model="selectValue" filterable placeholder="请选择产品组">
    <el-option
      v-for="item in checkList"
      :key="item.productgroupid"
      :label="item.groupname"
      :value="item.productgroupid"
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
  import { productgroupQueryList,queryUsersByid,saveUsers } from '@/api/api';
import { ref,reactive,  } from 'vue'
  import { ElMessage } from 'element-plus'


function createUnrefFn(id,username){
  queryListByUserNameFn(id,username)
    queryUsersByidFn(id)
}


  const checkList = ref([])
  const selectValue = ref([])
  const dialogVisible = ref(false)
const formLabelAlign = reactive({
    id:0,
    usersProductGroupList: [
        // {
        //   id: '',
        //   productgroupid: '32',
        // },
    ],
    userCompanyDetails: {
        companyname: '',
        parentCityCode: '',
        cityscode: '',
        contactperson: '',
        contactmobile: '',
        contacttelephone: '',
        addressinfo: '',
        zipcode: '',
        qq: '',
        fax: '',
         email: '',
        website: '',
        companytype: '',
        companyinfo: '',
    },
    userattribute:1,
    usertype: 1,
    username: '',
    parentname: '',
    tuanxianstatus: 1,
    mobile: '',
    telephone: '',
    parentCityCode: '',
    cityscode: '',
    addressinfo: '',
    email: '',
    qq: '',
    zipcode: '',
    userlevelid: '',
    status: 1,
    loginpassword: '',
    confirmPassword: '',
    operatepassword: '',
    confirmOperatePassword: '',
    secretkey: '',
    remark: '',
})

const submitForm = async () => {
    formLabelAlign.usersProductGroupList = []
        selectValue.value.map(item=>{
            formLabelAlign.usersProductGroupList.push({
                id:'',
                productgroupid:item
            })
        })
        delete formLabelAlign['userCompany']
          await saveUsers({
            ...formLabelAlign,
          })
          ElMessage.success('保存成功！')
          dialogVisible.value = false

}
async function queryListByUserNameFn(id,username){
    let {data} = await productgroupQueryList({
    subuserid:id,
    username:'admin',
  })
  console.log(checkList)
  checkList.value = data
}
 
  async function queryUsersByidFn(id){
   let {data:list} = await queryUsersByid({
        id,
    })
    Object.assign(formLabelAlign,list)
    formLabelAlign.userCompanyDetails = list.userCompany
    selectValue.value = []
    list.usersProductGroupList.map(item=>{
        selectValue.value.push(Number(item.productgroupid))
    })
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