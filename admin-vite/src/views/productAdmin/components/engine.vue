<template>
    <el-form label-position="right" :model="formLabelAlign" ref="ruleFormRef">

        <el-card style="margin-bottom: 10px;">
            <template #header>
                <div class="flexsCen flexBet">
                    <div class="card-header">
                        <span>投保规则</span>
                    </div>
                    <el-button size="small" type="primary">从现有产品复制投保规则</el-button>
                </div>
            </template>
            <el-alert title="若不设置，字段默认为选填、核保自动通过且费率因子为1。" type="warning" />
            <dl>
                <dt style="font-weight: bold;" class="flexsAliCen"><el-icon style="margin-right: 10px;">
                        <Setting />
                    </el-icon><el-text class="mx-1">通用投保规则设定</el-text></dt>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">1.生效时间及规则设置</el-text>
                        <el-button :icon="Edit" size="small" plain type="primary">设置规则</el-button>
                        <el-text class="mx-1" type="info">可进行是否倒签、是否零时生效、等待期等生效相关设置</el-text>
                    </el-space>
                </dd>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">2.个人投保人年龄</el-text>
                        <el-button :icon="Edit" size="small" plain type="primary">设置规则</el-button>
                        <el-text class="mx-1" type="info">当投保人是个人时，投保人年龄范围设置</el-text>
                    </el-space>
                </dd>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">3.被保险人年龄</el-text>
                        <el-button :icon="Edit" size="small" plain type="primary">设置规则</el-button>
                        <el-text class="mx-1" type="info">保单生效时间，被保险人的允许年龄范围</el-text>
                    </el-space>
                </dd>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">4.被保险人的身份识别</el-text>
                        <el-button :icon="Edit" size="small" plain type="primary">设置规则</el-button>
                        <el-text class="mx-1" type="info">对应不同类型的被保险人，可配置不同的规则</el-text>
                    </el-space>
                </dd>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">5.多被保险人折扣</el-text>
                        <el-button :icon="Edit" size="small" plain type="primary">设置规则</el-button>
                        <el-text class="mx-1" type="info">被拒保或待人工审核的被保险人，不计入多人系数</el-text>
                    </el-space>
                </dd>
            </dl>
            <dl>
                <dt style="font-weight: bold;" class="flexsAliCen">
                    <div style="width: 100%;" class="flexBet">
                        <div> <el-icon style="margin-right: 10px;">
                                <Setting />
                            </el-icon><el-text class="mx-1">高级投保规则设定</el-text></div>

                        <el-button :icon="Plus" size="small" plain type="primary">添加规则</el-button>
                    </div>
                </dt>
            </dl>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="date" label="字段名称" />
                <el-table-column prop="name" label="归属范围" />
                <el-table-column prop="name" label="字段类型" />
                <el-table-column prop="name" label="是否必填" />
                <el-table-column prop="name" label="操作">
                    <template #default="scope">
                        <el-button :icon="Edit" size="small" plain type="primary">修改规则</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-card style="margin-bottom: 10px;">
            <template #header>
                <div class="flexsCen flexBet">
                    <div class="card-header">
                        <span>退保规则</span>
                    </div>
                    <el-button size="small" type="primary">从现有产品复制投保规则</el-button>
                </div>
            </template>
            <dl>
                <dt style="font-weight: bold;" class="flexsAliCen"><el-icon style="margin-right: 10px;">
                        <Setting />
                    </el-icon><el-text class="mx-1">通用投保规则设定</el-text></dt>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">1.保单注销的退费比例</el-text>
                        <el-form-item>
                            <el-input v-model="input2" placeholder="">
                                <template #append>%</template>
                            </el-input>
                        </el-form-item>
                        <el-text class="mx-1" type="info">保单在生效日前退保时，退还给客户的费用比例</el-text>
                    </el-space>
                </dd>
                <dd class="flexsAliCen">
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">2.犹豫期</el-text>
                        <el-space>
                            <el-form-item label="生效日期起">
                                <el-space>
                                    <el-form-item style="width: 50px;">
                                        <el-input v-model="input2" placeholder="" />
                                    </el-form-item>
                                    <el-form-item>
                                        <el-select  style="width: 90px;" v-model="formLabelAlign.instypeid" placeholder="">
                                            <el-option v-for="(item, index) of 5" :key="index" :label="index"
                                                :value="index" />
                                        </el-select>
                                    </el-form-item>
                                </el-space>

                            </el-form-item>
                        </el-space>
                        <el-text class="mx-1" type="info">犹豫期内退保，全额退款，若无犹豫期请填写0</el-text>
                    </el-space>
                </dd>

                <dd>
                    <el-space :size="50">
                        <el-text style="width: 200px;" class="mx-1">3.犹豫期后退保</el-text>
                        <div>
                            <el-text class="mx-1">需人工处理，退款方式如下</el-text>
                            <el-form-item style="width: 50px;">
                                <el-input style="margin-top: 10px;" type="textarea" :rows="4" v-model="input2"
                                    placeholder=""></el-input>
                            </el-form-item>
                        </div>
                    </el-space>
                </dd>
            </dl>
        </el-card>
        <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)">
                提交
            </el-button>
            <el-button @click="closeTag()">返回</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: 'engine',
}
</script>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Delete, Edit } from '@element-plus/icons-vue'
import {
    queryInscompanySimpleList,
    queryProducttypeList,
    instypeQueryAllList,
    saveProduct,
    queryBenefitTree
} from '@/api/api'
import { statusmark } from '@/pinia/modules/statusmark'
import { reactive, ref,computed } from 'vue'
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
            // {
            //     benefitdetailsid: 883,
            //     benefitsid: 332,
            //     productsid: '442',
            //     displayorder: 1,
            // }
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

const submitForm = async formEl => {
    // if (!formEl) return
    // await formEl.validate(async (valid, fields) => {
    //     if (valid) {
    //         if (!formLabelAlign.id) delete formLabelAlign['id']
    //         let { data } = await saveProduct({
    //             ...formLabelAlign,
    //         })
    //         ElMessage.success('保存成功！')
    //         emit('onSuccess', data)
    //     } else {
    //         console.info(fields)
    //         ElMessage.error('请查看您填写的参数是否正确！')
    //     }
    // })
    ElMessage.success('提交成功！')

}

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
        ...pbList,
        displayorder: formLabelAlign.pbList.length,
    })

    console.log(collapseName.value)
}

const fitTreeList = ref([])
queryBenefitTreeFn()
async function queryBenefitTreeFn() {
    const { data } = await queryBenefitTree({
        inscompanyid: 7,
        instypeid: 1
    })
    fitTreeList.value = data

}
</script>


<style scoped>
dd {
    margin-top: 10px;
}
</style>
