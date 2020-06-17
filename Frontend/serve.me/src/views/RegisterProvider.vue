<template>
  <div class="register-provider">
    <div class="container">
      <div class="row space-top-8 space-bottom-6">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h3 class="text-left space-bottom-6">Registo como Prestador de Serviços</h3>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
          <form @submit.prevent="onSubmit">
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="nome"
                placeholder="Nome"
              />
            </fieldset>
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
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="contribuinte"
                placeholder="N.º de Contribuinte"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="telemovel"
                placeholder="N.º de Telemóvel"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="morada"
                placeholder="Morada"
              />
            </fieldset>
            <div class="row">
              <div class="col-md-4">
                <fieldset class="form-group">
                  <input
                    class="form-control form-control-lg"
                    type="text"
                    v-model="freguesia"
                    placeholder="Freguesia"
                  />
                </fieldset>
              </div>
              <div class="col-md-4">
              <fieldset class="form-group">
                <input
                  class="form-control form-control-lg"
                  type="text"
                  v-model="concelho"
                  placeholder="Concelho"
                />
              </fieldset>
              </div>
              <div class="col-md-4">              
              <fieldset class="form-group">
                <input
                  class="form-control form-control-lg"
                  type="text"
                  v-model="distrito"
                  placeholder="Distrito"
                />
              </fieldset>
              </div>
            </div>
            <div class="space-top-3 text-right">
                <button class="btn btn-yellow">
                Registar
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
  name: "register-provider",
  created() {
    //Scrolls to top when view is displayed
    window.scrollTo(0, 0);
  }
};
</script>
