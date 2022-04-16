package bg.tu_varna.sit;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DeterministicAutomata deterministicAutomata = new DeterministicAutomata();
        AutomataReader automataReader = new AutomataReader();

        deterministicAutomata.write();

        DeterministicAutomata deterministicAutomata1 = automataReader.readAutomata();
        System.out.println(Arrays.deepToString(deterministicAutomata1.table));
    }
}
