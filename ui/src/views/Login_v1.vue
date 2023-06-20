<template>
<!--  <HelloWorld msg="Welcome to Your Vue.js App"></HelloWorld>-->
  <div class="login">
    <div style="top: 500px">这是登录页面</div>
  <div class="container">
    <el-form :model="form">
      <el-form-item label="邮箱">
        <el-input v-model="form.userEmail"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.userPassward" type="password"></el-input>
      </el-form-item>
      <el-button @click="login()">登录</el-button>
      <el-button @click="jumptoregist()">注册</el-button>
      <el-button @click="retrievePassword()">忘记密码</el-button>
    </el-form>

  </div>
  </div>
</template>

<script>
import {login} from "@/api/login";
import {ElMessage} from "element-plus";

export default {
  name: "Login_v1",
  data(){
    return{
      form:{
        userEmail:'',
        userPassward:''
      },
      shuju:{}
    }
  },
  methods:{
    jumptoregist(){
      this.$router.push('/regist')
    },
    retrievePassword(){
      this.$router.push('/retrievePassword')
    },
    option(type,message){
      ElMessage({
        message: message,
        type: type,
      })
    },
    login(){
        login(this.form).then(response => {
          // 请求成功处理
          console.log(response.data);
          if(response.data.code===200){
            this.option("success",response.data.msg)
            var storage = window.localStorage;
            storage.clear();
            console.log('清空了token')
            localStorage.setItem("Token",response.data.data)
            console.log("新token为"+localStorage.getItem("Token"))
            this.$router.push('/history')
          }else {
            this.option("error",response.data.msg)
          }
          // alert(this.shuju)
        })
            .catch(error => {
              this.option("error","服务器错误请重试！")
              // 请求失败处理
              console.error(error);
            });
        // alert(this.shuju.code)
    }
  }
}
</script>

<style>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 400px; /* 可根据需要设置容器的高度 */
  width: 700px;
  border: 1px solid black;
}
.login{
  /*background-image: url("");*/
  background-color: pink;
  background-size: cover;
  background-position: center;
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
</style>
