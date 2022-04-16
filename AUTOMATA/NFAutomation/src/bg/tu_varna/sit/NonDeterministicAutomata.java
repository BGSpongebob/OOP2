package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonDeterministicAutomata extends Automata{
    public NonDeterministicAutomata() {
        boolean nonDetermined = false;

        for (int i = 1; i <= states.size(); i++){
            for (int j = 1; j<= variables.size(); j++){
                if (table[i][j].length()>1){
                    nonDetermined=true;
                    break;
                }
            }
        }

        if (nonDetermined){
            List<String> states1 = new ArrayList<>();
            states1.add(firstState);
            List<String> endStates1 = new ArrayList<>();
            String[][] table1;

            List<List<String>> dynamicTable = new ArrayList<List<String>>();

            List<String> row0 = new ArrayList<String>(Arrays.asList(table[0]));
            List<String> row1 = new ArrayList<String>(Arrays.asList(table[1]));
            dynamicTable.add(row0);
            dynamicTable.add(row1);


            int index = 1;
            boolean newState;
            do {
            for (int i = 1; i <= variables.size(); i++){
                String current = dynamicTable.get(index).get(i);
                if (!current.equals("")){
                    if (!states1.contains(current)){
                        String[] parts = current.split("");
                        List<String> row = new ArrayList<String>();
                        states1.add(current);
                        row.add(current);
                        for (String p : parts){
                            if (endStates.contains(p))
                                endStates1.add(current);
                        }
                        for (int j = 1; j <= variables.size(); j++){
                            StringBuilder newTransition = new StringBuilder();
                            List<String> containedStates = new ArrayList<>();
                            for (String s : parts){
                                int partIndex = states.indexOf(s)+1;
                                String[] trans = table[partIndex][j].split("");
                                for (String s1 : trans){
                                    if (!containedStates.contains(s1)){
                                        containedStates.add(s1);
                                        newTransition.append(s1);
                                    }
                                }
                            }
                            char[] charArray = newTransition.toString().toCharArray();
                            Arrays.sort(charArray);
                            newTransition = new StringBuilder(new String(charArray));
                            row.add(newTransition.toString());
                        }
                        dynamicTable.add(row);
                    }
                }

            }
            index++;
            }while (index <= states1.size());

            table1 = new String[states1.size()+1][variables.size()+1];

            for (int i = 1; i <= states1.size(); i++)
                table1[i][0] = states1.get(i-1);
            for (int i = 1; i <= variables.size(); i++)
                table1[0][i] = variables.get(i-1);

            for (int i = 1; i <= states1.size(); i++){
                for (int j = 1; j<= variables.size(); j++){
                    table1[i][j] = dynamicTable.get(i).get(j);
                }
            }

            states = states1;
            endStates = endStates1;
            table = table1;
        }
    }
}
