<template>
    <div class="home">
        <b-carousel
        id="carousel"
        :interval="4000"
        controls
        indicators
        img-width="1024"
        img-height="480"
        >
            <b-carousel-slide
                img-src="https://hoechstleister.net/wp-content/uploads/2018/03/Hof-und-Garten.jpg">
            </b-carousel-slide>

            <b-carousel-slide 
                img-src="https://images.unsplash.com/photo-1524247108137-732e0f642303?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1651&q=80">
            </b-carousel-slide>

            <b-carousel-slide 
                img-src="https://images.unsplash.com/photo-1586280268958-9483002d016a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80">
            </b-carousel-slide>
        </b-carousel>

        <div class="container">
            <div class="row">
                <div class="col-12 space-top-6">
                    A <strong>Serve.Me</strong> é um marketplace online de procura e contratação de serviços,<br />
                    ideal para quem procura uma mãozinha extra e <br />para quem tem uma para oferecer!
                </div>
            </div>
            <div class="row justify-content-between space-top-6 space-bottom-6">
                <div class="card col-5">
                    <div class="card-body">
                        <p><strong>PRESTADOR DE SERVIÇOS</strong></p>
                        <div class="space-bottom-3">
                            <router-link
                                :to="{ name: 'login-provider' }"
                                tag="button"
                                class="btn btn-green btn-block"
                            >
                                Login
                            </router-link>
                        </div>
                        <div>
                            <router-link
                                :to="{ name: 'register-provider' }"
                                tag="button"
                                class="btn btn-green btn-block"
                            >
                                Registo
                            </router-link>
                        </div>
                    </div>
                </div>
                <div class="card col-5">
                    <div class="card-body">
                        <p><strong>CLIENTE</strong></p>
                        <div class="space-bottom-3">
                            <router-link
                                :to="{ name: 'login-client' }"
                                tag="button"
                                class="btn btn-green btn-block"
                            >
                                Login
                            </router-link>
                        </div>
                        <div>
                            <router-link
                                :to="{ name: 'register-client' }"
                                tag="button"
                                class="btn btn-green btn-block"
                            >
                                Registo
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
  .carousel-item img {
      height:90vh!important;
  }
</style>

<script>
export default {
  name: "home"
};
</script>
