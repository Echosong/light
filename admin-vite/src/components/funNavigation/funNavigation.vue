<template>
    <el-form   style="margin-bottom: 10px;" class="flexBet">
        <div>
            <slot></slot>
        </div>
    
    <div class="right">
        <el-tooltip class="box-item" effect="dark" content="隐藏搜索" placement="top">
            <el-button @click="$emit('showFn')" plain :icon="Search" circle />
        </el-tooltip>
        <el-tooltip  class="box-item" effect="dark" content="刷新" placement="top">
            <el-button   @click="$emit('f5')" plain :icon="Refresh" circle />
        </el-tooltip>
    </div>
    </el-form>
</template>

<script setup>
import { Search, Refresh } from '@element-plus/icons-vue';
</script>