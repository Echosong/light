<!--
 *                   ___====-_  _-====___
 *             _--^^^#####//      \\#####^^^--_
 *          _-^##########// (    ) \\##########^-_
 *         -############//  |\^^/|  \\############-
 *       _/############//   (@::@)   \############\_
 *      /#############((     \\//     ))#############\
 *     -###############\\    (oo)    //###############-
 *    -#################\\  / VV \  //#################-
 *   -###################\\/      \//###################-
 *  _#/|##########/\######(   /\   )######/\##########|\#_
 *  |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 *  `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *     `   `  `      `   / | |  | | \   '      '  '   '
 *                      (  | |  | |  )
 *                     __\ | |  | | /__
 *                    (vvv(VVV)(VVV)vvv)
 * 
 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 *                神兽保佑            永无BUG
 * 
 * @Descripttion: 
 * @version: 
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-26 12:14:10
 * @Author: huzhushan@126.com

 -->
<template>
  <el-config-provider  :size="piniaSize" >
    <Suspense>
    <router-view />
  </Suspense>
  </el-config-provider>
</template>

<script>
import { defineComponent ,onMounted} from 'vue'
import { ElConfigProvider } from 'element-plus'
import { statusmark } from './pinia/modules/statusmark'
import {storeToRefs} from "pinia"
import { FontSize } from './pinia/modules/fontSize'
import { ref, watch } from '@vue/runtime-core'
import { useRoute } from 'vue-router'
// import DevicePixelRatio from '@/utils/evicePixelRatio.js';
export default defineComponent({
  components: {
    [ElConfigProvider.name]: ElConfigProvider,
  },
  setup() {
    onMounted(() => {
		// new DevicePixelRatio().init();
    // bodyScale()
    
	})


  const route = useRoute()
  watch(() => route.path,newRoute=> {
    let CompanyType = statusmark().g_statusmark['CompanyType']
    if (!CompanyType || CompanyType?.length <= 0) {
      let authorization = localStorage.getItem("VEA-TOKEN")
      if (authorization) {
        statusmark().listFn()
      }
    }
})
    const { piniaSize } = storeToRefs(FontSize())
    
    statusmark().listFn()
    return {
      piniaSize,

    }
  },
})
// const bodyScale = () => {
//       var devicewidth = document.documentElement.clientWidth;//获取当前分辨率下的可是区域宽度
//       var scale = devicewidth / 1920; // 分母——设计稿的尺寸
//       document.body.style.zoom = scale;//放大缩小相应倍数
// }

</script>



<style lang="scss">
html,
body,
#app {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  * {
    outline: none;
  }
}
</style>
