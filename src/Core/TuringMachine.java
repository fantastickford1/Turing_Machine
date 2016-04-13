package Core;

import java.util.LinkedList;

/**
 * Created by carli on 11/04/2016.
 */
public class TuringMachine {

    private static String input;
    private static String inicialState;
    private static String blankSymbol;
    private static String finalStates;
    private static String transitions[][];
    private static LinkedList<Character> tape;

    TuringMachine(){
        input = null;
        inicialState = null;
        blankSymbol = null;
        finalStates = null;
        transitions = null;
        tape = null;
    }

    public static void setInput(String input) {
        TuringMachine.input = input;
    }

    public static void setTransitions(String[][] transitions) {
        TuringMachine.transitions = transitions;
    }

    public static void setInicialState(String inicialState) {
        TuringMachine.inicialState = inicialState;
    }

    public static void setBlankSymbol(String blankSymbol) {
        TuringMachine.blankSymbol = blankSymbol;
    }

    public static void setFinalStates(String finalStates) {
        TuringMachine.finalStates = finalStates;
    }

    public boolean isSetUp(){
        if (TuringMachine.inicialState != null && TuringMachine.blankSymbol != null && TuringMachine.finalStates != null && TuringMachine.transitions != null) {
            return true;
        }
        return false;
    }

    public static String startingTuringMachine(){
        setTape();
        boolean finish = false;
        int move = 0;
        char blank = blankSymbol.charAt(0);
        char currentState = inicialState.charAt(0);
        char finalStat = finalStates.charAt(0);
        while (finish == false) {
            if (move > tape.size()-1)
                tape.addLast(blank);
            if (move < 0)
                tape.addFirst(blank);
            char currentInput = tape.get(move);
            int result = searchPosition(currentState,currentInput);
            if (result < 0 && currentState == finalStat)
                break;
            else if (result < 0 && currentState != finalStat)
                return "Cadena no aceptada";
            char replace = transitions[result][2].charAt(0);
            tape.set(move,replace);
            char movement = transitions[result][3].charAt(0);
            if (movement == 'R')
                move++;
            else if (movement == 'L')
                move--;
            currentState = transitions[result][4].charAt(0);

        }
        return tape.toString();
    }

    private static void setTape(){
        TuringMachine.tape = new LinkedList<>();
        char descInput[] = input.toCharArray();
        for (char inputChar : descInput) {
            tape.add(inputChar);
        }
    }
    
    private static int searchPosition(char state, char input){
        int cont = 0;
        int row;
        for (row = 0; row < transitions.length; row++) {
            char transInput = transitions[row][cont].charAt(0);
            if (transInput == input){
                cont++;
                char transState = transitions[row][cont].charAt(0);
                if (transState == state){
                    return row;
                }else {
                    cont = 0;
                }
            }
        }
        return -1;
    }
}
