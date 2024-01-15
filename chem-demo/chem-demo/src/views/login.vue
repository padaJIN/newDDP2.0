<template>
  <div class="main" style="height: 100%;">
  <div class="login">

    <header class="navbar">
      <app-navbar />
    </header>
    <div class="main0">
      <div class="main_left" style="height: 100%">
        <img src="../assets/images/small.png" class="firimg">
      </div>
      <div class="main_right">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form loginBox">
      <h1 class="title">数据处理平台</h1>
      <div class="sub"></div>
      <div class="username_input">
        <div class="username_left">
          <spin class="input_left">用户名：</spin>
        </div>
        <div class="username_right">
          <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
          >
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
          </el-input>
<!--      </div>-->
      </el-form-item>
        </div>
      </div>
      <div class="username_input">
        <div class="username_left">
          <spin class="input_left">密码：</spin>
        </div>
        <div class="username_right">
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
        </div>
      </div>
      <div class="username_input">
          <div class="username_left">
          <spin class="input_left">验证码：</spin>
        </div>
        <div class="username_right">
      <el-form-item prop="code" v-if="captchaEnabled">
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
        </div>
      </div>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">remember me </el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="text-align: center;
                 width: 70%;
                 margin-left: 15%;
                 height: 50px;
                 border-radius: 10px;
                 background: #2b6195e3;
                 font-size: medium;
                 border-color: #024b74;"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <div style="float: right;" v-if="register">
          <router-link class="link-type" :to="'/register'">立即注册</router-link>
        </div>

      </el-form-item>
    </el-form>
      </div>
   </div>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>@padaJIN</span>
    </div>
  </div>
  </div>
</template>

<script>
import request from "../store/utils/request"
import Navbar from "../components/Navbar/Navbar.vue";

export default {
  name: "Login",
  components: {
    "app-navbar": Navbar
  },
  data() {
    return {
      // UserDTO:{
      //   username:'',
      //   password:''
      // },
      username:'',
      password:'',
      codeUrl: "",
      loginForm: {
        username: "admin",
        password: "123456",
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
      captchaEnabled: true,
      // 注册开关
      register: false,
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

    },
    getCookie() {

    },
    handleLogin() {
      this.username = this.loginForm.username
      this.password = this.loginForm.password
      request.post("/login/login",{
        username:this.username,
        password:this.password
      }).then(res=>{
        console.log(res)
        localStorage.setItem("user", JSON.stringify(res))  // 存储用户信息到浏览器
        this.$message({
          type:"success",
          message:"登陆成功"
        })
        this.$router.push('/')
      })
    }
  }
};
</script>

<style scoped>
</style>
<!--<style  rel="stylesheet/scss" lang="scss">-->
<style >

.el-input--medium .el-input__inner {
  /*//background-color:*/
    background: #ffffff73;
    border: 1px solid #1010108a
}

.username_input{
  height: 40px;
  margin-bottom: 20px;
}
.username_right{
    width: 70%;
    height: 100%;
    float:right;
  //width: 30%;
    display: inline-block;
    /* line-height: 36px; */
    margin: auto;
    text-align: center;
    letter-spacing: 8px;
}
.username_left{
  width: 30%;
  //width: 30%;
    display: inline-block;
    /* line-height: 36px; */
    margin: auto;
    text-align: center;
    letter-spacing: 8px;
}
.yanzhengma_left{
  width: 20%;
  //width: 30%;
    display: inline-block;
    /* line-height: 36px; */
    margin: auto;
    text-align: center;
    letter-spacing: 8px;
}
.input_left{
  width: 90px;
    font-size: 14px;
    color: #3a3838;
    height: 40px;
    line-height: 40px;
}
.sub {
    width: 110%;
    height: 10px;
    margin: 5px -5% 30px -5%;
    font-size: 14px;
    color: #666;
    border-bottom: 1px solid rgb(0 0 0 / 0.4);
}
.title{
    font-family: Microsoft YaHei;
    margin: 0px auto 0px auto;
    text-align: center;
    color: #0f0f0f;
    font-size: xx-large;
    font-weight: normal;
    font-style: normal;
}
.main{
    width: 100%;
  height: 100%;
    background: url(../assets/images/bk-login.png) no-repeat;
    background-size: 100% 100%;
    overflow: hidden;
}
.main_left {
    float: left;
    width: 60%;
    position: relative;
}

.main_left img {
    position: absolute;
    width: 100%;
    height: 100%;
    opacity: 0.7;
    top: 0px;
    left: 0px;
}
.main_right{
  height: 100%;
    float: right;
    width: 40%;
}
.loginBox {
            width: 100%;
            height: 100%;
            //background-color: #0c1622;
            margin: 30px auto;
            border-radius: 10px;
            //box-shadow: 0 15px 25px 0 rgba(0, 0, 0, .6);
            padding: 40px;
            box-sizing: border-box;
        }
.el-input__inner{

}


header{
  height: 55px;
}
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  //background-image: url("../assets/images/bg12.jpg");
  //background-color: #212529;
  background-size: cover;
}
.main0{
    width: 1000px;
    height: 500px;
    background: #ffffff91;
    margin: 0 auto;
    margin-top: 10px;
    -webkit-box-shadow: 0 15px 25px 0 rgba(0, 0, 0, 0.6);
}
h1 {
            text-align: center;
            color: aliceblue;
            margin-bottom: 30px;
            font-family: 'Courier New', Courier, monospace;
        }




.input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
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


.form-control-dark {
  border-color: var(--bs-gray);
}
.form-control-dark:focus {
  border-color: #fff;
  box-shadow: 0 0 0 .25rem rgba(255, 255, 255, .25);
}

.text-small {
  font-size: 85%;
}

.dropdown-toggle:not(:focus) {
  outline: 0;
}

 .navbar {
   position: fixed;
   top: 0;
   left: 0;
   width: 100%;
   padding: 10px;
   /* 其他样式属性 */
 }

.navbar  ul {
  display: flex;

  list-style-type: none;
  margin: 0;
  padding: 0;
  justify-content: flex-end;
}

.navbar  ul li {
  align-items: center;
  margin-left: 10px;
  margin-top: -15px;
  font-family: 华文琥珀;
  font-size: large;
}

.navbar  ul li a {
  text-decoration: none;
  color: #333;
  /* 其他样式属性 */
}

.svg1{
  width: 50px;
  height: 50px;
}
</style>
