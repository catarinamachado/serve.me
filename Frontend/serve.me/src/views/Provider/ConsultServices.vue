<template>
  <div class="consult-services space-top-5 space-bottom-10 space-left-right-5">
      <div class="container">
          <h4 class="space-bottom-2">Serviços</h4>
            <b-row align-h="end">
                <b-col cols="7">
                    <b-form inline>
                        <label>Ordenar por:</label>
                        <b-dropdown id="dropdown-ordenar" :text="dropdown_item_ordenar" variant="btn btn-green" class="m-md-2">
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Categoria'">Categoria</b-dropdown-item>
                            <b-dropdown-item @click="dropdown_item_ordenar = 'Subcategoria'">Subcategoria</b-dropdown-item>
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
                            <b-card-title>{{service.categoria}}</b-card-title>
                            <b-card-sub-title class="mb-2">{{service.subcategoria}}</b-card-sub-title>
                            <b-card-text class="text-left space-top-3">
                                <p><strong>Descrição: </strong>{{service.descricao}}</p>
                                <p><strong>Concelho: </strong>{{service.concelho}}</p>
                                <p><strong>Data: </strong>{{service.data}}</p>
                                <p><strong>Hora início: </strong>{{service.hora_inicio}}</p>
                                <p><strong>Hora fim: </strong>{{service.hora_fim}}</p>
                                <p><strong>Duração: </strong>{{service.duracao}}</p>
                                <p><strong>Preço/hora: </strong>{{service.preco_hora}}</p>
                                <p><strong>Cliente: </strong><a href="#" class="card-link">{{service.cliente}}</a></p>
                            </b-card-text>
                        </b-card-body>
                        <b-card-footer>
                            <button class="btn btn-yellow-2">
                                Efetuar proposta
                            </button>
                        </b-card-footer>
                    </b-card>
                </b-card-group>
            </div>
        </div>
  </div>
</template>

<style>
</style>

<script>
export default {
  name: "consult-services",
  created() {
    window.scrollTo(0, 0);
  },
    data: function () {
    return {
      dropdown_item_ordenar: 'Categoria',
      dropdown_item_filtrar: 'Categoria',
      filter: '',
      services: [
          {id:1, img: 'https://www.tosccawebstore.com/imgs/produtos/CIMG0766.JPG', 
            categoria:'Jardinagem e Bricolage', subcategoria:'Vedação para Jardim', 
            descricao:'B', concelho:'Braga', data:'1998/08/15', hora_inicio:'09h00',
            hora_fim:'12h00', duracao: '1h', preco_hora: '4€', cliente: 'António Costa'},
          {id:2, img: 'https://ceramicaburguina.com.br/wp-content/uploads/2016/04/Jardim-pequeno-002.jpg',
            categoria:'Jardinagem e Bricolage', subcategoria:'Decoração de Jardins', 
            descricao:'D', concelho:'Braga', data:'1998/04/01', hora_inicio:'09h00',
            hora_fim:'12h00', duracao: '1h', preco_hora: '3€', cliente: 'António Costa'},
          {id:3, img: 'https://flores.culturamix.com/blog/wp-content/gallery/A-Manuten%C3%A7%C3%A3o-do-Canteiro-1/A-Manuten%C3%A7%C3%A3o-do-Canteiro-3.jpg',
            categoria:'Jardinagem e Bricolage', subcategoria:'Manutenção de Canteiros', 
            descricao:'A', concelho:'Braga', data:'1998/03/03', hora_inicio:'09h00',
            hora_fim:'12h00', duracao: '1h', preco_hora: '5€', cliente: 'António Costa'},
          {id:4, img: 'https://ambienteconsciente.files.wordpress.com/2010/10/poda1.jpg', 
            categoria:'Jardinagem e Bricolage', subcategoria:'Corte de Árvores', 
            descricao:'C', concelho:'Braga', data:'1998/12/22', hora_inicio:'09h00',
            hora_fim:'12h00', duracao: '1h', preco_hora: '2€', cliente: 'António Costa'},
          {id:5, img: 'https://decortips.com/pt/wp-content/uploads/2018/06/ervas-daninhas-remover-768x511.jpg',
            categoria:'Jardinagem e Bricolage', subcategoria:'Remoção de Ervas Daninhas', 
            descricao:'F', concelho:'Braga', data:'2022/07/02', hora_inicio:'09h00',
            hora_fim:'12h00', duracao: '1h', preco_hora: '1€', cliente: 'António Costa'}                                                
      ]
    }
  },
  computed: {
    formattedServices() {
        const services = this.services.slice()
            return services.sort((a, b) => {
                if (this.dropdown_item_ordenar === 'Categoria') {
                    return a.categoria < b.categoria ? -1 : a.categoria > b.categoria ? 1 : 0
                } else if (this.dropdown_item_ordenar === 'Subcategoria') {
                    return a.subcategoria < b.subcategoria ? -1 : a.subcategoria > b.subcategoria ? 1 : 0
                } else if (this.dropdown_item_ordenar === 'Concelho') {
                    return a.concelho < b.concelho ? -1 : a.concelho > b.concelho ? 1 : 0                    
                } else if (this.dropdown_item_ordenar === 'Data') {
                    return new Date(b.data) - new Date(a.data);
                } else if (this.dropdown_item_ordenar === 'Preço/hora') {
                return a.preco_hora < b.preco_hora ? -1 : a.preco_hora > b.preco_hora ? 1 : 0
                }
            }).filter(item => {
                return (item.categoria.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.subcategoria.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.concelho.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.preco_hora.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) ||
                       (item.data.toLowerCase().indexOf(this.filter.toLowerCase()) !== -1) 
            }).reduce((c, n, i) => {
                    if (i % 3 === 0) c.push([]);
                    c[c.length - 1].push(n);
                    return c;
            }, []);
    }
  }
};
</script>
