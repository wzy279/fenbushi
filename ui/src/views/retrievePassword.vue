<template>
  <div class="retrievePassword">
    <h2>找回密码</h2>
    <el-form :model="this.form">
      <el-form-item label="邮&nbsp;&nbsp;箱">
        <el-input v-model="form.userEmail"></el-input>
      </el-form-item>
      <el-form-item label="验证码">
        <el-input style="width: 243px" v-model="form.yanzhengma"></el-input>
        <el-button @click="getVerification()" style="width: 100px" :disabled=this.clikee>{{getV}}</el-button>
      </el-form-item>
      <el-form-item label="密&nbsp;&nbsp;码">
        <el-input v-model="form.password" type="password"></el-input>
      </el-form-item>
      <el-button @click="getVerification2()">修改密码</el-button>
      <el-button @click="goback()">返回</el-button>
    </el-form>
  </div>
</template>

<script>
import {getVerification, getVerification2} from "@/api/retrievePassword";
import {ElMessage} from "element-plus";

export default {
  name: "retrievePassword",
  data(){
    return{
      form:{
        yanzhengma:'',
        userEmail:'',
        password:''
      },
      getV : '获取验证码',
      clikee:false,
    }
  },
  methods:{
    option(type,message){
      ElMessage({
        message: message,
        type: type,
      })
    },
    goback(){
      this.$router.push('/login')
    },
    getVerification(){
        getVerification(this.form).then(response=>{
          console.log(response.data)
          if(response.data.code===200){
            this.option("success",response.data.msg)
            this.getVerificationn()
          }else {
            this.option("error",response.data.msg)
          }
        })
            .catch(error => {
              this.option("error","服务器错误请重试！")
              // 请求失败处理
              console.error(error);
            });
    },
    getVerification2(){
      getVerification2(this.form).then(response=>{
        console.log(response.data)
        if(response.data.code===200){
          this.option("success",response.data.msg)
          this.$router.push('/login')
        }else {
          this.option("error",response.data.msg)
        }
      })
          .catch(error => {
            this.option("error","服务器错误请重试！")
            // 请求失败处理
            console.error(error);
          });
    },
    getVerificationn(){
      var self = this;
      self.clikee=true
      var time = 61;
      var countdownInterval = setInterval(function() {
        console.log(time)
        time--;  // 减少剩余时间
        self.getV=time.toString()
        if (time <= 0) {
          // alert('倒计时结束')
          self.clikee = false;  // 启用按钮
          self.getV='获取验证码'
          clearInterval(countdownInterval);  // 停止计时器
        }

      }, 1000);


    }
  }
}
</script>

<style scoped>
.retrievePassword{
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
h2 {
  text-align: center;
  margin-top: 0;
}
</style>
