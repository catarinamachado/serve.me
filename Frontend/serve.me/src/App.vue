<template>
  <div id="app">
    <vue-headful title="Serve.Me"/>
    <TheNavbar :typeOf="$root.typeOf" :nome="$root.nome"/>
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
  props: {
    typeOf: String,
    nome: String
  },
  components: {
    TheNavbar,
    TheFooter
  },
  mounted() {
    if (localStorage.nome) {
      this.$root.nome = localStorage.nome
      this.$root.typeOf = localStorage.typeOf
    }
  }
}
</script>
