/* eslint-disable */

import sa from '../static/sa';
import router from '../router';

var RouteConfig = router;

/* 菜单集合 */
export default new Promise((suc, fail) => {
	sa.get("/permission/listByUser").then(res => {
		let returnMenu = res.data.map(item => {
			RouteConfig.forEach(r => {
				console.log(item);
				console.log(r);
				if (r.perms === item.perms) {
					item.view = r.view;
					if(item.type > 1){
						item.is_show = false;
					}
					
				}
			})
			return item;
		})
		suc(returnMenu);
	})
})
