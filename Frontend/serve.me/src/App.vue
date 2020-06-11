<template>
  <div id="app">
    <TheNavbar/>
    <LandingPage/>
    <TheFooter/>
  </div>
</template>

<script>
import './assets/styles/serve-me.css';
import TheNavbar from './components/TheNavbar.vue'
import LandingPage from './components/LandingPage.vue'
import TheFooter from './components/TheFooter.vue'

export default {
  name: 'App',
  components: {
    TheNavbar,
    LandingPage,
    TheFooter
  }
}
</script>
