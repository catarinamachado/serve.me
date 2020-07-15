<template>
  <div class="scheduled-services space-top-5 space-bottom-10 space-left-right-5">
    <h4 class="space-bottom-2">Serviços Agendados</h4>
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
      <template v-slot:cell(prestador_nome)="row">
        <b-link @click="seeProfile(row.item.prestador_email)">{{row.item.prestador_nome}}</b-link>
      </template> 
      
      <template v-slot:cell(proposta.precoProposto)="row">
          {{row.item.proposta.precoProposto}}€
      </template>
          
      <template v-slot:cell(acoes)="row">
        <b-button size="sm" @click="cancelar(row.item, row.index, $event.target)" class="btn btn-red mr-1">
          <i class="fas fa-times"></i>
        </b-button>
        <b-button size="sm" @click="classificar(row.item, row.index, $event.target)" class="btn btn-blue">
          <i class="fas fa-star"></i>
        </b-button>
      </template>
    </b-table>

    <!-- Info modal -->
    <b-modal :id="cancelarModal.id" :title="cancelarModal.title" @hide="resetCancelarModal">
      <pre>{{ cancelarModal.content }}</pre>
    </b-modal>

    <b-modal size="lg" :id="classificarModal.id" :title="classificarModal.title" @ok="handleAvaliar" @hide="resetClassificarModal">
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
                :placeholder="classificarModal.hora"
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
                :placeholder="classificarModal.preco + '€'"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Prestador"
            >
            <b-form-input
                :disabled='true'
                :placeholder="classificarModal.prestador"
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
</template>

<style scoped>
</style>

<script>
  export default {
    name: 'scheduled-services',
    created() {
      window.scrollTo(0, 0)
      this.scheduled_services()
    },
    data: function() {
      return {
        items: [],
        fields: [
          { key: 'pedido.classe', label: 'Classe', sortable: true },
          { key: 'pedido.categoria', label: 'Categoria', sortable: true},
          { key: 'pedido.descricao', label: 'Descrição', sortable: true},
          { key: 'prestador_nome', label: 'Prestador', sortable: true},
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
        cancelarModal: {
          id: 'cancelar-modal',
          title: '',
          content: ''
      },
        classificarModal: {
          id: 'classificar-modal',
          title: ''
        }      
  }},
  methods: {
    cancelar(item, index, button) {
      this.cancelarModal.title = `Cancelamento do serviço`
      this.cancelarModal.content = "Deseja cancelar o agendamento deste serviço?"
      this.$root.$emit('bv::show::modal', this.cancelarModal.id, button)
    },
    resetCancelarModal() {
      this.cancelarModal.title = ''
      this.cancelarModal.content = ''
    },
    classificar(item, index, button) {
      this.classificarModal.title = 'Classificação de Serviço';
      this.classificarModal.classe = item.pedido.classe;
      this.classificarModal.categoria = item.pedido.categoria;
      this.classificarModal.descricao = item.pedido.descricao;
      this.classificarModal.concelho = item.concelho;
      this.classificarModal.data = item.pedido.data;
      this.classificarModal.hora = item.proposta.horaProposta;
      this.classificarModal.duracao = item.pedido.duracao;
      this.classificarModal.preco = item.proposta.precoProposto;
      this.classificarModal.prestador = item.prestador_nome;
      this.classificarModal.email_prestador = item.prestador_email;
      this.classificarModal.id_servico = item.id;
      this.classificarModal.idx = index;
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.title = ''
      this.classificarModal.content = ''
    },
      handleAvaliar(){
      var modal = this.classificarModal;
      let data =  {
        classificacao: modal.rating,
        opiniao: modal.comentarios,
        email_prestador:  modal.email_prestador,
        idServico: modal.id_servico + ''
      }
      console.log(data)

       this.$axios({url: this.$backend + '/rating/prestador', data: data, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
          console.log(resp.status)
          this.$alert("Serviço classificado com sucesso!", "Sucesso", "success")  
          var newArray = this.items.slice(0, modal.idx).concat(this.items.slice(modal.idx + 1, this.items.length));
          this.items = newArray;        
        }).catch(err =>{
          console.log(err.response.data);
          this.$alert("Não foi possível classificar serviço.", "Erro", "error")
        }); 
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
    cleanData(list){
      if(list.length > 0){
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

          //Hora Fim - Cleaning
          str_hora = r.proposta.request.horaFimDisp;
          splitted = str_hora.split(' ')
          hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          r.proposta.request.horaFimDisp = hora[0] + 'h' + hora[1]

          //Duração
          var duracao = r.pedido.duracao + ''
          splitted = duracao.split('.')
          if(splitted.length > 1) {
              if (splitted[0] == '0') splitted[0] = '00'
              if (splitted[1].length == 1) splitted[1] = splitted[1] + '0'              
              r.pedido.duracao =  splitted[0] + 'h' + splitted[1];
          } else {
              r.pedido.duracao += 'h'
          }
        });

        return list
      } else {
        return []
      }
    },
    scheduled_services() {
      this.$axios({url: this.$backend + '/services/scheduled-services', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
          console.log(resp.data)          
          this.items = this.cleanData(resp.data);
      })
    },
    seeProfile(email){
      sessionStorage.setItem('email', email)

      this.$router.push({
         name: 'provider-profile'
      });
    }    
  }
}
</script>
