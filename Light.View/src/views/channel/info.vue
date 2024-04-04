

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
          渠道名
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
          渠道等级
        </div>
      </template>
      <div style="width:500px;" v-html="one.grade"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          渠道方案
        </div>
      </template>
      <div style="width:500px;" v-html="one.scheme"></div>
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
          成本价
        </div>
      </template>
      <div style="width:500px;" v-html="one.costPrice"></div>
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
    title.value = "查看渠道信息表";
    //获取当个信息
    const {data} = await sa.get("/channel/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>