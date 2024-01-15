<template>
  <div class="gpt3__header" id="home">
    <div class="gpt3__header-content">
      <h1 class="gradient__text">
<!--        Let&apos;s Build Something amazing with GPT-3 OpenAI-->
        使用COCR构造自己的数据集

      </h1>
      <p>
        ChineseOCR是实现文本、公式数据识别的实用工具。使用ChineseOCR可以方便快捷的将
        图片数据中的文本信息进行提取，并对提取出的数据进行进一步的清洗。<br>
        快使用ChineseOCR来构造属于自己的数据集吧！
      </p>

      <div class="gpt3__header-content__input">
<!--        <input type="email" placeholder="Your Email Address" />-->
        <button type="button" @click="testst">开始使用</button>
      </div>

<!--      <div class="gpt3__header-content__people">-->
<!--        <img :src="people" alt="people" />-->
<!--        <p>-->
<!--          1,600 people requested access a visit in last 24 hours-->
<!--        </p>-->
<!--      </div>-->
    </div>

    <div class="gpt3__header-image">
      <img :src="ai" alt="ai" />
    </div>
  </div>
</template>

<script>
import ai from "./../assets/ai.png";
import people from "./../assets/people.png";
import filezty1 from "./../assets/file_zty1.png";
import request from "../store/utils/request.js"


export default {
  data: () => ({ ai, people, filezty1 }),
  methods:{
    testst(){
     this.$router.push("/test")
    },
  }
};

</script>

<style>
.gpt3__header {
  display: flex;
  flex-direction: row;
  margin-left: 5rem;
  margin-right: 5rem;
  /*height: 800px;*/
}

.gpt3__header-content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  flex-direction: column;

  margin-right: 5rem;
}

.gpt3__header-content h1 {
  font-family: var(--font-family);
  font-weight: 800;
  font-size: 59px;
  line-height: 75px;
  letter-spacing: -0.04em;
}

.gpt3__header-content p {
  font-family: var(--font-family);
  font-weight: 400;
  font-size: 20px;
  line-height: 28px;
  color: var(--color-text);

  margin-top: 1.5rem;
}

.gpt3__header-content__input {
  width: 100%;
  margin: 2rem 0 1rem;

  display: flex;
  flex-direction: row;
}

.gpt3__header-content__input input {
  flex: 2;
  width: 100%;
  min-height: 50px;
  font-family: var(--font-family);
  font-weight: 400;
  font-size: 20px;
  line-height: 28px;
  background: var(--color-footer);
  border: 2px solid var(--color-footer);
  padding: 0 1rem;
  outline: none;
  color: #fff;

  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}

.gpt3__header-content__input button {
  flex: 0.6;
  width: 100%;
  min-height: 50px;
  font-family: var(--font-family);
  font-weight: 400;
  font-size: 20px;
  line-height: 28px;
  background: #ff4820;
  border: 2px solid #ff4820;
  padding: 0 1rem;
  color: #fff;
  cursor: pointer;
  outline: none;

  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.gpt3__header-content__people {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: row;

  margin-top: 2rem;
}

.gpt3__header-content__people img {
  width: 181.79px;
  height: 38px;
}

.gpt3__header-content__people p {
  margin: 0 0 0 1rem;
  font-family: var(--font-family);
  font-weight: 500;
  font-size: 12px;
  line-height: 38px;
  color: #fff;
  text-align: center;
}

.gpt3__header-image {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  /*height: 95%;*/
}

.gpt3__header-image img {
  width: 97%;
  height: 100%;
  /*background-height:*/
}

@media screen and (max-width: 1050px) {
  .gpt3__header {
    flex-direction: column;
  }

  .gpt3__header-content {
    margin: 0 0 3rem;
  }
}

@media screen and (max-width: 650px) {
  .gpt3__header-content h1 {
    font-size: 48px;
    line-height: 60px;
  }

  .gpt3__header-content p {
    font-size: 16px;
    line-height: 24px;
  }

  .gpt3__header-content__people {
    flex-direction: column;
  }

  .gpt3__header-content__people p {
    margin: 0;
  }

  .gpt3__header-content__input input,
  .gpt3__header-content__input button {
    font-size: 16px;
    line-height: 24px;
  }
}

@media screen and (max-width: 490px) {
  .gpt3__header-content h1 {
    font-size: 36px;
    line-height: 48px;
  }

  .gpt3__header-content p {
    font-size: 14px;
    line-height: 24px;
  }

  .gpt3__header-content__input input,
  .gpt3__header-content__input button {
    font-size: 12px;
    line-height: 16px;
  }
}
</style>
