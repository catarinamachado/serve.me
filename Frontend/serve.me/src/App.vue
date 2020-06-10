<template>
  <div id="app">
    <TheNavbar/>
    <LandingPage/>
    <TheFooter/>
  </div>
</template>

<script>
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

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
