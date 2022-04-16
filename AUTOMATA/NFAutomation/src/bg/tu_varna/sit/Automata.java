package bg.tu_varna.sit;

import java.io.Serializable;
import java.util.List;


public class Automata implements Serializable {
    protected String firstState;
    protected List<String> states;
    protected List<String> variables;
    protected List<String> endStates;
    protected String[][] table;

    public Automata(){
        AutomataMaker automataMaker = new AutomataMaker();
        automataMaker.makeAutomata(this);
    }
}
