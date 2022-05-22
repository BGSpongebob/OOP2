package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomataDeterminer{
    public static void determine(Automata automata){
        boolean nonDetermined = false;

        for (int i = 1; i <= automata.states.size(); i++){
            for (int j = 1; j<= automata.variables.size(); j++){
                if (automata.table[i][j].length()>1){
                    nonDetermined=true;
                    break;
                }
            }
        }

        if (nonDetermined){
            System.out.println("This automata is non-determined. It will be converted into a determined one in order to use it.");

            List<String> states1 = new ArrayList<>();
            states1.add(automata.firstState);
            List<String> endStates1 = new ArrayList<>();
            String[][] table1;

            List<List<String>> dynamicTable = new ArrayList<List<String>>();
            List<String> row0 = new ArrayList<String>(Arrays.asList(automata.table[0]));
            List<String> row1 = new ArrayList<String>(Arrays.asList(automata.table[1]));
            dynamicTable.add(row0);
            dynamicTable.add(row1);


            int index = 1;
            do {
                for (int i = 1; i <= automata.variables.size(); i++){
                    String current = dynamicTable.get(index).get(i);
                    if (!current.equals("")){
                        if (!states1.contains(current)){
                            String[] parts = current.split("");
                            List<String> row = new ArrayList<String>();
                            states1.add(current);
                            row.add(current);
                            for (String p : parts){
                                if (automata.endStates.contains(p))
                                    endStates1.add(current);
                            }
                            for (int j = 1; j <= automata.variables.size(); j++){
                                StringBuilder newTransition = new StringBuilder();
                                List<String> containedStates = new ArrayList<>();
                                for (String s : parts){
                                    int partIndex = automata.states.indexOf(s)+1;
                                    String[] trans = automata.table[partIndex][j].split("");
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

            table1 = new String[states1.size()+1][automata.variables.size()+1];

            for (int i = 1; i <= states1.size(); i++)
                table1[i][0] = states1.get(i-1);
            for (int i = 1; i <= automata.variables.size(); i++)
                table1[0][i] = automata.variables.get(i-1);

            for (int i = 1; i <= states1.size(); i++){
                for (int j = 1; j<= automata.variables.size(); j++){
                    table1[i][j] = dynamicTable.get(i).get(j);
                }
            }
            table1[0][0] = "";
            automata.states = states1;
            automata.endStates = endStates1;
            automata.table = table1;
        }
        else
            System.out.println("This automata is already determined. There will be no need for conversion.");
        System.out.println("Automata successfully created!");
    }
}