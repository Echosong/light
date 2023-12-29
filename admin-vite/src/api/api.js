import request from '@/utils/request'

export const adminBack = params => {
  return request({
    url: `/index`,
    method: 'get',
    params
  })
}

export const getConfigList = params => {
  return request({
    url: 'config/list',
    method: 'get',
    params,
  })
}


export const updatePassword = (data = {}) => {
  return request({
    url: 'user/updatePassword',
    method: 'post',
    data,
  })
}

export const listByUser = params => {
  return request({
    url: `/role/listByUser/${params.id}`,
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

export function roleList(){
  return request({
    url: `/role/list`,
    method: 'get',
  })
}
