import Vue from 'vue'
import vueHeadful from 'vue-headful'
import App from './App.vue'
import store from "./store";
import router from "./router";
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import VueSimpleAlert from "vue-simple-alert"
import axios from './axios.js'
import backend from './store/consts'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

//import StarRating from 'vue-star-rating'


Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.use(VueSimpleAlert)

Vue.config.productionTip = false

Vue.component('vue-headful', vueHeadful);
//Vue.component('star-rating', StarRating);

Vue.prototype.$backend = backend.URL
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
