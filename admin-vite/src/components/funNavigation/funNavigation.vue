<template>
    <div style="margin-bottom: 10px;" class="flexBet">
        <div>
            <el-button v-if="isAdd && permission('add')" @click="$emit('addFrom')" type="success" plain :icon="Plus">新增</el-button>
        </div>
    
    <div class="right">
        <el-tooltip class="box-item" effect="dark" content="隐藏搜索" placement="top">
            <el-button @click="$emit('showFn')" plain :icon="Search" circle />
        </el-tooltip>
        <el-tooltip  class="box-item" effect="dark" content="刷新" placement="top">
            <el-button  @click="$emit('refreshFn')" plain :icon="Refresh" circle />
        </el-tooltip>
    </div>
</div>
</template>

<script setup>
import { Plus, Search, Refresh } from '@element-plus/icons-vue';
import { permission } from '@/hooks/permission';
const props = defineProps({
    isAdd:{
        type:Boolean,
        default:true
    }
})
</script>