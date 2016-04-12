package Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(Stage stage){
        this.stage= stage;
    }

    @FXML
    private void close(){
        this.stage.close();
    }


}
