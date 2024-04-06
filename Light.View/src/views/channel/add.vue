<template>
    <Dialog v-model="isShow" :title="title" maxHeight="350px" style="width: 600px;">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="渠道名" prop="channelName" v-if="!query.channelName">
                <el-select style="width: 400px" v-model="m.channelName" filterable @change="selectChannel"  :allow-create="true"  >
                    <el-option v-for="item in channelNames" :key="item.channelName" :value="item.channelName" :label="item.channelName">
                    </el-option>
                </el-select>

            </el-form-item>
            <el-form-item label="保险公司" prop="companyName" v-if="!query.companyName">
                <el-select style="width: 400px" v-model="m.companyName" filterable @change="selectCompany" :allow-create="true" >
                    <el-option v-for="item in companyNames" :key="item.companyName" :value="item.companyName" :label="item.companyName">
                    </el-option>
                </el-select>

            </el-form-item>

            <el-form-item label="渠道方案" prop="scheme" v-if="!query.scheme">
                <el-select style="width: 400px" v-model="m.scheme" filterable  :allow-create="true" >
                    <el-option v-for="item in schemes" :key="item" :value="item" :label="item">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="渠道等级" v-if="!query.grade">
                <input-enum style="width: 400px" enumName="channelGradeEnum" v-model="m.grade"></input-enum>
            </el-form-item>

            <el-form-item label="伤残比例" style="width: 520px" prop="accidentRate" v-if="!query.accidentRate">
                <el-input v-model="m.accidentRate"></el-input>
            </el-form-item>
            <el-form-item label="成本价" prop="costPrice" style="width: 520px" v-if="!query.costPrice">
                <el-input v-model="m.costPrice"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="ok($parent)">
                    确定
                </el-button>
                <el-button @click="isShow = false">取消</el-button>
            </span>
        </template>
    </Dialog>
</template>

<script setup>
import Dialog from "@/components/dialog/index.vue";
import {inject, ref} from "vue";
import InputEnum from "@/components/enum/InputEnum.vue";

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    channelName: [],
    companyName: [],
    grade: [],
    scheme: [],
    accidentRate: [],
    costPrice: [],
}
const sa = inject('sa')
const ruleForm = ref();
const query = ref({});

async function open(data, parmas) {
    isShow.value = true;
    if (data) {
        title.value = "修改渠道信息表";
        let one = await sa.get("/channel/find/" + data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata = {
            channelName: '',
            companyName: '',
            grade: '',
            scheme: '',
            accidentRate: '',
            costPrice: ''
        }
        query.value = parmas || {};
        m.value = {...mdata, ...parmas}
        title.value = "添加渠道信息表";
    }
    channelData();
}

const channelNames = ref([])
const companyNames = ref([]);
const schemes = ref([]);

async function channelData(){
   let {data} = await sa.get("/channel/listChannelName");
    channelNames.value = data;
}


function selectChannel(){
    console.log("选择渠道")
    let model = m.value;
    //循环 channelNames 过滤等于model.channelName的数据
     channelNames.value.forEach(item=>{
        if(item.channelName === model.channelName){
            companyNames.value = item.companyNames;
        }
    })
    console.log("公司信息", channelNames, companyNames, model.channelName, model.companyName)
}

function  selectCompany(){
    let model = m.value;
    //循环 companyNames 过滤等于model.companyName的数据
     companyNames.value.filter(item=>{
         if(item.companyName === model.companyName){
             schemes.value = item.schemes;
         }
    })
}


//提交渠道信息表信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {

            await sa.post("/channel/save", m.value);
            parent.f5();
            isShow.value = false;
        } else {
            console.log("error submit!!");
            return false;
        }
    });
}

defineExpose({
    open
})

</script>