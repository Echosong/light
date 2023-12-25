<template>
    <el-form label-position="right" :model="formLabelAlign" ref="ruleFormRef">

        <el-card style="margin-bottom: 10px;">
            <template #header>
                <div class="card-header">
                    <span>保险计划名称与代码</span>
                </div>
            </template>
            <el-table class="tabel" :data="formLabelAlign.ppList" style="width: 100%">
                <el-table-column width="260" prop="date" label="计划名称">
                    <template #default="scope">
                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '计划名称不能为空',
                                trigger: 'blur',
                            },
                        ]" :prop="'ppList.' + scope.$index + '.productplanname'">
                            <el-input v-model="scope.row.productplanname" placeholder="计划名称" />
                        </el-form-item>
                    </template>
                </el-table-column>
                <el-table-column width="260" label="计划代码">
                    <template #default="scope">
                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '计划代码不能为空',
                                trigger: 'blur',
                            },
                        ]" :prop="'ppList.' + scope.$index + '.productplancode'">
                            <el-input v-model="scope.row.productplancode" placeholder="计划代码" />
                        </el-form-item>
                    </template>
                </el-table-column>
                <el-table-column label="接口标示">
                    <template #default="scope">
                        <el-form-item :prop="'ppList.' + scope.$index + '.interfaceinfo'">
                            <el-input v-model="scope.row.interfaceinfo" placeholder="接口标示" />
                        </el-form-item>
                    </template>
                </el-table-column>
                <el-table-column width="80">
                    <template #header>
                        <el-tooltip effect="dark" content="增加保险计划" placement="top">
                            <el-button @click="addPpListFn" circle :icon="Plus"></el-button>
                        </el-tooltip>
                    </template>
                    <template #default="scope">
                        <el-form-item>
                            <el-button @click="formLabelAlign.ppList.splice(scope.$index, 1)" type="danger" :icon="Delete"
                                circle />
                        </el-form-item>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-card>
            <el-collapse v-model="collapseName">
                <el-collapse-item v-for="(item, index) of formLabelAlign.pbList" :key="index" :title="`保障利益组${index + 1}`"
                    :name="index">
                    <el-form-item style="margin-top: 10px;" label-width="100px" :rules="[
                        {
                            required: true,
                            message: '计划名称不能为空',
                            trigger: 'blur',
                        },
                    ]" label="组名称" :prop="'pbList.' + index + '.groupname'">
                        <el-input v-model="item.groupname" placeholder="计划名称" />
                    </el-form-item>

                    <el-form-item label-width="100px" label="组代码">
                        <el-input v-model="item.groupcode" placeholder="计划名称" />
                    </el-form-item>
                    <el-form-item :rules="[
                        {
                            required: true,
                            message: '保障利益不能为空',
                            trigger: 'blur',
                        },
                    ]" label-width="100px" :prop="'pbList.' + index + '.productbenefitListFrom'" label="保障利益">
                        <el-select @remove-tag="reFn(index,$event)" @change="changeFn(index,$event)" style="width: 100%;" multiple v-model="item.productbenefitListFrom" placeholder="请选择">
                            <el-option v-for="item in fitTreeList" :key="item.value" :label="item.name" :value="item.id" />
                        </el-select>
                    </el-form-item>

                    <el-form-item label-width="100px">
                        <el-button @click="formLabelAlign.pbList.splice(index, 1); collapseName.splice(index, 1)" plain
                            type="danger" :icon="Delete">删除</el-button>
                    </el-form-item>
                </el-collapse-item>
            </el-collapse>
        </el-card>

        <!-- <el-card v-for="(item, index) of formLabelAlign.pbList" :key="index">
                <template #header>
                    <div class="card-header">
                        <span>保障利益组</span>
                    </div>
                </template>
                <el-form-item label-width="100px" :rules="[
                    {
                        required: true,
                        message: '计划名称不能为空',
                        trigger: 'blur',
                    },
                ]" label="组名称">
                    <el-input v-model="item.productplanname" placeholder="计划名称" />
                </el-form-item>

                <el-form-item label-width="100px" label="组代码">
                    <el-input v-model="item.productplanname" placeholder="计划名称" />
                </el-form-item>

                <el-form-item label-width="100px" label="保障利益">
                    <el-cascader style="width: 100%;" :props={ multiple: true } clearable v-model="value"
                        :options="benefitTree[index]" filterable />
                </el-form-item>
            </el-card> -->

        <el-form-item style="margin-top: 10px;">
            <el-button type="primary" @click="addPbListFn" :icon="Plus">增加保障利益组</el-button>
        </el-form-item>


        <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)">
                保存，下一步
            </el-button>
            <el-button @click="closeTag()">返回</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: 'interest',
}
</script>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import {
    queryInscompanySimpleList,
    queryProducttypeList,
    instypeQueryAllList,
    saveproductbenefitandplan,
    queryBenefitTree,
    queryProductplanList,
    queryProductBenefitGroupList
} from '@/api/api'
import { statusmark } from '@/pinia/modules/statusmark'
import { reactive, ref, computed } from 'vue'
import useCloseTag from '@/hooks/useCloseTag'
const emit = defineEmits(['onSuccess'])
const { closeTag } = useCloseTag()
const g_statusmark = computed(() => statusmark().g_statusmark);

const ruleFormRef = ref()
const benefitTree = ref([[]])
const collapseName = ref([0])
const ppList = [
    {
        productsid: '',
        displayorder: 0,
        productplanname: '',
        id: '',
        productplancode: '',
        interfaceinfo: '',
    },
]
const pbList = [
    {
        displayorder: 0,
        productsid: '',
        productbenefitListFrom: [],
        productbenefitList: [
        ],
        id: '',
        groupname: '',
        groupcode: '',
        options: 1,
    }
]
const formLabelAlign = reactive({
    ppList,
    pbList,
})
const productsid = ref(0)
const inscompanyid = ref(0)
const instypeid = ref(0)
const addForm = (obj) => {
    productsid.value = obj.id
    inscompanyid.value = obj.inscompanyid
    instypeid.value = obj.instypeid
    queryProductplanListFn()

    queryBenefitTreeFn()
}
defineExpose({
    addForm
})

function reFn(index,e){
    formLabelAlign.pbList[index].productbenefitList.map((item,index1)=>{
        if(e == item.benefitdetailsid){
            formLabelAlign.pbList[index].productbenefitList.splice(index1,1)
        }
    })
}

  function changeFn(index,e){
    let arr = formLabelAlign.pbList[index].productbenefitList.map(item=>item.benefitdetailsid)
    e.map(item=>{
        if(!arr.includes(item)){
            formLabelAlign.pbList[index].productbenefitList.push(addProductbenefitList(item,index))
        }
    })
 }

function addProductbenefitList(id,indexs){
    let arr = {}
    fitTreeList.value.map((item) => {
        if (item.attr2 == id) {
            arr = {
                        benefitdetailsid: item.attr2,
                        benefitsid: item.attr1,
                        productsid: productsid.value,
                        displayorder: indexs + 1,
                    }
        }
    })
    return arr
}

async function queryProductplanListFn() {
    const { data } = await queryProductplanList({
        productsid: productsid.value,
    })
    if (data.length > 0) {
        formLabelAlign.ppList = data
    }

}
async function queryProductBenefitGroupListFn() {
    const { data } = await queryProductBenefitGroupList({
        productId: productsid.value,
    })

    if (data.length > 0) {
        collapseName.value = []
        data.map((item, index) => {
            collapseName.value.push(index)
            item.productbenefitListFrom = []
            item.productbenefitList = []
            if (item.pbList && item.pbList.length > 0) {
                item.pbList.map(items => {
                    item.productbenefitList.push({
                        id:items.id,
                        benefitdetailsid: items.benefitdetailsid,
                        benefitsid: items.benefitsid,
                        productsid: items.productsid,
                        displayorder: items.displayorder,
                    })
                    fitTreeList.value.map(items1 => {
                        if (items.benefitdetailsid == items1.attr2) {
                            item.productbenefitListFrom.push(items.benefitdetailsid)
                        }
                    })
                })
            }

        })
       
        formLabelAlign.pbList = data
    }

}


const submitForm = async formEl => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            if (!productsid.value) {
                ElMessage.error('找不到产品')
                return
            }
            await saveproductbenefitandplan({
                ...dataFomat(formLabelAlign),
            })
            ElMessage.success('保存成功！')
            emit('onSuccess', productsid.value)
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}






function dataFomat(obj) {
    let pbgvList = []
    let ppList = []
    ppList = obj.ppList.map((item, index) => {
        return {
            productsid: productsid.value,
            displayorder: index + 1,
            productplanname: item.productplanname,
            id: item.id,
            productplancode: item.productplancode,
            interfaceinfo: item.interfaceinfo,
        }
    })

    pbgvList = obj.pbList.map((item, index) => {
        // let productbenefitList = item.productbenefitList.map((item., indexs) => {
        //     if (productbeneArr(id, index)) {
        //         return productbeneArr(id, index)
        //     }
        // })
        return {
            displayorder: index + 1,
            productsid: productsid.value,
            productbenefitList:item.productbenefitList,
            id: item.id,
            groupname: item.groupname,
            groupcode: item.groupcode,
            options: 1,
        }
    })

    return {
        pbgvList,
        ppList
    }
}



// function produCtChi(obj,id){
//      obj.children.map(item=>{

//     })
// }






const queryInscompanySimpleLists = ref([])
let { data: scompanySimpleLists } = await queryInscompanySimpleList({})
queryInscompanySimpleLists.value = scompanySimpleLists

const queryAllList = ref([])
let { data: AllList } = await queryProducttypeList({})
queryAllList.value = AllList

const instypeList = ref([])
let { data: insTypeLists } = await instypeQueryAllList({})
instypeList.value = insTypeLists

const addPpListFn = () => {
    formLabelAlign.ppList.push({
        ...ppList,
        displayorder: ppList.length,
    })
}
const addPbListFn = () => {
    collapseName.value.push(formLabelAlign.pbList.length)
    formLabelAlign.pbList.push({
        ...{
        displayorder: 0,
        productsid: '',
        productbenefitListFrom: [],
        productbenefitList: [
        ],
        id: '',
        groupname: '',
        groupcode: '',
        options: 1,
    },
        displayorder: formLabelAlign.pbList.length,
    })


}

const fitTreeList = ref([])
async function queryBenefitTreeFn() {
    const { data } = await queryBenefitTree({
        inscompanyid: inscompanyid.value,
        instypeid: instypeid.value
    })
    data.map(item => {
        item.attr2 = item.id
    })
    fitTreeList.value = data
    queryProductBenefitGroupListFn()
}


</script>

