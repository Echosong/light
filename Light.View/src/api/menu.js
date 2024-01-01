/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2021-07-26 13:37:30
 * @Author: huzhushan@126.com

 */
import request from '@/utils/request'

// 获取菜单
export const GetMenus = params => {
  return request({
    url: '/permission/listByUser',
    method: 'get',
    params,
  })
}