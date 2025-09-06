package main.command;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private List<Command> history= new ArrayList<>();

    public void eseguiComando(Command c){
        c.execute();
        history.add(c);
    }

}
