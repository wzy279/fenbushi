
import {createRouter,createWebHistory} from 'vue-router';
import Login_v1 from "@/views/Login_v1";
import regiset_v1 from "@/views/regiset_v1";
import history from '@/views/home/history';
import retrievePassword from "@/views/retrievePassword";
import kaoshi from "@/views/home/kaoshi";
const routers = createRouter({
    history:createWebHistory(),
    routes:[
        {path:'/login',component:Login_v1},
        {path:'/',redirect:'/login',component:Login_v1},
        {path:'/regist',component:regiset_v1},
        {path:'/history',component:history},
        {path:'/retrievePassword',component:retrievePassword},
        {path:'/kaoshi',component:kaoshi}
    ]
})


export default routers


