<template>
    <el-dialog
    v-model="dialogVisible"
    title="强制回调"
    width="500"
    :before-close="handleClose"
  >
  
  <el-form label-position="right" label-width="120" :model="formLabelAlign" ref="ruleFormRef" >

    <el-form-item  prop="orderNo" label="订单号">
            <el-input :disabled="true" v-model="formLabelAlign.orderNo" placeholder="订单号" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '请输入保单号',
                trigger: 'blur',
            }
        ]" prop="polyNo" label="保单号">
            <el-input  v-model="formLabelAlign.polyNo"  placeholder="保单号" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '请上传保单下载链接',
                trigger: 'blur',
            }
        ]" prop="pdfUrl" label="保单下载链接">
            <File ref="fileRef" :file="formLabelAlign.pdfUrl" @onremove="formLabelAlign.pdfUrl = ''"  @onSuccess="formLabelAlign.pdfUrl = $event" accept=".pdf"></File>
        </el-form-item>
</el-form>


    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive,ref} from 'vue'
import { ElMessage } from 'element-plus';
import { useRoute } from 'vue-router'
import {
    setOrderlnfo,
} from '@/api/api'
import File from '@/components/file/file.vue';

import { defineEmits } from 'vue'
const emit = defineEmits(["onSuccess"])
const ruleFormRef = ref()
const dialogVisible = ref(false)
const formLabelAlign = reactive({
    orderId: "",
    orderNo:"",
    polyNo:"",
    pdfUrl:""
})
const fileRef = ref(null)

const addFrom = function(item){
formLabelAlign.orderNo = item.orderno
formLabelAlign.orderId = item.id
formLabelAlign.polyNo = ""
formLabelAlign.pdfUrl = ""
fileRefIS()
}

function fileRefIS(){
    if(fileRef.value){
        fileRef.value.addFile(formLabelAlign.pdfUrl)
    }else{
        fileRefIS()
    }
}

defineExpose({
    dialogVisible,
    formLabelAlign,
    addFrom
})
const submitForm = async formEl => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            //过滤 空格
            formLabelAlign.polyNo =  formLabelAlign.polyNo.replace(/\s/g, "");
            await setOrderlnfo({
                ...formLabelAlign,
            })
            dialogVisible.value = false
            emit("onSuccess")
             ElMessage.success('提交成功！')
        } else {
            console.info(fields)
           ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}
</script>
<style scoped lang='scss'>

</style>
