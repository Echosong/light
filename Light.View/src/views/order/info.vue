

<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-descriptions
            class="margin-top"
            :column="1"
            size="default"
            border
        >
            <el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          订单日期
        </div>
      </template>
      <div style="width:500px;" v-html="one.orderTime"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          客户名称
        </div>
      </template>
      <div style="width:500px;" v-html="one.customerName"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          渠道名称
        </div>
      </template>
      <div style="width:500px;" v-html="one.channelName"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          保险公司
        </div>
      </template>
      <div style="width:500px;" v-html="one.companyName"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          方案
        </div>
      </template>
      <div style="width:500px;" v-html="one.scheme"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          渠道等级
        </div>
      </template>
      <div style="width:500px;" v-html="one.grade"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          伤残比例
        </div>
      </template>
      <div style="width:500px;" v-html="one.accidentRate"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          三类人数
        </div>
      </template>
      <div style="width:500px;" v-html="one.threeClass"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          三类售价
        </div>
      </template>
      <div style="width:500px;" v-html="one.threeClassPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          三类渠道价
        </div>
      </template>
      <div style="width:500px;" v-html="one.threeClassChannelPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          四类人数
        </div>
      </template>
      <div style="width:500px;" v-html="one.fourClass"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          四类渠道价
        </div>
      </template>
      <div style="width:500px;" v-html="one.fourClassChannelPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          四类售价
        </div>
      </template>
      <div style="width:500px;" v-html="one.fourClassPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          五类人数
        </div>
      </template>
      <div style="width:500px;" v-html="one.fiveClass"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          五类渠道价
        </div>
      </template>
      <div style="width:500px;" v-html="one.fiveClassChannelPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          五类售价
        </div>
      </template>
      <div style="width:500px;" v-html="one.fiveClassPrice"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          运营人
        </div>
      </template>
      <div style="width:500px;" v-html="one.operation"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          返利总额
        </div>
      </template>
      <div style="width:500px;" v-html="one.totalRebate"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          利润
        </div>
      </template>
      <div style="width:500px;" v-html="one.profit"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          回本日期
        </div>
      </template>
      <div style="width:500px;" v-html="one.backTime"></div>
</el-descriptions-item>

        </el-descriptions>
    </Dialog>
</template>

<script setup>
import Dialog from "@/components/dialog/index.vue";
import {inject, ref} from "vue";

const isShow = ref(false);
const title = ref("");
const sa = inject('sa')
const one = ref({});

async function open(row, query)  {
    isShow.value = true;
    title.value = "查看业绩数据";
    //获取当个信息
    const {data} = await sa.get("/order/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>