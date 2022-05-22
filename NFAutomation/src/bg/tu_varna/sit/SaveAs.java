package bg.tu_varna.sit;

import java.io.*;
import java.util.Scanner;

public class SaveAs {
    static void saveAs(Automata automata) throws IOException {
        System.out.println("Please enter the new path of your automata in the following manner:");
        System.out.println("C:\\Users\\User\\Desktop");
        Scanner scanner = new Scanner(System.in);
        String path;
        String name;
        String finalPath;
        boolean flag;
        do {
            flag = true;
            path = scanner.nextLine();
            File file = new File(path);
            if (!file.isDirectory()){
                System.out.println("This path does not exist or is not a directory.");
                flag = false;
            }

        }while (!flag);

        System.out.println("Please enter a file name:");

        do {
            flag = false;
            name = scanner.nextLine();
            finalPath = path+"\\"+name;
            System.out.println(finalPath);
            if (new File(finalPath).exists()) {
                System.out.println("This file already exists.");
                flag = true;
            }
        }while (flag);

        ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(finalPath));
        OOS.writeObject(automata);
        OOS.close();
        System.out.println("Automata successfully saved!");
    }
}