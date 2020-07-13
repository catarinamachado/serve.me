<template>
  <div class="consult-services space-top-5 space-bottom-10 space-left-right-5">
      <div class="container">
          <h4 class="space-bottom-2">Pedidos</h4>
            <b-row align-h="end">
                <b-col cols="7">
                    <b-form inline>
                        <label>Ordenar por:</label>
                        <b-dropdown id="dropdown-ordenar" :text="dropdown_item_ordenar" variant="btn btn-green" class="m-md-2">
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Classe'">Classe</b-dropdown-item>
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Categoria'">Categoria</b-dropdown-item>
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Concelho'">Concelho</b-dropdown-item>
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Data'">Data</b-dropdown-item>
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Preço/hora'">Preço/hora</b-dropdown-item>
                        </b-dropdown>
                    </b-form>
                </b-col>
                <b-col cols="4">
                    <b-form inline>
                        <label>Filtrar por:</label>

                        <label class="sr-only" for="inline-form-input">Filtro</label>
                        <b-input
                        v-model="filter"
                        id="inline-form-input"
                        class="mb-2 mr-sm-2 mb-sm-0 m-md-2"
                        ></b-input>
                    </b-form>
                </b-col>                
            </b-row>
            <div class="row justify-content-center">
              <b-card-group deck class="space-top-3" :key="row"
                    v-for="row in formattedServices">
                    <b-card v-for="service in row" :key="service"
                        no-body
                        style="max-width: 20rem;"
                        :img-src="service.img"
                        img-height="170"
                        img-alt="Img"
                        img-top
                    >
                        <b-card-body>
                            <b-card-title>{{service.classe}}</b-card-title>
                            <b-card-sub-title class="mb-2">{{service.categoria}}</b-card-sub-title>
                            <b-card-text class="text-left space-top-3">
                                <p><strong>Descrição: </strong>{{service.descricao}}</p>
                                <p><strong>Concelho: </strong>{{service.concelho}}</p>
                                <p><strong>Data: </strong>{{service.data}}</p>
                                <p><strong>Hora início: </strong>{{service.horaInicioDisp}}</p>
                                <p><strong>Hora fim: </strong>{{service.horaFimDisp}}</p>
                                <p><strong>Duração: </strong>{{service.duracao}}</p>
                                <p><strong>Preço/hora (€): </strong>{{service.preco}}</p>
                                <p><strong>Cliente: </strong><b-link @click="seeProfile(service.cliente_email)">{{service.cliente_nome}}</b-link>

                                <!-- FIXME
                                <a id="nome-href" 
                                @click="seeProfile(service.cliente_email)" class="card-link">
                                {{service.cliente_nome}}</a>
                                -->
                                </p>
                            </b-card-text>
                        </b-card-body>
                        <b-card-footer>
                            <button @click="proposta(service.classe, service.categoria, 
                                            service.descricao, service.concelho, service.data, service.horaInicioDisp,
                                            service.horaFimDisp, service.duracao, service.preco, service.cliente_nome,
                                            row.item, row.index, $event.target)" class="btn btn-yellow-2">
                                Efetuar proposta
                            </button>
                        </b-card-footer>
                    </b-card>
                </b-card-group>

                <!-- Info modal -->
                <b-modal :id="propostaModal.id" :title="propostaModal.title" 
                         size="lg" @hide="resetPropostaModal">

                    <form ref="form" @submit.stop.prevent="handleSubmit">
                        <b-form-group
                        label="Classe"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.classe"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Categoria"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.categoria"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Descrição"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.descricao"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Concelho"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.concelho"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Data"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.data"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Hora Início Disponibilidade"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.horaInicioDisp"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Hora Fim Disponibilidade"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.horaFimDisp"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Duração"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.duracao"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Preço/hora"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.preco"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        label="Cliente"
                        >
                        <b-form-input
                            :disabled='true'
                            :placeholder="propostaModal.cliente_nome"
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        :state="horainicio"
                        label="Hora início proposto"
                        label-for="horainicio-input"
                        invalid-feedback="Hora de início é obrigatória"
                        >
                        <b-form-input
                            id="horainicio-input"
                            v-model="horainicio"
                            :state="horainicio"
                            type="time"
                            required
                        ></b-form-input>
                        </b-form-group>

                        <b-form-group
                        :state="precohora"
                        label="Preço/hora proposto (€)"
                        label-for="precohora-input"
                        invalid-feedback="Preço/hora é obrigatório"
                        >
                        <b-form-input
                            id="precohora-input"
                            v-model="precohora"
                            :state="precohora"
                            type="number"
                            min="0"
                            required
                        ></b-form-input>
                        </b-form-group>
                    </form>
                </b-modal>
            </div>
        </div>
  </div>
</template>

<style>
.dropdown-item:active {
  color: white !important;
  background-color: var(--my-darker-green) !important;
  border-color: var(--my-darker-green) !important;
}

.btn-primary {
  color: white !important;
  background-color: var(--my-darker-green) !important;
  border-color: var(--my-darker-green) !important;
}
</style>

<script>
export default {
  name: "consult-services",
  created() {
    window.scrollTo(0, 0);
    this.publish_service();
  },
  data: function () {
    return {
        dropdown_item_ordenar: 'Classe',
        dropdown_item_filtrar: 'Classe',
        url_vedacao_jardim: 'https://www.tosccawebstore.com/imgs/produtos/CIMG0766.JPG',
        url_decoracao_jardim: 'https://ceramicaburguina.com.br/wp-content/uploads/2016/04/Jardim-pequeno-002.jpg',
        url_manutencao_canteiros: 'https://flores.culturamix.com/blog/wp-content/gallery/A-Manuten%C3%A7%C3%A3o-do-Canteiro-1/A-Manuten%C3%A7%C3%A3o-do-Canteiro-3.jpg',
        url_corte_arvores: 'https://ambienteconsciente.files.wordpress.com/2010/10/poda1.jpg',
        url_remocao_ervas: 'https://decortips.com/pt/wp-content/uploads/2018/06/ervas-daninhas-remover-768x511.jpg',
        url_preparacao_solo: 'https://i.ytimg.com/vi/Gqg5xHO6LKw/maxresdefault.jpg',
        url_limpeza_jardim: 'https://www.fluentu.com/blog/french/wp-content/uploads/sites/3/2014/10/10-cool-colorful-french-autumn-vocab-words2.jpg',
        url_plantacao_arvores: 'https://greensavers.sapo.pt/wp-content/uploads/2020/01/plantar_arvore.jpg',
        url_outros: 'https://blog.quintadellarte.com.br/wp-content/uploads/2019/08/303894-aprenda-como-ter-um-jardim-de-luxo-em-casa.jpg',
        filter: '',
        services: [],
        propostaModal: {
            id: 'proposta-modal',
            title: ''
        }
    }
  },
  computed: {
    formattedServices() {
        const services = this.services.slice()
            return services.sort((a, b) => {
                if (this.dropdown_item_ordenar === 'Classe') {
                    return a.classe < b.classe ? -1 : a.classe > b.classe ? 1 : 0
                } else if (this.dropdown_item_ordenar === 'Categoria') {
                    return a.categoria < b.categoria ? -1 : a.categoria > b.categoria ? 1 : 0
                } else if (this.dropdown_item_ordenar === 'Concelho') {
                    return a.concelho < b.concelho ? -1 : a.concelho > b.concelho ? 1 : 0
                } else if (this.dropdown_item_ordenar === 'Data') {
                    return new Date(b.data) - new Date(a.data);
                } else if (this.dropdown_item_ordenar === 'Preço/hora') {
                return a.preco < b.preco ? -1 : a.preco > b.preco ? 1 : 0
                }
            }).filter(item => {
                return (item.classe.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.categoria.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.concelho.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.preco.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.data.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) 
            }).reduce((c, n, i) => {
                    if (i % 3 === 0) c.push([]);
                    c[c.length - 1].push(n);
                    return c;
            }, []);
    }
  },
  methods: {
    proposta(classe, categoria, descricao, concelho, data,
             horaInicioDisp, horaFimDisp, duracao, preco, cliente_nome,
             item, index, button) {
        this.propostaModal.title = `Proposta de agendamento de serviço`;
        this.propostaModal.classe = classe;
        this.propostaModal.categoria = categoria;
        this.propostaModal.descricao = descricao;
        this.propostaModal.concelho = concelho;
        this.propostaModal.data = data;
        this.propostaModal.horaInicioDisp = horaInicioDisp;
        this.propostaModal.horaFimDisp = horaFimDisp;
        this.propostaModal.duracao = duracao;
        this.propostaModal.preco = preco;
        this.propostaModal.cliente_nome = cliente_nome;
        this.$root.$emit('bv::show::modal', this.propostaModal.id, button)
    },
    resetPropostaModal() {
        this.propostaModal.title = ''
    },
    seeProfile(email){
        sessionStorage.setItem('email', email);

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
      if ( month == 'DECEMBER') return '12';
    },
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

        //Descrição
        if(r.descricao == ''){
            r.descricao = '-'
        }

        //URL das fotos
        if(r.categoria == 'Vedação para Jardim'){
            r.img = this.url_vedacao_jardim
        } else if(r.categoria == 'Decoração de Jardins'){
            r.img = this.url_decoracao_jardim
        } else if(r.categoria == 'Manutenção de Canteiros'){
            r.img = this.url_manutencao_canteiros
        } else if(r.categoria == 'Corte de Árvores'){
            r.img = this.url_corte_arvores
        } else if(r.categoria == 'Remoção de Ervas Daninhas'){
            r.img = this.url_remocao_ervas
        } else if(r.categoria == 'Preparação do Solo para Jardinagem'){
            r.img = this.url_preparacao_solo
        } else if(r.categoria == 'Limpeza de Jardim'){
            r.img = this.url_limpeza_jardim
        } else if(r.categoria == 'Plantação de Árvores'){
            r.img = this.url_plantacao_arvores
        } else {
            r.img = this.url_outros
        }
      });

      return list;
    },
    publish_service() {
      this.$axios({url: this.$backend + '/services/', method: 'GET',
        headers: {
        'Authorization' : 'Bearer ' + localStorage.getItem('user-token')
        }}).then(resp => {
            this.services = this.cleanData(resp.data);
      })
    }
  }
};
</script>
