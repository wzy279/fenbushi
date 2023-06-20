<template>
  <el-container>
    <el-header><el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
    >
      <el-menu-item index="0">LOGO</el-menu-item>
      <div class="flex-grow" />
      <el-menu-item index="1">我要考试</el-menu-item>
      <el-menu-item index="2">查看历史分数</el-menu-item>
      <el-menu-item index="3">退出登录</el-menu-item>
    </el-menu></el-header>
  </el-container>
  <router-view></router-view>
<!--  <Login_v1></Login_v1>-->
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import Login_v1 from "@/views/Login_v1";

export default {
  name: 'App',
  components: {
    // Login_v1,
    // HelloWorld
  },
  methods:{
    handleSelect(key){
      console.log(key)
      if (key==1){
        this.$router.push('/kaoshi')
      }else {
        if(key==2){
          this.$router.push('/history')
        }else {
            var storage = window.localStorage;
            storage.clear();
            this.$router.push('/login');
        }
      }
    }
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.flex-grow {
  flex-grow: 1;
}
</style>
