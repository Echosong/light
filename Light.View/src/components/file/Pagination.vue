<script setup>
import {defineEmits} from "vue";

const props = defineProps({
    p:{
        type:Object,
        default:{page:1, pageSize:10, total:0}
    }
})

const emit = defineEmits(["f5"])

function gotoPage(){
    emit("f5",true)
}

</script>

<template>
    <div class="pages">
        <el-pagination
            background
            layout="total, prev, pager, next, sizes, jumper"
            v-model:current-page="p.page"
            v-model:page-size="p.pageSize"
            :total="p.total"
            :page-sizes="[1, 10, 20, 30, 40, 50, 100]"
            @current-change="gotoPage()"
            @size-change="gotoPage()">
        </el-pagination>
    </div>
</template>

<style scoped lang="scss">

</style>