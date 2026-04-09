package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LisaaAsuntoController implements Initializable {
    @FXML
    private TextField tunnusField;

    @FXML
    private Button lisaaAsuntoButton;

    @FXML
    private Button suljeLisaaAsuntoButton;

    private Yhtio yhtio = new Yhtio();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tunnusField.setOnAction(actionEvent -> lisaaAsuntoListaan());
        lisaaAsuntoButton.setOnAction(actionEvent -> lisaaAsuntoListaan());
        suljeLisaaAsuntoButton.setOnAction(actionEvent -> sulje());
    }

    //Linkittää LisaaAsuntoControllerin asunnot listan MainControllerin asunnot listan kanssa.
    public void lisaaAsuntoPaaIkkunaan(Yhtio yhtio) {
        this.yhtio = yhtio;
    }

    //Käsitellään tunnusField tekstikenttään annettu syöte
    private void lisaaAsuntoListaan(){
        String syote = tunnusField.getText();
        if(!syote.isEmpty() ){
            Asunto uusiAsunto = new Asunto(syote);
            yhtio.lisaaAsunto(uusiAsunto);
            tunnusField.clear();
            tunnusField.requestFocus();
        }
        else{
            IO.println("Ei onnistunut");
        }
    }

    public void sulje(){
        Scene scene = tunnusField.getScene();
        Stage ikkuna = (Stage) scene.getWindow();
        ikkuna.close();
        IO.println("Suljit lisaa asunto näkymän");
    }
}
