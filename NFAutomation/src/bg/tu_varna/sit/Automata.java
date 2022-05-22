package bg.tu_varna.sit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Automata implements Serializable {
    public String firstState;
    public List<String> states;
    public List<String> variables;
    public List<String> endStates;
    public String[][] table;

    public Automata(){
        AutomataMaker.make(this);
        AutomataDeterminer.determine(this);
    }

    public Automata(Automata automata1, Automata automata2){
        states = new ArrayList<>();
        variables = new ArrayList<>();
        endStates = new ArrayList<>();
    }
}