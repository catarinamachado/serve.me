<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link 
        routerLinkActive="active"
        :to="{ name: 'home' }"
      >
        <img src="../assets/logo.png" width="120" alt="SERVE.ME" class="d-inline-block align-middle mr-2">
      </router-link>
      <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div id="navbarSupportedContent" class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Registo
            </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'register-client' }"
                >
                  Cliente
                </router-link>
                <router-link
                  class="dropdown-item"
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
                class="dropdown-item"
                :to="{ name: 'login-client' }"
              >
                Cliente
              </router-link>
              <router-link
                class="dropdown-item"
                :to="{ name: 'login-provider' }"
              >
                Prestador de Serviços
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
import $ from 'jquery'

export default {
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