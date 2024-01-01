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
    <el-config-provider :size="piniaSize">
        <Suspense>
            <router-view/>
        </Suspense>
    </el-config-provider>
</template>

<script>
import {defineComponent, onMounted} from 'vue'
import {ElConfigProvider} from 'element-plus'
import {sysConfig} from './pinia/modules/sysConfig'
import {storeToRefs} from "pinia"
import {FontSize} from './pinia/modules/fontSize'
import {ref, watch} from '@vue/runtime-core'
import {useRoute} from 'vue-router'
export default defineComponent({
    components: {
        [ElConfigProvider.name]: ElConfigProvider,
    },
    setup() {
        onMounted(() => {
        })
        const route = useRoute()
        watch(() => route.path, newRoute => {

            let authorization = localStorage.getItem("VEA-TOKEN")
            if (authorization) {
                sysConfig().getDictList();
            }
        })
        const {piniaSize} = storeToRefs(FontSize())
        return {
            piniaSize,
        }
    },
})

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
