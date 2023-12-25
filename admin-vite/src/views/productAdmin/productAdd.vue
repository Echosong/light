<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="添加产品" name="first">
                <div style="margin-bottom: 40px;">
                    <el-steps align-center :active="stepActive" >
                        <el-step @click="stepFn(0)" title="基本信息" />
                        <el-step @click="stepFn(1)" title="保障利益" />
                        <el-step @click="stepFn(2)" title="保险方案" />
                        <el-step @click="stepFn(3)" title="投保字段" />
                        <el-step @click="stepFn(4)" title="规则引擎" />
                    </el-steps>
                </div>
                <Info ref="InfoRef" v-if="stepActive == 0" @on-success="onSuccess"></Info>

                <Interest  ref="InterestRef"  v-if="stepActive == 1" @on-success="onSuccess"></Interest>
                <Programme ref="ProgrammeRef" v-if="stepActive == 2" @on-success="onSuccess"></Programme>
                <Field ref="FieldRef" v-if="stepActive == 3" @on-success="onSuccess"></Field>
                <Engine ref="EngineRef" v-if="stepActive == 4" @on-success="onSuccess"></Engine>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
export default {
    name: "productAdd"
}
</script>
<script setup>
import { productQueryById } from '@/api/api';
import { useRoute } from 'vue-router';
import Info from './components/info.vue';
import Interest from './components/interest.vue';
import Programme from './components/programme.vue';
import Field from './components/field.vue';
import Engine from './components/engine.vue';
import { onMounted, reactive, ref } from 'vue'
onMounted(() => {
    if (useRoute().query.id) {
    formLabelAlign.id = useRoute().query.id
    listFn(formLabelAlign.id)
}
})
const InfoRef = ref(null)
const InterestRef = ref(null)
const ProgrammeRef = ref(null)
const FieldRef = ref(null)
const EngineRef = ref(null)
const activeName = ref('first')
const stepActive = ref(0)
const next = () => {
    if(stepActive.value != 4){
        stepActive.value++
    }
}
function stepFn(num){
    if(formLabelAlign.id){
        stepActive.value = num

        listFn(formLabelAlign.id)
    }
}

const formLabelAlign = reactive({
    id: 0,
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
    userattribute: 1,
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


const onSuccess = (id)=>{
    next()
    listFn(id)
}
async function listFn(id) {
    let { data: list } = await productQueryById({
        id
    })
    Object.assign(formLabelAlign,list)
    setTimeout(()=>{
        if(InfoRef && stepActive.value == 0) InfoRef.value?.addForm(list)
        if(InterestRef && stepActive.value == 1) InterestRef.value?.addForm(list)
        if(ProgrammeRef && stepActive.value == 2) ProgrammeRef.value.addForm(list)
        if(FieldRef && stepActive.value == 3) FieldRef.value.addForm(list)
        if(EngineRef && stepActive.value == 4) EngineRef.value.addForm(list)
    },1000)
}
</script>

<style scoped lang="scss">
   .app-container :deep(.el-step__line){
        background-color: #a0cfff;
    } 
    
    .app-container :deep(.el-step__head.is-process,){
        color: #409eff;
        border: #409eff;
    } 
    .app-container :deep(.el-step__title.is-process){
        color: #409eff;
        border: #409eff;
    } 

    .app-container :deep(.el-step__title.is-wait){
        color: #a0cfff;
        border: #a0cfff;
    } 
    .app-container :deep(.el-step__head.is-wait){
        color: #a0cfff;
        border: #a0cfff;
    } 

    
</style>
