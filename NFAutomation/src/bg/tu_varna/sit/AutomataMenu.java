package bg.tu_varna.sit;

import java.io.IOException;
import java.util.Scanner;

public class AutomataMenu {
    static void open(Automata automata) throws IOException {
        System.out.println("Automata opened!");
        System.out.println("Use 'help' to view all commands.");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();

            switch (command){
                case "help":
                    HelpMenu.help();
                    break;
                case "print":
                    AutomataPrinter.print(automata);
                    break;
                case "saveas":
                    SaveAs.saveAs(automata);
                    break;
                case "recognise":
                    WordRecogniser.recognise(automata);
                    break;
                case "empty":
                    EmptyLang.checkLang(automata);
                    break;
                case "close":
                    System.out.println("Automata closed.");
                    break;
                default:
                    System.out.println("Invalid Command.");
                    break;
            }
        }while (!command.equals("close"));
    }

}