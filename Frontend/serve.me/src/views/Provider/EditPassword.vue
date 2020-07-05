<template>
  <div class="edit-password content">
    <div class="container">
      <div class="row space-top-15 space-bottom-6">
        <div class="col-md-6 offset-md-3 text-left">
          <h5 class="text-left space-bottom-5">Alterar password</h5>
            <form id="edit-password">
               <p>
                    <label for="password-atual">Password atual</label>
                    <input class="w-75" type="password" name="password-atual" id="password-atual">
                </p>
               <p>
                    <label for="nova-password">Nova password</label>
                    <input class="w-75" type="password" name="nova-password" id="nova-password">
                </p>
               <p>
                    <label for="nova-nova-password">Confirmar nova password</label>
                    <input class="w-50" type="password" name="nova-nova-password" id="nova-nova-password">
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
  }
};
</script>
