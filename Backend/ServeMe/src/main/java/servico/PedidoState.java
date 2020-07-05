package servico;

public enum PedidoState {
    WAIT(-1),RESPONDED(0),SERVICE(1);

    private final int value;

    private PedidoState(int value) {
        this.value = value;
    }
    public int v() {
        return value;
    }
}
