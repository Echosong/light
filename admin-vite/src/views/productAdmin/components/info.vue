<template>
    <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef" style="max-width: 70%">
        <el-form-item :rules="[
            {
                required: true,
                message: '版本号不能为空',
                trigger: 'blur',
            }
        ]" prop="productversion" label="版本号">
            <el-input v-model="formLabelAlign.productversion" placeholder="版本号" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '产品全称不能为空',
                trigger: 'blur',
            }
        ]" prop="productname" label="产品全称">
            <el-input v-model="formLabelAlign.productname" placeholder="产品全称" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '产品简称不能为空',
                trigger: 'blur',
            }
        ]" prop="productshortname" label="产品简称">
            <el-input v-model="formLabelAlign.productshortname" placeholder="产品简称" />
        </el-form-item>

        <el-form-item prop="insproductcode" label="保险公司产品代码">
            <el-input v-model="formLabelAlign.insproductcode" placeholder="保险公司产品代码" />
        </el-form-item>
        <el-form-item :rules="[
            {
                required: true,
                message: '产品代码不能为空',
                trigger: 'blur',
            }
        ]" prop="productcode" label="产品代码">
            <el-input v-model="formLabelAlign.productcode" placeholder="产品代码" />
        </el-form-item>


        <el-form-item :rules="[
            {
                required: true,
                message: '保险公司不能为空',
                trigger: 'blur',
            }
        ]" prop="inscompanyid" label="保险公司">
            <el-select  v-model="formLabelAlign.inscompanyid" placeholder="保险公司">
                <el-option v-for="(item, index) of queryInscompanySimpleLists" :key="index" :label="item.companyname"
                    :value="item.id" />
            </el-select>
        </el-form-item>
        <el-form-item :rules="[
            {
                required: true,
                message: '归属险种不能为空',
                trigger: 'blur',
            }
        ]" prop="instypeid" label="归属险种">
            <el-select  v-model="formLabelAlign.instypeid" placeholder="归属险种">
                <el-option v-for="(item, index) of instypeList" :key="index" :label="item.instypename" :value="item.id" />
            </el-select>
        </el-form-item>
        <el-form-item prop="instypeid" label="录单形式">
            <el-radio-group v-model="formLabelAlign.inputtype">
                <el-radio :label="1" size="large">被保人为个人</el-radio>
                <el-radio :label="2" size="large">被保人为企业</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item prop="supporttype" label="是否个险">
            <el-radio-group v-model="formLabelAlign.supporttype">
                <el-radio :label="1" size="large">是</el-radio>
                <el-radio :label="2" size="large">否</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item prop="tuanxianstatus" label="是否可以新建计划">
            <el-radio-group v-model="formLabelAlign.tuanxianstatus">
                <el-radio :label="2" size="large">否</el-radio>
                <el-radio :label="1" size="large">是</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item :rules="[
            {
                required: true,
                message: '产品分类不能为空',
                trigger: 'blur',
            }
        ]" prop="producttypeid" label="产品分类">
            <el-select  v-model="formLabelAlign.producttypeid" placeholder="产品分类">
                <el-option v-for="(item, index) of queryAllList" :key="index" :label="item.producttypename"
                    :value="item.id" />
            </el-select>
        </el-form-item>


        <el-form-item :rules="[
            {
                required: true,
                message: '保险起期不能为空',
                trigger: 'blur',
            }
        ]" prop="periodformat" label="保险起期">
            <el-select  v-model="formLabelAlign.periodformat" placeholder="保险起期">
                <el-option label="精确到秒" :value="1" />
                <el-option label="精确到天" :value="2" />
            </el-select>
        </el-form-item>
        <el-form-item :rules="[
            {
                required: true,
                message: '产品状态不能为空',
                trigger: 'blur',
            }
        ]" prop="status" label="产品状态">
            <el-select  v-model="formLabelAlign.status" placeholder="产品状态">
                <el-option v-for="(item, index) of g_statusmark.ProductStatus" :key="index" :label="item.keyvalue"
                    :value="item.keycode" />
            </el-select>
        </el-form-item>

        <el-form-item prop="productpictureurl" label="产品图片450*300 ">
            <FileImage ref="fileImageRef" :file="formLabelAlign.productpictureurl"
                @onremove="formLabelAlign.productpictureurl = ''" @onSuccess="formLabelAlign.productpictureurl = $event">
            </FileImage>
        </el-form-item>

        <el-form-item prop="policytype" label="保单类型">
            <el-radio-group v-model="formLabelAlign.policytype">
                <el-radio :label="1" size="large">电子保单</el-radio>
                <el-radio :label="2" size="large">纸质保单</el-radio>
                <el-radio :label="3" size="large">二者选其一</el-radio>
                <el-radio :label="4" size="large">都有</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '限购份数不能为空',
                trigger: 'blur',
            }
        ]" prop="limitednumber" label="限购份数">
            <el-input v-model="formLabelAlign.limitednumber" placeholder="限购份数" />
        </el-form-item>

        <el-form-item  prop="xianzhitype" label="4类%(0表示不限)">
            <el-input type="number" v-model="formLabelAlign.xianzhitype" placeholder="4类比例% 0 不限" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '承保年龄范围不能为空',
                trigger: 'blur',
            }
        ]" prop="insage" label="承保年龄范围">
            <el-input v-model="formLabelAlign.insage" placeholder="承保年龄范围" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '价格区间不能为空',
                trigger: 'blur',
            }
        ]" prop="priceinfo" label="价格区间">
            <el-input v-model="formLabelAlign.priceinfo" placeholder="价格区间" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '适合人群不能为空',
                trigger: 'blur',
            }
        ]" prop="suitablecrowd" label="适合人群">
            <el-input v-model="formLabelAlign.suitablecrowd" placeholder="适合人群" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '保险期限不能为空',
                trigger: 'blur',
            }
        ]" prop="insperiod" label="保险期限">
            <el-input v-model="formLabelAlign.insperiod" placeholder="保险期限" />
        </el-form-item>

        <el-form-item :rules="[
            {
                required: true,
                message: '产品Slogan不能为空',
                trigger: 'blur',
            }
        ]" prop="productslogan" label="产品Slogan">
            <el-input v-model="formLabelAlign.productslogan" placeholder="产品Slogan" />
        </el-form-item>

        <el-form-item prop="verifyurl" label="验证网址">
            <el-input v-model="formLabelAlign.verifyurl" placeholder="验证网址" />
        </el-form-item>

        <el-form-item prop="smssend" label="出保短信">
            <el-radio-group v-model="formLabelAlign.smssend">
                <el-radio :label="0" size="large">保险公司发</el-radio>
                <el-radio :label="1" size="large">平台发</el-radio>
                <el-radio :label="2" size="large">平台代发（费用客户承担）</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item prop="smsinfo" label="短信内容">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.smsinfo" placeholder="短信内容" />
        </el-form-item>

        <el-form-item prop="productfeatures" label="产品特点">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.productfeatures" placeholder="产品特点" />
        </el-form-item>


        <el-form-item prop="insnotice" label="投保须知">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.insnotice" placeholder="投保须知" />
        </el-form-item>


        <el-form-item prop="appointment" label="特别约定">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.appointment" placeholder="特别约定" />
        </el-form-item>

        <el-form-item prop="tixing" label="免责事项">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.tixing" placeholder="免责事项" />
        </el-form-item>


        <el-form-item prop="zhiyeurl" label="职业类型文件">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.zhiyeurl" placeholder="职业类型文件" />
        </el-form-item>

        <el-form-item prop="aboutus" label="关于我们">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.aboutus" placeholder="关于我们" />
        </el-form-item>

        <el-form-item prop="faq" label="常见问题答疑">
            <el-input type="textarea" rows="4" v-model="formLabelAlign.faq" placeholder="常见问题答疑" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)">保存，下一步</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: "info"
}
</script>

<script setup>
import { ElMessage } from 'element-plus'
import FileImage from '@/components/file/fileImage.vue';
import {
    queryInscompanySimpleList,
    queryProducttypeList,
    instypeQueryAllList,
    saveProduct
} from '@/api/api'
import { statusmark } from '@/pinia/modules/statusmark';
import { reactive, ref,computed  } from 'vue'
const emit = defineEmits(["onSuccess"])
const g_statusmark = computed(() => statusmark().g_statusmark);
const ruleFormRef = ref()

const formLabelAlign = reactive({
    productversion: '',
    productname: '',
    productshortname: '',
    insproductcode: '',
    productcode: '',
    inscompanyid: '',
    instypeid: '',
    inputtype: 1,
    supporttype: 1,
    tuanxianstatus: '',
    producttypeid: '',
    periodformat: 1,
    status: "1",
    productpictureurl: '',
    policytype: 1,
    limitednumber: '',
    insage: '',
    priceinfo: '',
    suitablecrowd: '',
    insperiod: '',
    productslogan: '',
    verifyurl: '',
    smssend: 0,
    smsinfo: '',
    productfeatures: '',
    insnotice: '',
    appointment: '',
    tixing: '',
    zhiyeurl: '',
    aboutus: '',
    faq: '',
    xianzhitype: 0
})

const addForm = (obj)=>{
    obj.status = `${obj.status}`
    Object.assign(formLabelAlign,obj)
}
const submitForm = async (formEl) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            if (!formLabelAlign.id) delete formLabelAlign['id']
            let { data } = await saveProduct({
                ...formLabelAlign,
            })
            ElMessage.success('保存成功！')
            emit('onSuccess', data)
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')

        }
    })

}

defineExpose({
    formLabelAlign,
    addForm
})


const queryInscompanySimpleLists = ref([])
let { data: scompanySimpleLists } = await queryInscompanySimpleList({})
queryInscompanySimpleLists.value = scompanySimpleLists

const queryAllList = ref([])
let { data: AllList } = await queryProducttypeList({})
queryAllList.value = AllList


const instypeList = ref([])
let { data: insTypeLists } = await instypeQueryAllList({})
instypeList.value = insTypeLists



</script>