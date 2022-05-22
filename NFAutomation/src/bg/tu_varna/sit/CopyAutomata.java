package bg.tu_varna.sit;

public class CopyAutomata {
    public static Automata copy(Automata automata){
        Automata automata1 = new Automata(null,null);
        automata1.firstState=automata.firstState;
        automata1.states.addAll(automata.states);
        automata1.variables.addAll(automata.variables);
        automata1.endStates.addAll(automata.endStates);

        int k = automata.table.length;
        int p = automata.table[0].length;
        automata1.table = new String[k][p];
        for (int i = 0; i < k; i++)
            System.arraycopy(automata.table[i], 0, automata1.table[i], 0, p);

        return automata1;
    }
}
