<template>
  <div class="proposals space-top-5 space-bottom-10 space-left-right-5">

    <h4 class="space-bottom-2">Propostas</h4>
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
        <template v-slot:cell(precoProposto)="row">
            {{row.item.precoProposto}}€
        </template>
    </b-table>

    <div class="justify-content-center row my-1">
      <b-pagination size="md" :total-rows="this.items.length" :per-page="perPage" v-model="currentPage" class="customPagination"/>
    </div>
  </div>
</template>

<style scoped>
</style>

<script>
import backend from '../../store/consts'


  export default {
    name: 'proposals-provider',
    created() {
      window.scrollTo(0, 0);
      this.ProposesDone();
    },
    data: function() {
      return {
        items: [],
        fields: [
            { key: 'request.classe', label: 'Classe', sortable: true },
            { key: 'request.categoria', label: 'Subcategoria', sortable: true},
            { key: 'request.descricao', label: 'Descrição', sortable: true},
            { key: 'request.cliente_nome', label: 'Cliente', sortable: true},
            { key: 'request.data', label: 'Data', sortable: true},
            { key: 'horaProposta', label: 'Hora Início', sortable: true},
            { key: 'request.duracao', label: 'Duração', sortable: true},
            { key: 'precoProposto', label: 'Preço/hora', sortable: true},
            { key: 'vencedora', label: 'Informação', sortable: true},
            //{ key: 'acoes', label: '' }
        ],
        currentPage: 1,
        perPage: 5,
        pageOptions: [5, 10, 15],
        filter: null
  }},
  methods: {
    seeProfile(email){
        sessionStorage.setItem('email', email);

        console.log(email)

        this.$router.push({
           name: 'client-profile'
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
    },
    limpar(item, index, button) {
      this.$root.$emit('', button)
    },
     cleanData(list){
      list.forEach( r => {
        //Data -  Cleaning
        var str_data = r.request.data;
        var splitted = str_data.split('/')
        var num_month = this.getMonth(splitted[1]);
        r.request.data = splitted[2] + '/' + num_month + '/' + splitted[0] 
        
        //Hora Inicio - Cleaning
        var str_hora = r.horaProposta;
        splitted = str_hora.split(' ')
        var hora = splitted[1].split(':')
        if (hora[1] == '0') hora[1] = '00'
        r.horaProposta = hora[0] + 'h' + hora[1];
      
        //Duração
        var duracao = r.request.duracao + ''
        splitted = duracao.split('.')
        if(splitted.length > 1) {
            if (splitted[0] == '0') splitted[0] = '00'
            if (splitted[1].length == 1) splitted[1] = splitted[1] + '0'
            r.request.duracao =  splitted[0] + 'h' + splitted[1]
        } else {
            r.request.duracao += 'h'
        }      
      });
      return list;
    },
    ProposesDone:function(){
      let token = localStorage.getItem('user-token')
      let headers = {
        Authorization: 'Bearer ' + token
      }
      this.$axios({url: backend.URL + '/services/proposes-done', headers: headers, method: 'GET' }).
      then(resp => {
          console.log(resp.data);
          this.items = this.cleanData(resp.data);
          });
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
