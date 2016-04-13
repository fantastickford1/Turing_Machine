package Core;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Stage stage;
    private int lines;
    private String text[];
    private TuringMachine turingMachine;
    private String cadenaDeEntrada;

    @FXML
    Button StartButton,StepButton,ResetButton;
    @FXML
    TextField tape;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turingMachine = new TuringMachine();
    }

    /**
     * openFile abre el FileChooser de javaFX para abrir un archivo
     */
    @FXML private void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Turing","*.turing")
        );
        stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            lines = countFile(file);
            text = new String[lines];
            readFile(file);
        }
    }

    @FXML
    private void openSetUp(){
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("SetUp.fxml"));
        Parent root= null;
        try {
            root = (Parent) myLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SetUpController myController= myLoader.getController();
        Scene set= new Scene(root);
        myController.setStage(stage);
        stage.setScene(set);
        stage.setTitle("SetUp");
        stage.show();
    }

    /**
     * Metodo que lee una variable File linea por linea
     * @param file variable tipo File a leer
     */
    private void readFile(File file) {
        String data;
        int lineN = 0;
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader= new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((data = bufferedReader.readLine()) != null){
                text[lineN] = data;
                lineN++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createTransitions(text);
    }

    private int countFile(File file){
        int count = 0;
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((bufferedReader.readLine()) != null){
                count++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private void createTransitions(String[] postTransitions){
        String ext = postTransitions[0];
        String[] extS = ext.split(" ");
        String transitions[][] = null;
        if (extS.length != 5){
            //Alert
        }else {
            //[row][column]
            transitions = new String[lines][extS.length];
            int row = 0;
            for (String trans: postTransitions) {
                int column = 0;
                String [] splited = trans.split(" ");
                for (String character : splited) {
                    transitions[row][column] = character;
                    column++;
                }
                row++;
            }
        }
        turingMachine.setTransitions(transitions);
    }

    @FXML
    private void start(){
        cadenaDeEntrada = tape.getText();
        turingMachine.setInput(cadenaDeEntrada);
        String here = turingMachine.startingTuringMachine();
        tape.setText(here);
    }
}
