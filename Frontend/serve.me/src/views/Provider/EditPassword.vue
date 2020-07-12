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
import backend from '../../store/consts'

export default {
  name: "edit-password-provider",
  created() {
    window.scrollTo(0, 0);
  },
  data: function () {
    return {
      errors: []
    }
  },
  methods:{
      updatePassword: function(){
        let token = localStorage.getItem('user-token')
        let headers = {
            Authorization: 'Bearer ' + token
        }
        if(this.nova_password != this.nova_nova_password){
            var diff = "Por favor confirme a sua nova password"
            console.log(diff)
        }
        else{
          var password_atual= this.password_atual
          console.log("ATUAL " + password_atual)
          var CryptoJS = require("crypto-js");// Encrypt
          var encryptedBase64Key = 'c2VydmVtZW5jcmlwdGtleQ==';
          var parsedBase64Key = CryptoJS.enc.Base64.parse(encryptedBase64Key);
          var encrypted_pw = CryptoJS.AES.encrypt(password_atual, parsedBase64Key,{
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7}).toString();
           console.log("ENCRYPT " + encrypted_pw)
          let body = {
            pw_atual: encrypted_pw,
            pw_nova: this.nova_password,
          }
        this.$axios({url: backend.URL + '/profile/updatepw', headers: headers,data:body ,method: 'POST' }).
            then(resp => { 
                if(resp.data == 'OK'){
                    var good = "Password alterada com sucesso!"
                    console.log(good)
                }
                else{
                    var err = "Não foi possível alterar a password"
                    console.log(err)
                }
            });
        }
    }
  }
};
</script>

