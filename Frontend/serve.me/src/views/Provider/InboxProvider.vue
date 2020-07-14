<template>
  <div class="inbox-provider space-top-5 space-bottom-10 space-left-right-5">

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
    <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter" >
        <template v-slot:cell(preco_hora)="row">
            {{row.item.preco_hora}} €
        </template>

       <template v-slot:cell(cliente)="row">
        <b-link href="/#/client-profile">{{row.item.cliente}}</b-link>
      </template>

      <template v-slot:cell(acoes)="row">
        <b-button v-if="row.item.tipo == 'Aviso de cancelamento'" size="sm" @click="seen(row.item, row.index)" class="btn btn-green">
          OK
        </b-button>
        <b-button v-if="row.item.tipo == 'Por classificar'" size="sm" class="btn btn-green"  @click="classificar(row.item, row.index, $event.target)">
          <b-icon icon="star-fill" aria-hidden="true"></b-icon>
        </b-button>        
      </template>
    </b-table>
    <!-- Info modal -->
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
                :placeholder="classificarModal.preco"
            ></b-form-input>
            </b-form-group>

            <b-form-group
            label="Prestador"
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

<style scoped>
</style>

<script>
  export default {
    name: 'inbox-provider',
    created() {
      window.scrollTo(0, 0);
      this.inbox()
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
          tipo: "Por classificar"
        }
      ],
      fields: [
          { key: 'classe', label: 'Classe', sortable: true },
          { key: 'categoria', label: 'Categoria', sortable: true},
          { key: 'descricao', label: 'Descrição', sortable: true},
          { key: 'nome', label: 'Cliente', sortable: true},
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
      classificarModal: {
        id: 'classificar-modal',
        title: ''
      }
  }},
  methods: {
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
      if(tipo ==  2) return "Por classificar"

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
    limpar(item, index, button) {
      this.$root.$emit('', button)
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
    },handleAvaliar(){
      var modal = this.classificarModal;
      let data =  {
        classificacao: modal.rating ,
        opiniao: modal.comentarios ,
        email_cliente:  modal.email_cliente,
        idServico: modal.id_servico
      }
      console.log(data)
       this.$axios({url: this.$backend + '/rating/clientex', data: data ,method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
            console.log(resp.status)
            this.$alert("Serviço classificado com sucesso", "Sucesso", "success")  
            var newArray = this.items.slice(0, modal.idx).concat(this.items.slice(modal.idx + 1, this.items.length));
            this.items = newArray;        
        }).catch(err =>{
          console.log(err.data);
            this.$alert("Não foi possível classificar serviço.", "Erro", "error")

        }); 
    },
    classificar(item, index, button) {
      this.classificarModal.title = 'Classificação de Serviço';
      this.classificarModal.classe = item.classe;
      this.classificarModal.categoria = item.categoria;
      this.classificarModal.descricao = item.descricao;
      this.classificarModal.concelho = item.concelho;
      this.classificarModal.data = item.data;
      this.classificarModal.hora = item.hora;
      this.classificarModal.duracao = item.duracao;
      this.classificarModal.preco = item.preco;
      this.classificarModal.cliente = item.nome;
      this.classificarModal.email_cliente = item.email;
      this.classificarModal.id_servico = item.id;
      this.classificarModal.idx = index;
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.title = ''
      this.classificarModal.content = ''
    },
  }
}
</script>
