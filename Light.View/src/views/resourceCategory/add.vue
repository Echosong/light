<template>
    <Dialog v-model="isShow" :title="title" maxHeight="200px" style="width: 500px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="标题"  prop="title" v-if="!query.title" >
    <el-input v-model="m.title"></el-input>
</el-form-item>
<el-form-item label="排序"  prop="sort" v-if="!query.sort" >
    <el-input v-model="m.sort"></el-input>
</el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="ok($parent)">
                    确定
                </el-button>
                <el-button @click="isShow = false">取消</el-button>
            </span>
        </template>
    </Dialog>
</template>

<script setup>
import Dialog from "@/components/dialog/index.vue";
import {inject, ref} from "vue";


const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {title:[],
sort:[],
}
const sa = inject('sa')
const ruleForm = ref();
const  query = ref({});

async function open(data, parmas)  {
    isShow.value = true;
    if (data) {
        title.value = "修改 资源分类";
        let one = await sa.get("/resourceCategory/find/"+data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata  = {title:'',
sort:0}
        query.value = parmas || {};
        m.value ={...mdata, ...parmas}
        title.value = "添加 资源分类";
    }
}

//提交资源分类信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            
            await sa.post("/resourceCategory/save", m.value);
            parent.f5();
            isShow.value = false;
        } else {
            console.log("error submit!!");
            return false;
        }
    });
}

defineExpose({
    open
})

</script>