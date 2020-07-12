<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link 
        routerLinkActive="active"
        :to="{ name: 'home' }"
        v-if="typeOf == 'simple'"
      >
        <img src="../assets/imgs/logo.png" width="120" alt="SERVE.ME" class="d-inline-block align-middle mr-2">
      </router-link>
      <router-link 
        routerLinkActive="active"
        :to="{ name: 'scheduled-services' }"
        v-if="typeOf == 'client'"
      >
        <img src="../assets/imgs/logo.png" width="120" alt="SERVE.ME" class="d-inline-block align-middle mr-2">
      </router-link>
      <router-link 
        routerLinkActive="active"
        :to="{ name: 'scheduled-services-provider' }"
        v-if="typeOf == 'provider'"
      >
        <img src="../assets/imgs/logo.png" width="120" alt="SERVE.ME" class="d-inline-block align-middle mr-2">
      </router-link>
      <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- SIMPLE NAVBAR -->
      <div v-if="typeOf == 'simple'" id="navbarSupportedContent" class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Registo
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                <router-link
                  class="dropdown-item simple"
                  :to="{ name: 'register-client' }"
                >
                  Cliente
                </router-link>
                <router-link
                  class="dropdown-item simple"
                  :to="{ name: 'register-provider' }"
                >
                  Prestador de Serviços
                </router-link>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Login
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
              <router-link
                class="dropdown-item simple"
                :to="{ name: 'login-client' }"
              >
                Cliente
              </router-link>
              <router-link
                class="dropdown-item simple"
                :to="{ name: 'login-provider' }"
              >
                Prestador de Serviços
              </router-link>
            </div>
          </li>
        </ul>
      </div>

      <!-- CLIENT NAVBAR -->
      <div v-if="typeOf == 'client'" id="navbarSupportedContent" class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Os meus serviços
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'scheduled-services' }"
                >
                  Agendados
                </router-link>
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'published-services' }"
                >
                  Publicados
                </router-link>
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'history-services' }"
                >
                  Histórico
                </router-link>
            </div>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'inbox-client' }"
            >
              Inbox
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'publish-service' }"
            >
              Publicar pedido
            </router-link>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <img src="../assets/imgs/client/avatar_azul.png" width="20" alt="SERVE.ME" class="d-inline-block mr-1">
              {{nome}}
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'my-profile' }"
                >
                  Perfil
                </router-link>
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'edit-password' }"
                >
                  Alterar password
                </router-link>
                <router-link
                  class="dropdown-item client"
                  :to="{ name: 'home' }"
                  @click.native="logout"                  
                >
                  Terminar sessão
                </router-link>
            </div>
          </li>
        </ul>
      </div>


      <!-- PROVIDER NAVBAR -->
      <div v-if="typeOf == 'provider'" id="navbarSupportedContent" class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Dashboard
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'scheduled-services-provider' }"
                >
                  Serviços Agendados
                </router-link>
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'history-services-provider' }"
                >
                  Histórico
                </router-link>
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'stats-provider' }"
                >
                  Estatísticas
                </router-link>
            </div>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'inbox-provider' }"
            >
              Inbox
            </router-link>
          </li>          
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'proposals-provider' }"
            >
              Propostas
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'consult-services' }"
            >
              Consultar serviços
            </router-link>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <img src="../assets/imgs/provider/avatar_amarelo.png" width="20" alt="SERVE.ME" class="d-inline-block mr-1">
              {{nome}}
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown4">
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'my-profile-provider' }"
                >
                  Perfil
                </router-link>
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'edit-password-provider' }"
                >
                  Alterar password
                </router-link>
                <router-link
                  class="dropdown-item provider"
                  :to="{ name: 'home' }"
                  @click.native="logout"
                >
                  Terminar sessão
                </router-link>
            </div>
          </li>
        </ul>
      </div>

    </div>
  </nav>
</template>

<style scoped>
.navbar {
  padding: 15px !important;
}

nav .navbar-nav li a {
  padding: 15px !important;
  padding-left: 30px !important;
  padding-right: 30px !important;
}

nav .navbar-nav li a:hover {
  color: black !important;
}

.nav-item {
  position: relative;
}

.dropdown-item {
  font-size: 90%;
  line-height: 1;
}

.dropdown-item:active {
  color: black;
}

.fixed-top.scrolled {
  opacity: 0.9 !important;
  transition: opacity 200ms linear;
}

@media screen and (min-width: 768px) {
  .dropdown:hover .dropdown-menu, .btn-group:hover .dropdown-menu {
        display: block;
    }
    .dropdown-menu {
        margin-top: 0;
    }
    .dropdown-toggle {
        margin-bottom: 2px;
    }
    .navbar .dropdown-toggle, .nav-tabs .dropdown-toggle {
        margin-bottom: 0;
    }
}
</style>

<script scoped>
import {AUTH_LOGOUT} from '../store/action_calls/authentication'
import $ from 'jquery'

export default {
  props: {
    typeOf: String,
    nome: String
  },
  methods: {
    logout: function () {
      this.$store.dispatch(AUTH_LOGOUT)
        .then( resp => {
          var data = resp.data;
          console.log(data);
          this.$root.typeOf = 'simple';
          this.$root.nome = '';
          this.$router.push({
             name: 'home'
           });
        }).catch( err => {
          console.log("It failed!", err);
          this.loginError = true;
        })
    },
  },
  mounted() {
    $(function () {
      $(document).scroll(function () {
        var $nav = $(".fixed-top");
        $nav.toggleClass('scrolled', $(this).scrollTop() > $nav.height());
      });
    });
  }
};
</script>
