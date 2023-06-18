<template>
  <div class="login">
    <div class="container">
      <el-form>
        <el-form-item label="邮箱">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input></el-input>
        </el-form-item>
        <el-button>注册</el-button>
        <el-button @click="jumptologin()">返回</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "regiset_v1",
  data(){

  },
  methods:{
    jumptologin(){
      this.$router.push('/login')
    },
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
