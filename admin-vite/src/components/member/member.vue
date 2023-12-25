<template>
    <el-dialog append-to-body v-model="dialogVisible" title="个人中心" width="30%">
        <div>
            <el-form label-position="right" label-width="100" :model="formLabelAlign" ref="ruleFormRef"
                style="max-width: 70%">

                <el-form-item prop="username" label="用户名">
                    <el-input disabled v-model="formLabelAlign.username" placeholder="用户名" />
                </el-form-item>

                <el-form-item prop="name" label="姓名">
                    <el-input v-model="formLabelAlign.name" placeholder="姓名" />
                </el-form-item>
                <el-form-item prop="position"  label="角色">
                    <el-input disabled :value="jue.join(',')" placeholder="角色" />
                </el-form-item>
                <el-form-item prop="mobile" :rules="[
                    {
                        required: true, pattern: /^[0-9]{11}$/, message: '手机号为11位数字', trigger: 'blur'
                    },
                ]" label="电话">
                    <el-input v-model.number="formLabelAlign.mobile" placeholder="电话" />
                </el-form-item>

                <el-form-item prop="email" :rules="[
                    {
                        type: 'email',
                        message: '邮箱格式不正确',
                        trigger: 'blur'
                    },
                ]" label="Email">
                    <el-input v-model="formLabelAlign.email" placeholder="Email" />
                </el-form-item>

                <el-form-item prop="qq"  label="微信">
                    <el-input v-model="formLabelAlign.qq" placeholder="微信" />
                </el-form-item>

                <el-form-item prop="position"  label="职务">
                    <el-input v-model="formLabelAlign.position" placeholder="职务" />
                </el-form-item>

                <el-form-item prop="companyname" label="公司名称">
                    <el-input :rules="[
                        {
                            require: true, message: '公司名称不能为空', trigger: 'blur'
                        },
                    ]" v-model="formLabelAlign.companyname" placeholder="公司名称" />
                </el-form-item>


                <el-form-item prop="oldPassword" :rules="[
                    {
                        required: formLabelAlign.id ? false : true, min: 6, max: 16, message: '密码为6~16位', trigger: 'blur'
                    },
                ]" label="原始密码">
                    <el-input type="password" v-model="formLabelAlign.oldPassword" placeholder="原始密码" />
                </el-form-item>

                <el-form-item prop="password" :rules="[
                    {
                        required: formLabelAlign.id ? false : true, min: 6, max: 16, message: '密码为6~16位', trigger: 'blur'
                    },
                ]" label="新密码">
                    <el-input type="password" v-model="formLabelAlign.password" placeholder="新密码" />
                </el-form-item>

                <el-form-item prop="rePassword" :rules="[
                    {
                        required: formLabelAlign.id ? false : true, validator: validatePass, trigger: 'blur'
                    },
                ]" label="确认密码">
                    <el-input type="password" v-model="formLabelAlign.rePassword" placeholder="确认密码" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
                    <el-button @click="dialogVisible = false">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-dialog>
</template>
<script>
export default {
    name: "userAdd"
}
</script>
<script setup>
import { updatePassword,listByUser } from '@/api/api';
import { useAccount } from '@/pinia/modules/account';

// import useCloseTag from '@/hooks/useCloseTag'
// import { statusmark } from '@/pinia/modules/statusmark'
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'



// const { g_statusmark } = statusmark()
const ruleFormRef = ref()
// const { closeTag } = useCloseTag()

const dialogVisible = ref(false)
// watch(formLabelAlign.parentCityCode, (count, prevCount) => {
//     console.log(count)
//     console.log(prevCount)
// })

// 提供一个 getter 函数

const formLabelAlign = reactive({
    id: 0,
    companyname: "",
    username: '',
    mobile: '',
    password: '',
    rePassword: '',
    qq:"",

    oldPassword: '',
})

async function addForm(obj) {
    const { userinfo } = useAccount()
    formLabelAlign.id = userinfo.id
    formLabelAlign.username = userinfo.username
    formLabelAlign.mobile = userinfo.mobile
    formLabelAlign.companyname = userinfo.companyName
    listByUserFn()
}
 const jue = ref([])
async function listByUserFn(){
  let {data} =  await listByUser({id:formLabelAlign.id})
  jue.value = data.map(item=> item.name)
}
defineExpose({
    dialogVisible,
    addForm
})


const validatePass = (rule, value, callback) => {
    if ((formLabelAlign.password || value) && value !== formLabelAlign.password) {
        callback(new Error("与登录密码不一样!"))
    } else {
        callback()
    }
}

// const validatePassPay = (rule, value, callback) => {
//     if ((formLabelAlign.operatepassword || value) && value !== formLabelAlign.loginpassword) {
//         callback(new Error("与支付密码不一样!"))
//     } else {
//         callback()
//     }
// }

const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            await updatePassword({
                ...formLabelAlign
            })
            dialogVisible.value = false
            const { getUserinfo } = useAccount()
            await getUserinfo()
            ElMessage.success('保存成功！')

        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')

        }
    })

}

// const parentCity = ref([])
// const citys = ref([])
// const companyCitys = ref([])
// queryCitysListFn(1)
// async function queryCitysListFn(type) {
//     let { data } = await queryCitysList({
//         citylevel: 1
//     })
//     parentCity.value = data
// }

// async function queryCitysListcitysFn(parentcode, type) {
//     let { data } = await queryCitysList({
//         parentcode
//     })
//     if (type == 1) {
//         citys.value = data
//     } else {
//         companyCitys.value = data
//     }
// }

// const changeFn = (type) => {
//     if (type == 1) {
//         formLabelAlign.cityscode = ''
//     } else {
//         formLabelAlign.userCompanyDetails.cityscode = ''
//     }
//     queryCitysListcitysFn(type == 1 ? formLabelAlign.parentCityCode : formLabelAlign.userCompanyDetails.parentCityCode, type)
// }

// const levelList = ref([])
// let { data } = await queryUserLevelList({})
// levelList.value = data


// if (useRoute().query.id) {
//     formLabelAlign.id = useRoute().query.id
//     queryUsersByidFn()
// }

// async function queryUsersByidFn() {
//     let { data: list } = await queryUsersByid({
//         id: formLabelAlign.id,
//     })
//     Object.assign(formLabelAlign, list)
//     formLabelAlign.userCompanyDetails = list.userCompany
// }
</script>

<style></style>
