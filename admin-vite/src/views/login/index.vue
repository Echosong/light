<!--
 * @Descripttion: 
 * @version: 
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-27 18:24:27
 * @Author: huzhushan@126.com

-->
<template>
  <div class="login">
    <el-form class="form" :model="model" :rules="rules" ref="loginForm">
      <div class="title-container">
        <img class="logo" src="~@/assets/logo.png">
        <!-- <p class="title">后台管理系统</p> -->
      </div>
      <el-form-item prop="username">
        <el-input class="text" v-model="model.username" prefix-icon="User" clearable
          :placeholder="$t('login.username')" />
      </el-form-item>
      <el-form-item prop="password">
        <el-input class="text" v-model="model.password" prefix-icon="Lock" show-password clearable
          :placeholder="$t('login.password')" />
    </el-form-item>
    <el-form-item>
        <el-button type="primary" class="btn" size="large" @click="submit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- <div class="change-lang">
      <change-lang />
    </div> -->
</template>

<script>
import {
  defineComponent,
  getCurrentInstance,
  reactive,
  toRefs,
  ref,
  computed,
  watch,
} from 'vue'
import { Login } from '@/api/login'
import { useRouter, useRoute } from 'vue-router'
import ChangeLang from '@/layout/components/Topbar/ChangeLang.vue'
import useLang from '@/i18n/useLang'
import { useApp } from '@/pinia/modules/app'
import { setItem } from '@/utils/storage'

export default defineComponent({
  components: { ChangeLang },
  name: 'login',
  setup() {
    const { proxy: ctx } = getCurrentInstance() // 可以把ctx当成vue2中的this
    const router = useRouter()
    const route = useRoute()
    const { lang } = useLang()
    watch(lang, () => {
      state.rules = getRules()
    })
    const getRules = () => ({
      userName: [
        {
          required: true,
          message: ctx.$t('login.rules-username'),
          trigger: 'blur',
        },
      ],
    })
    const state = reactive({
      model: {
        username: import.meta.env.VITE_APP_ACCOUNT,
        password: import.meta.env.VITE_APP_PASSWORD,
      },
      rules: getRules(),
      loading: false,
      btnText: computed(() =>
        state.loading ? ctx.$t('login.logining') : ctx.$t('login.login')
      ),
      loginForm: ref(null),
      submit: () => {
        if (state.loading) {
          return
        }
        state.loginForm.validate(async valid => {
          if (valid) {
            state.loading = true
            const { data } = await Login(state.model)
            // ctx.$message.success({
            //   message: ctx.$t('login.loginsuccess'),
            //   duration: 1000,
            // })
            setItem("USERNAME",  state.model.username)
            const targetPath = decodeURIComponent(route.query.redirect)
            if (targetPath.startsWith('http')) {
              // 如果是一个url地址
              window.location.href = targetPath
            } else if (targetPath.startsWith('/')) {
              // 如果是内部路由地址
              router.push(targetPath)
            } else {
              router.push('/')
            }
            useApp().initToken(data)
            state.loading = false
          }
        })
      },
    })

    return {
      ...toRefs(state),
    }
  },
})
</script>

<style lang="scss" scoped>
.logo {
  max-width: 160px;
  display: block;
  margin: auto;
  margin-bottom: 20px;
}

.login {
  transition: transform 1s;
  transform: scale(1);
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: #2d3a4b;

  .form {
    width: 520px;
    max-width: 100%;
    padding:24px;
    border-radius: 14px;
    box-sizing: border-box;
    margin: 160px auto 0;
    background-color: #fff;
    box-shadow:0px 0px 9px 2px rgba(255, 255, 255, 0.4);
    .title {
      color: #fff;
      font-size: 24px;text-align: center;
      margin: 0;
      margin-bottom: 20px;
    }

    .text {
      font-size: 16px;
      :deep(.el-input__inner) {
        height: 48px;
        line-height: 48px;
      }
    }

    .btn {
      width: 100%;
    }
  }
}

.change-lang {
  position: fixed;
  right: 20px;
  top: 20px;

  :deep() {
    .change-lang {
      height: 24px;

      &:hover {
        background: none;
      }

      .icon {
        color: #fff;
      }
    }
  }
}
</style>
