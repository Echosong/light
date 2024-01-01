<template>
    <Dialog v-model="dialogVisible"   :title="`${formLabelAlign.id ? '修改' :'新增'}角色`"  maxHeight="200px" maxWidth="500px">
        <el-form  label-position="right" label-width="100" ref="ruleFormRef" :model="formLabelAlign" class="demo-form-inline">
            <el-form-item :rules="[
                {
                    required: true,
                    message: '角色名称不能为空',
                    trigger: 'blur',
                },
            ]" prop="name" label="角色名称">
                <el-input v-model="formLabelAlign.name" placeholder="请输入角色名称" />
            </el-form-item>

            <el-form-item prop="description" label="描述">
                <el-input type="textarea" v-model="formLabelAlign.description" placeholder="请输入描述" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="submitForm(ruleFormRef)">
                    确定
                </el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
            </span>
        </template>
    </Dialog>
</template>

<script setup>
import { ref, reactive,inject } from 'vue'
import { ElMessage } from 'element-plus'
import  Dialog  from '@/components/Dialog/index.vue'

const emit = defineEmits(['onsuccess'])
const sa = inject('sa')
const ruleFormRef = ref(null)

const dialogVisible = ref(false)
const formLabelAlign = reactive({
    id: 0,
    createTime:"",
    creatorId: 0,
    updaterId: 0,
    name: '',
    description: '',
})
const addFormFn = (obj)=> {
    console.log(obj)
    formLabelAlign.id = obj.id
    formLabelAlign.creatorId = obj.creatorId
    formLabelAlign.updaterId = obj.updaterId
    formLabelAlign.name = obj.name
    formLabelAlign.description = obj.description
}

//把数据导出
defineExpose({
    addFormFn,
    dialogVisible,
})
const submitForm = async formEl => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            await sa.post('/role/save',{
                ...formLabelAlign
            })
            ElMessage.success('保存成功！')
            dialogVisible.value = false
            emit('onsuccess')
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}


</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>
