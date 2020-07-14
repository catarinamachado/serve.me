<template>
  <div class="client-profile content">
    <div class="container">
      <div class="row space-top-8 space-bottom-6">
        <div class="col-md-6 offset-md-3 text-left">
          <h5 class="text-left space-bottom-5">Perfil</h5>
            <p>
                <label for="nome"><strong>Nome:</strong></label>
                <label class="m-md-2">{{nome}}</label>
            </p>
            <p>
                <label for="email"><strong>E-mail:</strong></label>
                <label class="m-md-2">{{email}}</label>
            </p>
            <p>
                <label for="telemovel"><strong>N.º de Telemóvel:</strong></label>
                <label class="m-md-2">{{telemovel}}</label>
            </p>
            <p>
                <label for="morada"><strong>Morada:</strong></label>
                <label class="m-md-2">{{morada}}</label>
            </p>
            <div class="row">
                <div class="col-4">
                    <p>
                        <label for="freguesia"><strong>Freguesia:</strong></label>
                        <label class="m-md-2">{{freguesia}}</label>
                    </p>
                </div>
                <div class="col-4">
                    <p>
                        <label for="concelho"><strong>Concelho:</strong></label>
                        <label class="m-md-2">{{concelho}}</label>
                    </p>
                </div>
                <div class="col-4">
                    <p>
                        <label for="distrito"><strong>Distrito:</strong></label>
                        <label class="m-md-2">{{distrito}}</label>
                    </p>
                </div>      
            </div> 
            <p>
                <label for="email"><strong>Classificação média:</strong></label>
                <b-form-rating
                id="rating-readonly"
                :value="classificacao"
                readonly
                show-value
                precision="3"
                inline
                no-border
                variant="warning" 
                ></b-form-rating>
            </p>
            <p>
                <label for="servicos_realizados"><strong>N.º de serviços realizados:</strong></label>
                <label class="m-md-2">{{nr_servicos_realizados}}</label>
            </p>
            <p>
                <label for="servicos_cancelados"><strong>N.º de serviços cancelados:</strong></label>
                <label class="m-md-2">{{nr_servicos_cancelados}}</label>
            </p>

            <p v-if="avaliacoes.length > 0" class="space-top-3">
                <label for="comentarios"><strong>Comentários:</strong></label>
            </p>
            <div v-for="avaliacao in avaliacoes" :key="avaliacao">
                <div class="border">
                    <p>
                        <label>{{avaliacao.avaliador}}</label>
                        (<b-form-rating
                        id="rating-readonly"
                        :value="avaliacao.classificacao"
                        readonly
                        precision="3"
                        inline
                        no-border
                        ></b-form-rating>) disse:
                    </p>
                    <p>
                        <label>{{avaliacao.opiniao}}</label>
                    </p>
                </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
input,select {
  margin-left: 10px;
}
</style>

<script>
export default {
  name: "client-profile",
  created() {
    window.scrollTo(0, 0);
    this.profile();
  },
  data: function () {
    return {
        nome: '',
        email: '',
        telemovel: '',
        morada:'',
        freguesia: '',
        concelho: '',
        distrito: '',
        classificacao: '',
        nr_servicos_realizados: '',
        nr_servicos_cancelados: '',
        avaliacoes: {}
    }
  },
  methods: {
      profile(){
        if (sessionStorage.getItem("email")) {
            this.email = sessionStorage.getItem("email");
        }

        let token = localStorage.getItem('user-token')
        let headers = {
            Authorization: 'Bearer ' + token
        }

        let data = {
            email_cli: this.email
        }

        this.$axios({url: this.$backend + '/profile/clienteprof', headers: headers, data: data, method: 'POST' })
        .then(resp => {
            this.nome = resp.data.nome
            this.email = resp.data.email
            this.telemovel = resp.data.nrTelm
            this.morada = resp.data.morada
            this.freguesia = resp.data.freguesia
            this.concelho = resp.data.concelho
            this.distrito = resp.data.distrito
            this.classificacao = resp.data.classificacao
            this.nr_servicos_realizados = resp.data.numServicosCancelados
            this.nr_servicos_cancelados = resp.data.numServicosRealizados
            this.avaliacoes = resp.data.avaliacoes
            console.log(resp.data)
        })
    }
  }
};
</script>
