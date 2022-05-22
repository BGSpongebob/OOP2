package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Initializer {
    public static void initialize(List<String> states3, List<String> variables3,
                                  Automata automata1, Automata automata2,
                                  List<String> allStates, List<List<String>> dynamicTable){
        for (String var : automata1.variables){
            if (!variables3.contains(var))
                variables3.add(var);
        }
        for (String var : automata2.variables){
            if (!variables3.contains(var))
                variables3.add(var);
        }

        for (String state : automata1.states){
            if (!allStates.contains(state))
                allStates.add(state);
        }
        for (String state : automata2.states){
            if (!allStates.contains(state))
                allStates.add(state);
        }

        for (String state : allStates){
            if (automata1.states.contains(state) && automata2.states.contains(state)){
                for (int i = 0; i <= automata1.states.size(); i++){
                    for (int j = 0; j < automata1.variables.size()+1; j++){
                        if (automata1.table[i][j].equals(state)) {
                            automata1.table[i][j] = state + "new1";
                            Collections.replaceAll(automata1.states, state, state + "new1");
                            Collections.replaceAll(automata1.endStates, state, state + "new1");
                        }
                    }
                }
                for (int i = 0; i <= automata2.states.size(); i++){
                    for (int j = 0; j < automata2.variables.size()+1; j++){
                        if (automata2.table[i][j].equals(state)){
                            automata2.table[i][j] = state + "new2";
                            Collections.replaceAll(automata2.states, state, state + "new2");
                            Collections.replaceAll(automata2.endStates, state, state + "new2");
                        }
                    }
                }
            }
        }

        List<String> row0 = new ArrayList<>();
        row0.add("");
        row0.addAll(variables3);
        dynamicTable.add(row0);
        automata1.firstState = automata1.states.get(0);
        automata2.firstState = automata2.states.get(0);
        states3.add("A");
    }
}
