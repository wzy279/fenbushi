<template>
    <div class="common-layout">
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </div>

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "home",
  data(){
    return{
      token:''
    }
  },
  created() {
    this.token = localStorage.getItem("token")
    console.log(this.token)
  }
}
</script>

<style scoped>

</style>
