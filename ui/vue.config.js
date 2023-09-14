const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false
})
module.exports = {
  devServer: {
    port: 80, // 此处修改你想要的端口号
    proxy:{
      '/api':{
        target:'http://localhost:81',
        changeOrigin:true,
      }
    }
  }

}



