import Vue from 'vue'
import vueHeadful from 'vue-headful'
import App from './App.vue'
import router from "./router";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.config.productionTip = false

Vue.component('vue-headful', vueHeadful);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
