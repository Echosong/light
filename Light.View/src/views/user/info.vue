

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
          账号
        </div>
      </template>
      <div style="width:500px;" v-html="one.username"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          密码
        </div>
      </template>
      <div style="width:500px;" v-html="one.password"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          性别
        </div>
      </template>
      <div style="width:500px;" v-html="one.sexEnum"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          身高
        </div>
      </template>
      <div style="width:500px;" v-html="one.height"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          体重
        </div>
      </template>
      <div style="width:500px;" v-html="one.weight"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          昵称
        </div>
      </template>
      <div style="width:500px;" v-html="one.nick"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          注册方式
        </div>
      </template>
      <div style="width:500px;" v-html="one.typeEnum"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          姓名
        </div>
      </template>
      <div style="width:500px;" v-html="one.name"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          状态
        </div>
      </template>
      <div style="width:500px;" v-html="one.stateEnum"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          邮箱
        </div>
      </template>
      <div style="width:500px;" v-html="one.email"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          注册ip
        </div>
      </template>
      <div style="width:500px;" v-html="one.regIp"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          登录ip
        </div>
      </template>
      <div style="width:500px;" v-html="one.loginIp"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          身份证正面
        </div>
      </template>
      <div style="width:500px;" v-html="one.frontCard"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          身份证反面
        </div>
      </template>
      <div style="width:500px;" v-html="one.reverseCard"></div>
</el-descriptions-item>
<el-descriptions-item>
 <template #label>
        <div class="cell-item" style="width:100px;">
          个人简介
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
    title.value = "查看用户";
    //获取当个信息
    const {data} = await sa.get("/user/find/"+row.id);
    one.value = data;
}
defineExpose({
    open
})
</script>

<style scoped>

</style>