<style scoped>
</style>

<template>
  <el-dialog
    v-if="m"
    :title="title"
    :visible.sync="isShow"
    width="550px"
    top="10vh"
    :append-to-body="true"
    :destroy-on-close="true"
    :close-on-click-modal="false"
    custom-class="full-dialog"
  >
    <div class="vue-box">
      <!-- 参数栏 -->
      <div class="c-panel">
        <el-form
          size="mini"
          v-if="m"
          ref="ruleForm"
          :rules="rules"
          :model="m"
          class="demo-ruleForm"
          label-width="90px"
        >
          
          <el-form-item>
            <span class="c-label">&emsp;</span>
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="ok('ruleForm')"
              >确定</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-dialog>
</template>

<script>
//import inputEnum from "../../sa-resources/com-view/input-enum.vue";
export default {
  //components: { inputEnum },
  props: ["params"],
  data() {
    return {
      m: {},
      title:"",
      isShow: false,
      rules: {},
      fileList:[],
    }
  },
  methods: {
    open: function (data) {
      this.isShow = true;
      if (data) {
        this.title = "修改 角色" ;
        this.m = data;
      }else{
        this.m = {}
        this.title = "添加 角色" ;
      }
    },
    success(response, file, fileList) {
        console.log(response,file, fileList);
    },

    //提交角色信息
    ok: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            this.sa.post("/role/save", this.m).then((res) => {
              console.log(res);
              this.$parent.f5();
              this.isShow = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  },
  created() {
  },
};
</script>