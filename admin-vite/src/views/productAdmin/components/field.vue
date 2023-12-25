<template>
    <el-card style="margin-bottom: 10px">
        <template #header>
            <div class="card-header">
                <div class="flexBet flexsAliCen">
                    <span>系统默认字段</span>
                </div>
            </div>
            <el-table :data="list" stripe style="width: 100%">
                <el-table-column prop="fieldname" label="字段名称" />
                <el-table-column prop="fieldTypeName" label="归属范围" />
                <!-- <el-table-column  prop="insTypeName" label="字段类型" /> -->
                <el-table-column label="是否必填">是</el-table-column>
            </el-table>
        </template>
    </el-card>

    <el-card style="margin-bottom: 10px">
        <template #header>
            <div class="card-header">
                <div class="flexBet flexsAliCen">
                    <span>必填投保字段</span>
                    <el-button @click="popFn(1)" :icon="Edit" type="primary">
                        编辑必填字段
                    </el-button>
                </div>
            </div>
            <el-table :data="list1" stripe style="width: 100%">
                <el-table-column prop="fieldname" label="字段名称" />
                <el-table-column prop="fieldTypeName" label="归属范围" />
                <!-- <el-table-column  prop="insTypeName" label="字段类型" /> -->
                <el-table-column label="是否必填">是</el-table-column>
            </el-table>
        </template>
    </el-card>
    <el-alert style="margin-bottom: 10px" :closable="false" show-icon title="①可选字段的费率因子不可大于1，且核保规则不可设置成拒保;  ②如果和必填字段冲突以必填为准"
        type="warning" />
    <el-card style="margin-bottom: 10px">
        <template #header>
            <div class="card-header">
                <div class="flexBet flexsAliCen">
                    <span>设置可选投保字段</span>
                    <el-button @click="popFn(0)" :icon="Edit" type="primary">
                        编辑可选字段
                    </el-button>
                </div>
            </div>
            <el-table :data="list2" stripe style="width: 100%">
                <el-table-column prop="fieldname" label="字段名称" />
                <el-table-column prop="fieldTypeName" label="归属范围" />
                <!-- <el-table-column  prop="insTypeName" label="字段类型" /> -->
                <el-table-column label="是否必填">否</el-table-column>
            </el-table>
        </template>
    </el-card>
    <el-button type="primary" @click="submitForm(ruleFormRef)">
        保存，下一步
    </el-button>

    <FieldPop @on-succes="onSucces" :list="fieldsList" ref="FieldPopRef" />
</template>

<script>
export default {
    name: 'field',
}
</script>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Edit } from '@element-plus/icons-vue'
import {
    saveProductsfields,
    queryProductsfields,
    queryFieldsList,
} from '@/api/api'
import { reactive, ref } from 'vue'

import FieldPop from './fieldPop.vue'
const FieldPopRef = ref(null)
const options = ref(-1)
const emit = defineEmits(['onSuccess'])
const ruleFormRef = ref()
const list = ref([])
const list1 = ref([])
const list2 = ref([])

const productsid = ref(0)
const addForm = obj => {
    console.log(obj)
    productsid.value = obj.id
    queryProductsfieldsFn()
}
defineExpose({
    addForm,
})

const submitForm = async () => {
    let list = list1.value.concat(list2.value)
    await saveProductsfields(list)
    ElMessage.success('保存成功！')
    emit('onSuccess', productsid.value)
}

const onSucces = e => {
    let listArr = e.map(item => {
        return {
            options: options.value,
            ruletype: 0,
            displayorder: 0,
            productsid: productsid.value,
            fieldsid: item.id,
            fieldTypeName: item.fieldTypeName,
            fieldname: item.fieldname,
        }
    })
    if (options.value == 0) {
        list2.value = listArr
    } else if (options.value == 1) {
        list1.value = listArr
    }
}


async function queryProductsfieldsFn() {
    let { data } = await queryProductsfields({ productsid: productsid.value })
    list2.value = []
    list1.value = []
    data.map(item => {
        if (item.options == 0) {
            list2.value.push(item)
        } else if (item.options == 1) {
            list1.value.push(item)
        }
    })
}



const fieldsList = ref()

queryFieldsListFn()
async function queryFieldsListFn() {
    const { data } = await queryFieldsList({})
    list.value = []
    fieldsList.value = data
    data.map(item => {
        if (item.defaultselected == 1) {
            list.value.push(item)
        }
    })
}

function popFn(id) {
    options.value = id
    FieldPopRef.value.dialogVisible = true
}
</script>
