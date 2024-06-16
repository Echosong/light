<template>
    <el-dropdown trigger="hover" @visible-change="f5" style=" cursor: pointer; " >
        <div class="userinfo">
            <el-icon size="20px" style="color: white">
                <InfoFilled/>
            </el-icon>
        </div>
        <template #dropdown>

            <el-dropdown-menu style="margin-right: 20px;margin-top: 20px; margin-left: 20px;">
                <el-dropdown-item > 当前版本: {{versionInfo.version }}</el-dropdown-item>
                <el-dropdown-item > 最后发布: {{versionInfo.buildTime }}</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>

</template>

<script setup>

import {inject, ref} from "vue";
import router from '@/router'

const versionInfo = ref({})

const sa = inject('sa')

f5();
async function f5(){
    const {data} = await sa.get("/version");
    versionInfo.value = data;
}
function memFn(item) {
    router.push(item.url);
    console.log(item)
}

</script>

<style scoped>
.errLog-container {
    margin-right: 10px;
}

.message-title {
    font-size: 16px;
    color: #333;
    font-weight: bold;
    padding-right: 8px;
}
</style>
