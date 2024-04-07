

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
          图标
        </div>
      </template>
      <div style="width:500px;" v-html="one.ico"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件名
        </div>
      </template>
      <div style="width:500px;" v-html="one.fileName"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件扩展名
        </div>
      </template>
      <div style="width:500px;" v-html="one.extend"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件存储路径
        </div>
      </template>
      <div style="width:500px;" v-html="one.filePath"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件类型
        </div>
      </template>
      <div style="width:500px;" v-html="one.fileTypeEnum"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件大小
        </div>
      </template>
      <div style="width:500px;" v-html="one.fileSize"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          相对路径
        </div>
      </template>
      <div style="width:500px;" v-html="one.urlPath"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件唯一标识
        </div>
      </template>
      <div style="width:500px;" v-html="one.uuid"></div>
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
    title.value = "查看文件管理";
    //获取当个信息
    const {data} = await sa.get("/file/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>