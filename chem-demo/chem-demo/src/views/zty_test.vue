<template>
  <div>
    <mavon-editor ref="md" v-model="value" :ishljs="true" />
  </div>


</template>

<script>
import CherryMarkDownCon from "@/components/Markdown/CherryMarkDownCon";

export default {
  components:{
    CherryMarkDownCon
  },
  data() {
    return {
      value:'???'
    };
  },
  mounted() {
  },

};
</script>

<style >
.article-editor {
  width: 1000px;
  margin: 0 auto;
  height: 100%;
  border: 1px solid grey;
  padding: 5px;
  border-radius: 5px;
}

.top {
  display: flex;
  height: 40px;
  line-height: 40px
}

.opt {
  text-align: right;
  padding-right: 5px;
}
</style>
