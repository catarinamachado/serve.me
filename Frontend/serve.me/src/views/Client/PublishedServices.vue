<template>
  <div class="published-services space-top-5 space-bottom-10 space-left-right-5">

    <h4 class="space-bottom-2">Pedidos Publicados</h4>
    <div class="justify-content-center my-1 row">
      <b-form-fieldset horizontal label="Linhas por página" class="col-6" label-size='6'>
         <b-form-select
            v-model="perPage"
            id="perPageSelect"
            size="sm"
            :options="pageOptions"
          ></b-form-select>
      </b-form-fieldset>

      <b-form-fieldset horizontal label="Filtro" class="col-6" label-size='2'>
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
    <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter" :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc" v-cloak>
      <template v-slot:cell(preco)="row">
        <p v-if="row.item.id != editRow"> {{row.item.preco}}€ </p>
        <b-form-input v-if="row.item.id == editRow" v-model="row.item.preco"/>
      </template>
      
      <template v-slot:cell(duracao)="row">
        <p v-if="row.item.id != editRow"> {{row.item.duracao}}h  </p>
        <b-form-input v-if="row.item.id == editRow" v-model="row.item.duracao"/>
      </template>
      
      <template v-slot:cell(acoes)="row">
        <div v-show="row.item.id != editRow">
          <b-button v-if="row.item.estado !== 'Agendado' && row.item.estado !== 'Cancelado' && row.item.estado !== 'Expirado'" 
          size="sm" @click="cancelar(row.item, row.index, $event.target)" class="btn btn-red mr-1"
          >
            <i class="fas fa-trash-alt"></i>
          </b-button>        
          <b-button v-if="row.item.estado !== 'Agendado' && row.item.estado !== 'Cancelado' && row.item.estado !== 'Expirado'"
           size="sm" class="btn btn-blue"
          @click="edit(row.item)" 
          >
            <i class="fas fa-pencil-alt"></i>
          </b-button>
        </div>
        <div v-show="row.item.id == editRow">  
          <b-button variant="outline-primary" size="sm" class="btn btn-blue" @click="sendEdit(row.item)">
            <b-icon icon="pencil"></b-icon>
          </b-button>
        </div>
      </template>
    </b-table>

    <!-- Info modal @hide="resetCancelarModal"-->
    <b-modal :id="cancelarModal.id" :title="cancelarModal.title"
      @cancel="resetCancelarModal"
      @ok="handleOk">
      <pre>{{ cancelarModal.content }}</pre>
    </b-modal>

    <!-- Info modal @hide="resetCancelarModal"
    <b-modal :id="editModal.id" :title="editModal.title"
      @cancel="resetEditModal"
      @ok="handleOk">
      <pre>{{ editModal.content }}</pre>
    </b-modal>

          @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
    
    <b-modal
      id="modal-edit"
      ref="modal"
      title="Editar Pedido"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Duração" label-for="dur-input">
          <b-form-input id="dur-input" v-model="this.editModal.dur"> </b-form-input>
        </b-form-group>
        <b-form-group label="Preço" label-for="preco-input">
          <b-form-input id="preco-input" v-model="this.editModal.preco"> </b-form-input>
        </b-form-group>
      </form>
    </b-modal>
    -->

    <div class="justify-content-center row my-1">
      <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
    </div>
  </div>
</template>

<style scoped>
[v-cloak] {
      display: none;
    }
    .edit {
      display: none;
    }
    .editing .edit {
      display: block
    }
    .editing .view {
      display: none;
    }
</style>

<script>
  //import  service from './publishedRequests'
  export default {
    name: 'published-services',
    created() {
      window.scrollTo(0, 0);
      this.PublishedRequests();
    },
    data: function() {
      return {
        items: [{
          categoria: "Teste1",
          subcategoria: "Teste2",
          descricao: "Descrição 10",
          data: "12/03/1233",
          hora_inicio: "14h00",
          hora_fim: "15h00",
          duracao: "1 hora",
          preco_hora: "4",
          estado: "Ativo"
        },
        {
          categoria: "Teste3",
          subcategoria: "Teste4",
          descricao: "Descrição 11",
          data: "13/03/1233",
          hora_inicio: "14h00",
          hora_fim: "15h00",
          duracao: "1 hora",
          preco_hora: "6",
          estado: "Pendente"
        },
        {
          categoria: "Teste5",
          subcategoria: "Teste6",
          descricao: "Descrição 12",
          data: "14/03/1233",
          hora_inicio: "14h00",
          hora_fim: "15h00",
          duracao: "1 hora",
          preco_hora: "8",
          estado: "Expirado"
        }
      ],
      fields: [
          { key: 'classe', label: 'Classe', sortable: true },
          { key: 'categoria', label: 'Categoria', sortable: true},
          { key: 'descricao', label: 'Descrição', sortable: true},
          { key: 'data', label: 'Data', sortable: true},
          { key: 'horaInicioDisp', label: 'Hora Início', sortable: true},
          { key: 'horaFimDisp', label: 'Hora Fim', sortable: true},
          { key: 'duracao', label: 'Duração', sortable: true},
          { key: 'preco', label: 'Preço/hora', sortable: true},
          { key: 'estado', label: 'Estado', sortable: true},
          { key: 'acoes', label: '' }
      ],
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      filter: null,
      sortBy: 'data',
      sortDesc: true,
      editRow: 0,
      cancelarModal: {
        id: 'cancelar-modal',
        title: '',
        content: '',
        selected: ''
      },

  }},
  methods: {
    cancelar(item, index, button) {
      this.cancelarModal.title = `Cancelamento do serviço`
      this.cancelarModal.content = "Deseja eliminar a publicação deste serviço?"
      this.selected = item.id
      this.$root.$emit('bv::show::modal', this.cancelarModal.id, button)

    },
    resetCancelarModal() {
      this.cancelarModal.title = ''
      this.cancelarModal.content = ''
      this.selected = ''
    },
    handleOk() {
        // Prevent modal from closing
        let req = {
        id: this.selected
      }
        this.$axios({url: this.$backend + '/services/delete-request', data: req, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {    
            console.log(resp.status)
            this.$alert("Pedido Cancelado Com Sucesso.", "Sucesso", "Success")          
        });
        this.PublishedRequests()
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
    }
    ,
    cleanData(list){
      list.forEach( r => {
        //Data -  Cleaning
        var str_data = r.data;
        var splitted = str_data.split('/')
        var num_month = this.getMonth(splitted[1]);
        r.data = splitted[2] + '/' + num_month + '/' + splitted[0] 
        
        //Hora Inicio - Cleaning
        var str_hora = r.horaInicioDisp;
        splitted = str_hora.split(' ')
        var hora = splitted[1].split(':')
        if (hora[1] == '0') hora[1] = '00'
        r.horaInicioDisp = hora[0] + 'h' + hora[1];

        //Hora Fim - Cleaning
        str_hora = r.horaFimDisp;
        splitted = str_hora.split(' ')
        hora = splitted[1].split(':')
        if (hora[1] == '0') hora[1] = '00'
        r.horaFimDisp = hora[0] + 'h' + hora[1];

      });
      return list;
    }
    ,
    PublishedRequests(){
      this.$axios({url: this.$backend + '/services/my-requests', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {    
            this.items = this.cleanData(resp.data);               
        });
    },
     edit (item) {
      this.editRow = item.id
      console.log(this.editRow)
      
    },
    sendEdit(item) {
      this.editRow = 0
      var str_data = item.data;
      var splitted = str_data.split('/')
      var splitted_horaI = item.horaInicioDisp.split('h')
      var splitted_horaF = item.horaFimDisp.split('h')
      var datainicio = splitted[2] + '/' + splitted[1] + '/'+ splitted[0] + ' ' + splitted_horaI[0] + ':' + splitted_horaI[1]
      var datafim = splitted[2] + '/' + splitted[1] + '/'+ splitted[0] + ' ' + splitted_horaF[0] + ':' + splitted_horaF[1]
      
      let req = {
        id: item.id,
        preco: item.preco,
        categoria: item.categoria,
        duracao: item.duracao,
        descricao: item.descricao,
        dataInicio: datainicio,
        dataFim: datafim

      }
      console.log(req)
      this.$axios({url: this.$backend + '/services/edit-request', data: req, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {    
            console.log(resp.status)
            this.$alert("Pedido editado com Sucesso.", "Sucesso", "success")          

        }).catch(err =>{
          console.log(err.data)
          this.$alert("Erro na edição do Pedido.", "Erro", "error") 
        });
    }
  },
  computed:{
  }
}
</script>