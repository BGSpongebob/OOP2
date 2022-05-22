package bg.tu_varna.sit;

public class EmptyLang {
    static void checkLang(Automata automata){
        if (automata.variables.isEmpty())
            System.out.println("The language of this automata is empty.");
        else
            System.out.println("The language of this automata is not empty.");
    }
}
