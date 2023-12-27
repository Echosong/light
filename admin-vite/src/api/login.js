/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2021-04-21 09:36:55
 * @Author: huzhushan@126.com

 */
import request from '@/utils/request'

// 登录接口
export const Login = data => {
  return request({
    url: '/user/login',
    method: 'post',
    data,
  })
}



// 获取登录用户信息
export const UserInfo = (params) => {
  return request({
    url: '/user/getCurrent',
    method: 'get',
    params
  })
}
