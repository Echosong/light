
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
const changeValue = ref("")
const enums = ref([])
const props = defineProps({
    enumName: { type: String, default: "" },
    selectValue: { default: 0},
})

onMounted(()=>{
    sa.get("/getEnums?enumName=" +  props.enumName).then((res) => {
        enums.value = res.data;
    });
    changeValue.value = props.selectValue;
})

const emits = defineEmits(['update:modelValue'])
function selectTo(parent) {
    emits('update:modelValue', changeValue.value);
}

</script>


<style>
</style>