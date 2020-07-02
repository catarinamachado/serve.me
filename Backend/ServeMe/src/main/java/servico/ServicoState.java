package servico;

public enum ServicoState {
    CANCELLED(-1),CREATED(0),DONE(1),EVALUATED(2);
    private final int value;

    private ServicoState(int value) {
        this.value = value;
    }
    public int v() {
        return value;
    }
}
