package bg.tu_varna.sit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DeterministicAutomata extends NonDeterministicAutomata {

    public void checkWord(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your word:");
        String word = sc.nextLine();

        boolean flag = false;
        int strIndex = 0;
        char c;
        int state = 1;
        String currentState = table[1][0];

        while (strIndex < word.length()){
            c = word.charAt(strIndex);
            int variable = 1;
            while (variable <= variables.size()){
                if (table[0][variable].equals(Character.toString(c))){
                    currentState = table[state][variable];
                    for (int i = 1; i <= states.size(); i++){
                        if (table[i][0].equals(currentState)) {
                            state = i;
                            break;
                        }
                    }
                    break;
                }
                variable++;
            }
            if (variable > variables.size()){
                flag = true;
                break;
            }
            strIndex++;
        }
        if (!flag && endStates.contains(currentState))
            System.out.println("Accepted!");
        else
            System.out.println("Rejected!");
    }

    public void write() throws IOException {
        AutomataWriter automataWriter = new AutomataWriter();
        automataWriter.writeAutomata(this);
    }

}
