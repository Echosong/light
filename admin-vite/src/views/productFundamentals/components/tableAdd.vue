<template>
    <el-table :data="detailsList" style="width: 100%">
        <el-table-column width="54" label="编号" prop="displayorder"></el-table-column>
        <el-table-column label="保障利益名称" prop="benefitdetailname">
            <template #default="scope">
                <el-input v-model="scope.row.benefitdetailname" placeholder="保障利益名称" />
            </template>
        </el-table-column>
        <el-table-column label="责任代码" prop="dutyno">
            <template #default="scope">
                <el-input v-model="scope.row.dutyno" placeholder="责任代码" />
            </template>
        </el-table-column>

        <el-table-column label="保障利益描述" prop="benefitdescription">
            <template #default="scope">
                <el-input type="textarea" :rows="3" v-model="scope.row.benefitdescription" placeholder="保障利益描述" />
            </template>
        </el-table-column>

        <el-table-column label="特别约定" prop="date">
            <template #default="scope">

                <el-row v-for="(item, index) of scope.row.saList" :key="index" justify="center">
                    <el-col :span="20">
                        <el-input style="margin-bottom: 6px;" v-model="item.specialagreedcontent"
                            placeholder="特别约定" />
                    </el-col>
                    <el-col :span="4">
                        <div @click="scope.row.saList.splice(index,1);orderSrotFn()" style="width: 100%;height: 100%;cursor: pointer;" class="flexsCen">
                            <el-icon>
                                <Close />
                            </el-icon>
                        </div>
                    </el-col>
                </el-row>
                <el-button size="small" style="margin: 0 auto;display: block;" type="primary"
                    @click="saListAdd(scope.$index)" :icon="Plus">增加特别约定</el-button>
            </template>
        </el-table-column>
        <el-table-column label="添加时间" prop="addtime">
            <template #default="scope">
                <span>{{ scope.row.addtime }}</span>
            </template>
        </el-table-column>

        <el-table-column width="60" label="操作">
            <template #default="scope">
                <el-popconfirm @confirm="deleteFn(scope.$index,scope.row)" title="确定将数据从数据库中删除吗">
                    <template #reference>
                        <el-button type="danger" :icon="Delete" circle></el-button>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>
</template>

<script setup>
import { ref } from 'vue'
import { parseTime } from '@/utils'
import { Delete, Plus } from '@element-plus/icons-vue'
import {deleteBenefitDetailsByid } from '@/api/api';
const detailsList = ref([])

function addInterest() {
    detailsList.value.push({
        saList: [
            {
                id: '',
                specialagreedcontent: '',
                displayorder: 0,
            },
        ],
        id: '',
        displayorder: 0,
        benefitdetailname: '',
        dutyno: '',
        benefitdescription: '',
        addtime: parseTime(new Date().getTime(), '{y}-{m}-{d} {h}:{i}:{s}'),
    })
    orderSrotFn()
}

function addListFn(data){
    detailsList.value = data
}


function deleteFn(index,item) {
    if(item.id){
        deleteBenefitDetailsByidFn(item.id)
    }
    detailsList.value.splice(index, 1)
    orderSrotFn()
}

function saListAdd(index) {
    detailsList.value[index].saList.push({
        id: '',
        specialagreedcontent: '',
        displayorder: 0,
    })

    orderSrotFn()
}

function orderSrotFn(){
    detailsList.value.map((item,index)=>{
        item.displayorder = index + 1
        item.saList.map((items,indexs)=>{
            items.displayorder = indexs + 1
        })
    })

}

async function deleteBenefitDetailsByidFn(id){
    await deleteBenefitDetailsByid({
        id
    })
}
defineExpose({
    detailsList,
    addListFn,
    addInterest,
})
</script>
