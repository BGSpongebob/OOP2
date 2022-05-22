package bg.tu_varna.sit;

import java.io.*;
import java.util.Scanner;

public class AutomataReader {
    public static Automata read() throws IOException, ClassNotFoundException {
        String ID;
        Scanner sc = new Scanner(System.in);
        boolean found;

        do {
            found = true;
            System.out.println("Enter the ID of your automata: ");
            ID = sc.nextLine();
            if (!Main.automataMap.containsKey(ID)){
                if (!(new File("Automata\\"+ID).exists())) {
                    System.out.println("This ID does not exist.");
                    found = false;
                }
                else{
                    ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("Automata\\"+ID));
                    Main.automataMap.put(ID, (Automata) OIS.readObject());
                    OIS.close();
                }
            }
        }while (!found);
        return Main.automataMap.get(ID);
    }
}