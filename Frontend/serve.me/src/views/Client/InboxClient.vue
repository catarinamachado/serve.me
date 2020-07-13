<template>
  <div class="inbox space-top-5 space-bottom-10 space-left-right-5">

    <h4 class="space-bottom-2">Inbox</h4>
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
       <template v-slot:cell(nome)="row">
        <b-link href="/#/provider-profile">{{row.item.nome}}</b-link>
      </template>

      <template v-slot:cell(preco)="row">
        {{row.item.preco}} €
      </template>
      
      <template v-slot:cell(duracao)="row">
        {{row.item.duracao}}h
      </template>

      <template v-slot:cell(acoes)="row">
        <b-button size="sm" v-if="row.item.tipo == 'Proposta de Serviço'" @click="rejeitar(row.item, row.index, $event.target)" class="btn btn-red mr-1">
          <i class="fas fa-times"></i>
        </b-button>
        <b-button size="sm" v-if="row.item.tipo == 'Proposta de Serviço'" @click="aceitar(row.item, row.index, $event.target)" class="btn btn-blue">
          <i class="fas fa-check"></i>
        </b-button>
        <b-button size="sm" v-if="row.item.tipo == 'Aviso de cancelamento'" class="btn btn-blue" @click="seen(row.item,row.index)">
          OK
        </b-button>
        <b-button size="sm" v-if="row.item.tipo == 'Por Avaliar'" @click="avaliar(row.item, row.index, $event.target)" class="btn btn-blue">
          <b-icon icon="star-fill" aria-hidden="true"></b-icon>
        </b-button>        
      </template>
    </b-table>

    <!-- Info modal -->
    <b-modal :id="rejeitarModal.id" :title="rejeitarModal.title"
      @hide="resetRejeitarModal"
      @ok="handleReject" >
      <pre>{{ rejeitarModal.content }}</pre>
    </b-modal>
    <b-modal :id="aceitarModal.id" :title="aceitarModal.title"
      @hide="resetAceitarModal"
      @ok="handleAccept">
      <pre>{{ aceitarModal.content }}</pre>
    </b-modal>

    <div class="justify-content-center row my-1" v-if="this.items.length > 0">
      <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
    </div>
  </div>
</template>

<style scoped>
</style>

<script>
  export default {
    name: 'inbox-client',
    created() {
      window.scrollTo(0, 0);
      //this.inbox()
    },
    data: function() {
      return {
        items: [{
          categoria: "Teste1",
          classe: "Teste2",
          descricao: "Descrição",
          nome: "Primeiro Último",
          data: "13/03/1233",
          hora: "14h00",
          duracao: "1 hora",
          preco: "4",
          tipo: "Proposta de Serviço"
        },
        {
          categoria: "Teste3",
          classe: "Teste4",
          descricao: "Descrição",
          nome: "Primeiro Último",
          data: "13/03/1233",
          hora: "14h00",
          duracao: "2 hora",
          preco: "6",
          tipo: "Por Avaliar"
        },
        {
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          prestador: "Primeiro Último",
          data: "14/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora_proposto: "4",
          informacao: "Proposta de Serviço"
        },
        {
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          prestador: "Primeiro Último",
          data: "12/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora_proposto: "4",
          informacao: "Proposta de Serviço"
        },
        {
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição",
          prestador: "Primeiro Último",
          data: "13/03/1233",
          hora_inicio: "14h00",
          duracao: "1 hora",
          preco_hora_proposto: "4",
          informacao: "Proposta de Serviço"
        }
      ],
      fields: [
          { key: 'classe', label: 'Classe', sortable: true },
          { key: 'categoria', label: 'Categoria', sortable: true},
          { key: 'descricao', label: 'Descrição', sortable: true},
          { key: 'nome', label: 'Prestador', sortable: true},
          { key: 'data', label: 'Data', sortable: true},
          { key: 'hora', label: 'Hora Início', sortable: true},
          { key: 'duracao', label: 'Duração', sortable: true},
          { key: 'preco', label: 'Preço/hora proposto', sortable: true},
          { key: 'tipo', label: 'Informação', sortable: true},
          { key: 'acoes', label: '' }
      ],
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      filter: null,
      rejeitarModal: {
        id: 'rejeitar-modal',
        title: '',
        content: ''
      },
      aceitarModal: {
        id: 'aceitar-modal',
        title: '',
        content: ''
      }      
  }},
  methods:{
    cleandata(list){
      if(list.length > 0){
        list.forEach(not => {
          //Data -  Cleaning
          var str_data = not.data;
          var splitted = str_data.split('/')
          var num_month = this.getMonth(splitted[1]);
          not.data = splitted[2] + '/' + num_month + '/' + splitted[0]

          //Hora Inicio - Cleaning
          var str_hora = not.hora;
          splitted = str_hora.split(' ')
          var hora = splitted[1].split(':')
          if (hora[1] == '0') hora[1] = '00'
          not.hora = hora[0] + 'h' + hora[1];

          //Informação - Cleaning
          var tipo = not.tipo;
          not.tipo = this.tipo2str(tipo);

        });
      return list
      }
      else{
        return []
      }
      
    },
    tipo2str(tipo){
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
    handleReject(){
      console.log('rejected');
      //TODO: Rejeitar serviço

    }
    ,
    rejeitar(item, index, button) {
      this.rejeitarModal.title = `Rejeitar serviço`
      this.rejeitarModal.content = "Deseja rejeitar este serviço?"
      this.$root.$emit('bv::show::modal', this.rejeitarModal.id, button)
    },
    resetRejeitarModal() {
      this.rejeitarModal.title = ''
      this.rejeitarModal.content = ''
    },
    aceitar(item, index, button) {
      this.aceitarModal.title = `Aceitar serviço`
      this.aceitarModal.content = "Deseja aceitar este serviço?"
      this.$root.$emit('bv::show::modal', this.aceitarModal.id, button)
    },
    resetAceitarModal() {
      this.aceitarModal.title = ''
      this.aceitarModal.content = ''
    },
    handleAccept(){
      //TODO: aceitar serviço
      console.log('accepted');
    },
    avaliar(item, index, button) {
      console.log(item,index,button);
      console.log('Avaliar');
    },
    handleAvaliar(){

    }
    ,
    inbox(){
      this.$axios({url: this.$backend + '/inbox/', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
            this.items = this.cleandata(resp.data);
            console.log(resp.data);            
        });
    },
    str2int(str){
      if(str == 'Aviso de cancelamento') return -1
      if(str == 'Proposta de Serviço') return 0
    },
    seen(item,index){
      let event = {
        id: item.id,
        tipo: this.str2int(item.tipo)
      }
      this.$axios({url: this.$backend + '/inbox/seen', data: event ,method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
            console.log(resp.status)  
            var newArray = this.items.slice(0, index).concat(this.items.slice(index + 1, this.items.length));
            this.items = newArray;        
        });
    }
  }
}
</script>
