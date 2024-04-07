<template>
    <el-select v-model="changeValue" filterable @change="selectTo" :disabled="disabled" >
        <el-option value="">请选择</el-option>
        <el-option v-for="item in enums" :key="item.id" :value="item.id" :label="item.name">
        </el-option>
    </el-select>
</template>

<script setup>


import {ref} from "@vue/runtime-core";
import {onMounted} from "vue";
import sa from "@/utils/sa";

const changeValue = ref(0)
const enums = ref([])
const props = defineProps({
    indexId: { default: ''},
    routeName: {default: ""},
    type: {default: 0},
    disabled: {default: false}
})

onMounted(async () => {
    const {data} = await sa.get("/"+props.routeName+"/getMap?type="+props.type);
    enums.value = data;
    changeValue.value = parseInt(data[0].id|| 10000);
})

const emits = defineEmits(['update:modelValue'])

function selectTo() {
    let selectValue = changeValue.value === 10000? null : changeValue.value;
    emits('update:modelValue', selectValue);
}


</script>
<style>
</style>