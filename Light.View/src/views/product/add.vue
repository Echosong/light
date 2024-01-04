<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="产品名称："  prop="name" >
    <el-input v-model="m.name"></el-input>
</el-form-item>
<el-form-item label="价格："  prop="price" >
    <el-input v-model="m.price"></el-input>
</el-form-item>
<el-form-item label="产品描述">
<Tinymce ref="info" v-model="m.info"></Tinymce> </el-form-item>
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
import Tinymce from '@/components/Tinymce/Tinymce.vue';
const info = ref()
const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {name:[],
price:[],
info:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 产品信息";
        m.value = data;
    } else {
        m.value = {name:'',
price:0,
info:''}
        title.value = "添加 产品信息";
    }
}

//提交产品信息信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
                let infoText = info.value.getContent()
    if(infoText){
        m.value.info = infoText
    }

            await sa.post("/product/save", m.value);
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