<template>
  <div class="space-top-5 space-bottom-10 space-left-right-5">
    <h4 class="space-bottom-2">Serviços Agendados</h4>
    <vue-cal style="height: 550px" :time-from="8 * 60" :time-to="19 * 60" locale="pt-br" :events="events"/>
    <div class="space-top-5">
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
        <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter">
          <template v-slot:cell(cliente)="row">
            <b-link @click="seeProfile(row.item.cliente_email)">{{row.item.cliente}}</b-link>
          </template>

          <template v-slot:cell(preco_hora)="row">
              {{row.item.preco_hora}} €
          </template>

          <template v-slot:cell(acoes)="row">
              <b-button size="sm" @click="cancelar(row.item, row.index, $event.target)" class="btn btn-red mr-1">
              <i class="fas fa-times"></i>
              </b-button>
              <b-button size="sm" class="btn btn-green">
              <i class="fas fa-check"></i>
              </b-button>
          </template>
        </b-table>

        <!-- Info modal -->
        <b-modal :id="resetCancelarModal.id" :title="cancelarModal.title"
            @hide="resetCancelarModal"
            @ok="handleReject" >
            <pre>{{ resetCancelarModal.content }}</pre>
          </b-modal>
          <b-modal :id="finalizarModal.id" :title="finalizarModal.title"
            @hide="resetFinalizarModal"
            @ok="handleAccept">
            <pre>{{ finalizarModal.content }}</pre>
          </b-modal>

        <div class="justify-content-center row my-1">
        <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
        </div>
      </div>
  </div>
</template>

<style>
.vuecal__menu, .vuecal__cell-events-count {background-color: var(--my-yellow);}
.vuecal__title-bar {background-color: #FBF6D6;}
.vuecal__cell--today, .vuecal__cell--current {background-color: rgba(252, 248, 224, 0.4) !important;}
</style>

<script>
import VueCal from 'vue-cal'
import 'vue-cal/dist/vuecal.css'
import 'vue-cal/dist/i18n/pt-br.js'

export default {
  name: "scheduled-services-provider",
  created() {
    window.scrollTo(0, 0);
    this.scheduled_services();
  },
  data: () => ({
    events: [],
    items: [],
    fields: [
        { key: 'classe', label: 'Classe', sortable: true },
        { key: 'categoria', label: 'Categoria', sortable: true},
        { key: 'descricao', label: 'Descrição', sortable: true},
        { key: 'cliente', label: 'Cliente', sortable: true},
        { key: 'data', label: 'Data', sortable: true},
        { key: 'hora_inicio', label: 'Hora Início', sortable: true},
        { key: 'duracao', label: 'Duração', sortable: true},
        { key: 'preco_hora', label: 'Preço/hora', sortable: true},
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
    },
    finalizarModal: {
      id: 'finalizar-modal',
      title: '',
      content: ''
    }  
  }),
  methods: {
    finalizar(item, index, button) {
      this.finalizarModal.title = `Finalizar serviço`
      this.finalizarModal.content = "Deseja finalizar este serviço?"
      this.$root.$emit('bv::show::modal', this.finalizarModal.id, button)
    },
    resetFinalizarModal() {
      this.finalizarModal.title = ''
      this.finalizarModal.content = ''
    },
    cancelar(item, index, button) {
      this.cancelarModal.title = `Cancelamento do serviço`
      this.cancelarModal.content = "Deseja cancelar o agendamento deste serviço?"
      this.$root.$emit('bv::show::modal', this.cancelarModal.id, button)
    },
    resetCancelarModal() {
      this.cancelarModal.title = ''
      this.cancelarModal.content = ''
    },
    cleanData(list){
      if(list.length > 0){
        list.forEach(r => {
          //Data -  Cleaning
          var str_data = r.data;
          var splitted = str_data.split('/')
          var num_month = this.getMonth(splitted[1]);
          r.data = splitted[2] + '/' + num_month + '/' + splitted[0]

          //Hora Inicio - Cleaning
          var str_hora = r.hora;
          splitted = str_hora.split(' ')
          var hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          r.hora = hora[0] + 'h' + hora[1];

          //Informação - Cleaning
          var tipo = r.tipo;
          r.tipo = this.tipo2str(tipo);
        });
      return list
      }
      else{
        return []
      }
    },
    tipo2str(tipo){ //FIX ME
      if(tipo == -1) return "Aviso de cancelamento"
      if(tipo ==  1) return "Proposta de Serviço"
      if(tipo ==  2) return "Por Avaliar"
    },
    getMonth(month){
      if ( month == 'JANUARY') return '01';
      if ( month == 'FEBRUARY') return '02';
      if ( month == 'MARCH') return '03';
      if ( month == 'APRIL') return '04';
      if ( month == 'MAY') return '05';
      if ( month == 'JUNE') return '06';
      if ( month == 'JULY') return '07';
      if ( month == 'AUGUST') return '08';
      if ( month == 'SEPTEMBER') return '09';
      if ( month == 'OCTOBER') return '10';
      if ( month == 'NOVEMBER') return '11';
      if ( month == 'DECEMBER') return '12';
    },
    cleanCalendar(list){
      var events = [];

      list.forEach(r => {
        //Data
        var str_data = r.data;
        var splitted = str_data.split('/')
        var num_month = this.getMonth(splitted[1]);
        events.start = splitted[2] + '-' + num_month + '-' + splitted[0]
        events.end = splitted[2] + '-' + num_month + '-' + splitted[0]

        //Data com Hora
        var str_hora = r.hora;
        splitted = str_hora.split(' ')
        var hora = splitted[1].split(':')
        if (hora[1] == '0') hora[1] = '00'
        events.start += ' ' + hora[0] + ':' + hora[1];
        events.end += ' ' + hora[0] + ':' + hora[1];

        //Título
        events.title = r.categoria
      });

      return events
    },
    scheduled_services() {
      this.$axios({url: this.$backend + '/services/my-services', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
            this.items = this.cleanData(resp.data);
            this.events = this.cleanCalendar(resp.data);
      })
    },
    seeProfile(email){
      sessionStorage.setItem('email', email);
      
      this.$router.push({
         name: 'client-profile'
       });
    }
  },
  components: {
      VueCal
  }
};
</script>
