<template>
  <div class="gpt3__whatgpt3 section__margin" id="wgpt3">
    <div class="gpt3__whatgpt3-feature">
      <feature
          title="What is Chinese OCR"
          text="Pix2Text (P2T)  利用最新的OCR技术识别图片中的数学公式和文字，把数学公式转换为Latex纯文字表示。Pix2Text (P2T) 期望成为 Mathpix 的免费开源 Python 替代工具，目前已经可以完成 Mathpix 的核心功能，支持识别既包含文字又包含公式的混合图片。"
      />
    </div>
    <div class="gpt3__whatgpt3-heading">
      <h1 class="gradient__text">
        ChineseOCR,好用超乎你想象
      </h1>
      <p>Explore the Library</p>
    </div>
    <div class="gpt3__whatgpt3-container">
      <feature
          title="Chatbots"
          text="We so opinion friends me message as delight. Whole front do of plate heard oh ought."
      />
      <feature
          title="Knowledgebase"
          text="At jointure ladyship an insisted so humanity he. Friendly bachelor entrance to on by. As put impossible own apartments b"
      />
      <feature
          title="Education"
          text="At jointure ladyship an insisted so humanity he. Friendly bachelor entrance to on by. As put impossible own apartments b"
      />
    </div>
  </div>
</template>
<script>
import Feature from "./Features.vue";
export default { components: { Feature } };
</script>
<style>
.gpt3__whatgpt3 {
  display: flex;
  flex-direction: column;

  padding: 2rem;
  background: var(--color-footer);

  /* ff 3.6+ */
  background: -moz-radial-gradient(
      circle at 30% -100%,
      #042c54 25%,
      rgba(4, 44, 84, 1) 85%,
      rgba(27, 120, 222, 1) 100%
  );

  /* safari 5.1+,chrome 10+ */
  background: -webkit-radial-gradient(
      circle at 30% -100%,
      #042c54 25%,
      rgba(4, 44, 84, 1) 85%,
      rgba(27, 120, 222, 1) 100%
  );

  /* opera 11.10+ */
  background: -o-radial-gradient(
      circle at 30% -100%,
      #042c54 25%,
      rgba(4, 44, 84, 1) 85%,
      rgba(27, 120, 222, 1) 100%
  );

  /* ie 10+ */
  background: -ms-radial-gradient(
      circle at 30% -100%,
      #042c54 25%,
      rgba(4, 44, 84, 1) 85%,
      rgba(27, 120, 222, 1) 100%
  );

  /* global 92%+ browsers support */
  background: radial-gradient(
      circle at 30% -100%,
      #042c54 25%,
      rgba(4, 44, 84, 1) 85%,
      rgba(27, 120, 222, 1) 100%
  );

  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.gpt3__whatgpt3-feature {
  display: flex;
}
.gpt3__whatgpt3-feature .gpt3__features-container__feature {
  margin: 0;
}

/* Customizing feature component as per needs */
.gpt3__whatgpt3-feature .gpt3__features-container_feature-text {
  max-width: 700px;
}

.gpt3__whatgpt3-heading {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  margin: 4rem 0 2rem;
}
.gpt3__whatgpt3-heading h1 {
  font-size: 34px;
  line-height: 45px;
  font-weight: 800;
  font-family: var(--font-family);
  max-width: 510px;
}

.gpt3__whatgpt3-heading p {
  font-family: var(--font-family);
  font-weight: 500;
  font-size: 16px;
  line-height: 30px;
  color: var(--color-subtext);
  cursor: pointer;
}

.gpt3__whatgpt3-container {
  display: flex;
  flex-wrap: wrap;
  /* flex-direction: row; */

  margin-top: 2rem;
}

/* Customizing feature component as per needs */
.gpt3__whatgpt3-container .gpt3__features-container__feature {
  flex: 1;
  margin: 1rem;
  min-width: 210px;
  display: unset;
  flex-direction: column;
}

/* Customizing feature component as per needs */
.gpt3__whatgpt3-container .gpt3__features-container_feature-text {
  margin-top: 0.5rem;
}

@media screen and (max-width: 850px) {
  .gpt3__whatgpt3-heading {
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 0rem;
  }

  .gpt3__whatgpt3-heading p {
    margin-top: 1rem;
  }
}

@media screen and (max-width: 650px) {
  /* Customizing feature component as per needs */
  .gpt3__whatgpt3-feature .gpt3__features-container__feature {
    flex-direction: column;
  }

  /* Customizing feature component as per needs */
  .gpt3__whatgpt3-feature .gpt3__features-container_feature-text {
    margin-top: 0.5rem;
  }

  .gpt3__whatgpt3-heading h1 {
    font-size: 28px;
    line-height: 40px;
  }
}

@media screen and (max-width: 350px) {
  /* Customizing feature component as per needs */
  .gpt3__whatgpt3-container .gpt3__features-container__feature {
    margin: 1rem 0;
    min-width: 100%;
  }
}
</style>
