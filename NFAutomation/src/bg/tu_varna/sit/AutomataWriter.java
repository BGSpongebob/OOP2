package bg.tu_varna.sit;

import java.io.*;
import java.util.Scanner;

public class AutomataWriter {
    public static void write(Automata automata) throws IOException {
        String ID="";
        Scanner sc = new Scanner(System.in);
        boolean exists;

        do {
            exists = false;
            System.out.println("Enter an ID for your automata: ");
            ID = sc.nextLine();
            if (new File("Automata\\"+ID).exists()) {
                System.out.println("This ID has already been taken.");
                exists = true;
            }
        }while (exists);

        FileWriter fileWriter = new FileWriter("Automata List", true);
        fileWriter.write(ID+"\n");
        fileWriter.close();
        ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("Automata\\"+ID));
        OOS.writeObject(automata);
        OOS.close();
        Main.automataMap.put(ID, automata);
        System.out.println("Automata successfully saved!");
    }
}