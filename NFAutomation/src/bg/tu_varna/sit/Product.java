package bg.tu_varna.sit;

import java.util.Collections;
import java.util.List;

public class Product {
    public static Automata getProduct(List<String> states3, List<String> variables3,
                                      List<String> endStates3, List<String> tempStates,
                                      Automata automata1, Automata automata2,
                                      List<List<String>> dynamicTable){

        for (String endState : endStates3){
            int e = tempStates.indexOf(endState);
            Collections.replaceAll(endStates3, endState, states3.get(e));
        }
        for (int k = 0; k < states3.size(); k++){
            for (int i = 1; i <= states3.size(); i++){
                for (int j = 0; j <= variables3.size(); j++)
                    if (dynamicTable.get(i).get(j).equals(tempStates.get(k)))
                        dynamicTable.get(i).set(j, states3.get(k));
            }
        }

        Automata automata3 = new Automata(automata1,automata2);
        String[][] table3 = new String[states3.size()+1][variables3.size()+1];

        for (int i = 1; i <= states3.size(); i++)
            table3[i][0] = states3.get(i-1);
        for (int i = 1; i <= variables3.size(); i++)
            table3[0][i] = variables3.get(i-1);

        for (int i = 1; i <= states3.size(); i++){
            for (int j = 1; j<= variables3.size(); j++){
                table3[i][j] = dynamicTable.get(i).get(j);
            }
        }

        table3[0][0] = "";
        automata3.firstState = states3.get(0);
        automata3.variables = variables3;
        automata3.states = states3;
        automata3.endStates = endStates3;
        automata3.table = table3;

        return automata3;
    }
}