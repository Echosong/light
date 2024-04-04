

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
          用户
        </div>
      </template>
      <div style="width:500px;" v-html="one.username"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          请求ip
        </div>
      </template>
      <div style="width:500px;" v-html="one.requestIp"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          地址
        </div>
      </template>
      <div style="width:500px;" v-html="one.address"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          描述
        </div>
      </template>
      <div style="width:500px;" v-html="one.description"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          浏览器
        </div>
      </template>
      <div style="width:500px;" v-html="one.browser"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          请求耗时
        </div>
      </template>
      <div style="width:500px;" v-html="one.time"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          方法名
        </div>
      </template>
      <div style="width:500px;" v-html="one.method"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          参数
        </div>
      </template>
      <div style="width:500px;" v-html="one.params"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          日志类型
        </div>
      </template>
      <div style="width:500px;" v-html="one.logTypeEnum"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          异常详情
        </div>
      </template>
      <div style="width:500px;" v-html="one.exceptionDetail"></div>
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
    title.value = "查看日志";
    //获取当个信息
    const {data} = await sa.get("/log/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>