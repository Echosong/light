<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="添加用户" name="first">
                <div>
                    <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef"
                        style="max-width: 70%">
                        <el-form-item prop="username" label="用户名">
                            <el-input disabled v-model="formLabelAlign.username" placeholder="用户名" />
                        </el-form-item>
                        <el-form-item prop="usertype" label="代扣代充">
                            <el-select  v-model="formLabelAlign.financialtype" placeholder="用户类型">
                                <el-option label="代充" :value="1" />
                                <el-option label="代扣" :value="2" />
                            </el-select>
                        </el-form-item>

                        <el-form-item :rules="[
                            {
                                required: true, message: '金额为必填项', trigger: 'blur'
                            },
                        ]" prop="amout" label="金额">
                            <el-input type="number" v-model="formLabelAlign.amout" placeholder="金额" />
                        </el-form-item>

                        <el-form-item prop="remark" label="备注">
                            <el-input type="textarea" :rows="5" v-model="formLabelAlign.remark" placeholder="备注" />
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
    name: "userAdd"
}
</script>
<script setup>
import { queryUsersByid, rechargeCapitalflow } from '@/api/api';
import useCloseTag from '@/hooks/useCloseTag'
import { ElMessage, ElMessageBox } from 'element-plus'
import { reactive, ref } from 'vue'
import { useRoute } from 'vue-router';
const activeName = ref('first')
const ruleFormRef = ref()
const { closeTag } = useCloseTag()

const formLabelAlign = reactive({
    id: 0,
    username: '',
    financialtype: 1,
    amout: '',
    remark: ''
})

const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            delete formLabelAlign['id']
            await rechargeCapitalflow({
                ...formLabelAlign
            })
            ElMessage.success('保存成功！')
            setTimeout(()=>{
                closeTag.value()
            },1000)

        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')

        }
    })

}


if (useRoute().query.id) {
    formLabelAlign.id = useRoute().query.id
    queryUsersByidFn()
}

async function queryUsersByidFn() {
    let { data: list } = await queryUsersByid({
        id: formLabelAlign.id,
    })
    formLabelAlign.username = list.username
}
</script>

<style></style>
