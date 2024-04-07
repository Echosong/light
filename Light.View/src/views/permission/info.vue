

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
          权限名称
        </div>
      </template>
      <div style="width:500px;" v-html="one.name"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          权限描述
        </div>
      </template>
      <div style="width:500px;" v-html="one.description"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          访问路径
        </div>
      </template>
      <div style="width:500px;" v-html="one.url"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          权限标识
        </div>
      </template>
      <div style="width:500px;" v-html="one.perms"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          父级id
        </div>
      </template>
      <div style="width:500px;" v-html="one.parentId"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          类型
        </div>
      </template>
      <div style="width:500px;" v-html="one.type"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          排序
        </div>
      </template>
      <div style="width:500px;" v-html="one.sort"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          图标
        </div>
      </template>
      <div style="width:500px;" v-html="one.icon"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          是否展现 1 展现 0 不展现
        </div>
      </template>
      <div style="width:500px;" v-html="one.show"></div>
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
    title.value = "查看权限";
    //获取当个信息
    const {data} = await sa.get("/permission/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>