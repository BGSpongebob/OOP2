package bg.tu_varna.sit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutomataLister {
    static void list() throws FileNotFoundException {
        Scanner scanner =  new Scanner(new File("Automata List"));
        while (scanner.hasNext())
            System.out.println(scanner.next());
        scanner.close();
    }
}