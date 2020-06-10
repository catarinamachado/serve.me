<template>
  <section>
    <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
        <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselIndicators" data-slide-to="1"></li>
        <li data-target="#carouselIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
        <div class="carousel-item carousel-item-max active">
            <img class="d-block w-100" src="../assets/landing_page/1.jpg" alt="serve.me">
        </div>
        <div class="carousel-item carousel-item-max">
            <img class="d-block w-100" src="../assets/landing_page/2.jpg" alt="serve.me">
        </div>
        <div class="carousel-item carousel-item-max">
            <img class="d-block w-100" src="../assets/landing_page/3.jpg" alt="serve.me">
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

    <div class="space-bottom-3">.
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
  padding-top: 30px;
}

.space-bottom-3 {
  padding-bottom: 3%;
}
</style>