/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2021-04-21 09:34:40
 * @Author: huzhushan@126.com

 */
const Layout = () => import('@/layout/index.vue')
const Redirect = () => import('@/views/redirect/index.vue')

export default [
  {
    path: '/redirect/:path(.*)',
    component: Layout,
    children: [
      {
        path: '',
        component: Redirect,
      },
    ],
  },
]
