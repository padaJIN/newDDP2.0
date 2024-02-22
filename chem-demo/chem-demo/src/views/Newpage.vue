<template>
  <div class="full-page">
    <Navbar></Navbar>

    <div class="content">
      <h1 class="h11">
        中英文OCR大模型
      </h1>
      <div class="upload-container">
        <el-upload
            class="upload-demo style2"
            drag
            action="123"
            multiple
            :on-success="successFile"

        >
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            Drop file here or <em>click to upload</em>
          </div>
          <template #tip>
            <div class="el-upload__tip">
              jpg/pdf files with a size less than 5M
            </div>
          </template>
        </el-upload>
      </div>

      <div class="input-container">
        <div class="two_input">
          <el-input
              v-model="text1"
              maxlength="20000"
              placeholder="Please input"
              show-word-limit
              :rows="20"
              type="textarea"
              style="height: 100%;width: 100%"
          />
        </div>



        <div style="margin: 20px 0" />
        <div class="two_input">

          <vue-latex class="katex" :expression=" this.text1 " display-mode />
<!--          <el-input-->
<!--              v-model="text2"-->
<!--              id="hello"-->
<!--              maxlength="20000"-->
<!--              placeholder="Please input"-->
<!--              show-word-limit-->
<!--              :rows="20"-->
<!--              type="textarea"-->
<!--              style="height: 100%;width: 100%"-->
<!--          />-->

        </div>
      </div>



    </div>
<!--    <div class="footer">-->
<!--      &copy; 2023 AI Lab-->
<!--    </div>-->
  </div>
</template>

<script>
import logo from "../assets/ai_lab_white.svg";
// import { ElNotification as notify } from 'element-plus'
import { UploadFilled } from '@element-plus/icons-vue';
import Navbar from "../components/Navbar/Navbar.vue";


export default {
  name: "Newpage",

  data(){
    return{
      // header:{
      //   token : this.getToken()
      // },
      logo,
      text1:'{\\frac{\\mathrm{d}v^{2}}{\\mathrm{d}T}}=1-{\\frac{C_{\\mathrm{e}}R_{\\mathrm{e}}^{2}}{24G_{\\mathrm{e}}}} 应当指出，对于所有的颗粒度小于50的选项，都要谨慎进行选择。所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50所有的颗粒度小于50',

      text2:'\\frac{a_i}{1+x}'
    }
  },
  components:{
    UploadFilled,Navbar
  },
  created() {
    // this.formatMath();
  },
  methods:{
    getToken(){
      let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
      return user.token
    },
    successFile(res){
      // console.log(res)
      const file = res;
      this.text2 = file;
      this.text1 = file;
    },
    gohomepage(){
      this.$router.push("/")
    },
  }
}
</script>

<style>

.katex .base {
    white-space: pre-wrap;
    display: contents;
}
.katex-display>.katex {
    text-align: left;
}
.two_input{
  height: 400px;
  width: 500px;
  color: black;
}

.h11{
  font-family: var(--font-family);
  font-weight: 800;
  font-size: 59px;
  line-height: 75px;
  letter-spacing: -0.04em;
  /*background: linear-gradient(to right, #80003a, #824500, #003cff, #420080, yellow, orange, red); !* 彩虹渐变 *!*/
  /*-webkit-background-clip: text; !* 设置背景裁剪为文字 *!*/
  /*-webkit-text-fill-color: transparent;*/
}

.footer {
  background-color: #333; /* 页脚背景色 */
  color: white; /* 文本颜色 */
  text-align: center; /* 文本居中 */
  padding: 20px 0; /* 上下边距 */
}

.custom-input {
  width: 500px; /* 设置宽度 */
  height: 400px;
  border-color: #ff9900; /* 设置边框颜色 */
  border-radius: 8px; /* 设置边框圆角 */
  font-size: 16px; /* 设置字体大小 */

}

.gradient-background {
  margin: 0;
  padding: 0;
  height: 100vh; /* 让背景铺满整个页面 */
  background: linear-gradient(to bottom, #cdf8fc, #ffffff); /* 从浅蓝色到白色的渐变 */
}
.full-page {
  height: 500px; /* 确保页面铺满整个视口高度 */
  display: flex;
  flex-direction: column;
  background: linear-gradient(to bottom, #0557a6, #ffffff); /* 整个页面的背景 */
}

.gpt3__navbar-links_log img {
  width: 183px;
  height: 87px;
}

/* 页头样式 */
.header {
  display: flex;
  align-items: center;
  margin-top: 10px;
  height: 60px; /* 页头高度 */
  position: relative; /* 使得 z-index 生效需要使用 position */
  z-index: 1; /* 提高页头的堆叠顺序 */
}

/* 分隔符样式 */
.separator {
  margin: 0 10px; /* 分隔符间距 */
  color: white; /* 设置分隔符颜色为白色 */
  font-weight: bold; /* 设置分隔符字体粗细 */
}

/* 页面内容样式 */
.content {
  flex: 1; /* 充满剩余空间 */
  padding: 20px; /* 为内容添加一些内边距 */
  position: relative; /* 使得 z-index 生效需要使用 position */
  z-index: 0; /* 默认堆叠顺序较低 */
}

.header-text {
  font-size: 24px; /* 设置字体大小 */
  font-weight: bold; /* 设置字体粗细 */
  color: white; /* 设置字体颜色为白色 */
}

.login-text {
  font-size: 18px; /* 设置字体大小 */
  color: white; /* 设置字体颜色为白色 */
}

.left-section {
  display: flex;
  align-items: center;
  margin-left: 300px; /* 设置左侧部分距离页面左边的距离 */
}

/* 右侧部分样式 */
.right-section {
  display: flex;
  align-items: center;
  margin-left: 700px;
}

.big_title{
  font-size: 48px; /* 设置字体大小 */
  background: linear-gradient(to right, violet, indigo, #fff200, #80005c, yellow, orange, red); /* 彩虹渐变 */
  -webkit-background-clip: text; /* 设置背景裁剪为文字 */
  -webkit-text-fill-color: transparent;
  /*margin-top: 50px;*/
}

.upload-container {
  background: transparent; /* 背景为透明 */
  width: 100vh; /* 设置宽度为700px */
  margin-top: 5vh;
  margin-bottom: 5vh;
  margin-left: 55vh;
}

.style2{
  background: transparent ! important; /* 背景为透明 */
}

.input-container {
  display: flex; /* 使用 Flex 布局 */
  gap: 20px; /* 设置两个输入框的间距 */
  justify-content: center;
  height: 400px;
}



</style>