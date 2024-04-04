

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
          字典编码
        </div>
      </template>
      <div style="width:500px;" v-html="one.dictCode"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          字典排序
        </div>
      </template>
      <div style="width:500px;" v-html="one.dictSort"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          字典标签
        </div>
      </template>
      <div style="width:500px;" v-html="one.dictLabel"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          字典键值
        </div>
      </template>
      <div style="width:500px;" v-html="one.dictValue"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          字典类型
        </div>
      </template>
      <div style="width:500px;" v-html="one.dictType"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          样式属性
        </div>
      </template>
      <div style="width:500px;" v-html="one.cssClass"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          表格字典样式
        </div>
      </template>
      <div style="width:500px;" v-html="one.listClass"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          是否默认
        </div>
      </template>
      <div style="width:500px;" v-html="one.defaultFlg"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          状态
        </div>
      </template>
      <div style="width:500px;" v-html="one.statusEnum"></div>
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
    title.value = "查看字典数据";
    //获取当个信息
    const {data} = await sa.get("/dictData/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>