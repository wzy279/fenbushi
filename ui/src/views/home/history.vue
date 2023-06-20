<template>
  注意！！！刚登录进来刷新一下页面来刷新一下token<br>
  <el-button @click="history()">点我获取历史分数</el-button>
  <el-table :data="tableData" border style="width: 70%; position: absolute; left: 10%" :cell-style="{ textAlign: 'center' }" :header-cell-style="{textAlign: 'center'}">
    <el-table-column prop="time" label="Date" />
    <el-table-column prop="score" label="Name"/>
  </el-table>
</template>


<script>
import {LookHistory} from "@/api/history";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "home",
  data(){
    return{
      token:'',
      tableData:[
        {
          time: '2016-05-03',
          score: 'Tom',
        },
        {
          time: '2016-05-02',
          score: 'Tom',
        },
      ]

    }
  },
  methods:{
    option(type,message){
      ElMessage({
        message: message,
        type: type,
      })
    },
    formatDate(date) {
  let year = date.getFullYear();
  let month = date.getMonth() + 1;
  let day = date.getDate();
  let hours = date.getHours();
  let minutes = date.getMinutes();

  // 格式化月份和日期为单个数字的情况，例如将 6 转换为 "06"
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;

  return `${year}-${month}-${day} ${hours}:${minutes}`;
},
    history(){
      // console.log(localStorage.getItem("Token"))
      LookHistory().then(response=>{
        console.log(response.data.data)
        if(response.data.code===200){
          this.option("success",response.data.msg)
          this.tableData = response.data.data
          for(let i = 0;i<this.tableData.length;i++){
            let backendTime = new Date(Date.parse(this.tableData[i].time))
            // let localTime = backendTime.toLocaleString('en-US', { timeZone: 'Asia/Shanghai' }); // 转换为中国时区（例如：中国标准时间）
            // console.log("localTime是"+localTime);
            this.tableData[i].time = this.formatDate(backendTime);
          }


        }else {
          this.option("error",response.data.msg)
        }

      })
    }
  },
  created() {
    // location.reload(true)
    this.token = localStorage.getItem("Token")
    console.log(this.token)
    // this.history()
  }
}
</script>

<style>

</style>
