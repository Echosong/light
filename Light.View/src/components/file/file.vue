<template>
    <el-upload style="width: 100%;" :on-remove="removeFn" :on-success="successFn" v-model:file-list="fileList" :accept="accept" class="upload-demo" :headers='headers' :data="{type:0}" drag :action="action">
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">拖拽或点击上传文件</div>
    </el-upload>

    
</template>

<script setup>
import { useApp } from '@/pinia/modules/app'
import { ElMessage } from 'element-plus'
import {onMounted, ref} from 'vue';

import { defineEmits } from 'vue'
const emit = defineEmits(["onSuccess","onremove"])
const { authorization } = useApp()
const action = ref(`/admin/file/upload`)
const headers =  {'Authorization':authorization}
const fileList = ref([])
const props = defineProps({
    file:{
        type:String,
        default:''
    },
    accept:{
        type:String,
        default:'image/*'
    }
})
onMounted(()=>{
    fileList.value = [{
        name:props.file,
        url:props.file
    }]
})

const successFn = (file)=>{
   let data = file.data
    if(file.code === 200){
        console.log(666666666667, file.data.url)
        emit('onSuccess',file.data.url)
    }else{
        ElMessage.error(file.message)
    }
}
const removeFn = (file)=>{
    emit('onremove','')
}

</script>