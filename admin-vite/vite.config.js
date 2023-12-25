/*
 *                   江城子 . 程序员之歌
 *
 *               十年生死两茫茫，写程序，到天亮。
 *                   千行代码，Bug何处藏。
 *               纵使上线又怎样，朝令改，夕断肠。
 *
 *               领导每天新想法，天天改，日日忙。
 *                   相顾无言，惟有泪千行。
 *               每晚灯火阑珊处，夜难寐，加班狂。
 *
 *
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-24 14:33:17
 * @Author: huzhushan@126.com

 */


import { loadEnv } from 'vite'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import { viteMockServe } from 'vite-plugin-mock'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'

// https://vitejs.dev/config/
export default ({ mode }) => {
  const env=loadEnv(mode, process.cwd());
  console.log(env)
  return defineConfig({
    plugins: [
      vue(),
      viteMockServe({
        ignore: /^_/, // 忽略以下划线`_`开头的文件
        mockPath: 'mock', // 指定mock目录中的文件全部是mock接口
        supportTs: false, // mockPath目录中的文件是否支持ts文件，现在我们不使用ts，所以设为false
        localEnabled: env.mode === 'mock', // 开发环境是否开启mock功能（可以在package.json的启动命令中指定mode为mock）
        prodEnabled: env.mode === 'mock', // 生产环境是否开启mock功能
        injectCode: `
          import { setupProdMockServer } from '../mock/_createProductionServer';
          setupProdMockServer();
        `,
      }),
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹
        iconDirs: [path.resolve(__dirname, 'src/assets/svg')],
        // 指定symbolId格式
        symbolId: 'icon-[dir]-[name]',
      })
    ],
    css: {
      preprocessorOptions: {
        scss: {
          // 全局变量
          // additionalData: '@import "./src/assets/style/global-variables.scss";',
          // element-plus升级到v2需要改成以下写法
          additionalData: `@use "./src/assets/style/global-variables.scss" as *;`,
        },
      },
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'),
        'vue-i18n': 'vue-i18n/dist/vue-i18n.cjs.js',
      },
      
    },
    server: {
      port: 3001,
      open: true,
      proxy: {
        '^/yunwoo': {
          target:env.VITE_APP_URL, // 后端接口的域名
          changeOrigin: true,
          rewrite: path => path.replace(/^\/yunwoo/, ''),
        },
      },
    },
    esbuild: false,
    build: {
      minify:"terser",
      terserOptions: {
        compress: {
          keep_infinity: true,
          // 删除console
          drop_console: true,
        },
      },
      // 禁用该功能可能会提高大型项目的构建性能
      brotliSize: false,
      rollupOptions: {
        output: {
          // 拆分单独模块
          manualChunks: {
            'element-plus': ['element-plus'],
            mockjs: ['mockjs'],
          },
        },
      },
    },
  })
}
