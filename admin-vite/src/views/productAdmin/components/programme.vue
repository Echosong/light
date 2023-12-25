<template>
    <el-form label-position="right" ref="ruleFormRef">
        <el-card style="margin-bottom: 10px">
            <div class="table_wrap">
                <el-scrollbar>
                    <table :style="{ width: 200 * productplanList.length + 'px', 'min-width': '100%' }" class="table" border="1">
                        <thead>
                            <tr>
                                <th class="text-center" width="140" rowspan="2">承保项目</th>
                                <th class="text-center" :colspan="productplanList.length * 2">
                                    各保险计划保险金额
                                </th>
                            </tr>
                            <tr>
                                <th style="position: relative; width: 300px;" v-for="(item, index) of productplanList"
                                    :colspan="2" :key="index" class="text-center">
                                    {{ item.productplanname }}
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <template v-for="item in benefitGroupPlanList ">
                                <tr>
                                    <td>
                                        <el-text type="primary">{{ item.group.groupname }}</el-text>
                                    </td>
                                    <template v-for="(item, index) of productplanList">
                                        <td style="text-align: center;"><el-text>计费金额（元）</el-text></td>
                                        <td style="text-align: center;"><el-text>保额或保障</el-text></td>
                                    </template>
                                </tr>
                                <tr v-for="items in item.pbpMap ">
                                    <td>
                                        <el-text>{{ items[0].benefitDetailName }} <br> ({{ items[0].dutyNo }})</el-text>
                                    </td>
                                    <template v-for="itemss in  items ">
                                        <td>
                                            <el-form-item>
                                                <el-input @input="itemss.displayamount = comFn($event)" type="number"
                                                    v-model="itemss.maxamount" />
                                            </el-form-item>
                                        </td>
                                        <td>
                                            <el-form-item>
                                                <el-input v-model="itemss.displayamount" />
                                            </el-form-item>
                                        </td>
                                    </template>
                                </tr>
                            </template>
                        </tbody>
                    </table>
                </el-scrollbar>
            </div>
        </el-card>

        <el-card>
            <template #header>
                <div class="card-header">
                    <span>基准价格表</span>
                </div>
            </template>
            <el-alert title="提示! 重要提醒，计费时间段请勿发生重复，否则计算结果有可能不正确！计算时间段不可为0" type="warning" effect="dark" />
            <el-form-item style="margin-top: 10px">
                <el-button type="primary" @click="addPbListFn" :icon="Plus">
                    增加时间段
                </el-button>
                <el-button type="primary" @click="addPbListItemFn" :icon="Plus">
                    增加计费项
                </el-button>
                <el-button type="primary" @click="isSb = true" :icon="Plus">
                    增加社保项
                </el-button>
            </el-form-item>
            <el-scrollbar>
                <table :style="{ width: 120 * productplanList.length + 500 + 'px', 'min-width': '100%' }"
                    v-for="( item, keys, is ) in  planAmountList.ppaMapByLine " class="table" border="1">
                    <thead v-if="Object.keys(item).length > 0">
                        <tr>
                            <th class="text-center" style="width: 80px" rowspan="2">
                                计费年龄段
                            </th>
                            <th class="text-center" style="width: 80px" rowspan="2">
                                计费时间段
                            </th>
                            <th class="text-center" style="width: 80px" rowspan="2">
                                职业类型
                            </th>
                            <th class="text-center" :colspan="productplanList.length + 1">
                                基础价格
                            </th>
                            <th class="text-center" style="width: 40px" rowspan="2">操作</th>
                        </tr>
                        <tr>
                            <th style="width: 120px" class="text-center"> </th>
                            <th style="width: 120px" v-for="( item, index ) of  productplanList " :key="index"
                                class="text-center">
                                {{ item.productplanname }}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="( items, key, index ) in  item " :key="key">
                            <td v-if="index == 0" :rowspan="Object.keys(item).length">
                                <el-form-item style="justify-items: center;">
                                    <el-form-item>
                                        <el-input type="number" :min="1" v-model="items[0].minyear"></el-input>
                                    </el-form-item>
                                    <el-text style="width: 100%;text-align: center;">至</el-text>
                                    <el-form-item>
                                        <div class="flexs">
                                            <el-input type="number" :min="1" v-model="items[0].maxyear"></el-input>
                                        </div>
                                    </el-form-item>
                                    <el-text style="width: 100%;text-align: center;">岁</el-text>
                                </el-form-item>
                            </td>

                            <td>
                                <el-form-item v-if="is == 0">
                                    <el-form-item>
                                        <el-input type="number" :min="1" v-model="items[0].minvalue"></el-input>
                                    </el-form-item>
                                    <el-text style="width: 100%;text-align: center;">至</el-text>
                                    <el-form-item>
                                        <el-input type="number" :min="1" v-model="items[0].maxvalue"></el-input>
                                    </el-form-item>

                                    <el-form-item style="width: 100%;margin-top: 10px;">
                                        <el-select style="width: 100%" v-model="items[0].periodunit" placeholder="">
                                            <el-option label="年" :value="1" />
                                            <el-option label="月" :value="4" />
                                            <el-option label="天" :value="2" />
                                            <el-option label="小时" :value="3" />
                                        </el-select>
                                    </el-form-item>
                                </el-form-item>
                                <div v-else class="flexs flexsCen"><el-text>同上</el-text></div>
                            </td>
                            <td>
                                <el-form-item>
                                    <el-form-item>
                                        <el-input type="number" v-model="items[0].minoccupationaltype"></el-input>
                                    </el-form-item>
                                    <el-text style="width: 100%;text-align: center;">至</el-text>
                                    <el-form-item>
                                        <el-input type="number" v-model="items[0].maxoccupationaltype"></el-input>
                                    </el-form-item>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item>
                                    <el-select v-model="items[0].valueunit" placeholder="">
                                        <!-- <el-option label="每xx" :value="1" /> -->
                                        <el-option label="一口价" :value="2" />
                                        <!-- <el-option label="每几xx增加" :value="3" /> -->
                                    </el-select>
                                </el-form-item>
                            </td>
                            <td v-for="itemsss  in  items ">
                                <el-form-item required>
                                    <el-input type="number" :min="0" v-model="itemsss.amount" placeholder="基础价格"></el-input>
                                </el-form-item>
                                <el-form-item v-if="items[0].valueunit == 3">
                                    <el-input :min="0" v-model="itemsss.periodvalue" placeholder="每几xx"></el-input>
                                </el-form-item>
                                <el-form-item v-if="items[0].valueunit == 3">
                                    <el-input :min="0" v-model="itemsss.addamount" placeholder="增加金额"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-input :min="0" placeholder="无社保" v-if="isSb"
                                        v-model="itemsss.secondamount"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item>
                                    <el-button style="margin: auto;display: block;" @click="deleteIetm(key, index, item)"
                                        type="danger" :icon="Delete" circle />
                                </el-form-item>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </el-scrollbar>
        </el-card>
        <el-form-item style="margin-top: 20px">
            <el-button type="primary" @click="submitForm(ruleFormRef)">
                保存，下一步
            </el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: 'programme',
}
</script>
<script setup>
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import {
    queryInscompanySimpleList,
    queryProducttypeList,
    instypeQueryAllList,
    saveplanamountandbenefit,
    queryBenefitTree,
    queryBenefitGroupPlanList,
    queryPlanAmountList,
    queryProductplanList,
} from '@/api/api'
import { reactive, ref } from 'vue'
import { generateCode } from '@/utils'
import { parseTime } from '@/utils'
const isSb = ref(false)
const emit = defineEmits(['onSuccess'])
const ruleFormRef = ref()
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
    },
]
const formLabelAlign = reactive({
    ppList,
    pbList,
})
const productsid = ref(0)
const addForm = obj => {
    productsid.value = obj.id
    queryProductplanListFn()
}
defineExpose({
    addForm,
})

const submitForm = async formEl => {
    const obj = planAmountList.value.ppaMapByLine
    const pbpMap = benefitGroupPlanList.value
    let ppavList = ppavListFn(obj)
    let pbpvList = pbpvListFn(pbpMap)
    for (let index = 0; index < ppavList.length; index++) {
        const item = ppavList[index];
        if (!item.amount || !item.maxoccupationaltype || !item.maxvalue || !item.maxyear || !item.minoccupationaltype || !item.minvalue || !item.minyear || !item.periodunit || !item.planamountrandom || !item.valueunit) {
            ElMessage.error('请填写所有参数！')
            return
        }
    }
    for (let index = 0; index < pbpvList.length; index++) {
        const item = pbpvList[index];
        if (!item.maxamount) {
            ElMessage.error('请填写所有参数！')
            return
        }
    }
    if (!formLabelAlign.id) delete formLabelAlign['id']
    let { data } = await saveplanamountandbenefit({
        pbpvList,
        ppavList,
        psapList: []
    })
    ElMessage.success('保存成功！')
    emit('onSuccess', productsid.value)
}

function deleteIetm(key, index, item) {
    delete item[key]
}
function groupbyField(data, fieldName) {
    var obj = {}
    for (const item in data) {
        var key = getValue(data[item], fieldName)
        if (obj[key] == null) {
            obj[key] = []
        }
        obj[key].push(data[item])
    }
    return obj
}

function getValue(data, fieldName) {
    if (fieldName) {
        var obj = data
        if (fieldName.search('.') == 0) {
            var fieldNames = fieldName.split('.')
            for (var i in fieldNames) {
                var fieldNamess = fieldNames[i].split('_')
                if (fieldNamess.length > 1) {
                    obj = obj[fieldNamess[1]]
                    return obj
                }
                obj = obj[fieldNames[i]]
            }
            return obj
        }
    }
    return null
}
function addPbListFn() {
    const obj = planAmountList.value.ppaMapByLine
    let firstvalue = Object.keys(obj)[0]
    let newKey = generateCode(8)
    obj[newKey] = {}
    let arr = {}
    for (const key in obj[firstvalue]) {
        const element = obj[firstvalue][key]
        let planamountrandom = parseTime(new Date(), '{y}{m}{d}') + generateCode(8)
        obj[newKey][planamountrandom] = element.map(items => {
            return {
                displayorder: 0,
                productsid: productsid.value,
                planamountrandom,
                valueunit: '',
                minyear: '',
                maxyear: '',
                minvalue: items.minvalue,
                maxvalue: items.maxvalue,
                periodunit: "",
                minoccupationaltype: '',
                maxoccupationaltype: '',
                productplanid: items.productplanid,
                id: '',
                amount: '',
                secondamount: '',
                periodvalue: '',
                addamount: '',
            }
        })
    }
}

function addPbListItemFn() {
    const obj = planAmountList.value.ppaMapByLine
    for (const key in obj) {
        const element = obj[key][Object.keys(obj[key])[0]]
        let planamountrandom = parseTime(new Date(), '{y}{m}{d}') + generateCode(8)

        obj[key][planamountrandom] = element.map(items => {
            return {
                displayorder: 0,
                productsid: productsid.value,
                planamountrandom,
                valueunit: '',
                minyear: items.minyear,
                maxyear: items.maxyear,
                minvalue: '',
                maxvalue: '',
                periodunit: '',
                minoccupationaltype: '',
                maxoccupationaltype: '',
                productplanid: items.productplanid,
                id: '',
                amount: '',
                secondamount: '',
                periodvalue: '',
                addamount: '',
            }
        })
    }
}


function pbpvListFn(obj) {
    let pbpvList = []
    obj.map(item => {
        for (const keys in item.pbpMap) {
            const elements = item.pbpMap[keys]
            elements.map(item => {
                pbpvList.push({
                    id: item.id || '',
                    maxamount: item.maxamount,
                    displayamount: item.displayamount,
                })
            })
        }
    })
    return pbpvList
}

function ppavListFn(obj) {
    let ppavList = []
    for (const key in obj) {
        const element = obj[key]
        let keyObjs = element[Object.keys(element)[0]][0]
        for (const keys in element) {
            const elements = element[keys]
            elements.map(items => {
                ppavList.push({
                    displayorder: 1,
                    productsid: items.productsid,
                    planamountrandom: items.planamountrandom,
                    valueunit: keyObjs.valueunit,
                    minyear: keyObjs.minyear,
                    maxyear: keyObjs.maxyear,
                    minvalue: elements[0].minvalue,
                    maxvalue: elements[0].maxvalue,
                    periodunit: elements[0].periodunit,
                    minoccupationaltype: elements[0].minoccupationaltype,
                    maxoccupationaltype: elements[0].maxoccupationaltype,
                    productplanid: items.productplanid,
                    id: items.id || '',
                    amount: items.amount,
                    secondamount: items.secondamount || '',
                    periodvalue: items.periodvalue || '',
                    addamount: items.addamount || '',
                })
            })
        }
    }

    return ppavList
}

const benefitGroupPlanList = ref([])
async function queryBenefitGroupPlanListFn() {
    const { data } = await queryBenefitGroupPlanList({
        productId: productsid.value,
    })
    data.map((item, index) => {
        item.pbpMap = groupbyField(item.pbplist, 'productbenefitid')

        //将psapList以productBenefitId分组
        // var psapMap = groupbyField(item.psapList, "productbenefitid");
    })
    benefitGroupPlanList.value = data
}

const planAmountList = ref([])
async function queryPlanAmountListFn() {
    const { data } = await queryPlanAmountList({
        productId: productsid.value,
    })
    const ppaMap = groupbyField(data, 'productplanid')
    const ppaMapByYear = groupbyField(data, 'maxyear')
    let ppaMapByLine = {}
    for (let timeList in ppaMapByYear) {
        ppaMapByLine[timeList] = groupbyField(
            ppaMapByYear[timeList],
            'planamountrandom'
        )
    }
    console.log(productplanList.value)
    if (Object.keys(ppaMapByYear) == 0) {
        let planamountrandom = parseTime(new Date(), '{y}{m}{d}') + generateCode(8)
        ppaMapByLine["65"] = {}
        ppaMapByLine["65"][planamountrandom] = productplanList.value.map(item => {
            return {
                displayorder: 0,
                productsid: productsid.value,
                planamountrandom,
                valueunit: '',
                minyear: '',
                maxyear: '',
                minvalue: '',
                maxvalue: '',
                periodunit: '',
                minoccupationaltype: '',
                maxoccupationaltype: '',
                productplanid: item.id,
                id: '',
                amount: '',
                secondamount: '',
                periodvalue: '',
                addamount: '',
            }
        })
    } else {
        for (const key in ppaMapByLine) {
            const item = ppaMapByLine[key];
            for (const keys in item) {
                const items = item[keys];
                for (let index = items.length; index < productplanList.value.length; index++) {
                    let obj = productplanList.value[index]
                    items.push({
                        displayorder: 0,
                        productsid: items[0].productsid,
                        planamountrandom:items[0].planamountrandom,
                        valueunit: '',
                        minyear: '',
                        maxyear: '',
                        minvalue: '',
                        maxvalue: '',
                        periodunit: '',
                        minoccupationaltype: '',
                        maxoccupationaltype: '',
                        productplanid: obj.id,
                        id: '',
                        amount: '',
                        secondamount: '',
                        periodvalue: '',
                        addamount: '',
                    })
                }
            }
        }
    }
    planAmountList.value = {
        data,
        ppaMap,
        ppaMapByYear,
        ppaMapByLine,
    }
}

const productplanList = ref([])
async function queryProductplanListFn() {
    const { data } = await queryProductplanList({
        productsid: productsid.value,
    })
    productplanList.value = data
    queryBenefitGroupPlanListFn()
    queryPlanAmountListFn()
}

function comFn(e) {
    if (e >= 10000) {
        let str = parseInt(e / 10000)
        return str + '万';
    } else {
        return e;
    }
}
</script>
<style scoped>
.table_wrap {
    width: 100%;
    overflow: auto;
}

.table {
    border-collapse: collapse;
    border-spacing: 0;
    border: 1px solid #ebeef5;
    table-layout: fixed;
    width: 100%;
}

.table th {
    background: #f5f7fa;
    padding: 8px;
}

/* .table th:nth-child(1) {
    position: sticky;
    left: 0;
}

.table td:nth-child(1) {
    position: sticky;
    left: 0;
} */


.table td {
    padding: 8px;
    background: #fff;
}
</style>
