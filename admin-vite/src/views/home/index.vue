<!--
 * @Descripttion: 
 * @version: 
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-24 18:18:43
 * @Author: huzhushan@126.com

-->
<template>
  <div class="app-container">
    <PanelGroup :numList='statistics'></PanelGroup>
    <el-row  style="background:#fff;margin-top: 30px;" :gutter="20">
      <el-col style="margin-bottom: 20px;" :lg="12" :sm="24" :span="12">
        <el-card class="box-card">
          <Line-chart :chart-data="statistics['周保单统计矩形图']" />
        </el-card>
      </el-col>
      <el-col :lg="12" :sm="24" :span="12">
        <el-card class="box-card">
          <Area-chart :chart-data="statistics['金额统计饼图']" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>

export default {
  name: "Dashboard"
}
</script>
<script setup>
import { adminBack } from '@/api/api';
import PanelGroup from './components/PanelGroup.vue';
import AreaChart from './components/areaChart.vue';
import LineChart from './components/lineChart.vue';
import { onMounted, reactive, ref } from 'vue'
let statistics = ref({})
listFn()
async function listFn(){
  let {data} =  await adminBack({})
  statistics.value = data
}


</script>