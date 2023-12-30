<template>
    <el-select v-model="changeValue" @change="selectTo($parent)">
        <el-option label="请选择" value=""></el-option>
        <el-option
            :key="item.code"
            v-for="item in enums"
            :value="item.code"
            :label="item.name"
        >
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
    enumName: {type: String, default: ""},
    modelValue: {type: Number, default: 0},
})

onMounted(async () => {
    const {data} = await sa.get("/getEnums?enumName=" + props.enumName);
    enums.value = data;
    changeValue.value = parseInt(props.modelValue || 0);
})

const emits = defineEmits(['update:modelValue'])

function selectTo() {
    emits('update:modelValue', changeValue.value);
}

</script>


<style>
</style>