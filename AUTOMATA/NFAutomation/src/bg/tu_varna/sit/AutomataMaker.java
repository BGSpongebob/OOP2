package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.Scanner;

public class AutomataMaker {
    public AutomataMaker(){};

    public void makeAutomata(Automata automata){
        automata.states = new ArrayList<>();
        automata.variables = new ArrayList<>();
        automata.endStates = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String temp;

        System.out.println("Enter the states of your automata. " +
                "The first state you enter will be the first state of the automata. " +
                "When you are done press Enter.");
        do {
            temp = sc.nextLine();
            if (!temp.isEmpty()) {
                if (!automata.states.contains(temp))
                    automata.states.add(temp);
                else
                    System.out.println("This state already exists.");
            }
        }while (!temp.isEmpty());

        System.out.println("Enter the variables of your automata. When you are done press Enter.");
        do {
            temp = sc.nextLine();
            if (!temp.isEmpty()) {
                if (!automata.variables.contains(temp))
                    automata.variables.add(temp);
                else
                    System.out.println("This variable already exists.");
            }
        }while (!temp.isEmpty());

        System.out.println("Enter the end states of your automata. When you are done press Enter.");
        do {
            temp = sc.nextLine();
            if (!temp.isEmpty()) {
                if (automata.states.contains(temp)) {
                    if (!automata.endStates.contains(temp))
                        automata.endStates.add(temp);
                    else
                        System.out.println("This is already an end state.");
                }
                else
                    System.out.println("That state does not exist.");
            }
        }while (!temp.isEmpty());

        automata.firstState = automata.states.get(0);

        int numOfStates = automata.states.size();
        int numOfVariables = automata.variables.size();

        automata.table = new String[numOfStates+1][numOfVariables+1];

        for (int i = 1; i <= numOfStates; i++)
            automata.table[i][0] = automata.states.get(i-1);
        for (int i = 1; i <= numOfVariables; i++)
            automata.table[0][i] = automata.variables.get(i-1);
        automata.table[0][0] = "";
        System.out.println("Please enter the transition values of your automata (state or multiple states(concatenated)): ");
        for (int i = 1; i <= numOfStates; i++){
            for (int j = 1; j<= numOfVariables; j++){
                boolean flag = true;
                System.out.println("row "+i+", col "+j+":");
                temp = sc.nextLine();
                String[] tempArr = temp.split("");
                for (String s : tempArr){
                    if (!automata.states.contains(s)&&!s.equals("")){
                        flag = false;
                        System.out.println("This state(s) does not exist.");
                        j--;
                        break;
                    }
                }
                if (flag)
                    automata.table[i][j] = temp;
            }
        }
    }

}
