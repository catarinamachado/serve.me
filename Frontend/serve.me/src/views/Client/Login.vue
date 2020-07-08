<template>
  <div class="login-client content">
    <div class="container">
      <div class="row space-top-15 space-bottom-10">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h3 class="text-left space-bottom-6">Login como Cliente</h3>
          <div v-if="errors" class="error-messages text-left space-bottom-2">
            <div v-for="(v, k) in errors" :key="k">{{ v | error }}</div>
          </div>
          <form @submit.prevent="login">
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="email"
                v-model="email"
                placeholder="E-mail"
                required
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="password"
                v-model="password"
                placeholder="Password"
                required
              />
            </fieldset>
            <div class="space-top-3 text-right">
              <button class="btn btn-blue">
              Entrar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.error-messages {
  color: var(--my-red) !important;
}
</style>

<script>
import axios from 'axios';

export default {
  name: "login-client",
  created() {
    window.scrollTo(0, 0);
  },  
  data() {
    return {
      email: '',
      password: '',
      errors: []
    }
  },
  methods: {
    login: function () {
      axios({
        method: 'post',
        url: this.$backend + '/login/cliente',
        headers: {
          'content-type': 'application/json',
          'Access-Control-Allow-Origin': this.$backend + '/login/cliente',
          'Access-Control-Allow-Credentials': 'true'
        }, 
        data: {
          email: this.email, 
          password: this.password
        }
      }).then((response) => {
          console.log(response);
          this.$root.typeOf = 'client';
          this.$root.nome = 'Olá';
          this.$router.push({name: 'home'})
        }, (error) => {
          console.log(error);
          this.errors.push('Credenciais inválidas.')
      });
    }
  }
};
</script>
