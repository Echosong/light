<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="添加用户" name="first">
                <div>
                    <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef"
                        style="max-width: 70%">

                        <el-form-item :rules="[
                            {
                                required: true,
                                message: '请输入标题',
                                trigger: 'blur',
                            },
                        ]" prop="title" label="标题">
                            <el-input v-model="formLabelAlign.title" placeholder="标题" />
                        </el-form-item>


                        <el-form-item  prop="content" label="详情">
                            <Tinymce ref="TinymceRef" v-model="formLabelAlign.content"></Tinymce>
                        </el-form-item>
                  
                        <el-form-item>
                            <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
                            <el-button @click="closeTag()">返回</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
export default {
  name: "addArticle"
}
</script>
<script setup>
import {articleSave,articlefind } from '@/api/api';
import Tinymce from '@/components/Tinymce/Tinymce.vue';
import useCloseTag from '@/hooks/useCloseTag'
import { ElMessage,ElMessageBox  } from 'element-plus'
import { reactive, ref  } from 'vue'
import { useRoute } from 'vue-router';
const TinymceRef = ref()
const activeName = ref('first')
const ruleFormRef = ref()
const { closeTag } = useCloseTag()

const formLabelAlign = reactive({
    id:0,
    title:"",
    content:""
})

const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
          await articleSave({
           ...formLabelAlign,
           content:TinymceRef.value.getContent()
          })
          ElMessage.success('保存成功！')
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}
if(useRoute().query.id){
    formLabelAlign.id = useRoute().query.id
    listFn()
}
async function listFn(){
   let {data} = await articlefind({
        id:formLabelAlign.id,
    })
    formLabelAlign.title = data.title
    formLabelAlign.content = data.content
}
</script>

<style></style>
