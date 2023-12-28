export default [
{perms: "log-add", view: () => import('@/views/log/add.vue')},
{perms: "file-list", view: () => import('@/views/file/list.vue')},
{perms: "article-add", view: () => import('@/views/article/add.vue')},
{perms: "config-add", view: () => import('@/views/config/add.vue')},
{perms: "permission-list", view: () => import('@/views/permission/list.vue')},
{perms: "log-list", view: () => import('@/views/log/list.vue')},
{perms: "product-list", view: () => import('@/views/product/list.vue')},
{perms: "role-list", view: () => import('@/views/role/list.vue')},
{perms: "article-list", view: () => import('@/views/article/list.vue')},
{perms: "user-list", view: () => import('@/views/user/list.vue')},
{perms: "user-add", view: () => import('@/views/user/add.vue')},
{perms: "file-add", view: () => import('@/views/file/add.vue')},
{perms: "role-add", view: () => import('@/views/role/add.vue')},
{perms: "product-add", view: () => import('@/views/product/add.vue')},
{perms: "config-list", view: () => import('@/views/config/list.vue')},
{perms: "permission-add", view: () => import('@/views/permission/add.vue')},
];