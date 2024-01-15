<template>
  <div class="body">
    <div class="nave_color">
      <NavbarBlack></NavbarBlack>
    </div>


    <div class="outer-container">

      <div class="inner-container">
        <el-table ref="multipleTable" :data="tableData" border style="width: 100% ; height: 90%"
                  @selection-change="handleSelectionChange"
                  :default-selection="selectedRows"
          >
          <el-table-column type="selection" width="55" />
          <el-table-column prop="fileName" label="文件名称" width="180" />
          <el-table-column prop="uploadDate" label="上传时间" width="180" />
          <el-table-column prop="remark" label="备注" />
        </el-table>


        <div class="center-container">
          <el-pagination
              v-model:currentPage="currentPage4"
              v-model:page-size="pageSize4"
              :page-sizes="[5, 10, 20]"
              :small="small"
              :disabled="disabled"
              :background="background"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange">
          </el-pagination>
        </div>


      </div>

      <div class="inner-container">
        <el-form :model="form" ref="myForm" label-width="120px" style="margin-top:50px">
          <el-form-item label="文件名">
            <el-input v-model="form.filenamelist" clearable style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="起始时间" >
            <el-col :span="11">
              <el-form-item >
                <el-date-picker
                    v-model="form.date1"
                    type="date"
                    label="Pick a date"
                    placeholder="Pick a day"
                    format="YYYY/MM/DD"
                    value-format="YYYY-MM-DD"
                    style="width: 80%"
                />
              </el-form-item>
            </el-col>
            <el-col class="text-center" :span="1">
              <span class="text-gray-500">-</span>
            </el-col>
            <el-col :span="11">
              <el-form-item >
                <el-time-picker
                    v-model="form.date2"
                    label="Pick a time"
                    placeholder="Pick a time"
                    format="HH:mm:ss"
                    value-format="HH:mm:ss"
                    style="width: 80%"
                />
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="终止时间" >
            <el-col :span="11">
              <el-form-item >
                <el-date-picker
                    v-model="form.date3"
                    type="date"
                    label="Pick a date"
                    placeholder="Pick a day"
                    format="YYYY/MM/DD"
                    value-format="YYYY-MM-DD"
                    style="width: 80%"
                />
              </el-form-item>
            </el-col>
            <el-col class="text-center" :span="1">
              <span class="text-gray-500">-</span>
            </el-col>
            <el-col :span="11">
              <el-form-item >
                <el-time-picker
                    v-model="form.date4"
                    label="Pick a time"
                    placeholder="Pick a time"
                    format="HH:mm:ss"
                    value-format="HH:mm:ss"
                    style="width: 80%"
                />
              </el-form-item>
            </el-col>
          </el-form-item>

          <el-form-item label="正则方式" >
            <el-row>
              <el-col :span="50">
                <el-checkbox-group v-model="form.type3">
                  <el-checkbox label="分句" name="type" />
                  <el-checkbox label="去除短句" name="type"/>
                  <el-checkbox label="去除长句" name="type" />
                  <el-checkbox label="去除乱码" name="type" />
                  <el-checkbox label="删除网址" name="type" />
                </el-checkbox-group>
              </el-col>
            </el-row>
          </el-form-item>

<!--          <el-form-item label="正则方式">-->
<!--            <el-checkbox-group v-model="form.type3">-->
<!--              <el-checkbox label="分句" name="type" />-->
<!--            </el-checkbox-group>-->
<!--            <el-checkbox-group v-model="form.type3">-->
<!--              <el-checkbox label="去除短句（句长小于5）" name="type" />-->
<!--            </el-checkbox-group>-->
<!--            <el-checkbox-group v-model="form.type3">-->
<!--              <el-checkbox label="去除长句（句长大于）" name="type" />-->
<!--            </el-checkbox-group>-->
<!--            <el-checkbox-group v-model="form.type3">-->
<!--              <el-checkbox label="去除乱码（杂乱字符超过7）" name="type" />-->
<!--            </el-checkbox-group>-->
<!--            <el-checkbox-group v-model="form.type3">-->
<!--              <el-checkbox label="删除网址" name="type" />-->
<!--            </el-checkbox-group>-->
<!--          </el-form-item>-->


          <el-form-item label="句长大于">
            <el-input-number v-model="form.num2" class="mx-4" :disabled="dis1"/>
          </el-form-item>

          <el-form-item label="句长小于">
            <el-input-number v-model="form.num3" class="mx-4" :disabled="dis2"/>
          </el-form-item>

          <el-form-item label="翻译">
            <el-radio-group v-model="form.resource1">
              <el-radio label="英译中" />
              <el-radio label="中译英" />
            </el-radio-group>
          </el-form-item>

        </el-form>

        <div class="button_container">
          <el-button round @click="clearAll">清空</el-button>
          <el-button type="primary" round @click="processFile">处理</el-button>
          <el-upload action="/database/upload" :on-success="fileuploadsuccess" :headers="header">
            <el-button type="success" round>上传文件</el-button>
          </el-upload>
          <el-button type="primary" round @click="downloadFile">下载文件</el-button>
          <el-button type="danger" round @click="deleteFile">删除</el-button>
        </div>


      </div>

    </div>

  </div>
</template>

<script>
// import Navbar from "@/components/Navbar/Navbar";
import NavbarBlack from "@/components/Navbar/NavbarBlack";
import { reactive } from 'vue'
import request from "../store/utils/request"

export default {
  name: "DatasetView",
  components: {
    NavbarBlack
  },
  data(){
    return{
      header:{
        token : this.getToken()
      },
      dis1:true,
      dis2:true,
      dates:"",
      datee:"",
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      selectedRows:[],
      form:{
        filenamelist:[],
        date1:'',
      },
      selectname:[],
      tempoo:[],
      tableData:[
        {
          fileName: '文件1',
          uploadDate: '2023-11-11 20:38:47',
          remark: '文件1',
        },
        {
          fileName: '文件2',
          uploadDate: '2023-12-11 20:38:47',
          remark: '文件2',
        },
        {
          fileName: '文件3',
          uploadDate: '2023-11-29 20:38:47',
          remark: '文件4',
        },
        {
          fileName: '文件4',
          uploadDate: '2023-11-14 20:38:47',
          remark: '文件6',
        }
      ]
    }
  },
  created() {
    this.getfilelist()
  },
  computed: {
    filteredData() {
      return this.tableData.filter(item => {
        const itemDate = item.name;
        const startDate = new Date(this.dates);
        const endDate = new Date(this.datee);
        const itemDateObj = new Date(itemDate);

        return itemDateObj > startDate && itemDateObj < endDate;
      });
    },
  },
  methods:{
    downloadFile(){
      console.log(this.form.filenamelist)
      const name = this.form.filenamelist[0]
      request.get("/database/download",{
        responseType: "blob",
        params:{
          filename: name
        }
      }).then(res=>{
        const  url = window.URL.createObjectURL(new Blob([res]))
        const link = document.createElement('a')
        link.href = url;
        link.setAttribute('download',name)
        document.body.appendChild(link);
        link.click()
        console.log(res)
      })
    },
    deleteFile(){
      request.post("/Delete/fileDelete",{
        filename: this.form.filenamelist
      }).then(res=>{
        console.log(res)
        this.getfilelist()
        this.$router.go(0)
      })
      console.log(this.form.filenamelist[0])

    },
    getToken(){
      let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
      return user.token
    },
    getfilelist(){
      request.get("/database/showFileList",{
        params:{
          pageNum : this.currentPage4,
          pageSize : this.pageSize4
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },
    clearAll(){
      this.form.filenamelist = [];
      this.$refs.multipleTable.clearSelection(); // 清空表格的选中状态
      this.selectedRows = [];
      this.form.date1 = '';
      this.form.date2 = '';
      this.form.date3 = '';
      this.form.date4 = '';
      this.form.type3 = [];
      this.form.resource1 = [];
      this.form.num2 = '';
      this.form.num3 = '';
      this.dis1 = true;
      this.dis2 = true;

      // this.$refs.myForm.resetFields();
    },
    fileuploadsuccess( res ){
      console.log(res)
      this.getfilelist()
    },
    processFile(){
      if(typeof this.form.type3 == 'undefined'){
        this.form.type3 =[]
      }

      if (this.form.resource1 != null){
        if(this.form.resource1 != [] )
          this.form.type3.push(this.form.resource1)
          console.log(this.form.resource1)
      }
      for (let i = 0; i < this.form.type3.length; i++) {
        if (this.form.type3[i] == "去除短句"){
          console.log(this.form.num3)
          const temp_str = "去除短句_" + this.form.num3;
          this.form.type3[i] = temp_str;
        }
        if (this.form.type3[i] == "去除长句"){
          console.log(this.form.num2)
          const temp_str = "去除长句_" + this.form.num2;
          this.form.type3[i] = temp_str;
        }
      }

      request.post("/txtHandle/cnHandle",{
        filename : this.form.filenamelist,
        process: this.form.type3
      }).then(res=>{
        console.log(res)
      })
      this.getfilelist()
    },
    handleSelectionChange(selection) {
      // selection 是选中的行数据数组
      this.selectname = selection;
      // this.form.filenamelist = selection
      // this.form.filenamelist = this.selectname.date
      // 在这里可以获取选中的文件名称
      this.form.filenamelist = this.selectname.map(row => row.fileName); // 假设文件名称的属性是 date
      // if (this.selectname.length > 0) {
      //   const fileName = this.selectname[0].date; // 假设文件名称的属性是 date
      //   this.form.filenamelist.push(fileName)
      //   // console.log('选中的文件名称:', fileName);
      // }else{
      //   this.form.filenamelist = " "
      // }
    },
    changefilename(listoo){
      this.selectname = listoo;
      this.form.filenamelist = this.selectname;
    },
    handleSizeChange(pageSize4){ //改变当前页面条数，触发
      this.pageSize4 = pageSize4
      this.getfilelist()
    },
    handleCurrentChange(currentPage4){ //改变当前页码触发
      this.currentPage4 = currentPage4
      this.getfilelist()
    }

  },
  watch:{
    'form.type3':function (newVal){
      this.dis1 = ! newVal.includes('去除长句');
      this.dis2 = ! newVal.includes('去除短句');
    },
    'form.date1':function (newVal) {
      this.dates = this.form.date1 +" "+  this.form.date2;
      this.datee = this.form.date3 +" "+  this.form.date4;
      this.selectedRows = []
      this.selectedRows = this.filteredData;
      this.$refs.multipleTable.clearSelection();
      console.log(this.selectedRows)
      this.tempoo = [];
      for (let i =0;i<this.selectedRows.length;i++){
        console.log("oppp")
        console.log(this.selectedRows[i].date)
        this.tempoo.push(this.selectedRows[i].date)
        for(let j=0;j<this.tableData.length;j++){
          if(this.selectedRows[i].date==this.tableData[j].date){
            this.$refs.multipleTable.toggleRowSelection(this.tableData[j])
          }
        }
      }
      this.changefilename(this.tempoo);
      // console.log(dates)
    },
    'form.date2':function (newVal) {
      this.dates = this.form.date1 +" "+  this.form.date2;
      this.datee = this.form.date3 +" "+  this.form.date4;
      this.selectedRows = [];
      this.selectedRows = this.filteredData;
      this.$refs.multipleTable.clearSelection();
      console.log(this.selectedRows)
      for (let i =0;i<this.selectedRows.length;i++){
        console.log("oppp")
        console.log(this.selectedRows[i].date)
        for(let j=0;j<this.tableData.length;j++){
          if(this.selectedRows[i].date==this.tableData[j].date){
            this.$refs.multipleTable.toggleRowSelection(this.tableData[j])
          }
        }
      }
    },
    'form.date3':function (newVal) {
      this.dates = this.form.date1 +" "+  this.form.date2;
      this.datee = this.form.date3 +" "+  this.form.date4;
      this.selectedRows = this.filteredData;
      this.$refs.multipleTable.clearSelection();
      console.log(this.selectedRows);
      for (let i =0;i<this.selectedRows.length;i++){
        console.log("oppp")
        console.log(this.selectedRows[i].date)
        for(let j=0;j<this.tableData.length;j++){
          if(this.selectedRows[i].date==this.tableData[j].date){
            this.$refs.multipleTable.toggleRowSelection(this.tableData[j])
          }
        }
      }
    },
    'form.date4':function (newVal) {
      // this.filteredData();
      // console.log(this.filteredData);
      this.dates = this.form.date1 +" "+  this.form.date2;
      this.datee = this.form.date3 +" "+  this.form.date4;
      this.selectedRows = this.filteredData;
      this.$refs.multipleTable.clearSelection();
      for (let i =0;i<this.selectedRows.length;i++){
        console.log("oppp")
        console.log(this.selectedRows[i].date)
        for(let j=0;j<this.tableData.length;j++){
          if(this.selectedRows[i].date==this.tableData[j].date){
            this.$refs.multipleTable.toggleRowSelection(this.tableData[j])
          }
        }
      }
      console.log("yaKHCAJKCAIUSDCILUB")
      console.log(this.selectedRows)
    },
  }

}
</script>

<style>
.button_container{
  display: flex; /* 使用 Flex 布局 */
  justify-content: space-between; /* 使按钮均匀分布在容器中 */
  width: 50vh;
  margin-left: 20vh;
  margin-top: 5vh;
}
.nave_color{
  /*background-color: #55afea;*/
  background: linear-gradient(to bottom, #0557a6, #ffffff)
}

.outer-container {
  /* 设置大的div的样式，例如宽度、高度、背景颜色等 */
  /*position: relative;*/
  display: flex;
  width: 100%; /* 根据实际需要设置宽度 */
  height: 825px; /* 根据实际需要设置高度 */
  background-color: white;
}

.inner-container {
  /* 设置小的div的样式，例如宽度、高度、背景颜色等 */
  flex: 1;
  margin: 50px; /* 可以根据需要调整间距 */
  border: 1px solid #ccc; /* 可以添加边框样式 */

}

.center-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

body {
  background-color: white;
}

.table_div{
  height: 400px;
  width: 500px;
}

</style>