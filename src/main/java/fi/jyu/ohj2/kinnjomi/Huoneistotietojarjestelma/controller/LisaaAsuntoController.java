package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LisaaAsuntoController implements Initializable {
    @FXML
    private TextField tunnusField;

    @FXML
    private Button lisaaAsuntoButton;

    @FXML
    private Button suljeLisaaAsuntoButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tunnusField.setOnAction(actionEvent -> lisaaTunnus());
        lisaaAsuntoButton.setOnAction(actionEvent -> lisaaAsunto());
        suljeLisaaAsuntoButton.setOnAction(actionEvent -> sulje());
    }

    private void lisaaTunnus(){
        IO.println("Lisäsit asunnollee tunnuksen");
    }

    private void lisaaAsunto(){
        IO.println("Lisäsit asunnon");
    }

    public void sulje(){
        IO.println("Suljit lisaa asunto näkymän");
    }
}
