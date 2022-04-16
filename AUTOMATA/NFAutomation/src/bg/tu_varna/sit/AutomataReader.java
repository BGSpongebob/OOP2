package bg.tu_varna.sit;

import java.io.*;
import java.util.Scanner;

public class AutomataReader {
    public AutomataReader(){};

    public DeterministicAutomata readAutomata() throws IOException, ClassNotFoundException {
        String ID;
        Scanner sc = new Scanner(System.in);
        boolean found;

        do {
            found = true;
            System.out.println("Enter the ID of your automata: ");
            ID = sc.nextLine();
            if (!(new File("Automata\\"+ID).exists())) {
                System.out.println("This ID does not exist.");
                found = false;
            }
        }while (!found);
        sc.close();

        ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("Automata\\"+ID));
        return (DeterministicAutomata)OIS.readObject();
    }
}
