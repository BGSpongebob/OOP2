package bg.tu_varna.sit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutomataUnion {
    public static Automata getUnion() throws IOException, ClassNotFoundException {
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

        String first = automata1.firstState+":"+automata2.firstState;
        List<String> row1 = new ArrayList<>();
        tempStates.add(first);
        row1.add(first);
        dynamicTable.add(row1);

        int index = 1;
        do {
            String current = dynamicTable.get(index).get(0);
            String[] parts = current.split(":");
            for (int i = 1; i <= variables3.size(); i++) {
                for (int j = 1; j <= variables3.size(); j++) {
                    temp = new StringBuilder();
                    boolean flag = true;
                    for (String p : parts) {
                        int i1, j1;
                        String var = variables3.get(j - 1);
                        if (automata1.states.contains(p)) {
                            if (automata1.variables.contains(var)) {
                                i1 = automata1.states.indexOf(p);
                                j1 = automata1.variables.indexOf(var);
                                if (!automata1.table[i1 + 1][j1 + 1].equals(""))
                                    temp.append(automata1.table[i1 + 1][j1 + 1]);
                                else
                                    flag = false;
                            } else
                                flag = false;
                            if (!automata2.variables.contains(var))
                                flag = false;
                        }
                        else {
                            if (automata2.variables.contains(var)) {
                                i1 = automata2.states.indexOf(p);
                                j1 = automata2.variables.indexOf(var);
                                if (!automata2.table[i1 + 1][j1 + 1].equals(""))
                                    temp.append(automata2.table[i1 + 1][j1 + 1]);
                                else
                                    flag = false;
                            } else
                                flag = false;
                            if (!automata1.variables.contains(var))
                                flag = false;
                        }
                        if (flag)
                            temp.append(":");
                    }

                    String t = temp.toString();
                    if (!t.equals("")) {
                        do {
                            if (t.charAt(t.length() - 1) == ':') {
                                t = t.substring(0, t.length() - 1);
                            }
                        } while (t.charAt(t.length() - 1) == ':');
                        dynamicTable.get(index).add(t);
                    } else
                        dynamicTable.get(index).add("");

                    if (!t.equals("")) {
                        if (!tempStates.contains(t)) {
                            String[] tArr = t.split(":");
                            for (String s : tArr) {
                                if (automata1.endStates.contains(s) || automata2.endStates.contains(s)) {
                                    endStates3.add(t);
                                    break;
                                }
                            }
                            List<String> row = new ArrayList<String>();
                            tempStates.add(t);
                            char C = (char) ('A' + tempStates.size() - 1);
                            states3.add(Character.toString(C));
                            row.add(t);
                            dynamicTable.add(row);
                        }
                    }
                }
            }
            index++;
        }while (index <= tempStates.size());

        System.out.println("Union successful!");
        return Product.getProduct(states3, variables3, endStates3, tempStates, automata1, automata2, dynamicTable);
    }
}
