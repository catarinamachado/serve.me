<template>
  <div class="space-top-5 space-bottom-10 space-left-right-5">
    <kalendar :configuration="calendar_settings" :events="events"/>
    <div>
        <h4 class="space-bottom-2">Serviços Agendados</h4>
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
        <template v-slot:cell(acoes)="row">
            <b-button size="sm" @click="cancelar(row.item, row.index, $event.target)" class="btn btn-red mr-1">
            <i class="fas fa-times"></i>
            </b-button>
            <b-button size="sm" class="btn btn-blue">
            <i class="fas fa-check"></i>
            </b-button>
        </template>
        </b-table>

        <!-- Info modal -->
        <b-modal :id="cancelarModal.id" :title="cancelarModal.title" @hide="resetCancelarModal">
        <pre>{{ cancelarModal.content }}</pre>
        </b-modal>

        <div class="justify-content-center row my-1">
        <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
        </div>
      </div>
  </div>
</template>

<script>
import { Kalendar } from 'kalendar-vue';

export default {
  name: "scheduled-services-provider",
  created() {
    window.scrollTo(0, 0);
  },
  data: () => ({
    calendar_settings: {
        style: 'material_design',
        cell_height: 10,
        current_day: new Date(),
        read_only: true,
        day_starts_at: 5,
        day_ends_at: 24,
        overlap: true,
        hide_dates: ['2019-10-31'], // Spooky
        past_event_creation: true
    },
    events: [],
    new_appointment: {
        title: null,
        description: null
    },
    items: [{
        categoria: "Teste1",
        subcategoria: "Teste2",
        descrição: "Descrição",
        prestador: "Primeiro Último",
        data: "13/03/1233",
        hora_início: "14h00",
        duração: "1 hora",
        preço_hora: "4€"
    },
    {
        categoria: "Teste3",
        subcategoria: "Teste4",
        descrição: "Descrição",
        prestador: "Primeiro Último",
        data: "13/03/1233",
        hora_início: "14h00",
        duração: "1 hora",
        preço_hora: "4€"
    },
    {
        categoria: "Teste1",
        subcategoria: "Teste2",
        descrição: "Descrição",
        prestador: "Primeiro Último",
        data: "14/03/1233",
        hora_início: "14h00",
        duração: "1 hora",
        preço_hora: "4€"
    },
    {
        categoria: "Teste1",
        subcategoria: "Teste2",
        descrição: "Descrição",
        prestador: "Primeiro Último",
        data: "12/03/1233",
        hora_início: "14h00",
        duração: "1 hora",
        preço_hora: "4€"
    },
    {
        categoria: "Teste1",
        subcategoria: "Teste2",
        descrição: "Descrição",
        prestador: "Primeiro Último",
        data: "13/03/1233",
        hora_início: "14h00",
        duração: "1 hora",
        preço_hora: "4€"
    }],
    fields: [
        { key: 'categoria', label: 'Categoria', sortable: true },
        { key: 'subcategoria', label: 'Subcategoria', sortable: true},
        { key: 'descrição', label: 'Descrição', sortable: true},
        { key: 'prestador', label: 'Prestador', sortable: true},
        { key: 'data', label: 'Data', sortable: true},
        { key: 'hora_início', label: 'Hora Início', sortable: true},
        { key: 'duração', label: 'Duração', sortable: true},
        { key: 'preço_hora', label: 'Preço/hora', sortable: true},
        { key: 'acoes', label: '' }
      ],
    currentPage: 1,
    perPage: 5,
    pageOptions: [5, 10, 15],
    filter: null,
    cancelarModal: {
        id: 'cancelar-modal',
        title: '',
        content: ''
    }
  }),
  methods: {
    cancelar(item, index, button) {
      this.cancelarModal.title = `Cancelamento do serviço`
      this.cancelarModal.content = "Deseja cancelar o agendamento deste serviço?"
      this.$root.$emit('bv::show::modal', this.cancelarModal.id, button)
    },
    resetCancelarModal() {
      this.cancelarModal.title = ''
      this.cancelarModal.content = ''
    }
  },
  components: {
      Kalendar
  }
};
</script>


