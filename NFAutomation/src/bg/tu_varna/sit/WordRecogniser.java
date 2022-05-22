package bg.tu_varna.sit;

import java.util.Scanner;

public class WordRecogniser {
    public static void recognise(Automata automata){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your word:");
        String word = sc.nextLine();

        boolean flag = false;
        int strIndex = 0;
        char c;
        int state = 1;
        String currentState = automata.table[1][0];

        while (strIndex < word.length()){
            c = word.charAt(strIndex);
            int variable = 1;
            while (variable <= automata.variables.size()){
                if (automata.table[0][variable].equals(Character.toString(c))){
                    currentState = automata.table[state][variable];
                    for (int i = 1; i <= automata.states.size(); i++){
                        if (automata.table[i][0].equals(currentState)) {
                            state = i;
                            break;
                        }
                    }
                    break;
                }
                variable++;
            }
            if (variable > automata.variables.size()){
                flag = true;
                break;
            }
            strIndex++;
        }
        if (!flag && automata.endStates.contains(currentState))
            System.out.println("Accepted!");
        else
            System.out.println("Rejected!");
    }
}