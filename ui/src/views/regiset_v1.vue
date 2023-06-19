<template>
  <div class="login">
    <div style="height: 300px;top: 100px">这是注册页面</div>
    <div class="container">

      <el-form :model="this.form">
        <el-form-item  label="邮箱">
          <el-input v-model="form.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.userPassward" type="password"></el-input>
        </el-form-item>
        <el-button @click="regist()">注册</el-button>
        <el-button @click="jumptologin()">返回</el-button>

      </el-form>
    </div>
  </div>
</template>

<script>
import {regist} from "@/api/regist";
import {ElMessage} from "element-plus";

export default {
  name: "regiset_v1",
  data(){
    return{
      form:{}
    }

  },
  methods:{
    jumptologin(){
      this.$router.push('/login')
    },
    option(type,message){
      ElMessage({
        message: message,
        type: type,
      })
    },
    regist(){
      regist(this.form).then(response=>{
        console.log(response.data)
        if(response.data.code===200){
          this.option("success",response.data.msg)
          this.jumptologin()
        }else{
          this.option("error",response.data.msg)
        }
      })
          .catch(error => {
            this.option("error","服务器错误请重试！")
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
