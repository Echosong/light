<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="理赔详情" name="first">
                <el-card v-if="list.orderno" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">订单信息</el-text>
                        </div>
                    </template>
                    <el-descriptions :column="3">
                    <el-descriptions-item v-if="list.orderno" label="订单号">{{ list.orderno }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.polyprice" label="保费">{{ list.polyprice }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.polyno" label="保单号">{{ list.polyno }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.addtime" label="下单时间">{{ list.addtime }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.productName" label="产品名称">{{ list.productName }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.productPlanName" label="产品计划">{{ list.productPlanName
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.polystartdate" label="保单起期">{{ list.polystartdate
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.polyenddate" label="保单止期">{{ list.polyenddate }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.enterpriseNameHolder" label="投保企业名称">{{ list.enterpriseNameHolder
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.enterpriseName" label="出险企业名称">{{ list.enterpriseName
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.insuredname" label="出险人姓名">{{ list.insuredname
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.identityno" label="出险人证件号码">{{ list.identityno
                    }}</el-descriptions-item>
                </el-descriptions>
                </el-card>
               
                
                <el-card v-if="list.accidenttime" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">报案信息</el-text>
                        </div>
                    </template>

                <el-descriptions border :column="3">
                    <el-descriptions-item v-if="list.accidenttime" label="出险时间">{{ list.accidenttime
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.visitinghospital" label="就诊医院">{{ list.visitinghospital
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.contacts" label="联系人">{{ list.contacts }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.contactstel" label="联系电话">{{ list.contactstel }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.visitsameday" label="是否当日就诊">{{ list.visitsameday
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.surgery" label="是否手术或住院">{{ list.surgery }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.moreSerious" label="有骨折或更重伤">{{ list.moreSerious
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.accidentdesc" label="事故描述">{{ list.accidentdesc
                    }}</el-descriptions-item>
                    <el-descriptions-item v-if="list.filesurls" label="事故照片或视频">
                        <el-link style="margin-right: 6px;" v-for="(items, indexs) of list.filesurls.split(';')"
                            :key="indexs" :href="urlMontage(items)" type="primary"
                            target="_blank">{{ splitStr(items) }}</el-link>
                    </el-descriptions-item>
                </el-descriptions>
                </el-card>

                <el-card style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">处理记录</el-text>
                        </div>
                    </template>
                <el-card v-if="backPolyInfoList.length > 0" style="margin-bottom: 10px;" v-for="(item, index) of backPolyInfoList" :key="index" shadow="always">
                    <el-descriptions :column="3">
                        <el-descriptions-item v-if="item.username" label="操作用户">{{ item.username }}</el-descriptions-item>
                        <el-descriptions-item v-if="item.claimsStatusName" label="理赔状态">{{ item.claimsStatusName
                        }}</el-descriptions-item>
                        <el-descriptions-item v-if="item.addtime" label="操作时间">{{ item.addtime }}</el-descriptions-item>
                        <el-descriptions-item v-if="item.claimsmoney" label="理赔估损金额">{{ item.claimsmoney
                        }}</el-descriptions-item>
                        <el-descriptions-item v-if="item.claimspaymoney" label="理赔实付金额">{{ item.claimspaymoney
                        }}</el-descriptions-item>
                        <el-descriptions-item v-if="item.accidentdesc" label="备注">{{ item.accidentdesc
                        }}</el-descriptions-item>
                        <el-descriptions-item label="附件">
                            <el-link style="margin-right: 6px;" v-for="(items, indexs) of item.filesurls.split(';')"
                                :key="indexs" :href="urlMontage(items)" type="primary"
                                target="_blank">{{ splitStr(items) }}</el-link>
                        </el-descriptions-item>
                    </el-descriptions>

                    
                </el-card>
                </el-card>
                <el-card  v-if="list.claimsstatus == 4 ||list.claimsstatus == 5 ||list.claimsstatus == 6 " style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">处理信息</el-text>
                        </div>
                    </template>
                    <el-card :key="index" shadow="always">

                        <el-form label-position="right" label-width="200" :model="formLabelAlign" ref="ruleFormRef"
                            style="max-width: 70%">
                            <el-form-item prop="claimsstatus" :rules="[
                            {
                                required: true,
                                message: '请选择理赔状态',
                                trigger: 'change',
                            }
                        ]" label="理赔状态">
                                <el-select  v-model="formLabelAlign.claimsstatus"
                                    placeholder="理赔状态">
                                    <el-option v-for="(item, index) of g_statusmark['ClaimsStatus']" :key="index" :label="item.keyvalue"
                        :value="item.keycode" />
                                </el-select>
                            </el-form-item>

                            <el-form-item :rules="[
                            {
                                required: true,
                                type: 'number',
                                message: '请填写正确的金额',
                                trigger: 'blur',
                            }
                        ]" v-if="formLabelAlign.claimsstatus == 4" prop="claimspaymoney" label="理赔实付金额">
                                <el-input type="number" v-model.number="formLabelAlign.claimspaymoney" placeholder="理赔实付金额" />
                            </el-form-item>
                            <el-form-item :rules="[
                            {
                                required: true,
                                type: 'number',
                                message: '请填写正确的金额',
                                trigger: 'blur',
                            }
                        ]" v-if="formLabelAlign.claimsstatus == 3"  prop="claimsmoney" label="理赔估损金额">
                                <el-input  type="number" v-model.number="formLabelAlign.claimsmoney" placeholder="理赔估损金额" />
                            </el-form-item>

                            <el-form-item :rules="[
                            {
                                required: true,
                                message: '请填写备注',
                                trigger: 'blur',
                            }
                        ]"  prop="accidentdesc" label="备注">
                                <el-input type="textarea" :rows="6" v-model="formLabelAlign.accidentdesc" placeholder="备注" />
                            </el-form-item>

                            <el-form-item prop="filesurls" label="附件">
                                <File ref="fileRef" :file="formLabelAlign.filesurls" @onremove="formLabelAlign.filesurls = ''"  @onSuccess="formLabelAlign.filesurls = $event"></File>
                            </el-form-item>
                            <el-form-item>
                            <el-button type="primary" @click="submitForm(ruleFormRef)">
                                提交
                            </el-button>
                        </el-form-item>
                        </el-form>

                    </el-card>
                    </el-card>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
export default {
    name: "settlementDet"
}
</script>
<script setup>

import { queryPolyClaims, queryBackPolyInfoClaims, queryOrderByOrderNoNew,saveBackClaims } from '@/api/api';
import { urlMontage } from '@/utils';
import { ref,reactive,computed } from 'vue'
import { useRoute } from 'vue-router';
import { statusmark } from '@/pinia/modules/statusmark';
import File from '@/components/file/file.vue';
import { ElMessage } from 'element-plus';
const g_statusmark = computed(() => statusmark().g_statusmark);

const fileRef = ref()
const activeName = ref('first')
const list = ref({})
const claimsno = useRoute().query.claimsno
const orderno = useRoute().query.orderno
const ruleFormRef = ref()
const formLabelAlign = reactive({
    claimsno,
    username:"yunwoo",
    accidentdesc:"",
    claimspaymoney:"",
    claimsstatus:"",
    claimsmoney:""
})


listFn()
async function listFn() {
    const { data } = await queryPolyClaims({
        claimsno,
        username: 'yunwoo'
    })
    list.value = data
}

function splitStr(str) {
    str = str.split('/')
    return str[str.length - 1]
}

const submitForm = async formEl => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            await saveBackClaims({
                saveData:JSON.stringify(formLabelAlign),
            })
            ElMessage.success('提交成功！')
            
            listFn()
        } else {
            console.info(fields)
            ElMessage.error('请查看您填写的参数是否正确！')
        }
    })
}

queryBackPolyInfoClaimsFn()
const backPolyInfoList = ref({})

/**
 *  backPolyInfoList 处理记录
 */
async function queryBackPolyInfoClaimsFn() {
    const { data } = await queryBackPolyInfoClaims({
        claimsno,
        username: 'yunwoo'
    })
    backPolyInfoList.value = data
}

queryOrderByOrderNoNewFn()
const orderByOrderNoNewList = ref({})
async function queryOrderByOrderNoNewFn() {
    const { data } = await queryOrderByOrderNoNew({
        orderno,
        username: 'yunwoo'
    })
    orderByOrderNoNewList.value = data
}
</script>
<style scoped>.app-container:deep(.el-card__header) {
    background: #e6e7e9;
}</style>