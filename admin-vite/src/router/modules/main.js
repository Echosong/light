/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-21 09:18:32
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-27 18:51:35
 * @Author: huzhushan@126.com

 */
// const List = () => import('@/views/test/index.vue')
// const Add = () => import('@/views/test/Add.vue')
// const Edit = () => import('@/views/test/Edit.vue')
// const Auth = () => import('@/views/test/Auth.vue')
// const NoAuth = () => import('@/views/test/NoAuth.vue')
// const Nest = () => import('@/views/test/Nest.vue')
// const NestPage1 = () => import('@/views/test/nest/Page1.vue')
// const NestPage2 = () => import('@/views/test/nest/Page2.vue')
// const Iscache = () => import('@/views/test/Cache.vue')
// const Nocache = () => import('@/views/test/Nocache.vue')
// const ErrorLog = () => import('@/views/test/error-log/index.vue')

export default [
  {
    path: '/systemAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'systemAdmin',
    meta: {
      title: '系统管理',
    },
    icon: 'Setting',
    children: [
      {
        path: 'set',
        name: 'set',
        component: () => import('@/views/set/set.vue'),
        meta: {
          title: '系统设置',
        },
      },
      {
        path: 'permissionList',
        name: 'permissionList',
        component: () => import('@/views/systemAdmin/permissionList.vue'),
        meta: {
          title: '权限管理',
        },
      },
      {
        path: 'roleList',
        name: 'roleList',
        component: () => import('@/views/systemAdmin/roleList.vue'),
        meta: {
          title: '角色管理',
        },
      },
      
      {
        path: 'logList',
        name: 'logList',
        component: () => import('@/views/systemAdmin/logList.vue'),
        meta: {
          title: '系统日志',
        },
      },
    ]
  },

  {
    path: '/userAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'userAdmin',
    meta: {
      title: '用户管理',
    },
    icon: 'User',
    children: [
      {
        path: 'userList',
        name: 'userList',
        component: () => import('@/views/userAdmin/userList.vue'),
        meta: {
          title: '用户',
        },
      },
      {
        path: 'userAdd',
        name: 'userAdd',
        component: () => import('@/views/userAdmin/userAdd.vue'),
        meta: {
          title: '添加用户',
        },
        hidden: true, // 不在菜单中显示
      },

      {
        path: 'prodGroups',
        name: 'prodGroups',
        component: () => import('@/views/userAdmin/prodGroups.vue'),
        meta: {
          title: '产品组',
        },
      },

      {
        path: 'prodGroupsAdd',
        name: 'prodGroupsAdd',
        component: () => import('@/views/userAdmin/prodGroupsAdd.vue'),
        meta: {
          title: '添加产品组',
        },
        hidden: true,
      },
      {
        path: 'withhold',
        name: 'withhold',
        component: () => import('@/views/userAdmin/withhold.vue'),
        meta: {
          title: '代充代扣',
        },
        hidden: true,
      },
    ],
  },
  
  {
    path: '/financeAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'financeAdmin',
    meta: {
      title: '财务管理',
    },
    icon: 'Wallet',
    children:[
      {
       
        path: 'cashFlow',
        name: 'cashFlow',
        component: () => import('@/views/financeAdmin/cashFlow.vue'),
        meta: {
          title: '资金流水',
        },
      },
    ]
  },
  {
    path: '/productFundamentals',
    component: () => import('@/layout/index.vue'),
    name: 'productFundamentals',
    meta: {
      title: '条款管理',
    },
    icon: 'Memo',
    children:[
      {
        path: 'productFundamentalsList',
        name: 'productFundamentalsList',
        component: () => import('@/views/productFundamentals/productFundamentalsList.vue'),
        meta: {
          title: '保障利益管理',
        },
      },
      {
        path: 'productFundamentalsAdd',
        name: 'productFundamentalsAdd',
        component: () => import('@/views/productFundamentals/productFundamentalsAdd.vue'),
        meta: {
          title: '保障利益管理',
        },
        hidden: true, // 不在菜单中显示
      },
    ]
  },
  {
    path: '/productAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'productAdmin',
    meta: {
      title: '产品管理',
    },
    icon: 'Handbag',
    children:[
      {
        path: 'productList',
        name: 'productList',
        component: () => import('@/views/productAdmin/productList.vue'),
        meta: {
          title: '保险产品',
        },
      },

      {
        path: 'productAdd',
        name: 'productAdd',
        component: () => import('@/views/productAdmin/productAdd.vue'),
        meta: {
          title: '添加产品',
        },
        hidden: true,
      },
    ]
  },

  {
    path: '/orderAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'orderAdmin',
    meta: {
      title: '订单管理',
    },
    icon: 'Tickets',
    children:[
      {
        path: 'orderAll',
        name: 'orderAll',
        component: () => import('@/views/orderAdmin/orderAll.vue'),
        meta: {
          title: '所有订单',
        },
      },
       
      {
        path: 'orderSlip',
        name: 'orderSlip',
        component: () => import('@/views/orderAdmin/orderSlip.vue'),
        meta: {
          title: '所有保单',
        },
      },
      {
        path: 'orderRecall',
        name: 'orderRecall',
        component: () => import('@/views/orderAdmin/orderRecall.vue'),
        meta: {
          title: '所有撤单',
        },
      },
      {
        path: 'orderDet',
        name: 'orderDet',
        component: () => import('@/views/orderAdmin/orderDet.vue'),
        meta: {
          title: '订单详情',
        },
        hidden: true, // 不在菜单中显示
      },
    ]
  },

  {
    path: '/articleAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'articleAdmin',
    meta: {
      title: '通知公告',
    },
    icon: 'Money',
    children:[
      {
        path: 'articleList',
        name: 'articleList',
        component: () => import('@/views/articleAdmin/articleList.vue'),
        meta: {
          title: '通知公告',
        },
      },
      {
        path: 'addArticle',
        name: 'addArticle',
        component: () => import('@/views/articleAdmin/addArticle.vue'),
        meta: {
          title: '添加通知公告',
        },
        hidden: true,
      },

      // {
      //   path: '/settlementDet',
      //   name: 'settlementDet',
      //   component: () => import('@/views/settlementAdmin/settlementDet.vue'),
      //   meta: {
      //     title: '理赔详情',
      //   },
      //   hidden: true,
      // },
    ]
  },
  {
    path: '/settlementAdmin',
    component: () => import('@/layout/index.vue'),
    name: 'settlementAdmin',
    meta: {
      title: '理赔管理',
    },
    icon: 'Money',
    children:[
      {
        path: 'settlementList',
        name: 'settlementList',
        component: () => import('@/views/settlementAdmin/settlementList.vue'),
        meta: {
          title: '理赔查询',
        },
      },
      {
        path: 'settlementDet',
        name: 'settlementDet',
        component: () => import('@/views/settlementAdmin/settlementDet.vue'),
        meta: {
          title: '理赔详情',
        },
        hidden: true,
      },

      // {
      //   path: '/settlementDet',
      //   name: 'settlementDet',
      //   component: () => import('@/views/settlementAdmin/settlementDet.vue'),
      //   meta: {
      //     title: '理赔详情',
      //   },
      //   hidden: true,
      // },
    ]
  },
]
