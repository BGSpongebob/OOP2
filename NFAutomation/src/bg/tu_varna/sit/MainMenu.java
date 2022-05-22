package bg.tu_varna.sit;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    static void menu() throws IOException, ClassNotFoundException {
        File file1 = new File("Automata");
        if (!file1.exists())
            file1.mkdir();
        File file2 = new File("Automata List");
        if (!file2.exists())
            file2.createNewFile();

        System.out.println("Use 'help' to view all commands.");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();

            switch (command){
                case "help":
                    HelpMenu.help();
                    break;
                case "open":
                    if (file2.length()==0)
                        System.out.println("There are no automata.");
                    else
                        AutomataMenu.open(AutomataReader.read());
                    break;
                case "list":
                    if (file2.length()==0)
                        System.out.println("There are no automata.");
                    else
                        AutomataLister.list();
                    break;
                case "build":
                    AutomataWriter.write(new Automata());
                    break;
                case "union":
                    if (file2.length()==0)
                        System.out.println("There are no automata.");
                    else {
                        Automata automata = AutomataUnion.getUnion();
                        AutomataPrinter.print(automata);
                        System.out.println("Would you like to save this automata (y / n)?");
                        String cmd;
                        do {
                            cmd = scanner.nextLine();
                            if (!cmd.equals("y") && !cmd.equals("n"))
                                System.out.println("Invalid Command.");
                            if (cmd.equals("y"))
                                AutomataWriter.write(automata);
                        } while (!cmd.equals("y") && !cmd.equals("n"));
                    }
                    break;
                case "concat":
                    if (file2.length()==0)
                        System.out.println("There are no automata.");
                    else {
                        Automata automata2 = AutomataConcatenation.getConcat();
                        AutomataPrinter.print(automata2);
                        System.out.println("Would you like to save this automata (y / n)?");
                        String cmd;
                        do {
                            cmd = scanner.nextLine();
                            if (!cmd.equals("y") && !cmd.equals("n"))
                                System.out.println("Invalid Command.");
                            if (cmd.equals("y"))
                                AutomataWriter.write(automata2);
                        } while (!cmd.equals("y") && !cmd.equals("n"));
                        break;
                    }
                case "exit":
                    break;
                default:
                    System.out.println("Invalid Command.");
                    break;
            }
        }while (!command.equals("exit"));
        scanner.close();
    }
}