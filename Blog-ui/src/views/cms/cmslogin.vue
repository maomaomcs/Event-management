<template>
  <div class="login">
    <div class="login-logo">
      <span> 云萌极客站 | 通行证</span>
    </div>
    <h2 class="main-title"><span>云萌极客</span></h2>
    <h3 class="sub-title">登录云萌极客站，开启学习新纪元！</h3>
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
          @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>

      <el-form-item prop="code" v-if="captchaOnOff">
        <el-input
          v-model="loginForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <div style="float: right;" v-if="register">
          <router-link class="link-type" :to="'/cmsRegister'">立即注册</router-link>
        </div>
      <el-form-item style="width:100%;">
        <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
          @click.native.prevent="handleLogin">
          <span v-if="!loading">登 录</span>
          <span v-else>正在登录中...</span>
        </el-button>
        
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
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
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'

export default {
  name: "CCLogin",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      // 验证码开关
      captchaOnOff: true,
      // 注册开关
      register: true,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
        if (this.captchaOnOff) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({ path: "/cms/main/cmsIndex" });
          }).catch(() => {
            this.loading = false;
            if (this.captchaOnOff) {
              this.getCode();
            }
          });
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
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
.sub-title {
  margin: 20px 0 60px 0;
  text-align: center;
  color: #ffffff;
}


.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
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

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #ffffff;
}

.login-code {
  width: 35%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
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

.login-code-img {
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
} 
</style>
