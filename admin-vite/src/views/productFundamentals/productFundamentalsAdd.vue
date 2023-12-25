<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="添加保障利益" name="first">
                <div>
                    <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef"
                        style="max-width: 90%">
                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '版本号不能为空',
                                trigger: 'blur',
                            },
                        ]" prop="clauseversion" label="版本号">
                            <el-input v-model="formLabelAlign.clauseversion" placeholder="版本号" />
                        </el-form-item>

                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '条款全称不能为空',
                                trigger: 'blur',
                            },
                        ]" prop="clausename" label="条款全称">
                            <el-input v-model="formLabelAlign.clausename" placeholder="条款全称" />
                        </el-form-item>


                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '保险公司不能为空',
                                trigger: 'change',
                            },
                        ]" prop="inscompanyid" label="保险公司">

                            <el-select  v-model="formLabelAlign.inscompanyid" placeholder="">
                                <el-option v-for="(item, index) of queryInscompanySimpleLists" :key="index"
                                    :label="item.companyname" :value="item.id" />
                            </el-select>
                        </el-form-item>
                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '报备险种不能为空',
                                trigger: 'change',
                            },
                        ]" prop="instypeid" label="报备险种">

                            <el-select  @change="changeFn" v-model="formLabelAlign.instypeid" placeholder="">
                                <el-option v-for="(item, index) of instypeList" :key="index" :label="item.instypename"
                                    :value="item.id" />
                            </el-select>
                        </el-form-item>

                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '条款类型不能为空',
                                trigger: 'change',
                            },
                        ]" prop="clausetypeid" label="条款类型">
                            <el-select  v-model="formLabelAlign.clausetypeid" placeholder="">
                                <el-option v-for="(item, index) of queryClauseTypeLists" :key="index" :label="item.typename"
                                    :value="item.id" />
                            </el-select>

                        </el-form-item>

                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '保障利益分类不能为空',
                                trigger: 'change',
                            },
                        ]" prop="benefitclassid" label="保障利益分类">
                            <el-select  v-model="formLabelAlign.benefitclassid" placeholder="">
                                <el-option v-for="(item, index) of queryAllList" :key="index" :label="item.benefitclassname"
                                    :value="item.id" />
                            </el-select>
                        </el-form-item>

                        <el-form-item prop="personaltype" label="是否个险">
                            <el-radio-group v-model="formLabelAlign.personaltype">
                                <el-radio :label="1">是</el-radio>
                                <el-radio :label="0">否</el-radio>
                            </el-radio-group>
                        </el-form-item>

                        <el-form-item prop="recordno" label="备案号">
                            <el-input v-model="formLabelAlign.recordno" placeholder="备案号" />
                        </el-form-item>

                        <el-form-item prop="benefitno" label="条款代码">
                            <el-input v-model="formLabelAlign.benefitno" placeholder="条款代码" />
                        </el-form-item>

                        <el-form-item prop="benefitinterestclause" label="保障利益条款">
                            <File ref="fileRef" accept=".pdf" :file="formLabelAlign.benefitinterestclause" @onremove="onremove"  @onSuccess="onSuccess"></File>
                        </el-form-item>

                        <el-form-item prop="waiver" label="免责事项">
                            <Tinymce ref="TinymceRef" v-model="formLabelAlign.waiver"></Tinymce>
                        </el-form-item>

                        <el-form-item prop="versionremark" label="版本说明">
                            <el-input type="textarea" :rows="5" v-model="formLabelAlign.versionremark" placeholder="版本说明" />
                        </el-form-item>
                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '状态不能为空',
                                trigger: 'change',
                            },
                        ]" prop="status" label="状态">
                            <el-select  v-model="formLabelAlign.status" placeholder="">
                                <el-option label="启用" :value="1" />
                                <el-option label="禁用" :value="0" />
                            </el-select>
                        </el-form-item>

                        <el-form-item :rules="[
                            {
                                required: true,
                                type: 'number',
                                message: '排序只能填数字',
                                trigger: 'blur',
                            }
                        ]" prop="displayorder" label="显示排序">
                            <el-input v-model.number="formLabelAlign.displayorder" placeholder="显示排序" />
                        </el-form-item>

                        <el-form-item label="保障利益">
                            <TableAdd ref="TableAddRef"></TableAdd>
                            <el-button style="margin-top: 20px;" type="primary" @click="addInterest">添加保障利益</el-button>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="submitForm(ruleFormRef)">
                                提交
                            </el-button>
                            <el-button @click="closeTag()">返回</el-button>
                        </el-form-item>
                    </el-form>
                </div>

            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
export default {
    name: 'productFundamentalsAdd',
}
</script>
<script setup>
import {
    instypeQueryAllList,
    queryInscompanySimpleList,
    queryClauseTypeList,
    saveBenefits,
    queryUserLevelList,
    benefitsQueryById,
    queryBenefitclassList,
} from '@/api/api'
import Tinymce from '@/components/Tinymce/Tinymce.vue';
import TableAdd from './components/tableAdd.vue';
import { onMounted } from 'vue'
import useCloseTag from '@/hooks/useCloseTag'
import { ElMessage, ElMessageBox } from 'element-plus'
import { reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
import File from '@/components/file/fileTals.vue';
import { parseTime } from '@/utils';
const fileRef = ref()
const activeName = ref('first')
const ruleFormRef = ref()
const { closeTag } = useCloseTag()
const TinymceRef = ref()
const formLabelAlign = reactive({
    id: 0,
    detailsList: [
        // {
        //   saList: [],
        //   id: 0,
        //   displayorder: '',
        //   benefitdetailname: '',
        //   dutyno: '',
        //   benefitdescription: '',
        // },
    ],
    waiver:'',
    clauseversion: '',
    clausename: '',
    inscompanyid: '',
    instypeid: '',
    clausetypeid: '',
    benefitclassid: '',
    recordno: '',
    benefitno: '',
    productfilesids: [],
    benefitinterestclause: [],
    versionremark: '',
    status: '',
    personaltype:0,
    displayorder: '',
})
// const validateUserName = async (rule, value, callback) => {
//   if (formLabelAlign.id) {
//     callback()
//     return
//   }

//   let { data } = await checkUserName({
//     username: value,
//   })
//   if (!data) {
//     callback()
//   } else {
//     callback(new Error('用户名已存在'))
//   }
// }

const submitForm = async formEl => {
    console.log(formLabelAlign.productfilesids)
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            if (!formLabelAlign.id) delete formLabelAlign['id']
            // delete formLabelAlign['userCompany']
            formLabelAlign.detailsList = TableAddRef.value.detailsList
            formLabelAlign.detailsList.map(item=>{
                delete item.addtime
            })
            await saveBenefits({
                ...formLabelAlign,
                productfilesids:formLabelAlign.productfilesids.join(";"),
                waiver:TinymceRef.value.getContent() || ''
            })
            ElMessage.success('保存成功！')
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}


const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data
onMounted(() => {
    if (useRoute().query.id) {
        formLabelAlign.id = useRoute().query.id
        benefitsQueryByIdFn()
    }
})
const TableAddRef = ref()
function addInterest() {
    TableAddRef.value.addInterest()
}

async function benefitsQueryByIdFn() {
    let { data: list } = await benefitsQueryById({
        id: formLabelAlign.id
    })
    queryBenefitclassListFn(list.instypeid)
    list.detailsList.map(item=>{
        console.log(item.addtime)
        item.addtime = parseTime(item.addtime)
    })
    Object.assign(formLabelAlign, list)
    formLabelAlign.productfilesids = removeLastSemicolon(formLabelAlign.productfilesids)
    formLabelAlign.productfilesids = formLabelAlign.productfilesids.split(";")[0] ? formLabelAlign.productfilesids.split(";") : []
    fileRef.value.addFile(removeLastSemicolon(list.benefitinterestclause).split(";"))
    TableAddRef.value.addListFn(list.detailsList)

}
function removeLastSemicolon(str) {
  if (str.endsWith(';')) {
    return str.slice(0, -1); // 去除最后一个字符（分号）
  }
  return str; // 字符串最后一个字符不是分号，直接返回原字符串
}



const instypeList = ref([])
let { data: insTypeLists } = await instypeQueryAllList({})
instypeList.value = insTypeLists


const queryInscompanySimpleLists = ref([])
let { data: scompanySimpleLists } = await queryInscompanySimpleList({})
queryInscompanySimpleLists.value = scompanySimpleLists



const queryClauseTypeLists = ref([])
let { data: clauseTypeLists } = await queryClauseTypeList({})
queryClauseTypeLists.value = clauseTypeLists
const queryAllList = ref([])
async function queryBenefitclassListFn(instypeid) {
    let { data: AllList } = await queryBenefitclassList({
        instypeid
    })
    queryAllList.value = AllList
}

function changeFn(e) {
    formLabelAlign.benefitclassid = ''
    queryBenefitclassListFn(e)
}

function onSuccess(url,id){
    formLabelAlign.benefitinterestclause += url + ";";
    formLabelAlign.productfilesids.push(id)
}
function onremove(url){
    console.log(url)
    let benefitinterestclause = formLabelAlign.benefitinterestclause.split(";")
    let index = benefitinterestclause.indexOf(url)
    if(index !== -1){
        console.log(index)
        benefitinterestclause.splice(index,1)
        formLabelAlign.benefitinterestclause = benefitinterestclause.join(";");
        formLabelAlign.productfilesids.splice(index,1)

        console.log(formLabelAlign.benefitinterestclause)
        console.log(formLabelAlign.productfilesids)
    }

    
}
</script>

<style></style>
