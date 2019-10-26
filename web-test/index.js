import Vue from "./node_modules/vue/dist/vue";
import VueRouter from "./node_modules/vue-router/dist/vue-router";
import loginForm from "./user/login";
import registerForm from "./user/register";
import './css/main.css'

Vue.use(VueRouter);
const router = new VueRouter({
    routes: [ //编写路由规则
        {
            path: "/login",  // 请求路径
            component: loginForm // 组件名称
        },
        {
            path: "/register",
            component: registerForm
        }
    ]
});

var vm = new Vue({
    el : "#app",
    components: {
        loginForm,
        registerForm,
    },
    router
});