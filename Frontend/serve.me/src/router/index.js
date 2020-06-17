import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  routes: [
    {
      name: "home",
      path: "/",
      component: () => import("@/views/Home")
    }
    ,
    {
      name: "login-client",
      path: "/login-client",
      component: () => import("@/views/LoginClient")
    }
    ,
    {
      name: "login-provider",
      path: "/login-provider",
      component: () => import("@/views/LoginProvider")
    }
  ]
});
