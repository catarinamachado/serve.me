<template>
  <div class="scheduled-services space-top-5 space-bottom-10 space-left-right-5">

    <h4 class="space-bottom-2">Histórico de Serviços</h4>
    <div class="justify-content-centermy-1 row">
      <b-form-fieldset horizontal label="Linhas por página" class="col-6" :label-size="6">
         <b-form-select
            v-model="perPage"
            id="perPageSelect"
            size="sm"
            :options="pageOptions"
          ></b-form-select>
      </b-form-fieldset>

      <b-form-fieldset horizontal label="Filtro" class="col-6" :label-size="2">
          <b-input-group size="sm">
            <b-form-input
              v-model="filter"
              type="search"
              id="filterInput"
              placeholder="Pesquisa"
            ></b-form-input>
            <b-input-group-append>
              <b-button :disabled="!filter" @click="filter = ''">Limpar</b-button>
            </b-input-group-append>
          </b-input-group>
      </b-form-fieldset>
    </div>

    <!-- Main table element -->
    <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter">
    </b-table>

    <div class="justify-content-center row my-1">
      <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
    </div>
  </div>
</template>

<style scoped>
</style>

<script>
  export default {
    name: 'history-services',
    created() {
      window.scrollTo(0, 0);
    },
    data: function() {
      return {
        items: [{
          categoria: "Teste1",
          subcategoria: "Teste2",
          descrição: "Descrição",
          prestador: "Primeiro Último",
          data: "13/03/1233",
          hora_início: "14h00",
          duração: "1 hora",
          preço_hora: "4€",
          estado: "Realizado"
        },
        {
          categoria: "Teste3",
          subcategoria: "Teste4",
          descrição: "Descrição",
          prestador: "Primeiro Último",
          data: "13/03/1233",
          hora_início: "14h00",
          duração: "1 hora",
          preço_hora: "4€",
          estado: "Cancelado"
        },
        {
          categoria: "Teste1",
          subcategoria: "Teste2",
          descrição: "Descrição",
          prestador: "Primeiro Último",
          data: "14/03/1233",
          hora_início: "14h00",
          duração: "1 hora",
          preço_hora: "4€",
          estado: "Por classificar"          
        }
      ],
      fields: [
          { key: 'categoria', label: 'Categoria', sortable: true },
          { key: 'subcategoria', label: 'Subcategoria', sortable: true},
          { key: 'descrição', label: 'Descrição', sortable: true},
          { key: 'prestador', label: 'Prestador', sortable: true},
          { key: 'data', label: 'Data', sortable: true},
          { key: 'hora_início', label: 'Hora Início', sortable: true},
          { key: 'duração', label: 'Duração', sortable: true},
          { key: 'preço_hora', label: 'Preço/hora', sortable: true},
          { key: 'estado', label: 'Estado' }
      ],
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      filter: null
  }}
}
</script>
