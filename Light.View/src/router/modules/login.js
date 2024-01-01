/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2021-04-21 09:34:37
 * @Author: huzhushan@126.com

 */
// login.js
const Login = () => import('@/views/login/index.vue')

export default [
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
]
