<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="添加用户" name="first">
                <div>
                    <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef"
                        style="max-width: 70%">
                        <el-form-item prop="usertype" label="用户类型">
                            <el-select  :disabled="formLabelAlign.id ? true : false" v-model="formLabelAlign.usertype" placeholder="用户类型">
                                <el-option label="公司" :value="1" />
                                <el-option label="个人" :value="0" />
                            </el-select>
                        </el-form-item>

                        <el-form-item prop="userattribute" label="用户所属">
                            <el-select  :disabled="formLabelAlign.id ? true : false" v-model="formLabelAlign.userattribute" placeholder="用户所属">
                                <el-option label="前台" :value="0" />
                                <el-option label="后台" :value="1" />
                            </el-select>
                        </el-form-item>
                        <el-form-item :rules="[
                            {
                                required: true,
                                min:4,
                                max:30,
                                message: '用户名必须大于4，小于30个字符',
                                trigger: 'blur',
                            },
                            {
                                required: formLabelAlign.id ? false : true, validator: validateUserName, trigger: 'blur'
                            }
                        ]" prop="username" label="用户名">
                            <el-input :disabled="formLabelAlign.id ? true : false" v-model="formLabelAlign.username" placeholder="用户名" />
                        </el-form-item>
                        <el-form-item prop="parentname" label="上级用户名">
                            <el-select  v-model="formLabelAlign.parentname" filterable remote reserve-keyword
                                :remote-method="remoteMethod" :loading="loading">
                                <el-option v-for="item in options" :key="item.value" :label="item.label"
                                    :value="item.value" />
                            </el-select>

                        </el-form-item>
                        <el-form-item prop="displaysub" label="下级用户权限">
                            <el-select  v-model="formLabelAlign.displaysub" placeholder="下级用户权限">
                                <el-option label="是" :value="1" />
                                <el-option label="否" :value="0" />
                            </el-select>

                        </el-form-item>
                        <el-form-item prop="tuanxianstatus" label="可查看产品">
                            <el-select  v-model="formLabelAlign.tuanxianstatus" placeholder="可查看产品">
                                <el-option label="个险产品" :value="1" />
                                <el-option label="团险产品" :value="2" />
                                <el-option label="所有产品" :value="3" />
                            </el-select>
                        </el-form-item>
                        <!-- <el-form-item label="所属产品组">
                            <el-input v-model="formLabelAlign.productGroup" />
                        </el-form-item> -->
                        <el-form-item prop="rate4"  label="4类等级百分比">
                            <el-input v-model="formLabelAlign.rate4" placeholder="4类等级百分比">
                            <template #append>%</template>
                            </el-input>
                        </el-form-item>
                        <el-form-item prop="mobile" :rules="[
                            {
                                required: true, pattern: /^[0-9]{11}$/, message: '手机号为11位填数字', trigger: 'blur'
                            },
                        ]" label="手机号">
                            <el-input v-model.number="formLabelAlign.mobile" placeholder="手机号" />
                        </el-form-item>
                        <el-form-item prop="telephone" :rules="[
                            {
                                pattern: /^([0-9]{3,4}-)?\d+$/,
                                message: '电话格式为  区号-电话号  格式，区号可以不写',
                                trigger: 'blur'
                            },
                        ]" label="电话">
                            <el-input v-model.number="formLabelAlign.telephone" placeholder="电话" />
                        </el-form-item>

                        <el-form-item   label="所在城市">
                            <el-form-item style="margin-right: 20px;" prop="parentCityCode" >
                                <el-select  :disabled="formLabelAlign.id ? true : false" v-model="formLabelAlign.parentCityCode" @change="changeFn(1)" placeholder="省">
                                    <el-option v-for="(item, index) of parentCity" :key="index" :label="item.citytitle"
                                        :value="item.citycode" />
                                </el-select>
                            </el-form-item>
                            <el-form-item prop="cityscode">
                                <el-select  :disabled="formLabelAlign.id ? true : false" v-model="formLabelAlign.cityscode" placeholder="市">
                                    <el-option v-for="(item, index) of citys" :key="index" :label="item.citytitle"
                                        :value="item.citycode" />
                                </el-select>
                            </el-form-item>
                        </el-form-item>

                        <el-form-item prop="addressinfo" label="联系地址">
                            <el-input v-model="formLabelAlign.addressinfo" placeholder="联系地址" />
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
                        <el-form-item prop="qq" label="微信">
                            <el-input v-model="formLabelAlign.qq" placeholder="微信" />
                        </el-form-item>
                        <el-form-item prop="zipcode" :rules="[
                            {
                                pattern: /^[0-9]{6}$/, message: '邮编为6位且只能是数字', trigger: 'blur'
                            },
                        ]" label="邮编">
                            <el-input v-model="formLabelAlign.zipcode" placeholder="邮编" />
                        </el-form-item>
                        <el-form-item prop="userlevelid" :rules="[
                            {
                                required: true, message: '用户级别不能为空', trigger: 'change'
                            },
                        ]" label="用户级别">
                            <el-select  v-model="formLabelAlign.userlevelid" placeholder="用户级别">
                                <el-option v-for="(item,index) of levelList" :key="index" :label="item.levelname" :value="item.id" />
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="status" :rules="[
                            {
                                required: true, message: '状态为空', trigger: 'change'
                            },
                        ]" label="状态">
                            <el-select  v-model="formLabelAlign.status" placeholder="状态">
                                <el-option label="启用" :value="1" />
                                <el-option label="禁止" :value="0" />
                            </el-select>
                        </el-form-item>
                        <el-form-item prop="loginpassword" :rules="[
                            {
                                required: formLabelAlign.id ? false : true, min: 6, max: 16, message: '密码为6~16位', trigger: 'blur'
                            },
                        ]" label="登录密码">
                            <el-input type="password" v-model="formLabelAlign.loginpassword" placeholder="登录密码" />
                        </el-form-item>
                        <el-form-item prop="confirmPassword" :rules="[
                            {
                                required: formLabelAlign.id ? false : true, validator: validatePass, trigger: 'blur'
                            },
                        ]" label="确认密码">
                            <el-input type="password" v-model="formLabelAlign.confirmPassword" placeholder="确认密码" />
                        </el-form-item>
                        <el-form-item prop="operatepassword" :rules="[
                            {
                                required: formLabelAlign.id ? false : true, min: 6, max: 16, message: '支付密码为6~16位', trigger: 'blur'
                            },
                        ]" label="支付密码">
                            <el-input type="password" v-model="formLabelAlign.operatepassword" placeholder="支付密码" />
                        </el-form-item>
                        <el-form-item prop="confirmOperatePassword" :rules="[
                            {
                                required: formLabelAlign.id ? false : true, validator: validatePassPay, message: '密码为6~16位', trigger: 'blur'
                            },
                        ]" label="确认支付密码">
                            <el-input type="password" v-model="formLabelAlign.confirmOperatePassword"
                                placeholder="确认支付密码" />
                        </el-form-item>

                        <el-form-item prop="secretkey" label="接口Key值">
                            <el-input v-model="formLabelAlign.secretkey" placeholder="接口Key值" />
                        </el-form-item>
                        <el-form-item prop="remark" label="备注">
                            <el-input v-model="formLabelAlign.remark" placeholder="备注" />
                        </el-form-item>
                        <el-form-item prop="notice" label="私信">
                            <el-input v-model="formLabelAlign.notice" placeholder="私信" />
                        </el-form-item>
                        <div v-if="formLabelAlign.usertype == 1">
                            <el-form-item prop="userCompanyDetails.companyname" label="公司名称">
                                <el-input :disabled="formLabelAlign.id ? true : false" :rules="[
                                    {
                                        require: true, message: '公司名称不能为空', trigger: 'blur'
                                    },
                                ]" v-model="formLabelAlign.userCompanyDetails.companyname" placeholder="公司名称" />
                            </el-form-item>
                            <el-form-item  label="所在城市">
                                <el-form-item style="margin-right: 20px;" prop="userCompanyDetails.parentCityCode" >
                                    <el-select  v-model="formLabelAlign.userCompanyDetails.parentCityCode"
                                        @change="changeFn(2)" placeholder="省">
                                        <el-option v-for="(item, index) of parentCity" :key="index" :label="item.citytitle"
                                            :value="item.citycode" />
                                    </el-select>
                                </el-form-item>
                                <el-form-item prop="userCompanyDetails.cityscode" >
                                    <el-select  v-model="formLabelAlign.userCompanyDetails.cityscode" placeholder="市">
                                        <el-option v-for="(item, index) of companyCitys" :key="index" :label="item.citytitle"
                                            :value="item.citycode" />
                                    </el-select>
                                </el-form-item>
                            </el-form-item>

                            <el-form-item prop="userCompanyDetails.contactperson"  :rules="[
                                {
                                    required: true, message: '联系人不能为空', trigger: 'blur'
                                },
                            ]" label="联系人">
                                <el-input v-model="formLabelAlign.userCompanyDetails.contactperson" placeholder="联系人" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.contactmobile"  :rules="[
                                {
                                    required: true, pattern: /^[0-9]{11}$/, message: '手机号为11位填数字', trigger: 'blur'
                                },
                            ]" label="手机号">
                                <el-input v-model.number="formLabelAlign.userCompanyDetails.contactmobile"
                                    placeholder="手机号" />
                            </el-form-item>

                            <el-form-item prop="userCompanyDetails.contacttelephone" :rules="[
                                {
                                    pattern: /^([0-9]{3,4}-)?\d+$/,
                                    message: '电话格式为  区号-电话号  格式，区号可以不写',
                                    trigger: 'blur'
                                },
                            ]" label="联系电话">
                                <el-input v-model="formLabelAlign.userCompanyDetails.contacttelephone" placeholder="联系电话" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.Fax" :rules="[
                                {
                                    pattern: /^([0-9]{3,4}-)?\d+$/, message: '传真格式为  区号-电话号  格式，区号可以不写', trigger: 'blur'
                                },
                            ]" label="Fax">
                                <el-input v-model="formLabelAlign.userCompanyDetails.fax" placeholder="Fax" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.email" :rules="[
                                {
                                    type: 'email',
                                    message: '邮箱格式不正确',
                                    trigger: 'blur'
                                },
                            ]" label="email">
                                <el-input v-model="formLabelAlign.userCompanyDetails.email" placeholder="email" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.qq" label="微信">
                                <el-input v-model="formLabelAlign.userCompanyDetails.qq" placeholder="微信" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.zipcode" :rules="[
                                {
                                    pattern: /^[0-9]{6}$/, message: '邮编为6位且只能是数字', trigger: 'blur'
                                },
                            ]" label="邮编">
                                <el-input v-model="formLabelAlign.userCompanyDetails.zipcode" placeholder="邮编" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.website" label="网站及地址">
                                <el-input v-model="formLabelAlign.userCompanyDetails.website" placeholder="网站及地址" />
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.companytype" :rules="[
                                {
                                    required: true,
                                    message: '公司类型不能为空',
                                    trigger: 'change',
                                },
                            ]" label="公司类型">
                                <el-select  v-model="formLabelAlign.userCompanyDetails.companytype" placeholder="">
                                    <el-option v-for="(item, index) of g_statusmark['CompanyType']" :key="index"
                                        :label="item.keyvalue" :value="Number(item.keycode)" />
                                </el-select>
                            </el-form-item>
                            <el-form-item prop="userCompanyDetails.companyinfo" label="公司简介">
                                <el-input type="textarea" :rows="5" v-model="formLabelAlign.userCompanyDetails.companyinfo"
                                    placeholder="公司简介" />
                            </el-form-item>
                        </div>
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
import { queryCitysList,queryUserBylikename,saveUsers,queryUserLevelList,queryUsersByid,checkUserName } from '@/api/api';
import useCloseTag from '@/hooks/useCloseTag'
import { statusmark } from '@/pinia/modules/statusmark'
import { ElMessage,ElMessageBox  } from 'element-plus'
import { reactive, ref,watch,computed  } from 'vue'
import { useRoute } from 'vue-router';
const activeName = ref('first')
const ruleFormRef = ref()
const { closeTag } = useCloseTag()
const g_statusmark = computed(() => statusmark().g_statusmark);


// watch(formLabelAlign.parentCityCode, (count, prevCount) => {
//     console.log(count)
//     console.log(prevCount)
// })

// 提供一个 getter 函数

const formLabelAlign = reactive({
    id:0,
    usersProductGroupList: [
        // {
        //   id: '',
        //   productgroupid: '32',
        // },
    ],
    rate4:'',
    userCompanyDetails: {
        companyname: '',
        parentCityCode: '',
        cityscode: '',
        contactperson: '',
        contactmobile: '',
        contacttelephone: '',
        addressinfo: '',
        zipcode: '',
        qq: '',
        fax: '',
         email: '',
        website: '',
        companytype: '',
        companyinfo: '',
    },
    displaysub:'',
    userattribute:1,
    usertype: 1,
    username: '',
    parentname: '',
    tuanxianstatus: 1,
    mobile: '',
    telephone: '',
    parentCityCode: '',
    cityscode: '',
    addressinfo: '',
    email: '',
    qq: '',
    zipcode: '',
    userlevelid: '',
    status: 1,
    loginpassword: '',
    confirmPassword: '',
    operatepassword: '',
    confirmOperatePassword: '',
    secretkey: '',
    remark: '',
    notice:""
})

watch(
  () => formLabelAlign.parentCityCode,
  (code) => {
   
    queryCitysListcitysFn(code, 1)
  }
)

watch(
  () => formLabelAlign.userCompanyDetails?.parentCityCode,
  (code) => {
    queryCitysListcitysFn(code, 2)
  }
)

const loading = ref(false)
const options = ref([])
const remoteMethod = async (query) => {
  if (query) {
    loading.value = true

    let {data} = await queryUserBylikename({
        name:query
    })
    loading.value = false
    options.value = data.dataList.map(item=>{
        return {
            label:item.username,
            value:item.username
        }
    })

  } else {
    options.value = []
  }
}

const validatePass = (rule, value, callback) => {
    if ((formLabelAlign.loginpassword || value) && value !== formLabelAlign.loginpassword) {
        callback(new Error("与登录密码不一样!"))
    } else {
        callback()
    }
}

const validatePassPay = (rule, value, callback) => {
    if ((formLabelAlign.operatepassword || value) && value !== formLabelAlign.loginpassword) {
        callback(new Error("与支付密码不一样!"))
    } else {
        callback()
    }
}

const validateUserName = async (rule, value, callback) => {
        if(formLabelAlign.id){
            callback()
            return
        }

        let {data} =  await checkUserName({
            username:value
        })
       if(!data){
        callback()
       }else{
        callback(new Error("用户名已存在"))
       }
}

const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid || formLabelAlign.id) {
            if(!formLabelAlign.id) delete formLabelAlign['id']
          delete formLabelAlign['userCompany']
          await saveUsers({
            ...formLabelAlign
          })
          ElMessage.success('保存成功！')
        } else {
            console.log(555, fields)
            ElMessage.error('请查看您填写的参数是否正确！')

        }
    })

}

const parentCity = ref([])
const citys = ref([])
const companyCitys = ref([])
queryCitysListFn(1)
async function queryCitysListFn(type) {
    let { data } = await queryCitysList({
        citylevel: 1
    })
    parentCity.value = data
}

async function queryCitysListcitysFn(parentcode, type) {
    let { data } = await queryCitysList({
        parentcode
    })
    if (type == 1) {
        citys.value = data
    } else {
        companyCitys.value = data
    }
}

const changeFn = (type) => {
    if (type == 1) {
        formLabelAlign.cityscode = ''
    } else {
        formLabelAlign.userCompanyDetails.cityscode = ''
    }
    queryCitysListcitysFn(type == 1 ? formLabelAlign.parentCityCode : formLabelAlign.userCompanyDetails.parentCityCode, type)
}

const levelList = ref([])
let {data} = await queryUserLevelList({})
levelList.value = data


if(useRoute().query.id){
    formLabelAlign.id = useRoute().query.id
    queryUsersByidFn()
}

async function queryUsersByidFn(){
   let {data:list} = await queryUsersByid({
        id:formLabelAlign.id,
    })
    Object.assign(formLabelAlign,list)
    formLabelAlign.userCompanyDetails = list.userCompany
}
</script>

<style></style>
