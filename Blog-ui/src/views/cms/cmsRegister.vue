<template>
  <div class="register">
    <div class="login-logo">
      <span> 云萌极客站 | 通行证</span>
    </div>
    <h2 class="main-title"><span>云萌极客</span></h2>
    <h3 class="sub-title">注册云萌极客站，开启学习新纪元！</h3>
    <!-- <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form"> -->
    <el-form class="register-form">
    <h3 class="title">云萌极客站当前仅支持特邀用户登录使用，注册功能敬请期待！</h3>
    <!-- <el-form-item prop="username">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item> -->
    <!-- <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item> -->
    <!-- <el-form-item prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="确认密码"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item> -->
    <!-- <el-form-item prop="code" v-if="captchaOnOff">
        <el-input
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="register-code">
          <img :src="codeUrl" @click="getCode" class="register-code-img"/>
        </div>
      </el-form-item> -->
    <!-- <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleRegister"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
      </el-form-item> -->
    <div style="float: right;">
      <router-link class="link-type" :to="'/cmsLogin'">使用已有账户登录</router-link>
    </div>
    </el-form>
    <!--  底部  -->
    <div class="el-register-footer">
      <span>© 2022-2023 CloudCode Team. All Rights Reserved | </span>
      <a target="_blank" href="http://beian.miit.gov.cn">蜀ICP备2022018075号-2 | </a><a target="_blank"
        href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=51018102000210">
        <img src="https://base.cloudcode.team/webimage/beian.png" style="height: 15px;width: 15px;" />
        <span> 川公网安备 51018102000210号</span>
      </a>
    </div>
  </div>
</template>

<script>
// import { getCodeImg, register } from "@/api/login";

export default {
  // name: "Register",
  data() {
    // const equalToPassword = (rule, value, callback) => {
    //   if (this.registerForm.password !== value) {
    //     callback(new Error("两次输入的密码不一致"));
    //   } else {
    //     callback();
    //   }
    // };
    // return {
    //   codeUrl: "",
    // registerForm: {
    //     username: "",
    //     password: "",
    //     confirmPassword: "",
    //     code: "",
    //     uuid: ""
    //   },
    //   registerRules: {
    //     username: [
    //       { required: true, trigger: "blur", message: "请输入您的账号" },
    //       { min: 2, max: 20, message: '用户账号长度必须介于 2 和 20 之间', trigger: 'blur' }
    //     ],
    //     password: [
    //       { required: true, trigger: "blur", message: "请输入您的密码" },
    //       { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
    //     ],
    //     confirmPassword: [
    //       { required: true, trigger: "blur", message: "请再次输入您的密码" },
    //       { required: true, validator: equalToPassword, trigger: "blur" }
    //     ],
    //     code: [{ required: true, trigger: "change", message: "请输入验证码" }]
    //   },
    //   loading: false,
    //   captchaOnOff: true
    // };
  },
  created() {
    // this.getCode();
  },
  methods: {
    // getCode() {
    //   getCodeImg().then(res => {
    //     this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
    //     if (this.captchaOnOff) {
    //       this.codeUrl = "data:image/gif;base64," + res.img;
    //       this.registerForm.uuid = res.uuid;
    //     }
    //   });
    // },
    // handleRegister() {
    // this.$refs.registerForm.validate(valid => {
    //   if (valid) {
    //     this.loading = true;
    //     register(this.registerForm).then(res => {
    //       const username = this.registerForm.username;
    //       this.$alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", '系统提示', {
    //         dangerouslyUseHTMLString: true,
    //         type: 'success'
    //       }).then(() => {
    //         this.$router.push("/cmsLogin");
    //       }).catch(() => {});
    //     }).catch(() => {
    //       this.loading = false;
    //       if (this.captchaOnOff) {
    //         this.getCode();
    //       }
    //     })
    //   }
    // });
    // }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.register {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
  background-image: url("https://base.cloudcode.team/wechat/image/backv1.jpg");
  background-size: cover;
}

.login-logo {
  width: 400px;
  height: 24px;
  margin-right: 10px;
  padding-right: 10px;
  background-image: url("https://base.cloudcode.team/wechat/image/Logo-SCS.png");
  background-repeat: no-repeat;
  background-size: auto 24px;
  display: inline-block;
  vertical-align: middle;
  zoom: 1;
  position: absolute;
  left: 200px;
  top: 50px;

  span {
    line-height: 24px;
    font-size: 20px;
    height: 24px;
    padding-left: 30px;
    color: #ffffff;
    vertical-align: middle;
    font-weight: 600;
  }
}

.sub-title {
  margin: 20px 0 60px 0;
  text-align: center;
  color: #ffffff;
}

.main-title {
  margin: 100px 0 0 0;
  height: 34px;
  text-align: center;
  color: #ffffff;
  background-image: url("https://base.cloudcode.team/wechat/image/Logo-SCS.png");
  background-repeat: no-repeat;
  background-size: auto 34px;
  display: inline-block;
  vertical-align: middle;
  zoom: 1;

  span {
    line-height: 34px;
    font-size: 34px;
    height: 34px;
    padding-left: 40px;
    color: #ffffff;
    vertical-align: middle;
    font-weight: 600;
  }
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #ffffff;
}

.register-form {
  border-radius: 15px;
  background: rgba(20, 54, 79, 0.4);
  width: 330px;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.register-tip {
  font-size: 13px;
  text-align: center;
  color: #ffffff;
}

.register-code {
  width: 35%;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-register-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: rgb(255, 255, 255);
  font-family: Arial;
  font-size: 14px;
  letter-spacing: 1px;
}

.register-code-img {
  height: 38px;
}

.el-input__inner {
  background: rgba(255, 255, 255, 1);
}

.oauth-login {
  display: flex;
  align-items: cen;
  cursor: pointer;
}

.oauth-login-item {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.oauth-login-item img {
  height: 25px;
  width: 25px;
}

.oauth-login-item span:hover {
  text-decoration: underline red;
  color: red;
}</style>
