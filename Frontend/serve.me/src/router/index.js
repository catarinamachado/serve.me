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
      name: "register-client",
      path: "/register-client",
      component: () => import("@/views/Client/Register")
    }
    ,
    {
      name: "login-client",
      path: "/login-client",
      component: () => import("@/views/Client/Login")
    }
    ,
    {
      name: "publish-service",
      path: "/publish-service",
      component: () => import("@/views/Client/PublishService")
    }
    ,
    {
      name: "scheduled-services",
      path: "/scheduled-services",
      component: () => import("@/views/Client/ScheduledServices")
    }
    ,
    {
      name: "published-services",
      path: "/published-services",
      component: () => import("@/views/Client/PublishedServices")
    }
    ,
    {
      name: "history-services",
      path: "/history-services",
      component: () => import("@/views/Client/HistoryServices")
    }
    ,
    {
        name: "register-provider",
        path: "/register-provider",
        component: () => import("@/views/Provider/Register")
    }
    ,
    {
      name: "login-provider",
      path: "/login-provider",
      component: () => import("@/views/Provider/Login")
    }
  ]
});
