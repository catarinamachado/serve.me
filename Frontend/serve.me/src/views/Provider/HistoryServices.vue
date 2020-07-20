<template>
  <div class="history-services space-top-5 space-bottom-10 space-left-right-5">
    <h4 class="space-bottom-2">Histórico de Serviços</h4>
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
      <template v-slot:cell(proposta.precoProposto)="row">
        {{row.item.proposta.precoProposto}}€
      </template>

      <template v-slot:cell(estado)="row">
      <b-form-group>
          {{row.item.estado}}
          </b-form-group>
      </template>

      <template v-slot:cell(acoes)="row">
        <b-button id="amarelo" v-b-modal.classificar-modal size="sm" v-if="row.item.estado === 'Realizado '" @click="classificar(row.item, row.index, $event.target)" class="btn btn-blue">
            <i class="fas fa-star"></i>
        </b-button>
        </template>
    </b-table>

    <!-- Info modal -->
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
                :placeholder="classificarModal.duracao + 'h'"
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
</template>

<style>
.btn-primary {
  color: white !important;
  background-color: var(--my-darker-green) !important;
  border-color: var(--my-darker-green) !important;
}

#amarelo {
  background-color: var(--my-yellow) !important;
  border-color: var(--my-yellow) !important;
}
</style>

<script>
  export default {
    name: 'history-services-provider',
    created() {
      window.scrollTo(0, 0);
      this.CompletedServices();
    },    
    data: function() {
      return {
        items: [],
        fields: [
          { key: 'pedido.classe', label: 'Classe', sortable: true },
          { key: 'pedido.categoria', label: 'Categoria', sortable: true},
          { key: 'pedido.descricao', label: 'Descrição', sortable: true},
          { key: 'cliente_nome', label: 'Cliente', sortable: true},
          { key: 'data', label: 'Data', sortable: true},
          { key: 'horaInicioDisp', label: 'Hora Início', sortable: true},
          { key: 'pedido.duracao', label: 'Duração', sortable: true},
          { key: 'proposta.precoProposto', label: 'Preço/hora', sortable: true},
          { key: 'estado', label: 'Estado' },
          { key: 'acoes', label:''}
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
    handleOK(){
      var modal = this.classificarModal;
      let data =  {
        classificacao: modal.rating ,
        opiniao: modal.comentarios ,
        email_cliente: modal.cliente_email,
        idServico: modal.id_servico + ''
      }

      this.$axios({url: this.$backend + '/rating/cliente', data: data, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
          console.log(resp.status)
          this.$alert("Serviço classificado com sucesso!", "Sucesso", "success")  
          //var newArray = this.items.slice(0, modal.idx).concat(this.items.slice(modal.idx + 1, this.items.length));
          this.items = this.CompletedServices;        
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
      this.classificarModal.data = item.data;
      this.classificarModal.hora_inicio = item.horaInicioDisp;
      this.classificarModal.duracao = item.pedido.duracao;
      this.classificarModal.preco_hora = item.proposta.precoProposto;
      this.classificarModal.cliente = item.cliente_nome;
      this.classificarModal.cliente_email = item.cliente_email;
      this.classificarModal.id_servico = item.id; 
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.title = ''
      this.classificarModal.content = ''
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
    },    
    cleanData(list){
      if(list.length > 0 ){
        list.forEach( r => {
          //Data -  Cleaning
          var str_data = r.pedido.data;
          var splitted = str_data.split('/')
          var num_month = this.getMonth(splitted[1]);
          r.data = splitted[2] + '/' + num_month + '/' + splitted[0] 
        
          //Hora Inicio - Cleaning
          var str_hora = r.pedido.horaInicioDisp;
          splitted = str_hora.split(' ')
          var hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          r.horaInicioDisp = hora[0] + 'h' + hora[1];
          //Hora Fim - Cleaning
          str_hora = r.pedido.horaFimDisp;
          splitted = str_hora.split(' ')
          hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          r.horaFimDisp = hora[0] + 'h' + hora[1];

          //Estado
          if(r.estado === "Realizado[Por AvaliarC]"){
            r.estado = "Realizado"
          }
          if(r.estado === "Realizado[Por AvaliarP]"){
            r.estado = "Realizado "
          }

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
      return list;
      }
      return [];

    },
    CompletedServices: function(){
      let token = localStorage.getItem('user-token')
      let headers = {
        Authorization: 'Bearer ' + token
      }
      this.$axios({url: this.$backend + '/services/completed-services', headers: headers, method: 'GET' }).
      then(resp => {
          console.log(resp.data);
          this.items = this.cleanData(resp.data);
        }
        );
    }
  }
}
</script>