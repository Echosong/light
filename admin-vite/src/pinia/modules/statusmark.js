/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-27 14:57:06
 * @Author: huzhushan@126.com

 */
import { defineStore } from 'pinia'
import { queryStatusDictionaryList } from '@/api/api'
import { groupBy } from 'lodash'

export const statusmark = defineStore('statusmark', {
  state: () => ({
    g_statusmark: {},
  }),
  actions: {
    // 获取所有类别
    async listFn() {

      this.g_statusmark = localStorage.getItem('g_statusmark') ? JSON.parse(localStorage.getItem('g_statusmark')) : []
      let {data} = await queryStatusDictionaryList()
     let res = groupBy(data, 'statusmark')
     localStorage.setItem('g_statusmark',JSON.stringify(res))

     this.g_statusmark = res
     return Promise.resolve(res)
    },
  },
})
