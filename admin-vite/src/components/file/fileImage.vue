<template>
    <!-- <el-upload style="width: 100%;" :on-remove="removeFn" :on-success="successFn" v-model:file-list="fileList" accept="application/pdf" class="upload-demo" :headers='headers' :data="{type:0}" drag :action="action" multiple>
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">拖拽或点击上传文件</div>
    </el-upload> -->
    <el-upload
    class="avatar-uploader"
    :headers='headers' :data="{type:0}" drag :action="action"
    :show-file-list="false"
    :on-remove="removeFn"
    :on-success="successFn"
  >
    <img v-if="file" :src="file" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
    <template #tip>
      <div class="el-upload__tip">
        请上传大小合适的图片
      </div>
    </template>
  </el-upload>
</template>

<script setup>
import { useApp } from '@/pinia/modules/app'
import { ElMessage } from 'element-plus'
import { ref } from 'vue';
import { defineEmits } from 'vue'
const emit = defineEmits(["onSuccess","onremove"])

const { authorization } = useApp()
const action = ref(`/admin/file/upload`)
const headers =  {'Authorization':authorization}
const props = defineProps({
    file:{
        type:String,
        default:''
    },
})

const successFn = (file)=>{
   let data = file.data
    if(file.code === 200){
        emit('onSuccess',data.url)
    }else{
        ElMessage.error(file.message)
    }
}
const removeFn = (file)=>{
    emit('onremove','')
}

</script>

<style scoped>
.avatar-uploader .avatar {
  width: 230px;
  height: 153px;
  display: block;
}
</style>