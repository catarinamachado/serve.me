<template>
  <div class="login-provider content">
    <div class="container">
      <div class="row space-top-15 space-bottom-10">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h3 class="text-left space-bottom-6">Login como Prestador de Serviços</h3>
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
                <button class="btn btn-yellow">
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
import {PROVIDER_AUTH_REQUEST} from '../../store/action_calls/authentication'

export default {
  name: "login-provider",
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
      let user = {
        email: this.email,
        password: this.password
      }
      this.$store.dispatch(PROVIDER_AUTH_REQUEST, user)
        .then( resp => {
          this.loginError = false;
          var data = resp.data;
          this.$root.typeOf = 'provider';
          this.$root.nome = data.nome;
          this.$router.push({
             name: 'scheduled-services-provider'
           });
        }).catch( err => {
          // Instead, this happens:
          console.log("It failed!", err);
          this.loginError = true;
        })
    }
  }  
};
</script>
