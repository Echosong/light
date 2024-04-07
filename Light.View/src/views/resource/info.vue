

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
          标题
        </div>
      </template>
      <div style="width:500px;" v-html="one.title"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          类别
        </div>
      </template>
      <div style="width:500px;" v-html="one.type"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          文件路径
        </div>
      </template>
      <div style="width:500px;" v-html="one.path"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          说明
        </div>
      </template>
      <div style="width:500px;" v-html="one.info"></div>
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
    title.value = "查看办公资源文件存在";
    //获取当个信息
    const {data} = await sa.get("/resource/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>