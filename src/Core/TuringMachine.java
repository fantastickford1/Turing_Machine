package Core;

/**
 * Created by carli on 11/04/2016.
 */
public class TuringMachine {

    private static String inicialState;
    private static String blankSymbol;
    private static String finalStates;
    private static String transitions[][];

    public static void setTransitions(String[][] transitions) {
        TuringMachine.transitions = transitions;
    }
}
