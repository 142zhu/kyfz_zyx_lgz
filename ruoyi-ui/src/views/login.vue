<template>
  <div class="login">
    <div class="login-header"></div>
    <div class="login-container">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
        <h3 class="title">中山大学有组织科研辅助系统</h3>
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
        <el-form-item prop="code" v-if="captchaEnabled">
          <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%"
            @keyup.enter.native="handleLogin">
            <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img" />
          </div>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin: 0px 0px 25px 0px;color:black">记住密码</el-checkbox>
        <el-form-item style="width: 100%">
          <el-button :loading="loading" size="medium"
            style="width: 100%;background-color:#005825;color:#fff;font-size:15px;font-weight:700"
            @click.native.prevent="handleLogin">
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
          <div style="float: right" v-if="register">
            <router-link class="link-type" :to="'/register'">立即注册</router-link>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div class="footer">
      <ul>
        <li>版权所有：中山大学</li>
        <li>技术支持：中山大学网络与信息中心</li>
        <li>浏览器支持：</li>
        <li>相关链接：<a href="https://www.sysu.edu.cn/" style="color:white;">https://www.sysu.edu.cn/</a></li>
        <li>联系方式：020-84036866</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
import { decrypt, encrypt } from "@/utils/jsencrypt";
import Cookies from "js-cookie";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
        code: "",
        uuid: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }],
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: false,
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then((res) => {
        this.captchaEnabled =
          res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password:
          password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), {
              expires: 30,
            });
            Cookies.set("rememberMe", this.loginForm.rememberMe, {
              expires: 30,
            });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
          }
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" }).catch(() => { });
            })
            .catch(() => {
              this.loading = false;
              if (this.captchaEnabled) {
                this.getCode();
              }
            });
        }
      });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  font-size: 25px;
  font-weight: 700;
  color: black;
}

.login-form {
  position: relative;
  border-radius: 6px;
  top: 150px;
  //background: #ffffff;
  background: rgba(255, 255, 255, 0.3);
  max-width: 400px;
  /* 修改为 max-width */
  padding: 25px;
  /* 简化 padding 样式 */
  margin: 0 auto;
  /* 添加 margin 样式，实现水平居中 */
  justify-content: center;
  align-items: center;

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
  color: #bfbfbf;
}

.login-code {
  width: 33%;
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
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 38px;
}

.login-header,
.login-container,
.footer {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.login-header {
  top: 0;
  width: 100%;
  height: 110px;
  background: url("../assets/images/header.png") center center no-repeat;
  background-size: cover;
}

.login-container {
  top: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 70%;
  background: url("../assets/images/login-background2.jpg") no-repeat;
  background-size: cover;
}

.footer {
  bottom: 0;
  width: 100%;
  height: 60px;
  color: white;
  background: url("../assets/images/footer.png") center center no-repeat;
}

.footer ul {
  margin: 0;
  padding: 0;
  list-style: none;
  line-height: 60px;
  text-align: center;
}

.footer li {
  display: inline-block;
  margin-right: 20px;
}

.footer li:last-child {
  margin-right: 0;
}

.footer a {
  text-decoration: none;
}
</style>
