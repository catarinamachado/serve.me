<template>
  <div class="login-provider">
    <div class="container">
      <div class="row space-top-15 space-bottom-17">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h3 class="text-left space-bottom-6">Login como Prestador de Serviços</h3>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
          <form @submit.prevent="onSubmit(email, password)">
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="email"
                placeholder="E-mail"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="password"
                v-model="password"
                placeholder="Password"
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

<script>
export default {
  name: "login-provider"
};
</script>
