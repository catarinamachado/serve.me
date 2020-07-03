<template>
  <div class="my-profile content">
    <div class="container">
      <div class="row space-top-10 space-bottom-6">
        <div class="col-md-6 offset-md-3 text-left">
          <h5 class="text-left space-bottom-5">Perfil</h5>
            <form id="my-profile">
                <p>
                    <label for="nome">Nome</label>
                    <input class="w-75" type="text" placeholder="Primeiro e último Nome" name="nome" id="nome" v-model="nome">
                </p>
                <p>
                    <label for="email">E-mail</label>
                    <input class="w-75" type="email" placeholder="email@email.com" name="email" id="email" v-model="email">
                </p>
                <p>
                    <label for="contribuinte">N.º de Contribuinte</label>
                    <input type="number" placeholder="111222333" name="contribuinte" id="contribuinte" v-model="contribuinte">
                </p>
                <p>
                    <label for="telemovel">N.º de Telemóvel</label>
                    <input type="tel" placeholder="900000000" name="telemovel" id="telemovel" v-model="telemovel">
                </p>
                <p>
                    <label for="morada">Morada</label>
                    <input class="w-75" placeholder="Morada" type="text" name="morada" id="morada" v-model="morada">
                </p>
                <div class="row">
                    <div class="col-4">
                        <p>
                            <label for="freguesia">Freguesia</label>
                            <input type="text" placeholder="Freguesia" name="freguesia" id="freguesia" v-model="freguesia">
                        </p>
                    </div>
                    <div class="col-4">
                        <p>
                            <label for="concelho">Concelho</label>
                            <input type="text" placeholder="Concelho" name="concelho" id="concelho" v-model="concelho">
                        </p>
                    </div>
                    <div class="col-4">
                        <p>
                            <label for="distrito">Distrito</label>
                            <input type="text" placeholder="Distrito" name="distrito" id="distrito" v-model="distrito">
                        </p>
                    </div>      
                </div>
                <div class="row space-top-3">
                    <div class="col-6">
                        <p class="text-left">
                            <input class="btn btn-red" type="submit" value="Eliminar conta">
                        </p>
                    </div>
                    <div class="col-6">
                        <p class="text-right">
                            <input class="btn btn-yellow" type="submit" value="Guardar">
                        </p>
                    </div>
                </div>                
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
  name: "my-profile-provider",
  created() {
    window.scrollTo(0, 0);
  }
};
</script>
