<template>
  <div id="app">
    <vue-headful title="Serve.Me"/>
    <TheNavbar typeOf="client" name="Maria"/>
    <router-view></router-view>
    <TheFooter/>
  </div>
</template>

<script>
import './assets/styles/serve-me.css';
import TheNavbar from './components/TheNavbar.vue'
import TheFooter from './components/TheFooter.vue'

export default {
  name: 'App',
  components: {
    TheNavbar,
    TheFooter
  }
}
</script>
