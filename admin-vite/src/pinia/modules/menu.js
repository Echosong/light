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
import {defineStore} from 'pinia'
import {fixedRoutes, asyncRoutes} from '@/router'
import {GetMenus} from '@/api/menu'

import router from '@/router'
import {ref} from 'vue'
import {handleTree} from '@/utils'
import {useAccount} from './account'

const Layout = () => import('@/layout/index.vue')

export const useMenus = defineStore('menu', () => {
  const generateUrl = (path, parentPath) => {
    //随机三位数字
    return path.startsWith('/')
      ? path
      : path
        ? `${parentPath}/${path}`
        : parentPath
  }

  function getRoute(item) {
    return {
      path: generateUrl('/' + (item.perms || "")),
      component: Layout,
      name: item.name,
      meta: {
        title: item.name,
      },
      icon: item.icon
    }
  }

  const getFilterRoutes = (targetRoutes, ajaxRoutes) => {
    const filterRoutes = []

    const parentRoutes = ajaxRoutes.filter(item => item.parentId === 0);
    let i = 0;
    parentRoutes.forEach(item => {
      if(!item.perms || item.perms.length < 3){
        item.perms =  ''+i;
      }
      i++;
      const route = getRoute(item)
      const sonRoutes = item.children;
      route.children = [];
      sonRoutes.forEach(son => {
        const sonRoute = getRoute(son)
        var target = targetRoutes.find(t => t.perms === son.perms);
        if(target) {
          sonRoute.component = target.view;
          route.children.push(sonRoute)
        }
      })
      filterRoutes.push(route);
    })
    console.log(67676, filterRoutes)
    return filterRoutes
  }

  const getFilterMenus = (arr, parentPath = '') => {
    let menus = [];
    arr.forEach(item => {
      const menu = {
        url: generateUrl(item.path, parentPath),
        title: item.meta.title,
        icon: item.icon,
      }
      if (item.children && item.name !== 'Dashboard') {
        menu.children = []
        item.children.forEach(child => {
          let childMenu = {
            url: generateUrl(child.path, menu.url),
            title: child.meta.title,
            icon: child.icon,
          }
          menu.children.push(childMenu)
        })
      }
      menus.push(menu)
    })
    return menus;
  }

  const menus = ref([])
  const setMenus = data => {
    menus.value = data
  }
  const generateMenus = async () => {
    // // 方式二：有动态菜单
    // // 从后台获取菜单
    const {userinfo} = useAccount()
    const {data} = await GetMenus({userId: userinfo.id})
    let dataRoutes = handleTree(data);
    // 过滤出需要添加的动态路由
    try {
      const filterRoutes = getFilterRoutes(asyncRoutes, dataRoutes)
      filterRoutes.forEach(route => router.addRoute(route))
      // 生成菜单
      const menus = getFilterMenus([...fixedRoutes, ...filterRoutes])
      console.log(66666666, menus)
      setMenus(menus)
    }catch (e) {
      console.log(e)
    }
  }
  return {
    menus,
    setMenus,
    generateMenus,
  }
})
