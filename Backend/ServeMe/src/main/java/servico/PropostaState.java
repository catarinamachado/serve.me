package servico;

public enum PropostaState {
    REJECTEDSEEN(-2),REJECTED(-1),UNSEEN(0),WINNER(1), WINNERSEEN(2);

    private final int value;

    private PropostaState(int value) {
        this.value = value;
    }
    public int v() {
        return value;
    }
}

/*
* When the client sees the porpose, are 3 options:
* 1. Do nothing, propose stays in the inbox(pending)
* 2. Accept, propose change state to winner (Unseen but doesnt matter, Service is Created)
* 3. Reject, propose changes state to rejected (Unseen)
*
*
* The other states exists in order to clean inbox
* */
