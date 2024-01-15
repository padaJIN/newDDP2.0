<template>
  <Navbar></Navbar>
  <div class="home-container">
    <div><span class="rounded-font">DPS</span>
      <!--      <img src="../assets/images/analytics.png">-->
    </div>
    <div class="search-bar">
      <input type="text" v-model="searchQuery" placeholder="查询化工相关学术内容">
      <button @click="search">
        <i class="arrow-icon"></i>
      </button>
    </div>
    <div class="function-buttons">
      <button @click="handleFunction('button1')">用户管理</button>
      <button @click="handleFunction('button2')">数据爬取</button>
      <button @click="handleFunction('button3')">文本翻译</button>
      <button >待探索</button>
    </div>
  </div>
</template>

<script>
import Navbar from "../components/Navbar/Navbar.vue";


export default {
  name:'SearchView',
  components:{
    Navbar
  },
  data() {
    return {
      searchQuery: '' // 用来存储查询关键词
    };
  },
  methods: {
    search() {
      // 这里可以处理搜索逻辑
      if (this.searchQuery) {
        window.location.href = `https://xueshu.baidu.com/s?wd=${this.searchQuery}`;
      } else {
        alert('请输入搜索关键词');
      }
    },
    handleFunction(button) {
      // 这里可以处理功能按钮的逻辑
      if(button = 'button1'){
        this.$router.push('/mydatabase');
      }
      if(button = 'button2'){
        this.$router.push('/dataScrap');
      }
      if(button = 'button3'){
        this.$router.push('/transCHN');
      }

    }
  }
};
</script>

<style>

body {
  background-color: #040c18;
}

.home-container {
//background-color: #043f7852;
  background: linear-gradient(to bottom, #051427, #043f78 80%, #030c18);
  height: 60vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
//height: 50vh;
}

.search-bar {
  display: flex;
  margin-bottom: 20px;
  margin-top: 10px;
}

.search-bar input {
  padding: 8px;
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 4px;
  height: 110%;
}

.search-bar button {
  padding: 8px 15px;
  margin-left: 10px;
  background-color: #f4f7f9;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 80px;
  height: 110%;
}

.function-buttons button {
  padding: 10px 20px;
  margin: 5px;
  background-color: #052c53;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  width: 100px;
}
.rounded-font {
  font-family: 'Arial', sans-serif; /* 使用一个 sans-serif 的字体可以让字体看起来更加圆润 */
  padding: 10px; /* 增加一些内边距 */
  color: #ccd0d4;
  font-size: 50px;
  margin: 20px;

}

button {
  border: none;
  background: none;
  cursor: pointer;
}

.arrow-icon {
  /* 通过 CSS 设置箭头的样式 */
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  transform: rotate(-45deg);
}

img {
  margin-left: 10px; /* 调整图片和文字之间的间距 */
  width: 40px;
  height: 40px;
  margin-right: 10px;
}
</style>
