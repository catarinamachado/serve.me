<template>
  <div class="history-services space-top-5 space-bottom-10 space-left-right-5">
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
            <template v-slot:cell(cliente)="row">
                <b-link href="/#/client-profile">{{row.item.cliente}}</b-link>
            </template>            
            <template v-slot:cell(estado)="row">
            <b-button v-if="row.item.estado === 'Por classificar'" size="sm" @click="classificar(row.item, row.index, $event.target)" class="btn btn-green mr-1">
                Por classificar
            </b-button>
            <b-form-group v-if="row.item.estado === 'Realizado' || row.item.estado === 'Cancelado'">
                {{row.item.estado}}
            </b-form-group>
        </template>
    </b-table>

    <!-- Info modal -->
    <b-modal size="lg" :id="classificarModal.id" :title="classificarModal.title" @hide="resetClassificarModal">
        <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group
            label="Categoria"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.categoria"
            ></b-form-input>
            </b-form-group>
                        
            <b-form-group
            label="Subcategoria"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.subcategoria"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Descrição"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.descricao"
            ></b-form-input>
            </b-form-group>
                    
            <b-form-group
            label="Data"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.data"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Hora Início"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.hora_inicio"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Duração"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.duracao"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Preço/hora"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.preco_hora"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Cliente"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.cliente"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            :state="classificacao"
            label="Classificação"
            label-for="classificacao-input"
            invalid-feedback="Classificação é obrigatória"
            >
            <b-form-rating v-model="rating" variant="warning"></b-form-rating>
            </b-form-group>

            <b-form-group
            :state="comentarios"
            label="Comentários"
            label-for="comentarios-input"
            >
            <b-form-input
                id="comentarios-input"
                v-model="comentarios"
                :state="comentarios"
            ></b-form-input>
            </b-form-group>
        </form>
    </b-modal>

    <div class="justify-content-center row my-1">
      <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
    </div>
  </div>
</template>

<style>
.btn-primary {
  color: white !important;
  background-color: var(--my-darker-green) !important;
  border-color: var(--my-darker-green) !important;
}
</style>

<script>
  export default {
    name: 'history-services-provider',
    created() {
      window.scrollTo(0, 0);
    },
    data: function() {
      return {
        rating: null,
        items: [{
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          cliente: "Primeiro Último",
          data: "13/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora: "4€",
          estado: "Realizado"
        },
        {
          categoria: "Teste3",
          subcategoria: "Teste4",
          descricao: "Descrição",
          cliente: "Primeiro Último",
          data: "13/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora: "4€",
          estado: "Cancelado"
        },
        {
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          cliente: "Primeiro Último",
          data: "14/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora: "4€",
          estado: "Por classificar"          
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
          { key: 'estado', label: 'Estado' }
      ],
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      filter: null,
      classificarModal: {
        id: 'classificar-modal',
        title: ''
      }
  }},
  methods: {
    classificar(item, index, button) {
      this.classificarModal.title = `Classificação de Serviço`;
      this.classificarModal.categoria = item.categoria;
      this.classificarModal.subcategoria = item.subcategoria;
      this.classificarModal.descricao = item.descricao;
      this.classificarModal.concelho = item.concelho;
      this.classificarModal.data = item.data;
      this.classificarModal.hora_inicio = item.hora_inicio;
      this.classificarModal.duracao = item.duracao;
      this.classificarModal.preco_hora = item.preco_hora;
      this.classificarModal.cliente = item.cliente;      
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.title = ''
      this.classificarModal.content = ''
    }
  }
}
</script>
