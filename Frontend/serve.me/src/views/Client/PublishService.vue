<template>
  <div class="publish-service content">
    <div class="container">
      <div class="row space-top-10 space-bottom-6">
        <div class="col-md-6 offset-md-3 text-left">
          <h5 class="text-left space-bottom-5">Publicar pedido</h5>
            <form id="publish-service">
                <p>
                    <label for="categoria">Categoria</label>
                    <select name="categoria" id="categoria" v-model="categoria">
                    <option>Jardinagem e Bricolage</option>
                    </select>
                </p>
                <p>
                    <label for="subcategoria">Subcategoria</label>
                    <select name="subcategoria" id="subcategoria" v-model="subcategoria">
                    <option>Vedação para Jardim</option>
                    <option>Decoração de Jardins</option>
                    <option>Manutenção de Canteiros</option>
                    <option>Corte de Árvores</option>
                    <option>Remoção de Ervas Daninhas</option>
                    <option>Preparação do Solo para Jardinagem</option>
                    <option>Limpeza de Jardim</option>
                    <option>Plantação de Árvores</option>
                    <option>Outro</option>                                                                                                                                         
                    </select>
                </p>
                <p>
                    <label for="descricao">Descrição</label>
                    <input class="w-75" type="text" name="descricao" id="descricao" v-model="descricao">
                </p>
                <p>
                    <label for="data">Data</label>
                    <input type="date" name="data" id="data" v-model="data">
                </p>
                <p>
                    <label for="horainicial">Hora inicial de disponibilidade</label>
                    <input type="time" name="horainicial" id="horainicial" v-model="horainicial">
                </p>
                <p>
                    <label for="horafim">Hora fim de disponibilidade</label>
                    <input type="time" name="horafim" id="horafim" v-model="horafim">
                </p>
                <p>
                    <label for="duracao">Duração</label>
                    <input type="time" name="duracao" id="duracao" v-model="duracao">
                </p>
                <p>
                    <label for="preco">Preço por hora (€)</label>
                    <input type="number" name="preco" id="preco" v-model="preco" min="0">
                </p>
                <p class="space-top-3 text-right">
                    <input class="btn btn-blue" type="submit" value="Publicar">
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
  name: "publish-service",
  created() {
    window.scrollTo(0, 0);
  }
};
</script>
