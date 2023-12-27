import request from '@/utils/request'
import {ElMessage, ElMessageBox} from 'element-plus'

export default class sa {
   static get(url, params) {
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
    ElMessageBox.loading(msg, {
      lock: true,
      text: '加载中',
      spinner: 'el-icon-loading',
      background: 'rgba(0, 0, 0, 0.7)'
    });
    setTimeout(() => {
      ElMessageBox.close();
    }, 10000);
  }

  static arrayDelete(arr, item){
    var index = arr.indexOf(item);
    if (index > -1) {
      arr.splice(index, 1);
    }
  }

}