

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
          排序
        </div>
      </template>
      <div style="width:500px;" v-html="one.sort"></div>
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
    title.value = "查看资源分类";
    //获取当个信息
    const {data} = await sa.get("/resourceCategory/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>