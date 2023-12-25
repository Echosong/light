<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="订单详情" name="first">
                <el-card v-if="list.polyInfoList && list.polyInfoList.length > 0" style="margin-top: 20px"
                         class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">订单号:{{ list.orderno || '' }}</el-text>
                        </div>
                    </template>
                    <el-descriptions :column="3">
                        <el-descriptions-item label="产品:">
                            {{ list.productName }}
                        </el-descriptions-item>
                        <el-descriptions-item label="保险公司:">{{ list.companyName }}</el-descriptions-item>
                        <el-descriptions-item label="用户名:">{{ list.username }}</el-descriptions-item>
                        <el-descriptions-item label="下单时间:">{{ parseTime(list.addtime) }}</el-descriptions-item>
                        <el-descriptions-item v-if="list.paytime" label="支付成功时间:">{{
                                list.paytime
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="总金额:">{{ list.amount }}</el-descriptions-item>
                        <el-descriptions-item label="实际应支付金额:">{{ list.payamount }}</el-descriptions-item>
                        <el-descriptions-item label="订单状态:">
                            <el-text class="mx-1" type="danger">{{ list.orderStatusName }}</el-text>
                        </el-descriptions-item>

                        <el-descriptions-item label="外部订单号:">{{ list.outorderno }}</el-descriptions-item>
                        <el-descriptions-item label="人数:">{{ list.polycount }}</el-descriptions-item>
                        <el-descriptions-item label="投保人:">{{ list.holderTypeName }}</el-descriptions-item>
                        <el-descriptions-item label="订单来源:">{{ list.orderSourceName }}</el-descriptions-item>
                        <el-descriptions-item label="核保单号:">{{ list.extendinfo1 }}</el-descriptions-item>
                        <el-descriptions-item label="核保时间:">{{
                                parseTime(list.underwritingtime)
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="核保信息:">
                            <el-text class="mx-1" type="danger">{{
                                    list.underwritingdesc
                                }}
                            </el-text>
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>


                <el-card v-if="list.policyholder" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">投保人</el-text>
                        </div>
                    </template>
                    <el-descriptions :column="3">
                        <el-descriptions-item label="投保人姓名:">{{
                                list.policyholder.holdername
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="证件:">{{
                                list.policyholder.certificateTypeName
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="证件号:">{{ list.policyholder.identityno }}</el-descriptions-item>
                        <el-descriptions-item label="出生日期:">
                            {{ parseTime(new Date(list.policyholder.birthday, '{y}-{m}-{d}')) }}
                        </el-descriptions-item>


                        <el-descriptions-item label="性别:">{{ list.policyholder.sexTypeName }}</el-descriptions-item>
                        <el-descriptions-item label="手机号:">{{ list.policyholder.telephone }}</el-descriptions-item>
                        <el-descriptions-item label="职业:">{{
                                list.policyholder.occupationinfo
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="详细联系地址:">{{
                                list.policyholder.addressinfo
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="邮编:">{{ list.policyholder.zipcode }}</el-descriptions-item>
                    </el-descriptions>
                </el-card>


                <el-card v-if="list.policyEnterpriseHolder" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">企业投保人</el-text>
                        </div>
                    </template>
                    <el-descriptions :column="3">
                        <el-descriptions-item label="企业名称:">
                            {{ list.policyEnterpriseHolder.enterprisename }}
                        </el-descriptions-item>
                        <el-descriptions-item label="组织机构代码:">{{
                                list.policyEnterpriseHolder.organizationcode
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="企业地址:">{{
                                list.policyEnterpriseHolder.businessaddressinfo
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="联系人:">{{
                                list.policyEnterpriseHolder.contactperson
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="联系电话:">{{
                                list.policyEnterpriseHolder.contacttel
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.policyEnterpriseHolder.businessmobile" label="联系电话:">{{
                                list.policyEnterpriseHolder.businessmobile
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="邮箱:">{{
                                list.policyEnterpriseHolder.email
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.policyEnterpriseHolder.taxTypeName" label="纳税人类型:">{{
                                list.policyEnterpriseHolder.taxTypeName
                            }}
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>

                <el-card v-if="list.policyEnterpriseInfoList" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">企业被保人</el-text>
                        </div>
                    </template>
                    <el-descriptions :column="3">
                        <el-descriptions-item label="企业名称:">
                            {{ list.policyEnterpriseInfoList[0].enterprisename }}
                        </el-descriptions-item>
                        <el-descriptions-item label="组织机构代码:">{{
                                list.policyEnterpriseInfoList[0].organizationcode
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="企业地址:">{{
                                list.policyEnterpriseInfoList[0].businessaddressinfo
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="联系人:">{{
                                list.policyEnterpriseInfoList[0].contactperson
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="联系电话:">{{
                                list.policyEnterpriseInfoList[0].contacttel
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.policyEnterpriseInfoList[0].businessmobile" label="联系电话:">
                            {{
                                list.policyEnterpriseInfoList[0].businessmobile
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="邮箱:">{{
                                list.policyEnterpriseInfoList[0].email
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.policyEnterpriseInfoList[0].taxTypeName" label="纳税人类型:">{{
                                list.policyEnterpriseInfoList[0].taxTypeName
                            }}
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>


                <el-card v-if="list.polyInvoiceList" style="margin-top: 20px" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">发票信息</el-text>
                        </div>
                    </template>
                    <el-descriptions v-if="list.polyInvoiceList" :column="3">
                        <el-descriptions-item label="发票抬头:">
                            {{ list.polyInvoiceList.invoicetitle }}
                        </el-descriptions-item>
                        <el-descriptions-item label="纳税人识别号:">{{
                                list.polyInvoiceList.uservoiceno
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="发票类型:">{{
                                list.polyInvoiceList.invoicetypeName
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="发票金额:">{{
                                list.polyInvoiceList.invoiceamount
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="接收邮箱:">{{ list.polyInvoiceList.email }}</el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.businessmobile" label="地址:">{{
                                list.polyInvoiceList.address
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item label="电话:">{{
                                list.policyEnterpriseHolder.telephone
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.bankname" label="开户行:">{{
                                list.polyInvoiceList.bankname
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.bank" label="账号:">{{
                                list.polyInvoiceList.bank
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.shoujianname" label="收件人姓名:">{{
                                list.polyInvoiceList.shoujianname
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.shoujianmobile" label="收件人手机:">{{
                                list.polyInvoiceList.shoujianmobile
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.shoujianaddress" label="收件人地址:">{{
                                list.polyInvoiceList.shoujianaddress
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.statusName" label="申请状态:">{{
                                list.polyInvoiceList.statusName
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.addTime" label="申请时间:">{{
                                list.polyInvoiceList.statusName
                            }}
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.invoiceurl" label="下载发票:">
                            <el-link :href="list.polyInvoiceList.invoiceurl" target="_blank">下载</el-link>
                        </el-descriptions-item>
                        <el-descriptions-item v-if="list.polyInvoiceList.returnmsg" label="返回信息:">{{
                                list.polyInvoiceList.returnmsg
                            }}
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>


                <el-card v-if="list.polyInfoList && list.polyInfoList.length > 0" style="margin-top: 20px"
                         class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text>保单信息表</el-text>
                        </div>
                    </template>
                    <el-table :data="list.polyInfoList" stripe style="width: 100%">
                        <el-table-column width="60" prop="polyno" label="序号">
                            <template #default="scope">
                                {{ scope.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column width="120" prop="insuredname" label="姓名"/>
                        <el-table-column width="120" prop="certificateTypeName" label="证件类型"/>
                        <el-table-column width="170" prop="identityno" label="证件号"/>
                        <el-table-column width="180" prop="birthday" label="出生日期">
                            <template #default="scope">
                                {{ parseTime(new Date(scope.row.birthday).getTime(), '{y}-{m}-{d}') }}
                            </template>
                        </el-table-column>
                        <el-table-column width="60" prop="sexTypeName" label="性别"/>
                        <el-table-column width="60" prop="sexTypeName" label="年龄">
                            <template #default="scope">{{ calculateAge(scope.row.birthday) }}</template>
                        </el-table-column>
                        <el-table-column prop="occupationinfo" label="工种"/>
                        <el-table-column width="100" prop="orderStatusName" label="保费">
                            <template #default="scope">
                                {{ scope.row.polyprice }}{{
                                    scope.row.reduceadd == 1 ?
                                        '(加人)'
                                        : scope.row.reduceadd == -1 ? '(减人)' : ''
                                }}
                            </template>
                        </el-table-column>
                        <el-table-column width="180" prop="polystartdate" label="生效日">
                            <template #default="scope">
                                {{ parseTime(new Date(scope.row.polystartdate).getTime()) }}
                            </template>
                        </el-table-column>
                        <el-table-column width="180" prop="polyenddate" label="终止日">
                            <template #default="scope">
                                {{ parseTime(new Date(scope.row.polyenddate).getTime()) }}
                            </template>
                        </el-table-column>
                        <el-table-column width="100" fixed="right" label="操作">
                            <template #default="scope">
                                <el-link :href="scope.row.polyurl" target="_blank"
                                         v-if="scope.row.polyStatusName == '出保成功' || scope.row.polyStatusName == '撤保失败'"
                                         style="margin-right: 8px;" type="primary">保单下载
                                </el-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
                <el-card v-if="list.ordertravelinfo && list.ordertravelinfo.length > 0" style="margin-top: 20px"
                         class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">其他信息:</el-text>
                        </div>
                    </template>
                    <el-descriptions style="margin-bottom: 20px;" :column="3">
                        <el-descriptions-item v-for="(item, index) of list.ordertravelinfo" :key="index"
                                              :label="item.fieldName">{{ item.fieldsvalue }}
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>


                <el-card v-if="list.capitalflowList && list.capitalflowList.length > 0" style="margin-top: 20px"
                         class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">支付详情</el-text>
                        </div>
                    </template>
                    <el-table :data="list.capitalflowList"
                              style="width: 100%">
                        <el-table-column prop="serialno" label="流水号"/>
                        <el-table-column prop="batchno" label="交易号"/>
                        <el-table-column prop="financialtypeName" label="财务类型"/>
                        <el-table-column prop="tradeName" label="交易类型"/>
                        <el-table-column prop="amout" label="收入支出">
                            <template #default="scope">
                                <span style="color: #fd5315;"> ￥{{
                                        scope.row.financialtype == 1 ? '+' :
                                            '-'
                                    }}{{ scope.row.amout }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="tradetime" label="交易时间"/>
                        <el-table-column prop="remark " label="备注"/>
                    </el-table>
                </el-card>


                <el-card style="margin-top: 20px;" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">请求保险公司接口详情</el-text>
                        </div>
                    </template>
                    <el-table :data="urlLists" style="width: 100%">
                        <el-table-column prop="requestkey" label="请求key"/>
                        <el-table-column prop="requestsecondkey" label="请求Secondkey"/>
                        <el-table-column width="80" prop="requesttype" label="请求类型"/>
                        <el-table-column width="200" prop="requestinfo" label="请求信息">
                            <template #default="scope">
                                <el-input type="textarea" rows="5" v-model="scope.row.requestinfo"></el-input>
                                <el-button
                                    v-if="(scope.row.requestinfo).includes('standard.insureNoPay') || (scope.row.requestinfo).includes('endorse.endorseApplyCal')"
                                    @click="recall(scope.row.requestinfo)" type="primary" text>重复调用
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column prop="requesttime" label="请求时间"></el-table-column>
                        <el-table-column prop="responsekey" label="响应key"/>
                        <el-table-column width="200" prop="responseinfo" label="返回信息">
                            <template #default="scope">
                                <el-input type="textarea" rows="5" v-model="scope.row.responseinfo"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="responsetime" label="返回时间"/>
                        <el-table-column width="90" align="center">
                            <template #header>
                                <el-button @click="queryRequestInsFn" type="primary" text>查询</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </el-card>

                <el-card style="margin-top: 20px;" class="box-card">
                    <template #header>
                        <div style="width: 100%" class="flexBet">
                            <el-text tag="b">用户请求接口详情</el-text>
                        </div>
                    </template>
                    <el-table :data="urlListsUser" style="width: 100%">
                        <el-table-column prop="userkey" label="请求key"/>
                        <el-table-column prop="servicename" label="请求Secondkey"/>
                        <el-table-column width="80" prop="requestkey" label="请求类型"/>
                        <el-table-column width="200" prop="requestinfo" label="请求信息">
                            <template #default="scope">
                                <el-input type="textarea" rows="5" v-model="scope.row.requestinfo"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="requesttime" label="请求时间"></el-table-column>
                        <el-table-column prop="responsekey" label="响应key"/>
                        <el-table-column width="200" prop="responseinfo" label="返回信息">
                            <template #default="scope">
                                <el-input type="textarea" rows="5" v-model="scope.row.responseinfo"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="responsetime" label="返回时间"/>
                        <el-table-column width="90" align="center">
                            <template #header>
                                <el-button @click="queryUserRequestFn" type="primary" text>查询</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>

            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
export default {
    name: "orderDet"
}
</script>
<script setup>
import {calculateAge, parseTime} from '@/utils'
import {
    queryByOrderNo,
    queryRequestIns,
    queryUserRequest,
    cancelPoly,
    lineCancelPoly,
    dingHeReCorrent, dingHeReInsureNo
} from '@/api/api';
import {ref} from 'vue'
import {useRoute} from 'vue-router';
import {ElMessage, ElMessageBox} from 'element-plus'

const activeName = ref('first')
const list = ref({})
const orderno = useRoute().query.orderno
listFn()

async function listFn() {
    const {data} = await queryByOrderNo({
        orderno
    })
    list.value = data
}

const urlLists = ref([])

async function queryRequestInsFn() {
    const {data: urlList} = await queryRequestIns({
        orderNo: orderno
    })
    urlLists.value = urlList
}

const urlListsUser = ref([])

async function queryUserRequestFn() {
    const {data: urlList} = await queryUserRequest({
        orderNo: orderno
    })
    urlListsUser.value = urlList
}

async function recall(requestinfo) {
    //针对 requestinfo 获取第一个 { 之后的字符串
    requestinfo = requestinfo.substring(requestinfo.indexOf('{') , requestinfo.length);
    let requestinfoJson;
    try {
        requestinfoJson = JSON.parse(requestinfo);
    }catch (e) {
        ElMessage.error('请检查请求信息是否正确！')
        return;
    }
    //原单号
    let originalorderno = list.value.originalorderno;
    let res;
    if (originalorderno && originalorderno.length > 1) {
        //批改
        res  = await dingHeReCorrent(requestinfoJson, list.value.orderno);
    } else {
        //新保
        res = await dingHeReInsureNo(requestinfoJson, list.value.orderno);
    }
    if (res.status !== 0) {
        ElMessage.error(res.statusInfo)
        return;
    }
    ElMessage.success(res.data);
    this.queryRequestInsFn();
}


async function lineCancelPolyFn(orderNo, polyNo, userName) {
    await ElMessageBox.confirm('是否确认退保?', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
    })
    await lineCancelPoly({
        userName,
        orderNo,
        polyNo
    })
    ElMessage.success('成功！')
    listFn()
}

async function cancelPolyFn(orderNo, polyNo, userName) {
    await ElMessageBox.confirm('是否确认退保?', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
    })
    await cancelPoly({
        userName,
        orderNo,
        polyNo
    })
    ElMessage.success('成功！')
    listFn()
}

</script>

<style scoped>
.app-container:deep(.el-card__header) {
    background: #e6e7e9;
}

</style>