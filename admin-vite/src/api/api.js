import request from '@/utils/request'


//==================================新开接口=============================================

export const getConfigList = params => {
  return request({
    url: 'config/list',
    method: 'get',
    params,
  })
}

//================================================================================
export const backQueryUsersList = params => {
  return request({
    url: 'users/backQueryUsersList',
    method: 'post',
    params,
  })
}

export const queryStatusDictionaryList = (data = {}) => {
  return request({
    url: 'users/queryStatusDictionaryList',
    method: 'post',
    data,
  })
}
export const updatePassword = (data = {}) => {
  return request({
    url: 'users/updatePassword',
    method: 'post',
    data,
  })
}

export const queryUserLevelList = params => {
  return request({
    url: 'userlevel/queryUserLevelList',
    method: 'post',
    params,
  })
}


export const queryCitysList = (data = {}) => {
  return request({
    url: 'users/queryCitysList',
    method: 'post',
    data,
  })
}


export const queryUserBylikename = params => {
  return request({
    url: 'users/queryUserBylikename',
    method: 'post',
    params,
  })
}


export const productCopy = params => {
  return request({
    url: '/product/copy',
    method: 'get',
    params,
  })
}
export const saveUsers = data => {
  return request({
    url: 'users/saveUsers',
    method: 'post',
    data,
  })
}

export const checkUserName = data => {
  return request({
    url: 'users/checkByUserName',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    data,
  })
}

export const queryUsersByid = data => {
  return request({
    url: 'users/queryUsersByid',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const backQueryAllList = data => {
  return request({
    url: 'productgroup/backQueryAllList',
    method: 'post',
    data,
  })
}


export const queryMatchTradeTypeList = data => {
  return request({
    url: 'capitalflow/backQueryAllList',
    method: 'post',
    data,
  })
}


export const benefitclassQueryAllList = data => {
  return request({
    url: 'benefitclass/queryAllList',
    method: 'post',
    data,
  })
}

export const instypeQueryAllList = data => {
  return request({
    url: 'instype/queryAllList',
    method: 'post',
    data,
  })
}


export const clausetypeQueryAllList = data => {
  return request({
    url: 'clausetype/queryAllList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const capitalflowQueryMatchTradeTypeList = data => {
  return request({
    url: 'capitalflow/queryMatchTradeTypeList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const queryProductsList = data => {
  return request({
    url: 'product/queryProductsList',
    method: 'post',
    data,
  })
}

export const productDelete = data => {
  return request({
    url: 'product/delete',
    method: 'delete',
    params: data
  })
}

export const productgroupDelete = data => {
  return request({
    url: 'productgroup/delete',
    method: 'delete',
    params: data
  })
}

export const saveProductgroup = data => {
  return request({
    url: 'productgroup/saveProductgroup',
    method: 'post',
    data,
  })
}
export const setOrderlnfo = data => {
  return request({
    url: 'inner/setOrderInfo',
    method: 'post',
    data,
  })
}
export const queryById = data => {
  return request({
    url: 'productgroup/queryById',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const rechargeCapitalflow = data => {
  return request({
    url: 'capitalflow/rechargeCapitalflow',
    method: 'post',
    data,
  })
}


export const queryListByUserName = data => {
  return request({
    url: 'productgroup/queryListByUserName',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const productgroupQueryList = data => {
  return request({
    url: 'productgroup/queryList',
    method: 'post',
    data,
  })
}


export const backQueryBenefitsList = data => {
  return request({
    url: 'benefits/backQueryBenefitsList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const benefitsDelete = params => {
  return request({
    url: 'benefits/delete',
    method: 'delete',
    params,
  })
}


export const queryInscompanySimpleList = data => {
  return request({
    url: 'company/queryInscompanySimpleList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const queryClauseTypeList = data => {
  return request({
    url: 'clausetype/queryClauseTypeList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const saveBenefits = data => {
  return request({
    url: 'benefits/saveBenefits',
    method: 'post',
    data,
  })
}


export const queryBenefitclassList = data => {
  return request({
    url: 'benefitclass/queryBenefitclassList',
    method: 'post',
    data,
  })
}


export const benefitsQueryById = data => {
  return request({
    url: 'benefits/queryById',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const deleteBenefitDetailsByid = data => {
  return request({
    url: 'benefits/deleteBenefitDetailsByid',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const backQueryOrdersList = data => {
  return request({
    url: 'order/backQueryOrdersList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const queryByOrderNo = data => {
  return request({
    url: 'order/queryByOrderNo',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const queryRequestIns = data => {
  return request({
    url: 'order/queryRequestIns',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const queryUserRequest = data => {
  return request({
    url: 'order/queryUserRequest',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const cancelPoly = data => {
  return request({
    url: 'order/cancelPoly',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const lineCancelPoly = data => {
  return request({
    url: 'order/lineCancelPoly',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const backQueryPolyInfoList = data => {
  return request({
    url: 'order/backQueryPolyInfoList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const backeQueryPolyReport = data => {
  return request({
    url: 'order/backeQueryPolyReport',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const queryPolyReport = data => {
  return request({
    url: 'order/queryPolyReport',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const backQueryCancelPolyInfoList = data => {
  return request({
    url: 'order/backQueryCancelPolyInfoList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const backQueryClaimsList = data => {
  return request({
    url: 'order/backQueryClaimsList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const queryBackPolyInfoClaims = data => {
  return request({
    url: 'order/queryBackPolyInfoClaims',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const queryPolyClaims = data => {
  return request({
    url: 'order/queryPolyClaims',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const queryOrderByOrderNoNew = data => {
  return request({
    url: 'order/queryOrderByOrderNoNew',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const backQueryProductsList = data => {
  return request({
    url: 'product/backQueryProductsList',
    method: 'post',
    data,
  })
}


export const queryProducttypeList = data => {
  return request({
    url: 'producttype/queryProducttypeList',
    method: 'post',
    data,
  })
}


export const saveProduct = data => {
  return request({
    url: 'product/saveProduct',
    method: 'post',
    data,
  })
}

export const productQueryById = data => {
  return request({
    url: 'product/queryById',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const articleDelete = params => {
  return request({
    url: `/article/delete/${params.id}`,
    method: 'get',
    params
  })
}

export const queryBenefitTree = data => {
  return request({
    url: 'productbenefitandplan/queryBenefitTree',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const saveproductbenefitandplan = data => {
  return request({
    url: 'productbenefitandplan/saveproductbenefitandplan',
    method: 'post',
    data,
  })
}


export const queryProductplanList = data => {
  return request({
    url: 'productbenefitandplan/queryProductplanList',
    method: 'post',
    data,
  })
}


export const queryPlanAmountList = params => {
  return request({
    url: 'planAmountAndBenefit/queryPlanAmountList',
    method: 'get',
    params,
  })
}


export const queryBenefitGroupPlanList = data => {
  return request({
    url: 'planAmountAndBenefit/queryBenefitGroupPlanList',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}

export const saveBackClaims = data => {
  return request({
    url: 'order/saveBackClaims',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    method: 'post',
    data,
  })
}


export const saveplanamountandbenefit = data => {
  return request({
    url: 'planAmountAndBenefit/saveplanamountandbenefit',
    method: 'post',
    data,
  })
}


export const queryFieldsList = data => {
  return request({
    url: 'fields/queryFieldsList',
    method: 'post',
    data,
  })
}


export const queryProductBenefitGroupList = data => {
  return request({
    url: 'productbenefitandplan/queryProductBenefitGroupList',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    data,
  })
}


export const saveProductsfields = data => {
  return request({
    url: 'productsfields/saveProductsfields',
    method: 'post',
    data,
  })
}


export const queryProductsfields = data => {
  return request({
    url: 'productsfields/queryProductsfields',
    method: 'post',
    data,
  })
}


export const logListPages = data => {
  return request({
    url: 'log/listPage',
    method: 'put',
    data,
  })
}


export const permissionAll = data => {
  return request({
    url: '/permission/all',
    method: 'get',
    data,
  })
}


export const roleList = data => {
  return request({
    url: 'role/list',
    method: 'get',
    data,
  })
}


export const roleSave = data => {
  return request({
    url: '/role/save',
    method: 'post',
    data,
  })
}
export const permissionSave = data => {
  return request({
    url: '/permission/save',
    method: 'post',
    data,
  })
}

export const roleDelete = data => {
  return request({
    url: `/role/delete/${data.id}`,
    method: 'delete',
    data,
  })
}

export const saveUserRole = data => {
  return request({
    url: `/role/saveUserRole`,
    method: 'post',
    data
  })
}

export const listByUser = params => {
  return request({
    url: `/role/listByUser/${params.id}`,
    method: 'get',
    params
  })
}

export const exportPolyinvoice = params => {
  return request({
    url: `/policy/exportPolyinvoice`,
    method: 'get',
    params
  })
}

export const permissionDelete = data => {
  return request({
    url: `/permission/delete/${data.id}`,
    method: 'delete',
    data,
  })
}

export const shareInsureNo = params => {
  return request({
    url: `/notify/shareInsureNo/${params.orderno}`,
    method: 'get',
    params
  })
}

export const shareCorrect = params => {
  return request({
    url: `/notify/shareCorrect/${params.orderno}`,
    method: 'get',
    params
  })
}

export const shareCancel = params => {
  return request({
    url: `/notify/shareCancel/${params.orderno}`,
    method: 'get',
    params
  })
}


export const updateRolePermissions = data => {
  return request({
    url: `/permission/updateRolePermissions/${data.roleId}`,
    method: 'put',
    data: data.arr,
  })
}


export const listByRole = params => {
  return request({
    url: `/permission/listByRole/${params.roleId}`,
    method: 'get',
    params
  })
}
export const articleListPage = data => {
  return request({
    url: `/article/listPage`,
    method: 'put',
    data
  })
}


export const articleSave = data => {
  return request({
    url: `/article/save`,
    method: 'post',
    data
  })
}

export const articlefind = params => {
  return request({
    url: `/article/find/${params.id}`,
    method: 'get',
    params
  })
}


export const adminBack = params => {
  return request({
    url: `/back`,
    method: 'get',
    params
  })
}


// 上传文件
export function upLoadfile(param) {
  return request({
    url: '/file/upload',
    method: 'post',
    // params: param
    data: param,
    headers: {'Content-Type': 'multipart/form-data;charset=utf-8'},
  })
}

export function getConfiglist(params) {
  return request({
    url: `/config/list`,
    method: 'get',
    params
  })
}

export function saveConfig(params) {
  return request({
    url: `/config/save`,
    method: 'post',
    data: params
  })
}

/**
 * 鼎和重新调用接口
 * @param params
 * @param orderNo
 * @returns {*}
 */
export function dingHeReInsureNo(params, orderNo) {
  return request({
    url: `/inner/dingHeReInsureNo/${orderNo}`,
    method: 'post',
    data: params
  })
}

/**
 * 鼎和改报文修复接口
 * @param params
 * @param orderNo
 * @returns {*}
 */
export function dingHeReCorrent(params, orderNo) {
  return request({
    url: `/inner/dingHeReCorrent/${orderNo}`,
    method: 'post',
    data: params
  })
}