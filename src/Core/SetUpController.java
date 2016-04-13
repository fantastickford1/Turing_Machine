package Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by carli on 11/04/2016.
 */
public class SetUpController implements Initializable {

    private Stage stage;
    private String inicial;
    private String espacio;
    private String aceptacion;
    private TuringMachine turingMachine;

    @FXML
    TextField stateField,blackField,acceptationField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(Stage stage){
        this.stage= stage;
    }

    @FXML
    private void close(){
        String initialState = stateField.getText();
        String espacio = blackField.getText();
        String aceptacion = acceptationField.getText();
        turingMachine.setInicialState(initialState);
        turingMachine.setBlankSymbol(espacio);
        turingMachine.setFinalStates(aceptacion);
        this.stage.close();
    }


}
