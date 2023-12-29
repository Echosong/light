<script setup>
const props = defineProps({
    fileUrl:{
        type:String,
        default:''
    }
})
</script>

<template>
    <a v-if="fileUrl" :href="fileUrl.split(',')[0] " target="_blank">下载</a>
    <span v-else> 暂无</span>
</template>

<style scoped lang="scss"></style>