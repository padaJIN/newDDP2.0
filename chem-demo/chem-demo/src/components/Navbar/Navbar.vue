<template>
  <div class="gpt3__navbar">
    <div class="gpt3__navbar-links">
      <div class="gpt3__navbar-links_log">
        <img :src="logo" alt="logo" @click="gohomepage"/>
      </div>
      <div class="gpt3__navbar-links_container">
        <p v-for="({ name, href }, index) in menuItems" :key="index + name">
          <a :href="href">{{ name }}</a>
        </p>
      </div>
    </div>
    <div class="gpt3__navbar-sign">
      <el-dropdown>
        <span class="el-dropdown-link zty" @click="oppsignin">
                    {{user.username}}
          <!-- <el-icon class="el-icon--right">
              <arrow-down />
          </el-icon> -->
                    <el-icon><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
<!--            <el-dropdown-item @click="myinfo">我的信息</el-dropdown-item>-->
            <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <button type="button" @click="handleToggleMenu">Sign up</button>
    </div>
    <div class="gpt3__navbar-menu">
      <Fragment @click="handleToggleMenu">
        <close-icon v-if="toggleMenu" />
      </Fragment>
      <Fragment @click="handleToggleMenu">
        <menu-icon v-if="!toggleMenu" />
      </Fragment>
      <div
        v-if="toggleMenu"
        class="gpt3__navbar-menu_container scale-up-center"
      >
        <div class="gpt3__navbar-menu_container-links">
          <p v-for="({ name, href }, index) in menuItems" :key="index + name">
            <a :href="href">{{ name }}</a>
          </p>
        </div>
        <div class="gpt3__navbar-menu_container-links-sign">
          <p>Sign in</p>
          <button type="button">Sign up</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import logo from "../../assets/ai_lab_white.svg";
import RiCloseLine from "./RiCloseLine.vue";
import RiMenu3Line from "./RiMenu3Line.vue";
import { ArrowDown } from '@element-plus/icons-vue'

export default {
  data: () => ({
    logo,
    title: "something",
    toggleMenu: false,
    menuItems: [
      { name: "What is COCR", href: "about" },
      { name: "COCR", href: "newpage" },
      { name: "Dataset", href: "dataset" },
      { name: "Search", href: "search" }
    ],
    user: {
      username : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : "Log in"

    }
  }),
  components: {
    "menu-icon": RiMenu3Line,
    "close-icon": RiCloseLine
  },
  methods: {
    logout(){
      localStorage.removeItem("user")
      // localStorage.removeItem("menus")
      this.$router.push("/login")
      this.$router.go(0)
    },
    oppsignin(){
      this.$router.push('/login')
      console.log("wehy")
    },
    handleToggleMenu() {
      this.$router.push('/signup')
      console.log("here");

      this.toggleMenu = !this.toggleMenu;
    },
    gohomepage(){
      this.$router.push('/')
    }
  }
};
</script>
<style scoped>
.gpt3__navbar {
  display: flex;
  justify-content: space-between;
  margin-left: 4rem;
  margin-right: 4rem;
  /* margin-top: 2vh; */
  height: 10vh;
  /*padding: 2rem 6rem;*/
}

.gpt3__navbar-links {
  flex: 1;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-top: 10px;
}

.gpt3__navbar-links_log img {
  width: 183px;
  height: 87px;
}
.gpt3__navbar-links_container {
  display: flex;
}

.gpt3__navbar-sign {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.gpt3__navbar-links_container p,
.gpt3__navbar-sign p,
.gpt3__navbar-sign span,
.gpt3__navbar-menu p {
  color: #fff;
  font-family: var(--font-family);
  font-weight: 500px;
  font-size: 18px;
  line-height: 25px;
  text-transform: capitalize;

  margin: 0 1rem;
  cursor: pointer;
}

.gpt3__navbar-sign button,
.gpt3__navbar-menu button {
  padding: 0.5rem 1rem;
  color: #fff;
  font-family: "Manrope";
  font-weight: 500;
  font-size: 18px;
  line-height: 25px;
  border: none;
  background: #ff4820;
  border-radius: 5px;
}

.gpt3__navbar-menu {
  margin-left: 1rem;

  display: none;
  position: relative;
}
.gpt3__navbar-menu svg {
  cursor: pointer;
}

.gpt3__navbar-menu_container {
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  flex-direction: column;

  text-align: end;

  background: var(--color-footer);
  padding: 2rem;
  position: absolute;
  top: 40px;
  right: 0;
  margin-top: 1rem;
  min-width: 210px;
  border-radius: 5px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
}

.gpt3__navbar-menu_container p {
  margin: 1rem 0;
}

.gpt3__navbar-menu_container-links-sign {
  display: none;
}

@media screen and (max-width: 1050px) {
  .gpt3__navbar-links_container {
    display: none;
  }

  .gpt3__navbar-menu {
    display: flex;
  }
}

@media screen and (max-width: 700px) {
  .gpt3__navbar {
    padding: 2rem 4rem;
  }
}

@media screen and (max-width: 550px) {
  .gpt3__navbar {
    padding: 2rem;
  }

  .gpt3__navbar-sign {
    display: none;
  }

  .gpt3__navbar-menu_container {
    top: 20px;
  }

  .gpt3__navbar-menu_container-links-sign {
    display: block;
  }
}
</style>
