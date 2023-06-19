
import {createRouter,createWebHistory} from 'vue-router';
import Login_v1 from "@/views/Login_v1";
import regiset_v1 from "@/views/regiset_v1";
import home from '@/views/home/home';
import retrievePassword from "@/views/retrievePassword";
const routers = createRouter({
    history:createWebHistory(),
    routes:[
        {path:'/login',component:Login_v1},
        {path:'/',redirect:'/login',component:Login_v1},
        {path:'/regist',component:regiset_v1},
        {path:'/home',component:home},
        {path:'/retrievePassword',component:retrievePassword}
    ]
})


export default routers


