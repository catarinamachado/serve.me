<template>
  <div class="my-profile content">
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
                <label class="m-md-2">Rua Serve Me n.º 4</label>
            </p>
            <div class="row">
                <div class="col-4">
                    <p>
                        <label for="freguesia"><strong>Freguesia:</strong></label>
                        <label class="m-md-2">Braga</label>
                    </p>
                </div>
                <div class="col-4">
                    <p>
                        <label for="concelho"><strong>Concelho:</strong></label>
                        <label class="m-md-2">Braga</label>
                    </p>
                </div>
                <div class="col-4">
                    <p>
                        <label for="distrito"><strong>Distrito:</strong></label>
                        <label class="m-md-2">Braga</label>
                    </p>
                </div>      
            </div> 
            <p>
                <label for="email"><strong>Classificação média:</strong></label>
                <b-form-rating
                id="rating-readonly"
                value="3.6536"
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
                <label class="m-md-2">432</label>
            </p>
            <p>
                <label for="servicos_cancelados"><strong>N.º de serviços cancelados:</strong></label>
                <label class="m-md-2">323</label>
            </p>

            <p class="space-top-3">
                <label for="comentarios"><strong>Comentários:</strong></label>
            </p>
            <!-- v-for -->
            <div class="border">
                <p>
                    <label>Nome Prestador</label>
                    (<b-form-rating
                    id="rating-readonly"
                    value="3.6536"
                    readonly
                    precision="3"
                    inline
                    no-border
                    ></b-form-rating>) disse:
                </p>
                <p>
                    <label>Cliente muito simpático!</label>
                </p>
            </div>
            
            <div class="border">
                <p>
                    <label>Nome Prestador</label>
                    (<b-form-rating
                    id="rating-readonly"
                    value="3.6536"
                    readonly
                    precision="3"
                    inline
                    no-border
                    ></b-form-rating>) disse:
                </p>
                <p>
                    <label>Cliente muito simpático!</label>
                </p>
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


            console.log(resp.data)
        })
    }
  }
};
</script>
