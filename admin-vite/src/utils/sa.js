import request from '@/utils/request'
import {ElMessage, ElMessageBox, ElLoading } from 'element-plus'

export default class sa {
   static get(url, params={}) {
    return request({
      url: url,
      method: 'get',
      params: params
    })
  }

   static post(url, params) {
    return request({
      url: url,
      method: 'post',
      data: params
    })
  }

   static put(url, params) {
    return request({
      url: url,
      method: 'put',
      data: params
    })
  }

   static delete(url, params) {
    return request({
      url: url,
      method: 'delete',
      data: params
    })
  }

   static upload(url, params) {
    return request({
      url: url,
      method: 'post',
      data: params,
      headers: {'Content-Type': 'multipart/form-data;charset=utf-8'},
    })
  }

   static download(url, params) {
    return request({
      url: url,
      method: 'get',
      params: params,
      responseType: 'blob'
    })
  }

  static ok(msg) {
    ElMessage.success(msg);
  }

  static error(msg) {
    ElMessage.error(msg);
  }

  static confirm(msg, callback) {
    ElMessageBox.confirm(msg, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      callback();
    }).catch(() => {
    });
  }

  static loading(msg) {
    var loading = ElLoading.service({text: msg});
    setTimeout(() => {
      loading.close();
    }, 10000);
    return loading;
  }

  static arrayDelete(arr, item){
    var index = arr.indexOf(item);
    if (index > -1) {
      arr.splice(index, 1);
    }
  }

}