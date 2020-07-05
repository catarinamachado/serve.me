package servico;

public enum ServicoState {
    CANCELLEDSEEN(-12),PROVIDERCANCELLED(-2),CLIENTCANCELLED(-1),CREATED(0),CLIENTDONE(1),PROVIDERDONE(2),EVALUATED(12);
    private final int value;

    private ServicoState(int value) {
        this.value = value;
    }
    public int v() {
        return value;
    }
}
