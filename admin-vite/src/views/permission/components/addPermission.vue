<template>
    <Dialog v-model="dialogVisible" :title="title" maxHeight="500px">
        <el-form label-position="right" label-width="100" :inline="true" ref="ruleFormRef" :model="formLabelAlign"
            class="demo-form-inline">
            <el-form-item :rules="[
                {
                    required: true,
                    message: '菜单名称不能为空',
                    trigger: 'blur',
                },
            ]" prop="name" label="菜单名称">
                <el-input v-model="formLabelAlign.name" placeholder="请输入菜单名称" />
            </el-form-item>
            <el-form-item  prop="url" label="外部url">
                <el-input v-model="formLabelAlign.url" placeholder="请输入外部url" />
            </el-form-item>

            <el-form-item prop="type" label="权限等级">
                <el-input v-model.number="formLabelAlign.type" placeholder="按钮权限填2，其他为1" />
            </el-form-item>

            <el-form-item :rules="[
                {
                    required: true,
                    message: '权限码不能为空',
                    trigger: 'blur',
                },
            ]" prop="perms" label="权限码">
                <el-input v-model="formLabelAlign.perms" placeholder="请输入权限码" />
            </el-form-item>

          
            <el-form-item prop="icon" label="icon">
                <el-input v-model="formLabelAlign.icon" placeholder="请输入ElementIcon" />
            </el-form-item>
            <el-form-item prop="description" label="描述">
                <el-input v-model="formLabelAlign.description" placeholder="请输入描述" />
            </el-form-item>
            <el-form-item prop="sort" label="排序">
                <el-input v-model.number="formLabelAlign.sort" placeholder="请输入序号" />
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
import { ref, reactive, inject } from 'vue'
import { ElMessage } from 'element-plus'
import { parseTime } from '@/utils'
import Dialog from "@/components/Dialog/index.vue";

const emit = defineEmits(['onsuccess'])
const sa = inject('sa')
const ruleFormRef = ref(null)

const dialogVisible = ref(false)
const formLabelAlign = reactive({
    id: 0,
    name: '',
    description: '',
    url: '',
    perms: '',
    parentId: 0,
    type: 1,
    icon: '',
    sort:''
})
const title = ref('新增权限')
const addFormFn = (obj,editObj) => {
    formLabelAlign.name = obj.name
    formLabelAlign.description = obj.description
    formLabelAlign.url = obj.url
    formLabelAlign.perms = obj.perms
    formLabelAlign.type = obj.type
    formLabelAlign.icon = obj.icon
    formLabelAlign.sort = obj.sort
    if(editObj.id && editObj.parentId){
        formLabelAlign.id = editObj.id
        formLabelAlign.parentId = editObj.parentId
        title.value = '修改子级权限'
    }
    if(!editObj.id && editObj.parentId){
        formLabelAlign.id = editObj.id
        formLabelAlign.parentId = editObj.parentId
        title.value = '新增子级权限'
    }


    if(!editObj.id && !editObj.parentId){
        formLabelAlign.id = editObj.id
        formLabelAlign.parentId = editObj.parentId
        title.value = '新增权限'
    }

    if(editObj.id && !editObj.parentId){
        formLabelAlign.id = editObj.id
        formLabelAlign.parentId = editObj.parentId
        title.value = '修改权限'
    }
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
            formLabelAlign.type = formLabelAlign.type || 1
            await sa.post('/permission/save', {
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
