<template>
    <div class="home-page">
        <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
            <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselIndicators" data-slide-to="1"></li>
            <li data-target="#carouselIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <div class="carousel-item carousel-item-max active">
                <img class="d-block w-100" src="../assets/imgs/landing_page/1.jpg" alt="serve.me">
            </div>
            <div class="carousel-item carousel-item-max">
                <img class="d-block w-100" src="../assets/imgs/landing_page/2.jpg" alt="serve.me">
            </div>
            <div class="carousel-item carousel-item-max">
                <img class="d-block w-100" src="../assets/imgs/landing_page/3.jpg" alt="serve.me">
            </div>
            </div>
            <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
            </a>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12 space-top-6">
                    A <strong>serve.me</strong> é um marketplace online de procura e contratação de serviços,<br />
                    ideal para quem procura uma mãozinha extra e <br />para quem tem uma para oferecer!
                </div>
            </div>
            <div class="row justify-content-between space-top-6 space-bottom-6">
                <div class="card col-5">
                    <div class="card-body">
                        <p><strong>PRESTADOR DE SERVIÇOS</strong></p>
                        <div class="space-bottom-3">
                            <button type="button" class="btn btn-serv btn-block">Login</button>
                        </div>
                        <div>
                            <button type="button" class="btn btn-serv btn-block">Registo</button>
                        </div>
                    </div>
                </div>
                <div class="card col-5">
                    <div class="card-body">
                        <p><strong>CLIENTE</strong></p>
                        <div class="space-bottom-3">
                            <button type="button" class="btn btn-serv btn-block">Login</button>
                        </div>
                        <div>
                            <button type="button" class="btn btn-serv btn-block">Registo</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<style scoped>
.carousel-item-max {
  height: 500px; /* carousel height */
}

.carousel-item-max img {
  height: 500px; /* carousel height */
  position: absolute;
  object-fit: cover;
  top: 0;
  left: 0;
}

#carouselIndicators {
  padding-top: 20px;
}

.btn-serv {
  color: white;
  background-color: var(--my-green);
  border-color: var(--my-green);
}

.btn-serv:hover,
.btn-serv:active,
.btn-serv:visited,
.btn-serv:focus {
  color: white;
  background-color: var(--my-darker-green);
  border-color: var(--my-darker-green);
}

.space-top-6 {
  padding-top: 6%;
}

.space-bottom-6 {
  padding-bottom: 6%;
}

.space-bottom-3 {
  padding-bottom: 3%;
}
</style>

<script>
import './assets/styles/serve-me.css';

export default {
  name: "home",
};
</script>
