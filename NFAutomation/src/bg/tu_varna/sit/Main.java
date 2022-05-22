package bg.tu_varna.sit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class Main {
    public static Map<String, Automata> automataMap = new HashMap<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainMenu.menu();
    }
}