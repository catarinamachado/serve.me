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
      
      <template v-slot:cell(proposta.request.preco)="row">
          {{row.item.proposta.request.preco}} €
      </template>
          
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
</template>

<style scoped>
</style>

<script>
  export default {
    name: 'scheduled-services',
    created() {
      window.scrollTo(0, 0);
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
          { key: 'proposta.request.horaInicioDisp', label: 'Hora Início', sortable: true},
          { key: 'pedido.duracao', label: 'Duração', sortable: true},
          { key: 'proposta.request.preco', label: 'Preço/hora', sortable: true},
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
    format_data(list){
      list.forEach( r => {
          console.log(r)
      });
      return list;
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
      list.forEach(r => {
        //Data -  Cleaning
        r.data = r.pedido.data
        var str_data = r.pedido.data
        var splitted = str_data.split('/')
        var num_month = this.getMonth(splitted[1])
        r.pedido.data = splitted[2] + '/' + num_month + '/' + splitted[0]

        //Hora Inicio - Cleaning
        var str_hora = r.proposta.request.horaInicioDisp
        splitted = str_hora.split(' ')
        var hora = splitted[1].split(':')
        if (hora[1] == '0') hora[1] = '00'
        r.proposta.request.horaInicioDisp = hora[0] + 'h' + hora[1]

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
            r.pedido.duracao =  splitted[0] + 'h' + splitted[1] + '0';
        } else {
            r.pedido.duracao += 'h'
        }
      });

      return list
    },
    scheduled_services() {
      this.$axios({url: this.$backend + '/services/scheduled-services', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
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
