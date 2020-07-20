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
    <b-table striped hover :items="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter"
    :sort-by.sync="sortBy" :sort-desc.sync="sortDesc">
      <template v-slot:cell(nome)="row">
        <b-link @click="seeProfile(row.item.email)">{{row.item.nome}}</b-link>
      </template>

      <template v-slot:cell(preco)="row">
        {{row.item.preco}}€
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
        <b-button v-b-modal.avaliar-modal size="sm" v-if="row.item.tipo == 'Por classificar'" @click="classificar(row.item, row.index, $event.target)" class="btn btn-blue">
          <i class="fas fa-star"></i>
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
      this.inbox()
    },
    data: function() {
      return {
        items: [],
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
        sortBy: 'data',
        sortDesc: true,
        rejeitarModal: {
          id: 'rejeitar-modal',
          title: '',
          content: '',
          id_proposta: '',
          idx:''
        },
        aceitarModal: {
          id: 'aceitar-modal',
          title: '',
          content: '',
          id_proposta: '',
          idx:''
        },
        avalModal:{
          item: '',
          classificacao: 0,
          comments: ''
        },
        classificarModal: {
          id: 'classificar-modal',
          title: ''
        }
  }},
  methods:{
    seeProfile(email){
        sessionStorage.setItem('email', email);

        console.log(email)

        this.$router.push({
           name: 'provider-profile'
         });
    },
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

          //Duração
          var duracao = not.duracao + ''
          splitted = duracao.split('.')
          if(splitted.length > 1) {
              if (splitted[0] == '0') splitted[0] = '00'
              if (splitted[1].length == 1) splitted[1] = splitted[1] + '0'
              not.duracao =  splitted[0] + 'h' + splitted[1];
          } else {
              not.duracao += 'h'
          }
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
    handleReject(){
      let data = {
        id_proposta: this.rejeitarModal.id_proposta
      }
      this.$axios({url: this.$backend + '/services/reject-propose', data: data ,method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
            console.log(resp.data)
            this.$alert("Proposta rejeitada.", "Sucesso", "success")
            var newArray = this.items.slice(0, this.rejeitarModal.idx).concat(this.items.slice(this.rejeitarModal.idx + 1, this.items.length));
            this.items = newArray;          
        }).catch(err =>{
          console.log(err.data);
            this.$alert("Não foi possível rejeitar proposta.", "Erro", "error")

        });
    }
    ,
    rejeitar(item, index, button) {
      this.rejeitarModal.title = `Rejeitar serviço`
      this.rejeitarModal.content = "Deseja rejeitar este serviço?"
      this.rejeitarModal.id_proposta = item.id
      this.rejeitarModal.idx = index
      this.$root.$emit('bv::show::modal', this.rejeitarModal.id, button)
    },
    resetRejeitarModal() {
      this.rejeitarModal.title = ''
      this.rejeitarModal.content = ''
      this.rejeitarModal.id_proposta = ''
      this.rejeitarModal.idx = ''
    },
    aceitar(item, index, button) {
      this.aceitarModal.title = `Aceitar serviço`
      this.aceitarModal.content = "Deseja aceitar este serviço?"
      this.aceitarModal.id_proposta = item.id
      this.aceitarModal.idx = index
      this.$root.$emit('bv::show::modal', this.aceitarModal.id, button)
    },
    resetAceitarModal() {
      this.aceitarModal.title = ''
      this.aceitarModal.content = ''
      this.aceitarModal.id_proposta = ''
      this.aceitarModal.idx = ''
    },
    handleAccept(){
      let data = {
        id_proposta: this.aceitarModal.id_proposta
      }

      this.$axios({url: this.$backend + '/services/accept-propose', data: data, method: 'POST',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {   
          console.log(resp.data)
          this.$alert("Proposta aceite.", "Sucesso", "success")
          var newArray = this.items.slice(0, this.aceitarModal.idx).concat(this.items.slice(this.aceitarModal.idx + 1, this.items.length));
          this.items = newArray;          
        }).catch(err =>{
          console.log(err.data);
          this.$alert("Não foi possível aceitar proposta.", "Erro", "error")
        });
    },
    avaliar(item, index, button) {
      console.log(item,index,button);
      this.avalModal.item = item;
    },
    inbox(){
      this.$axios({url: this.$backend + '/inbox/', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
          console.log(resp.data);
          this.items = this.cleandata(resp.data);
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
      this.classificarModal.prestador = item.nome;
      this.classificarModal.email_prestador = item.email;
      this.classificarModal.id_servico = item.id;
      this.classificarModal.idx = index;
      this.$root.$emit('bv::show::modal', this.classificarModal.id, button);
    },
    resetClassificarModal() {
      this.classificarModal.title = ''
      this.classificarModal.content = ''
    },
    setCurrentSelectedRating: function(rating) {
      var cur = "You have Selected: " + rating + " stars";
      console.log(cur);
      this.avalModal.classificacao = rating;
    },
    resetavalModal() {
      this.avalModal.item = '';
      this.avalModal.classificacao = '';
      this.avalModal.comments = '';    
    },
    handlerOk() {
      // Prevent modal from closing
  /*     if(this.avalModal.classificacao == ''){
        this.$alert("Por favor, adicione uma Classificação.", "Error", "error");
        return
      }

      // Trigger submit handler
      console.log(this.avalModal); */

      console.log('aqui')
      //this.handleSubmit()
    },
    handleSubmit() {
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('avaliar-modal')
      })
    }
  },
}
</script>
