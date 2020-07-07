<template>
  <div class="inbox-provider space-top-5 space-bottom-10 space-left-right-5">

    <h4 class="space-bottom-2">Inbox</h4>
    <div class="justify-content-center my-1 row">
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
    <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter" >
        <template v-slot:cell(preco_hora)="row">
            {{row.item.preco_hora}} €
        </template>

       <template v-slot:cell(cliente)="row">
        <b-link href="/#/client-profile">{{row.item.cliente}}</b-link>
      </template>

      <template v-slot:cell(acoes)="row">
        <b-button v-if="row.item.informacao == 'Aviso de cancelamento'" size="sm" @click="limpar(row.item, row.index, $event.target)" class="btn btn-green">
          OK
        </b-button>
        <b-button v-if="row.item.informacao == 'Requer classificação'" size="sm" class="btn btn-green">
          Classificar
        </b-button>        
      </template>
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
    name: 'inbox-provider',
    created() {
      window.scrollTo(0, 0);
    },
    data: function() {
      return {
        items: [{
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          cliente: "Primeiro Último",
          data: "13/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora: "4",
          informacao: "Aviso de cancelamento"
        },
        {
          categoria: "Teste3",
          subcategoria: "Teste4",
          descricao: "Descrição",
          cliente: "Primeiro Último",
          data: "13/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora: "4",
          informacao: "Requer classificação"
        }
      ],
      fields: [
          { key: 'categoria', label: 'Categoria', sortable: true },
          { key: 'subcategoria', label: 'Subcategoria', sortable: true},
          { key: 'descricao', label: 'Descrição', sortable: true},
          { key: 'cliente', label: 'Cliente', sortable: true},
          { key: 'data', label: 'Data', sortable: true},
          { key: 'hora_inicio', label: 'Hora Início', sortable: true},
          { key: 'duracao', label: 'Duração', sortable: true},
          { key: 'preco_hora', label: 'Preço/hora', sortable: true},
          { key: 'informacao', label: 'Informação', sortable: true},
          { key: 'acoes', label: '' }
      ],
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      filter: null
  }},
  methods: {
    limpar(item, index, button) {
      this.$root.$emit('', button)
    }
  }
}
</script>
