package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AsukkaanTiedotController implements Initializable {
    @FXML
    private TextField nimiField;

    @FXML
    private TextField ikaField;

    @FXML
    private TextField sahkopostiField;

    @FXML
    private Button lisaaTiedotButton;

    @FXML
    private Button suljeAsukasButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nimiField.setOnAction(actionEvent -> lisaaNimi());
        ikaField.setOnAction(actionEvent -> lisaaIka());
        sahkopostiField.setOnAction(actionEvent -> lisaaSahkoposti());
        lisaaTiedotButton.setOnAction(actionEvent -> tallennaTiedot());
        suljeAsukasButton.setOnAction(actionEvent -> sulje());
    }

    public void lisaaNimi(){
        IO.println("Lisäsit asukkaalle nimen");
    }

    public void lisaaIka(){
        IO.println("Lisäsit asukkaalle iän");
    }

    public void lisaaSahkoposti(){
        IO.println("Lisäsit asukkaalle sähköpostin");
    }

    public void tallennaTiedot(){
        IO.println("Tallensit asukkaalle asettamasi tiedot");
    }

    public void sulje(){
        Scene scene = nimiField.getScene();
        Stage ikkuna = (Stage) scene.getWindow();
        ikkuna.close();
        IO.println("Suljit Asukkaan tiedot ikkunan");
    }
}
