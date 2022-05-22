package bg.tu_varna.sit;

public class HelpMenu {
    static void help(){
        System.out.println("Main menu commands:");
        System.out.println("help : Lists all commands.");
        System.out.println("open : Opens a saved automata.");
        System.out.println("list : Lists all saved automata.");
        System.out.println("build : Crates, determines (if needed) and saves a new automata.");
        System.out.println("union : Builds the union of two saved automata.");
        System.out.println("concat : Builds the concatenation of two saved automata (only works on automata with non-empty transitions).");
        System.out.println("exit : Exits the program.");
        System.out.println();
        System.out.println("Opened automata menu commands:");
        System.out.println("print : Prints all transitions of the automata.");
        System.out.println("saveas : Saves a copy of the automata in a chosen location.");
        System.out.println("recognise : Checks whether a word is a part of the automata's language or not.");
        System.out.println("empty : Checks whether the language of the automata is empty or not.");
        System.out.println("close : Returns to the main menu.");
    }
}