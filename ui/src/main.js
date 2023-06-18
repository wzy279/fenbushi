import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index'
import axios from "axios";
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'


axios.defaults.baseURL='http://127.0.0.1:4523/m1/2884852-0-default'
const app = createApp(App)
app.use(ElementPlus,{
    locale:zhCn,
})
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router)
app.config.globalProperties.$http = axios
app.mount('#app')
