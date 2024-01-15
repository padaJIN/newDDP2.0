<template>
  <div>
    <h1 class="custom-heading">中文PDF转TXT</h1>
    <el-upload
        :before-upload="FormatD"
        class="upload-demo"
        drag
        action="http://192.168.50.59:8080/FileDeal/fileDeal"
        multiple
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text" >将pdf文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能pdf格式文件,文件不超过500M</div>
      <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    </el-upload>
  </div>
</template>

<script>
export default {
  name:"test",
  data() {
    return {
      selectedFile: null,
      errorMessage: ""
    };
  },
  methods: {
    FormatD(file) {
      // const file = event.target.files[0];
      if (file) {
        // 执行文件格式检查
        if (this.isValidFileFormat(file)) {
          this.selectedFile = file;
          this.errorMessage = "文件已上传,正在处理请稍等";
        } else {
          this.$message.error("请上传符合要求的PDF格式数据,上传已取消");
          return false; // 阻止文件上传
        }
      }
    },
    isValidFileFormat(file) {
      // 在这里添加您的文件格式检查逻辑，例如检查文件的扩展名或MIME类型
      const allowedFormats = ["application/pdf"];
      return allowedFormats.includes(file.type);
    },
  },
};
</script>


<style scoped>
/* 添加样式来美化页面 */
.custom-heading {
  font-size: 34px;
  color: #FFFFFF;
  text-align: center;
  background-color: #304156;
  border-radius: 30px;
  /* 可以添加更多样式属性 */
}
</style>