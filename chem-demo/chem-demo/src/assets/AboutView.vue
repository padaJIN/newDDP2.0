<template>
  <div class="about">
    <Navbar>

    </Navbar>
    <what-lab>

    </what-lab>
    <AboutCOCR></AboutCOCR>
  </div>
</template>

<script>
import "../assets/css/index.css";
import Navbar from "../components/Navbar/Navbar.vue";
import WhatLab from "../components/Whatisailab/WhatLab"
import AboutCOCR from "@/components/Whatisailab/AboutCOCR";

export default {
  name: 'about',
  components: {
    Navbar,WhatLab,AboutCOCR
  }
};
</script>

<style>

body {
  background-color: #040c18;
}

</style>
