package EA_ServeMe.responses;

// Tipos:
// -1 : Cancelamento
// 1 : Proposta
// 2 : Avaliação




public class InboxResponse {

    private int ID;
    private String nome;
    private String email;
    private String descricao;
    private String classe;
    private String categoria;
    private String data;
    private String hora;
    private double duracao;
    private double preco;
    private int tipo;

    public InboxResponse(int ID, String nome, String email, String descricao, String classe, String categoria, String data, String hora, double duracao, double preco, int tipo) {
        this.ID = ID;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.classe = classe;
        this.categoria = categoria;
        this.data = data;
        this.hora = hora;
        this.duracao = duracao;
        this.preco = preco;
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
