package bg.tu_varna.sit;

public class AutomataPrinter {
    public static void print(Automata automata) {
        int cols = automata.variables.size()+1;
        for (String[] row : automata.table){
            for (String i : row) {
                System.out.print(i);
                if(i.length()<4)
                    System.out.print("\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
            for (int i = 0; i < cols*2; i++)
                System.out.print("----");
            System.out.println();
        }
        System.out.println("End states: ");
        for (String s : automata.endStates)
            System.out.print(s+" ");
        System.out.println();
    }
}