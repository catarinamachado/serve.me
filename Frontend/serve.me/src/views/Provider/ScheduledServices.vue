<template>
  <div class="space-top-5 space-bottom-10 space-left-right-5">
    <h4 class="space-bottom-2">Serviços Agendados</h4>
    <vue-cal style="height: 576px" :time-from="8 * 60" :time-to="19 * 60" locale="pt-br" :events="events"/>
    <div class="space-top-5">
        <div class="justify-content-center my-1 row">
        <b-form-fieldset horizontal label="Linhas por página" class="col-6" label-size="6">
            <b-form-select
                v-model="perPage"
                id="perPageSelect"
                size="sm"
                :options="pageOptions"
            ></b-form-select>
        </b-form-fieldset>

        <b-form-fieldset horizontal label="Filtro" class="col-6" label-size="2">
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
          <template v-slot:cell(cliente_nome)="row">
            <b-link @click="seeProfile(row.item.cliente_email)">{{row.item.cliente_nome}}</b-link>
          </template>

          <template v-slot:cell(proposta.precoProposto)="row">
              {{row.item.proposta.precoProposto}}€
          </template>

          <template v-slot:cell(acoes)="row">
              <b-button size="sm" @click="cancelar(row.item, row.index, $event.target)" class="btn btn-red mr-1">
                <i class="fas fa-times"></i>
              </b-button>
              <b-button size="sm" @click="classificar(row.item, row.index, $event.target)" class="btn btn-green">
                <i class="fas fa-star"></i>
              </b-button>
          </template>
        </b-table>

        <!-- Info modal -->
        <b-modal :id="cancelarModal.id" :title="cancelarModal.title"
            @hide="cancelarModal"
            @ok="handleCancelar" >
            <pre>{{ cancelarModal.content }}</pre>
        </b-modal>

        <b-modal size="lg" :id="classificarModal.id" :title="classificarModal.title" @ok="handleOK" @hide="resetClassificarModal">
              <form ref="form" @submit.stop.prevent="handleSubmit">
                  <b-form-group
                  label="Classe"
                  >
                  <b-form-input
                      :disabled='true'
                      :placeholder="classificarModal.classe"
                  ></b-form-input>
                  </b-form-group>
                              
                  <b-form-group
                  label="Categoria"
                  >
                  <b-form-input
                      :disabled='true'
                      :placeholder="classificarModal.categoria"
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
                      :placeholder="classificarModal.preco_hora + '€'"
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
                    label="Classificação"
                    label-for="classificacao-input"
                    invalid-feedback="Classificação é obrigatória"
                  >
                  <b-form-rating v-model="classificarModal.rating" variant="warning"></b-form-rating>
                  </b-form-group>

                  <b-form-group
                    label="Comentários"
                    label-for="comentarios-input"
                  >
                  <b-form-input
                      id="comentarios-input"
                      v-model="classificarModal.comentarios"
                  ></b-form-input>
                  </b-form-group>
              </form>
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

.vuecal__event {background-color: var(--my-green);border: 1px solid var(--my-green);color: #fff;}
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
    selected_service: '',
    fields: [
        { key: 'pedido.classe', label: 'Classe', sortable: true },
        { key: 'pedido.categoria', label: 'Categoria', sortable: true},
        { key: 'pedido.descricao', label: 'Descrição', sortable: true},
        { key: 'cliente_nome', label: 'Cliente', sortable: true},
        { key: 'pedido.data', label: 'Data', sortable: true},
        { key: 'proposta.horaProposta', label: 'Hora Início', sortable: true},
        { key: 'pedido.duracao', label: 'Duração', sortable: true},
        { key: 'proposta.precoProposto', label: 'Preço/hora', sortable: true},
        { key: 'acoes', label: '' }
      ],
    currentPage: 1,
    perPage: 5,
    pageOptions: [5, 10, 15],
    filter: null,
    classificarModal: {
        id: 'classificar-modal',
        title: '',
        content: ''
    },
    cancelarModal: {
      id: 'cancelar-modal',
      title: '',
      content: ''
    }  
  }),
  methods: {
    handleOK(){
      var modal = this.classificarModal;
      let data =  {
        classificacao: modal.rating,
        opiniao: modal.comentarios,
        email_cliente: modal.cliente_email,
        idServico: modal.id_servico + ''
      }

      this.$axios({url: this.$backend + '/rating/cliente', data: data, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
          console.log(resp.status)
          this.$alert("Serviço classificado com sucesso!", "Sucesso", "success")  
          var newArray = this.items.slice(0, modal.idx).concat(this.items.slice(modal.idx + 1, this.items.length))
          this.items = newArray
          this.scheduled_services()
        }).catch(err =>{
          console.log(err.data);
          this.$alert("Não foi possível classificar serviço.", "Erro", "error")
        });
    },
    classificar(item, index, button) {
      this.classificarModal.title = `Classificação de Serviço`;
      this.classificarModal.classe = item.pedido.classe;
      this.classificarModal.categoria = item.pedido.categoria;
      this.classificarModal.descricao = item.pedido.descricao;
      this.classificarModal.concelho = item.concelho;
      this.classificarModal.data = item.pedido.data;
      this.classificarModal.hora_inicio = item.proposta.horaProposta;
      this.classificarModal.duracao = item.pedido.duracao;
      this.classificarModal.preco_hora = item.proposta.precoProposto;
      this.classificarModal.cliente = item.cliente_nome;
      this.classificarModal.cliente_email = item.cliente_email;
      this.classificarModal.id_servico = item.id;
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.rating = ''
      this.classificarModal.comentarios = ''
      this.classificarModal.title = ''
      this.classificarModal.content = ''
    },
    cancelar(item, index, button) {
      this.cancelarModal.title = `Cancelamento do serviço`
      this.cancelarModal.content = "Deseja cancelar o agendamento deste serviço?"
      this.selected_service = item.id
      this.$root.$emit('bv::show::modal', this.cancelarModal.id, button)
    },
    resetCancelarModal() {
      this.cancelarModal.title = ''
      this.cancelarModal.content = ''
    },
    handleCancelar(){
      let pedido = {
        id_servico: this.selected_service
      }

      this.$axios({url: this.$backend + '/services/cancel-service', data: pedido, method: 'POST',
        headers: {
          'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
          console.log(resp.status)
          this.$alert("Serviço cancelado com sucesso.", "Sucesso", "success")
          this.scheduled_services();
        })  
    },
    cleanData(list){
      if(list.length > 0) {
        list.forEach(r => {
          //Data -  Cleaning
          r.data = r.pedido.data
          var str_data = r.pedido.data
          var splitted = str_data.split('/')
          var num_month = this.getMonth(splitted[1])
          r.pedido.data = splitted[2] + '/' + num_month + '/' + splitted[0]

          //Hora Inicio - Cleaning
          var str_hora = r.proposta.horaProposta
          splitted = str_hora.split(' ')
          var hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          r.proposta.horaProposta = hora[0] + 'h' + hora[1]

          //Duração
          var duracao = r.pedido.duracao + ''
          splitted = duracao.split('.')
          if(splitted.length > 1) {
              if (splitted[0] == '0') splitted[0] = '00'
              if (splitted[1].length == 1) splitted[1] = splitted[1] + '0'
              r.pedido.duracao =  splitted[0] + 'h' + splitted[1]
          } else {
              r.pedido.duracao += 'h'
          }
        });
        return list
      } else {
        return []
      }
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
      var events = []
      
      if(list.length > 0) {
        list.forEach(r => {
          var tmp_start = ''
          var tmp_end = ''

          //Data
          var str_data = r.data;
          var splitted = str_data.split('/')
          var num_month = this.getMonth(splitted[1])
          tmp_start = splitted[0] + '-' + num_month + '-' + splitted[2]
          tmp_end = splitted[0] + '-' + num_month + '-' + splitted[2]

          var hora_inicio = r.proposta.horaProposta;
          splitted = hora_inicio.split('h')
          tmp_start += ' ' + splitted[0] + ':' + splitted[1]

          var duracao_tmp = r.pedido.duracao
          var duracao = duracao_tmp.split('h')
          var h_fim = parseInt(splitted[0]) + parseInt(duracao[0])
          var min_fim = 0

          if(isNaN(parseInt(duracao[1]))) {
            duracao[1] = 0
          }

          if(parseInt(splitted[1]) + parseInt(duracao[1]) >= 60) {
            h_fim = parseInt(h_fim) + 1
            min_fim = (parseInt(splitted[1]) + parseInt(duracao[1])) % 60
          } else {
            min_fim = parseInt(splitted[1]) + parseInt(duracao[1])
          }
          tmp_end += ' ' + h_fim + ':' + min_fim

          var event = {
            start: tmp_start,
            end: tmp_end,
            title: r.pedido.categoria
          } 

          events.push(event)
        });
      }

      return events;
    },
    scheduled_services() {
      this.$axios({url: this.$backend + '/services/scheduled-services', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
          console.log(resp.data)
            this.items = this.cleanData(resp.data);
            this.events = this.cleanCalendar(resp.data);
      })
    },
    seeProfile(email){
      sessionStorage.setItem('email', email)
      
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
