<script setup>
const props = defineProps({
    imgUrl:{
        type:String,
        default:''
    }
})
</script>

<template>
    <el-image
        style="width: 100px; height: 100px"
        :src="imgUrl?imgUrl.split(',')[0] : '/assets/img/default.png'"
        :zoom-rate="1.2"
        :max-scale="7"
        :min-scale="0.2"
        :preview-src-list="imgUrl?imgUrl.split(',') : ['/assets/img/default.png']"
        :initial-index="4"
        fit="cover"
        :preview-teleported="true"
    />
</template>

<style scoped lang="scss">

</style>