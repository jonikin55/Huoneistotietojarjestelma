package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class LisaaAsuntoController extends HuoneistoController implements Initializable {
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
        suljeLisaaAsuntoButton.setOnAction(actionEvent -> sulje(suljeLisaaAsuntoButton));
    }

    //Linkittää LisaaAsuntoControllerin asunnot listan MainControllerin asunnot listan kanssa.
    public void lisaaAsuntoPaaIkkunaan(Yhtio yhtio) {
        this.yhtio = yhtio;
    }

    //Käsitellään tunnusField tekstikenttään annettu syöte
    private void lisaaAsuntoListaan() {
        String syote = tunnusField.getText().trim();
        if (!validoiAsunto(syote)) {
            return;
        }
        Asunto uusiAsunto = new Asunto(syote);
        yhtio.lisaaAsunto(uusiAsunto);
        tunnusField.clear();
        tunnusField.requestFocus();
    }

    private boolean validoiAsunto(String syote) {
        tunnusField.setStyle("");

        if (syote.isBlank()) {
            virheIlmoitus(tunnusField, "Tunnus puuttuu!");
            return false;
        }
        if (syote.length() > 8) {
            virheIlmoitus(tunnusField, "Enintään 8 merkkiä!");
            return false;
        }
        return true;
    }

}
