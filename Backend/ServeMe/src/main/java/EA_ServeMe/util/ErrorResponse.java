package EA_ServeMe.util;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String localError;
    private List<String> msgs;

    public ErrorResponse() {
        localError = "";
        msgs = new ArrayList<>();
    }

    public void addMsg(String msg){this.msgs.add(msg);}

    public ErrorResponse build(){
        return this;
    }

    public String getLocalError() {
        return localError;
    }

    public void setLocalError(String localError) {
        this.localError = localError;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }
}
