<template>
  <div class="nave_color">
    <app-navbar />
  </div>
  <div class="login_container" style="position:relative">
      <div style="width: 300px;  position:absolute; top:150px;left:50%;margin-left:-150px;" >
          <div style="color: black;front-size: 30px; text-align:center; padding:30px">
            欢迎注册
          </div>
          <el-form ref="form" :inline="true" :rules="rules" :model="formInline" size="normal" label-position="left" label-width="100px">
            <el-form-item label="用户名" class="item" prop="username">
              <el-input v-model="formInline.username" placeholder="请输入" ></el-input>
            </el-form-item>
            <el-form-item label="密码" class="item" prop="password">
              <el-input v-model="formInline.password" placeholder="请输入" show-password></el-input>
            </el-form-item>
            <el-form-item label="邮箱" class="item" prop="email">
              <el-input v-model="formInline.email" placeholder="请输入" ></el-input>
            </el-form-item>
            <el-form-item label="电话" class="item" prop="phone">
              <el-input v-model="formInline.phone" placeholder="请输入" ></el-input>
            </el-form-item>
            <el-button type="primary" style="margin-left:120px" @click="newsignup">注册</el-button>
          </el-form>
      </div>
  </div>
</template>

<script>
import request from "../store/utils/request"
import Navbar from "../components/Navbar/NavbarBlack.vue";


export default {
  name: "Signup",
  components: {
    "app-navbar": Navbar
  },
  data(){
    return{
      formInline:{},
      rules:{
        username:[
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          }
        ],
        password:[
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          }
        ],
        email:[
          {
            required: false,
            message: '请输入邮箱',
            trigger: 'blur',
          }
        ]
      }
    }
  },
  methods:{
    newsignup(){
      request.post("/login/register",{
        username:this.formInline.username,
        password:this.formInline.password
      }).then(res=>{
        console.log("yes")
        this.$message({
          type:"success",
          message:"注册成功"
        })
        this.$router.push('/login')
      })
    }
  }
}
</script>

<style >

.item .el-form-item__label{
  color: black;
}

.login_container{
  background-color: white;
  height: 100vh;
  background-size: cover;

}
.nave_color{
  /*background-color: #55afea;*/
  background: linear-gradient(to bottom, #0557a6, #ffffff)
}

</style>