package servico;

public enum PedidoState {
    CANCELLED(-100),EXPIRED(-10),WAIT(-1),RESPONDED(0),SERVICE(1), DONE(100);

    private final int value;

    private PedidoState(int value) {
        this.value = value;
    }
    public int v() {
        return value;
    }
}
