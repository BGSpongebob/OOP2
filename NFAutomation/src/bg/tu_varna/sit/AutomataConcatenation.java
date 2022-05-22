package bg.tu_varna.sit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomataConcatenation {
    public static Automata getConcat() throws IOException, ClassNotFoundException {
        Automata automata1, automata2;
        System.out.println("Please choose your first automata.");
        automata1 = CopyAutomata.copy(AutomataReader.read());
        System.out.println("Please choose your second automata.");
        automata2 = CopyAutomata.copy(AutomataReader.read());

        List<String> states3 = new ArrayList<>();
        List<String> variables3 = new ArrayList<>();
        List<String> endStates3 = new ArrayList<>();
        List<String> tempStates = new ArrayList<>();
        List<String> allStates = new ArrayList<>();
        List<List<String>> dynamicTable = new ArrayList<List<String>>();
        StringBuilder temp;

        Initializer.initialize(states3, variables3, automata1, automata2, allStates, dynamicTable);

        tempStates.add(automata1.firstState);
        for (int i = 0; i <= automata2.states.size(); i++){
            for (int j = 0; j < automata2.variables.size()+1; j++){
                if (automata1.endStates.contains(automata1.table[i][j])){
                    automata1.table[i][j] += ":"+automata2.firstState;
                }
            }
        }

        List<String> row1 = new ArrayList<>();
        row1.add(automata1.firstState);
        for (String f : variables3){
            if (automata1.variables.contains(f)){
                int i = automata1.variables.indexOf(f);
                row1.add(automata1.table[1][i+1]);
            }
            else
                row1.add("");
        }
        dynamicTable.add(row1);

        int index = 1;
        do {
            for (int i = 1; i <=  variables3.size(); i++){
                String current = dynamicTable.get(index).get(i);
                if (!current.equals("")){
                    if (!tempStates.contains(current)){
                        String[] sParts = current.split(":");
                        ArrayList<String> parts = new ArrayList<>(Arrays.asList(sParts));
                        for (String p : parts){
                            if (automata2.endStates.contains(p)){
                                endStates3.add(current);
                                break;
                            }
                        }
                        List<String> row = new ArrayList<String>();
                        tempStates.add(current);
                        char C = (char)('A'+tempStates.size()-1);
                        states3.add(Character.toString(C));
                        row.add(current);

                        for (int j = 1; j <= variables3.size(); j++){
                            temp = new StringBuilder();
                            boolean flag = true;
                            for (String p : parts){
                                int i1;
                                String t = variables3.get(j-1);
                                if (automata1.states.contains(p)) {
                                    if (automata1.variables.contains(t)){
                                        i1 = automata1.states.indexOf(p);
                                        int j1 = automata1.variables.indexOf(t);
                                        if (!automata1.table[i1 + 1][j1 + 1].equals("")) {
                                            temp.append(automata1.table[i1 + 1][j1 + 1]);
                                            if (automata1.endStates.contains(automata1.table[i1 + 1][j1 + 1]) && !temp.toString().contains(automata2.firstState)) {
                                                temp.append(":").append(automata2.firstState);
                                            }
                                        }
                                        else
                                            flag = false;
                                    }
                                    else
                                        flag = false;
                                    if (!automata2.variables.contains(t))
                                        flag = false;
                                }
                                else {
                                    if (automata2.variables.contains(t)) {
                                        i1 = automata2.states.indexOf(p);
                                        int j1 = automata2.variables.indexOf(t);
                                        if (!temp.toString().contains(automata2.table[i1 + 1][j1 + 1])) {
                                            if (!automata2.table[i1 + 1][j1 + 1].equals(""))
                                                temp.append(automata2.table[i1 + 1][j1 + 1]);
                                            else
                                                flag = false;
                                        }
                                    }
                                    else
                                        flag = false;
                                    if (!automata1.variables.contains(t))
                                        flag = false;
                                }
                                if (flag)
                                    temp.append(":");
                            }
                            String t = temp.toString();
                            if (!t.equals("")){
                                do {
                                    if (t.charAt(t.length()-1)==':') {
                                        t = t.substring(0, t.length() - 1);
                                    }
                                }while (t.charAt(t.length()-1)==':');
                                row.add(t);
                            }
                            else
                                row.add("");
                        }
                        dynamicTable.add(row);
                    }
                }
            }
            index++;
        }while (index <= tempStates.size());

        System.out.println("Concatenation successful!");
        return Product.getProduct(states3, variables3, endStates3, tempStates, automata1, automata2, dynamicTable);
    }
}