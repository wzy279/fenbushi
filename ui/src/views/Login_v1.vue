<template>
<!--  <HelloWorld msg="Welcome to Your Vue.js App"></HelloWorld>-->
  <div class="login">

  <div class="container">
    <el-form :model="form">
      <el-form-item label="邮箱">
        <el-input v-model="form.userEmail"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.userPassward"></el-input>
      </el-form-item>
      <el-button @click="login()">登录</el-button>
      <el-button @click="jumptoregist()">注册</el-button>
    </el-form>
  </div>
  </div>
</template>

<script>
import {login} from "@/api/login";

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

    login(){
        login(this.form).then(response => {
          // 请求成功处理
          console.log(response.data);
        })
            .catch(error => {
              // 请求失败处理
              console.error(error);
            });
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
