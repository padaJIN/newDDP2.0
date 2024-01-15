<template>
  <div class="gpt3__whatgpt3 section__margin" id="wgpt3">
    <!--    <div class="gpt3__whatgpt3-feature">-->
    <!--      <feature-->
    <!--          title="About AILab"-->
    <!--          text="大连理工大学软件学院"-->
    <!--      />-->
    <!--    </div>-->
    <div class="gpt3__features-container__feature">
      <div class="gpt3__features-container__feature-title">
        <div></div>
        <h1>About AILab</h1>
      </div>
      <!--      <div class="gpt3__features-container_feature-text">-->
      <!--        <div>        <p>大连理工大学软件学院</p></div>-->

      <!--    </div>-->
    </div>
    <div class="gpt3__whatgpt3-heading" style="margin-top: 20px;">
      <div>
        <h1 class="gradient__text">
          AILab——大连理工大学软件学院
        </h1>
      </div>
    </div>
    <!--      <p>Explore the Library</p>-->
    <div style="color: var(--color-subtext);color: var(--color-text);width: 90%;text-align: left;font-weight: 500;">
      我们是一支专注于人工智能、深度学习和数据挖掘等相关领域的研究团队。allab实验室以推动算法研究为己任，致力于将先进的算法应用于自然语言处理、计算机视觉、智能决策、机器人等多个领域，为这些应用领域提供智能、高效和可持续的解决方案。
    </div><!--      <div>从事人工智能、深度学习、数据挖掘等相关领域的算法研究工作，致力于把提出的算法应用于自然语言处理、计算机视觉、智能决策、机器人等各个方向。</div>-->
    <div class="gpt3__whatgpt3-container">
      <div class="gpt3__features-container__feature">
        <div class="gpt3__features-container__feature-title"><div></div>
          <h1>研究方向</h1>
        </div>
        <div class="gpt3__features-container_feature-text">
          <p>我们的研究方向涵盖了人工智能领域的多个关键领域，包括但不限于自然语言处理、计算机视觉、智能决策和机器人等领域</p>
        </div>
      </div>
      <div class="gpt3__features-container__feature">
        <div class="gpt3__features-container__feature-title">
          <div></div>
          <h1>研究团队</h1>
        </div>
        <div class="gpt3__features-container_feature-text">
          <p>我们的研究团队充满活力，包括研究科学家，博士/硕士研究生等，也欢迎有科研热情的本科同学加入，共同致力于解决人工智能领域的重大挑战。</p>
        </div>
      </div>
      <div class="gpt3__features-container__feature">
        <div class="gpt3__features-container__feature-title">
          <div></div>
          <h1>合作与创新</h1>
        </div>
        <div class="gpt3__features-container_feature-text">
          <p>为促进研究的深入和发展，我们鼓励内外部合作，与学术界、工业界及其他研究机构建立紧密联系。分享知识和资源，致力于推动人工智能领域创新。</p>
        </div>
      </div>
    </div>
    <!--    </div>-->
    <!--    <div class="gpt3__whatgpt3-container">-->
    <!--      <feature-->
    <!--          title="Chatbots"-->
    <!--          text="We so opinion friends me message as delight. Whole front do of plate heard oh ought."-->
    <!--      />-->
    <!--      <feature-->
    <!--          title="Knowledgebase"-->
    <!--          text="At jointure ladyship an insisted so humanity he. Friendly bachelor entrance to on by. As put impossible own apartments b"-->
    <!--      />-->
    <!--      <feature-->
    <!--          title="Education"-->
    <!--          text="At jointure ladyship an insisted so humanity he. Friendly bachelor entrance to on by. As put impossible own apartments b"-->
    <!--      />-->
    <!--    </div>-->
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
