<template>
  <div class="stats space-top-5 space-bottom-10 space-left-right-5">
    <div class="container">
        <h4 class="space-bottom-5">Estatísticas no ano {{ano}}</h4>
        <div class="row">
            <div class="col-6">
                <label><strong>Ganho anual: </strong> {{ganho_anual}}€</label>
            </div>
            <div class="col-6">
                <label><strong>N.º serviços anual: </strong> {{nr_servicos}}</label>
            </div>
        </div>
        <div class="row">      
        </div>        
    </div>
  </div>
</template>

<style scoped>
</style>

<script>
  export default {
    name: 'stats-provider',
    created() {
      window.scrollTo(0, 0);
    },
      data: () => ({
          ano: 2020,
          ganho_anual: 1003,
          nr_servicos: 109
      })
}
</script>
