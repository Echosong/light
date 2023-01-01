<template>
    <el-select v-model="changeValue" filterable @change="selectTo">
        <el-option value="">请选择</el-option>
        <el-option v-for="item in categorys" :key="item.id" :value="item.id" :label="item.name">
        </el-option>
    </el-select>
</template>

<script>
export default {
    props: {
        categoryId: { default: 0 },
        type:{default:1}
    },
    model: {
        prop: "categoryId",
        event: "event1",
    },

    data() {
        return {
            categorys: [],
            changeValue: this.categoryId
        }
    },
    created() {
        console.log(this.changeValue, this.categoryId, this.type);
        this.sa.get("/category/list").then(res => {
            this.categorys = res.data.filter(item=>{
                return item.type == this.type;
            });
        })
    },
    methods: {
        selectTo() {
            this.$emit("event1", this.changeValue);
        },
    },
}
</script>
<style>
</style>