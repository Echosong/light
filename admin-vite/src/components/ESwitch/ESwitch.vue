<script setup>

import {onMounted, ref} from "vue";
const props = defineProps({
    modelValue: {type:Number, default: false}
})

const switchValue = ref(props.modelValue > 0)

const emits = defineEmits(['update:modelValue','change'])

onMounted(()=>{
    console.log(props.modelValue)
})

function change(){
    console.log("回来值",switchValue.value);
    emits('update:modelValue', switchValue.value? 1: 0)
    emits('change')
}

</script>

<template>
    <el-switch  v-model="switchValue" @change="change"></el-switch>
</template>

<style scoped lang="scss">

</style>