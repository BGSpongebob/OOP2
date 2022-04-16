package bg.tu_varna.sit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AutomataWriter {
    public AutomataWriter(){};

    public void writeAutomata(DeterministicAutomata automata) throws IOException {
        File file = new File("Automata");
        if (!file.exists()){
            file.mkdir();
        }

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


        ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("Automata\\"+ID));
        OOS.writeObject(automata);
        OOS.close();
    }
}
