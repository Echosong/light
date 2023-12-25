<template>
    <el-upload class="avatar-uploader" :data="{ type: 0 }" :headers='headers' :action="action" accept="image/*"
        :show-file-list="false" :on-remove="removeFn" :on-success="successFn">
        <el-image  v-if="imageUrl" style="width: 100%; height: 100%" :src="imageUrl" fit="contain" />
        <el-icon v-else class="avatar-uploader-icon">
            <Plus />
        </el-icon>
    </el-upload>
</template>

<script setup>
import { useApp } from '@/pinia/modules/app'
import { urlMontage } from '@/utils';
import { ElMessage } from 'element-plus'
import { ref } from 'vue';
const emit = defineEmits(["onSuccess", "onremove"])
const { authorization } = useApp()
// const action = ref(`http://127.0.0.1:3001/yunwoo/file/upload`)
const action = ref(`/yunwoo/file/upload`)
const headers = { 'Authorization': authorization }
const props = defineProps({
    imageUrl: {
        type: String,
        default: ""
    },
})
const successFn = (file) => {
    if (file.value) {
        emit('onSuccess', file.value)
    } else {
        ElMessage.error("上传失败！")
    }
}
const removeFn = (file) => {
    emit('onremove', '')
}

</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>