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
      name: "inbox-client",
      path: "/inbox-client",
      component: () => import("@/views/Client/InboxClient")
    }
    ,
    {
      name: "my-profile",
      path: "/my-profile",
      component: () => import("@/views/Client/MyProfile")
    }
    ,
    {
      name: "edit-password",
      path: "/edit-password",
      component: () => import("@/views/Client/EditPassword")
    }
    ,
    {
      name: "provider-profile",
      path: "/provider-profile",
      component: () => import("@/views/Client/ProviderProfile")
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
    ,
    {
      name: "history-services-provider",
      path: "/history-services-provider",
      component: () => import("@/views/Provider/HistoryServices")
    }
    ,
    {
      name: "my-profile-provider",
      path: "/my-profile-provider",
      component: () => import("@/views/Provider/MyProfile")
    }
    ,
    {
      name: "inbox-provider",
      path: "/inbox-provider",
      component: () => import("@/views/Provider/InboxProvider")
    }
    ,
    {
      name: "proposals-provider",
      path: "/proposals-provider",
      component: () => import("@/views/Provider/ProposalsProvider")
    }
    ,
    {
      name: "consult-services",
      path: "/consult-services",
      component: () => import("@/views/Provider/ConsultServices")
    }
    ,
    {
      name: "scheduled-services-provider",
      path: "/scheduled-services-provider",
      component: () => import("@/views/Provider/ScheduledServices")
    }
    ,
    {
      name: "client-profile",
      path: "/client-profile",
      component: () => import("@/views/Provider/ClientProfile")
    }
    ,
    {
      name: "edit-password-provider",
      path: "/edit-password-provider",
      component: () => import("@/views/Provider/EditPassword")
    }
  ]
});
