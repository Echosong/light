<template>
    <el-upload style="width: 100%;" :on-remove="removeFn" :on-success="successFn" v-model:file-list="fileList"
        :accept="accept" class="upload-demo" :headers='headers' :data="{ type: 0 }" drag :action="action">
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">拖拽或点击上传文件</div>
    </el-upload>
</template>

<script setup>
import { useApp } from '@/pinia/modules/app'
import { ElMessage } from 'element-plus'
import { ref } from 'vue';
import { defineEmits } from 'vue'
const emit = defineEmits(["onSuccess", "onremove"])
const { authorization } = useApp()
// const action = ref(`http://127.0.0.1:3001/yunwoo/file/upload`)
const action = ref(`/yunwoo/file/upload`)
const headers = { 'Authorization': authorization }
const fileList = ref([
    //   {
    //     name: 'food.jpeg',
    //     url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    //   }
])
const props = defineProps({
    file: {
        type: String,
        default: ''
    },
    accept: {
        type: String,
        default: 'image/*'
    }
})

defineExpose({
    addFile
})
function addFile(e) {
    if (e) {
        e.map(item => {
            if (item) {
                fileList.value.push({
                name: item,
                url: item
            })
            }
        })
    }

}



const successFn = (file) => {
    let data = file.data
    if (file.status == 0) {
        emit('onSuccess', data.fileurl,data.id)
    } else {
        ElMessage.error(file.statusInfo)
    }
}
const removeFn = (file) => {
    if(file.response){
        emit('onremove', file.response.data.fileurl)
    }else{
        emit('onremove', file.url)

    }
   
}

</script>