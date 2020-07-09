<template>
  <div class="register-provider content">
    <div class="container">
      <div class="row space-top-8 space-bottom-6">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h3 class="text-left space-bottom-6">Registo como Prestador de Serviços</h3>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
          <form @submit.prevent="onSubmit">
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="nome"
                placeholder="Nome"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="email"
                v-model="email"
                placeholder="E-mail"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="password"
                v-model="password"
                placeholder="Password"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="number"
                v-model="contribuinte"
                placeholder="N.º de Contribuinte"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="tel"
                v-model="telemovel"
                placeholder="N.º de Telemóvel"
              />
            </fieldset>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="morada"
                placeholder="Morada"
              />
            </fieldset>
            <div>
                <b-dropdown id="dropdown-distritos" :text="dropdown_item_distritos" block variant="secondary" size="lg">
                    <b-dropdown-item v-for="distrito in distritos" :key="distrito"
                                     @click="dropdown_item_distritos = distrito"
                    >{{ distrito }}</b-dropdown-item>
                </b-dropdown>
            </div>
            <div class="space-top-3 space-bottom-3 ">
                <b-dropdown id="dropdown-concelhos" :text="dropdown_item_concelhos" block variant="secondary" size="lg">
                    <b-dropdown-item v-for="concelho in concelhos" :key="concelho"
                                     @click="dropdown_item_concelhos = concelho"
                    >{{ concelho }}</b-dropdown-item>
                </b-dropdown>
            </div>
            <fieldset class="form-group">
              <input
                class="form-control form-control-lg"
                type="text"
                v-model="freguesia"
                placeholder="Freguesia"
              />
            </fieldset>
            <div class="space-top-3 text-right">
                <button class="btn btn-yellow">
                Registar
                </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.dropdown-item:active {
  color: white !important;
  background-color: var(--my-yellow) !important;
  border-color: var(--my-yellow) !important;
}

.btn-secondary {
  color: #6C757D !important;
  background-color: white !important;
  border-color: #d0d4dc !important;
  text-align: left !important;
}
</style>

<script>
export default {
  name: "register-provider",
  created() {
    window.scrollTo(0, 0);
  },
  data: function () {
    return {
        nome: '',
        email: '',
        password: '',
        contribuinte: '',
        telemovel: '',
        morada: '',
        freguesia: '',
        concelho: '',
        dropdown_item_distritos: 'Distrito',
        dropdown_item_concelhos: 'Concelho',
        distritos: [
            'Aveiro',
            'Beja',
            'Braga',
            'Bragança',
            'Castelo Branco',
            'Coimbra',
            'Évora',
            'Faro',
            'Guarda',
            'Leiria',
            'Lisboa',
            'Portalegre',
            'Porto',
            'Santarém',
            'Setúbal',
            'Viana do Castelo',
            'Vila Real',
            'Viseu',
            'R.A. Açores',
            'R.A. Madeira'
        ],
        concelhos_aveiro: [
            'Águeda',
            'Albergaria-a-Velha',
            'Anadia',
            'Arouca',
            'Aveiro',
            'Castelo de Paiva',
            'Espinho',
            'Estarreja',
            'Ílhavo',
            'Mealhada',
            'Murtosa',
            'Oliveira de Azeméis',
            'Oliveira do Bairro',
            'Ovar',
            'Santa Maria da Feira',
            'São João da Madeira',
            'Sever do Vouga',
            'Vagos',
            'Vale de Cambra'
        ],
        concelhos_beja: [
            'Aljustrel',
            'Almodôvar',
            'Alvito',
            'Barrancos',
            'Beja',
            'Castro Verde',
            'Cuba',
            'Ferreira do Alentejo',
            'Mértola',
            'Moura',
            'Odemira',
            'Ourique',
            'Serpa',
            'Vidigueira'
        ],
        concelhos_braga: [
            'Amares',
            'Barcelos',
            'Braga',
            'Cabeceiras de Basto',
            'Celorico de Basto',
            'Esposende',
            'Fafe',
            'Guimarães',
            'Póvoa de Lanhoso',
            'Terras de Bouro',
            'Vieira do Minho',
            'Vila Nova de Famalicão',
            'Vila Verde',
            'Vizela'      
        ],
        concelhos_braganca: [
            'Alfândega da Fé',
            'Bragança',
            'Carrazeda de Ansiães',
            'Freixo de Espada à Cinta',
            'Macedo de Cavaleiros',
            'Miranda do Douro',
            'Mirandela',
            'Mogadouro',
            'Torre de Moncorvo',
            'Vila Flor',
            'Vimioso',
            'Vinhais'            
        ],
        concelhos_castelobranco: [
            'Belmonte',
            'Castelo Branco',
            'Covilhã',
            'Fundão',
            'Idanha-a-Nova',
            'Oleiros',
            'Penamacor',
            'Proença-a-Nova',
            'Sertã',
            'Vila de Rei',
            'Vila Velha de Ródão'        
        ],
        concelhos_coimbra: [
            'Arganil',
            'Cantanhede',
            'Coimbra',
            'Condeixa-a-Nova',
            'Figueira da Foz',
            'Góis',
            'Lousã',
            'Mira',
            'Miranda do Corvo',
            'Montemor-o-Velho',
            'Oliveira do Hospital',
            'Pampilhosa da Serra',
            'Penacova',
            'Penela',
            'Soure',
            'Tábua',
            'Vila Nova de Poiares'
        ],
        concelhos_evora: [
            'Alandroal',
            'Arraiolos',
            'Borba',
            'Estremoz',
            'Évora',
            'Montemor-o-Novo',
            'Mora',
            'Mourão',
            'Olivença',
            'Portel',
            'Redondo',
            'Reguengos de Monsaraz',
            'Vendas Novas',
            'Viana do Alentejo',
            'Vila Viçosa'
        ],
        concelhos_faro: [
            'Albufeira',
            'Alcoutim',
            'Aljezur',
            'Castro Marim',
            'Faro',
            'Lagoa',
            'Lagos',
            'Loulé',
            'Monchique',
            'Olhão',
            'Portimão',
            'São Brás de Alportel',
            'Silves',
            'Tavira',
            'Vila do Bispo',
            'Vila Real de Santo António'
        ],
        concelhos_guarda: [
            'Aguiar da Beira',
            'Almeida',
            'Celorico da Beira',
            'Figueira de Castelo Rodrigo',
            'Fornos de Algodres',
            'Gouveia',
            'Guarda',
            'Manteigas',
            'Mêda',
            'Pinhel',
            'Sabugal',
            'Seia',
            'Trancoso',
            'Vila Nova de Foz Côa'
        ],
        concelhos_leiria: [
            'Alcobaça',
            'Alvaiázere',
            'Ansião',
            'Batalha',
            'Bombarral',
            'Caldas da Rainha',
            'Castanheira de Pera',
            'Figueiró dos Vinhos',
            'Leiria',
            'Marinha Grande',
            'Nazaré',
            'Óbidos',
            'Pedrógão Grande',
            'Peniche',
            'Pombal',
            'Porto de Mós'
        ],
        concelhos_lisboa: [
            'Alenquer',
            'Amadora',
            'Arruda dos Vinhos',
            'Azambuja',
            'Cadaval',
            'Cascais',
            'Lisboa',
            'Loures',
            'Lourinhã',
            'Mafra',
            'Odivelas',
            'Oeiras',
            'Sintra',
            'Sobral de Monte Agraço',
            'Torres Vedras',
            'Vila Franca de Xira'
        ],
        concelhos_portalegre: [
            'Alter do Chão',
            'Arronches',
            'Avis',
            'Campo Maior',
            'Castelo de Vide',
            'Crato',
            'Elvas',
            'Fronteira',
            'Gavião',
            'Marvão',
            'Monforte',
            'Nisa',
            'Ponte de Sor',
            'Portalegre',
            'Sousel'
        ],
        concelhos_porto: [
            'Amarante',
            'Baião',
            'Felgueiras',
            'Gondomar',
            'Lousada',
            'Maia',
            'Marco de Canaveses',
            'Matosinhos',
            'Paços de Ferreira',
            'Paredes',
            'Penafiel',
            'Porto',
            'Póvoa de Varzim',
            'Santo Tirso',
            'Trofa',
            'Valongo',
            'Vila do Conde',
            'Vila Nova de Gaia'
        ],
        concelhos_santarem: [
            'Abrantes',
            'Alcanena',
            'Almeirim',
            'Alpiarça',
            'Benavente',
            'Cartaxo',
            'Chamusca',
            'Constância',
            'Coruche',
            'Entroncamento',
            'Ferreira do Zêzere',
            'Golegã',
            'Mação',
            'Ourém',
            'Rio Maior',
            'Salvaterra de Magos',
            'Santarém',
            'Sardoal',
            'Tomar',
            'Torres Novas',
            'Vila Nova da Barquinha'
        ],
        concelhos_setubal: [
            'Alcácer do Sal',
            'Alcochete',
            'Almada',
            'Barreiro',
            'Grândola',
            'Moita',
            'Montijo',
            'Palmela',
            'Santiago do Cacém',
            'Seixal',
            'Sesimbra',
            'Setúbal',
            'Sines'
        ],
        concelhos_vianadocastelo: [
            'Arcos de Valdevez',
            'Caminha',
            'Melgaço',
            'Monção',
            'Paredes de Coura',
            'Ponte da Barca',
            'Ponte de Lima',
            'Valença',
            'Viana do Castelo',
            'Vila Nova de Cerveira'
        ],
        concelhos_vilareal: [
            'Alijó',
            'Boticas',
            'Chaves',
            'Mesão Frio',
            'Mondim de Basto',
            'Montalegre',
            'Murça',
            'Peso da Régua',
            'Ribeira de Pena',
            'Sabrosa',
            'Santa Marta de Penaguião',
            'Valpaços',
            'Vila Pouca de Aguiar',
            'Vila Real'
        ],
        concelhos_viseu: [
            'Armamar',
            'Carregal do Sal',
            'Castro Daire',
            'Cinfães',
            'Lamego',
            'Mangualde',
            'Moimenta da Beira',
            'Mortágua',
            'Nelas',
            'Oliveira de Frades',
            'Penalva do Castelo',
            'Penedono',
            'Resende',
            'Santa Comba Dão',
            'São João da Pesqueira',
            'São Pedro do Sul',
            'Sátão',
            'Sernancelhe',
            'Tabuaço',
            'Tarouca',
            'Tondela',
            'Vila Nova de Paiva',
            'Viseu',
            'Vouzela'
        ],
        concelhos_acores: [
            'Calheta',
            'Corvo',
            'Horta',
            'Lagoa',
            'Lajes das Flores',
            'Lajes do Pico',
            'Madalena',
            'Nordeste',
            'Ponta Delgada',
            'Povoação',
            'Praia da Vitória',
            'Ribeira Grande',
            'Santa Cruz da Graciosa',
            'Santa Cruz das Flores',
            'São Roque do Pico',
            'Velas',
            'Vila do Porto',
            'Vila Franca do Campo'            
        ],
        concelhos_madeira: [
            'Calheta',
            'Câmara de Lobos',
            'Funchal',
            'Machico',
            'Ponta do Sol',
            'Porto Moniz',
            'Porto Santo',
            'Ribeira Brava',
            'Santa Cruz',
            'Santana',
            'São Vicente'
        ]        
    }
  },
  computed: {
    concelhos: function() {
        if (this.dropdown_item_distritos == 'Aveiro') {
            return this.concelhos_aveiro;
        } else if (this.dropdown_item_distritos == 'Beja') {
            return this.concelhos_beja;
        } else if (this.dropdown_item_distritos == 'Braga') {
            return this.concelhos_braga;
        } else if (this.dropdown_item_distritos == 'Bragança') {
            return this.concelhos_braganca;
        } else if (this.dropdown_item_distritos == 'Castelo Branco') {
            return this.concelhos_castelobranco;
        } else if (this.dropdown_item_distritos == 'Coimbra') {
            return this.concelhos_coimbra;
        } else if (this.dropdown_item_distritos == 'Évora') {
            return this.concelhos_evora;
        } else if (this.dropdown_item_distritos == 'Faro') {
            return this.concelhos_faro;
        } else if (this.dropdown_item_distritos == 'Guarda') {
            return this.concelhos_guarda;
        } else if (this.dropdown_item_distritos == 'Leiria') {
            return this.concelhos_leiria;
        } else if (this.dropdown_item_distritos == 'Lisboa') {
            return this.concelhos_lisboa;
        } else if (this.dropdown_item_distritos == 'Portalegre') {
            return this.concelhos_portalegre;
        } else if (this.dropdown_item_distritos == 'Porto') {
            return this.concelhos_porto;
        } else if (this.dropdown_item_distritos == 'Santarém') {
            return this.concelhos_santarem;
        } else if (this.dropdown_item_distritos == 'Setúbal') {
            return this.concelhos_setubal;
        } else if (this.dropdown_item_distritos == 'Viana do Castelo') {
            return this.concelhos_vianadocastelo;
        } else if (this.dropdown_item_distritos == 'Vila Real') {
            return this.concelhos_vilareal;
        } else if (this.dropdown_item_distritos == 'Viseu') {
            return this.concelhos_viseu;
        } else if (this.dropdown_item_distritos == 'R.A. Açores') {
            return this.concelhos_acores;
        } else if (this.dropdown_item_distritos == 'R.A. Madeira') {
            return this.concelhos_madeira;
        } else {
            return ['Concelho'];
        }
    }
  }
};
</script>
