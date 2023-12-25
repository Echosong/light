<template>
    <div class="app-container">
        <el-form :inline="true" ref="ruleFormRef" :model="formInline" class="demo-form-inline">
            <el-form-item :rules="[
                {
                    required: true, message: '产品组名称不能为空', trigger: 'blur'
                },
            ]" prop="groupname" label="产品组名称">
                <el-input v-model="formInline.groupname" placeholder="请输入产品组名称" />
            </el-form-item>
            <el-form-item :rules="[
                {
                    required: true, message: '用户级别必选', trigger: 'blur'
                },
            ]" prop="userlevelid" label="用户级别">
                <el-select  v-model="formInline.userlevelid" placeholder="">
                    <el-option v-for="(item, index) of levelList" :key="index" :label="item.levelname" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item :rules="[
                {
                    required: true, message: '排序不能为空', trigger: 'blur'
                },
            ]" prop="displayorder" label="显示排序">
                <el-input v-model.number="formInline.displayorder" placeholder="请输入排序" />
            </el-form-item>
            <!-- <el-form-item>
                <el-button type="primary" :icon="Search" @click="onSubmit">
                    搜索
                </el-button>
                <el-button type="danger" :icon="Refresh" @click="resetting">
                    重置
                </el-button>
            </el-form-item> -->
        </el-form>
    </div>
    
    <div class="app-container">
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="保险公司">
                <el-input v-model="companyName" placeholder="请输入保险公司" />
            </el-form-item>
        </el-form>
        <el-table ref="table" :data="filterTableData" stripe style="width: 100%">
            <el-table-column label="已选" width="55">
                <template #default="scope">
                    <el-checkbox v-model="scope.row.check" />
                </template>
            </el-table-column>
            <el-table-column prop="companyName" label="保险公司" />
            <el-table-column prop="insTypeName" label="产品分类" />
            <el-table-column prop="productcode" label="产品代码" />
            <el-table-column prop="productname" label="产品名称" />
            <el-table-column width="180" prop="address" label="推广费">
                <template #default="scope">
                    <el-input type="number" v-model="scope.row.rate" @blur="blurInp(scope.row.rate)" placeholder="推广费">
                        <template #append>%</template>
                    </el-input>
                </template>
            </el-table-column>
            <el-table-column label="是否可用" width="100">
                <template #default="scope">
                    <el-checkbox v-model="scope.row.available" />
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top: 20px">
            <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
            <el-button @click="closeTag()">返回</el-button>
        </div>
    </div>
</template>
<script>
export default {
    name: 'userList',
}
</script>
<script setup>
import useCloseTag from '@/hooks/useCloseTag'
import { queryProductsList, queryUserLevelList,saveProductgroup,queryById } from '@/api/api'
import { reactive, ref ,computed} from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRoute } from 'vue-router'
const { closeTag } = useCloseTag()
const route = useRoute()
let productgroupid = route.query?.productgroupid || ''

const ruleFormRef = ref()
const date = ref('')

const formInline = reactive({
    username: 'admin',
    detailsList: [
        // {
        //   productcode: '',
        //   rate: '',
        //   available: '',
        //   id: '',
        //   productgroupid: '',
        // },
    ],
    groupname: '',
    userlevelid: '',
    displayorder: '',
    productgroupid: ''
})


const companyName = ref('')
const filterTableData = computed(() =>
list.value.filter(
    (data) =>
        !companyName.value ||
      data.companyName?.toLowerCase().includes(companyName.value?.toLowerCase())
  )
)

const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            let saveData = from()
            await saveProductgroup(saveData)
            ElMessage.success('保存成功！')

        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}

function from() {
    formInline.detailsList = []
   
    formInline.productgroupid = productgroupid
    for (let i = 0; i < list.value.length; i++) {
        const item = list.value[i];
        if (item.check) {
            if (item.rate < 0 || item.rate > 100) {
                ElMessage.error('佣金不能为空，且必须是0~100的数，可以有2位小数！')
                throw Error('佣金不能为空，且必须是0~100的数，可以有2位小数！');
            }
            formInline.detailsList.push(
                {
                    productcode: item.productcode,
                    rate: item.rate,
                    available: item.available ? 1 : 0,
                    id: '',
                    productgroupid,
                }
            )
        }
    }
    if(formInline.detailsList.length > 0){
        return formInline
    }else{
        ElMessage.error('请选择产品！')
        throw Error('请选择产品！');
    }
    
}


const blurInp = e => {
    if (e < 0 || e > 100) {
        ElMessage.error('佣金不能为空，且必须是0~100的数，可以有2位小数！')
    }
}

const list = ref([])
const table = ref()
listFn()
async function listFn() {
    const { data } = await queryProductsList({
        ...formInline,
    })
    data.map(item => {
        item.available = true
        item.check = false
    })
    if(productgroupid){
        editListFn(data)
    }else{
        list.value = data
    }
}
async function editListFn(objArr){
    const { data } = await queryById({
        id: productgroupid,
    })
    formInline.groupname = data.groupname
    formInline.displayorder = data.displayorder
    formInline.userlevelid = data.userlevelid

    data.detailsList.map(item=>{
        objArr.map(items=>{
            if(item.productcode == items.productcode){
                items.check = true
                items.rate = item.rate
                items.available = item.available ? true : false
            }
        })
    })
    list.value = objArr

}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data


</script>

<style></style>
