import Vue from 'vue'
import vueHeadful from 'vue-headful'
import App from './App.vue'
import store from "./store";
import router from "./router";
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import axios from './axios.js'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

Vue.config.productionTip = false

Vue.component('vue-headful', vueHeadful);

Vue.prototype.$backend = 'http://25.113.37.183:8083/api'
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  data(){
    return {
      typeOf: 'simple',
      nome: ''
    }
  },
  render: h => h(App)
}).$mount('#app')
