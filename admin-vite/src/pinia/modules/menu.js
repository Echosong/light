/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-27 16:41:46
 * @Author: huzhushan@126.com
 * @HomePage: https://huzhushan.gitee.io/vue3-element-admin
 * @Github: https://github.com/huzhushan/vue3-element-admin
 * @Donate: https://huzhushan.gitee.io/vue3-element-admin/donate/
 */
import { defineStore } from 'pinia'
import { fixedRoutes, asyncRoutes } from '@/router'
import { GetMenus } from '@/api/menu'

import router from '@/router'
import { ref } from 'vue'
import { handleTree } from '@/utils'
import { useAccount } from './account'

export const useMenus = defineStore('menu', () => {
  const generateUrl = (path, parentPath) => {
    return path.startsWith('/')
      ? path
      : path
      ? `${parentPath}/${path}`
      : parentPath
  }

  const getFilterRoutes = (targetRoutes, ajaxRoutes) => {
    const filterRoutes = []

    ajaxRoutes.forEach(item => {
      const target = targetRoutes.find(target => {
        if(target.name === item.name){
          target.title = item.title
          target.icon = item.icon
          return target
        }
      })
      if (target) {
        const { children: targetChildren, ...rest } = target
        const route = {
          ...rest,
        }
        if (item.children) {
          if(item.children[0].type == 1){
            route.children = getFilterRoutes(targetChildren, item.children)
          }else{
            route.meta.actionType = item.children.map(items=>{
              return items.name
            })
          }
        }
        filterRoutes.push(route)
      }
    })
    return filterRoutes
  }

  const getFilterMenus = (arr, parentPath = '') => {
    const menus = []
    
    arr.forEach(item => {

      if (!item.hidden) {
        
        const menu = {
          url: generateUrl(item.path, parentPath),
          title: item.meta.title,
          icon: item.icon,
        }
        if (item.children) {
          if(item.name != 'Dashboard'){
            if (item.children.filter(child => !child.hidden).length < 1) {
            
              menu.url = generateUrl(item.children[0].path, menu.url)
            } else {
              menu.children = getFilterMenus(item.children, menu.url)
            }
          }
          
        }
        menus.push(menu)
      }
    })




    return menus
  }


  const menus = ref([])
  const setMenus = data => {
    menus.value = data
  }
  const generateMenus = async () => {
    // // 方式一：只有固定菜单
    // const menus = getFilterMenus([...fixedRoutes, ...asyncRoutes])
    // setMenus(menus)

    // // 方式二：有动态菜单
    // // 从后台获取菜单
    const { userinfo } = useAccount()
    const { data } = await GetMenus({ userId: userinfo.id })
     const dataList =  data.filter(item => {
      item.title = item.name
        item.name = item.perms
        return item
    })
      asyncRoutes.forEach(item => {
        router.removeRoute(item.name)
      })
      // 过滤出需要添加的动态路由
      const filterRoutes = getFilterRoutes(asyncRoutes, handleTree(dataList))
      filterRoutes.forEach(route => router.addRoute(route))
      // 生成菜单
      const menus = getFilterMenus([...fixedRoutes, ...filterRoutes])
      setMenus(menus)
  }
  return {
    menus,
    setMenus,
    generateMenus,
  }
})
