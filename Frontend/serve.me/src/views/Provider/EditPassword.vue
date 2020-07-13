<template>
  <div class="edit-password content">
    <div class="container">
      <div class="row space-top-15 space-bottom-6">
        <div class="col-md-6 offset-md-3 text-left">
          <h5 class="text-left space-bottom-5">Alterar password</h5>
            <form id="edit-password" @submit.prevent="updatePassword">
               <p>
                    <label for="password-atual">Password atual</label>
                    <input class="w-75" type="password" name="password-atual" id="password-atual" v-model="password_atual">
                </p>
               <p>
                    <label for="nova-password">Nova password</label>
                    <input class="w-75" type="password" name="nova-password" id="nova-password" v-model="nova_password">
                </p>
               <p>
                    <label for="nova-nova-password">Confirmar nova password</label>
                    <input class="w-50" type="password" name="nova-nova-password" id="nova-nova-password" v-model="nova_nova_password">
                </p>
                <p class="text-right">
                    <input class="btn btn-yellow" type="submit" value="Alterar">
                </p>
            </form>
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
  name: "edit-password-provider",
  created() {
    window.scrollTo(0, 0);
  },
  methods:{
      updatePassword: function(){
        let token = localStorage.getItem('user-token')
        let headers = {
            Authorization: 'Bearer ' + token
        }

        if(this.password_atual == undefined || this.nova_password == undefined || this.nova_nova_password == undefined || 
           this.password_atual == '' || this.nova_password == '' || this.nova_nova_password == '') {
          this.$alert("Preencha todos os campos.", "Erro", "error");
        } else if(this.nova_password != this.nova_nova_password) {
            this.$alert("As novas passwords não coincidem.", "Erro", "error");
        } else {
          var password_atual = this.password_atual
          var CryptoJS = require("crypto-js");// Encrypt
          var encryptedBase64Key = 'c2VydmVtZW5jcmlwdGtleQ==';
          var parsedBase64Key = CryptoJS.enc.Base64.parse(encryptedBase64Key);
          var encrypted_pw = CryptoJS.AES.encrypt(password_atual, parsedBase64Key,{
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7}).toString();
          let body = {
            pw_atual: encrypted_pw,
            pw_nova: this.nova_password,
          }
          this.$axios({url: this.$backend + '/profile/updatepw', headers: headers, data:body, method: 'POST' }).
            then(resp => { 
                if(resp.data == 'OK') {
                    this.$alert("Password alterada com sucesso!", "Sucesso", "success")
                }
                else {
                    this.$alert("Não foi possível alterar a password.", "Erro", "error")
                }

                document.getElementById('password-atual').value = ''
                document.getElementById('nova-password').value = ''
                document.getElementById('nova-nova-password').value = ''
            }).catch(err => {
                console.log(err)
                this.$alert("A password atual está incorreta.", "Erro", "error")
            })
        }
    }
  }
};
</script>

