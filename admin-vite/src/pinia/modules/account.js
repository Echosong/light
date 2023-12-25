/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-27 14:57:06
 * @Author: huzhushan@126.com

 */
import { defineStore } from 'pinia'
import { UserInfo } from '@/api/login'
import { getItem } from '@/utils/storage'

export const useAccount = defineStore('account', {
  state: () => ({
    userinfo: null,
    permissionList: [],
  }),
  actions: {
    // 清除用户信息
    clearUserinfo() {
      this.userinfo = null
    },
    // 获取用户信息
    async getUserinfo() {
      const { data } = await UserInfo({
        username:getItem("USERNAME")
      })
        this.userinfo = data
        return Promise.resolve(data)
    },
  },
})
