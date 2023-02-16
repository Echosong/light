export default {
    inserted(el, binding, vnode) {
      const { value } = binding
      const all_permission = "*:*:*"; // 全部权限
      const permissions = JSON.parse(localStorage.getItem("permission"))
      console.log("执行指令", permissions);
      if (value && value instanceof Array && value.length > 0) {
        // 判断是否有权限
        const permissionFlag = value
        const hasPermissions = permissions.some(permission => {
          return all_permission === permission || permissionFlag.includes(permission)
        })
        // 如果没有权限，则移除元素
        if (!hasPermissions) {
          el.parentNode && el.parentNode.removeChild(el)
        }
      } else {
        throw new Error(`请设置操作权限标签值`)
      }
    }
  }