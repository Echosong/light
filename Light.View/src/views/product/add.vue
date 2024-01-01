<template>
    <el-dialog v-if="m" :title="title" v-model="isShow" width="800px" top="10vh" :append-to-body="true"
               :destroy-on-close="true" :close-on-click-modal="false" custom-class="full-dialog" draggable>
        <div class="vue-box">
            <!-- 参数栏 -->
            <div class="c-panel">
                <el-form size="mini" v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                         label-width="120px">
                    <el-form-item label="产品名称："  prop="name" >
            <el-input v-model="m.name"></el-input>
          </el-form-item>
<el-form-item label="价格："  prop="price" >
            <el-input v-model="m.price"></el-input>
          </el-form-item>
                    <el-form-item>
                        <span class="c-label">&emsp;</span>
                        <el-button type="primary" icon="el-icon-plus" size="small" @click="ok($parent)">确定
                        </el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </el-dialog>
</template>

<script setup>

import {inject, ref} from "vue";

//create_editor

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {name:[],
price:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        this.title = "修改 产品信息";
        
        m.value = data;
    } else {
        m.value = {name:'',
price:0}
        title.value = "添加 产品信息";
    }
}




//提交产品信息信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            
            //replace_editor
             sa.post("/product/save", m.value).then((res) => {
                parent.f5();
                isShow.value = false;
            });
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