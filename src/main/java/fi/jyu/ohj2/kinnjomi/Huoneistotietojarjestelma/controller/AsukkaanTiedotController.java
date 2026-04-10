package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AsukkaanTiedotController extends HuoneistoController implements Initializable {
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

    private Asukas asukas;
    private Asunto klikattuasunto;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nimiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        ikaField.setOnAction(actionEvent -> lisaaAsukasListaan());
        sahkopostiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        lisaaTiedotButton.setOnAction(actionEvent -> lisaaAsukasListaan());
        suljeAsukasButton.setOnAction(actionEvent -> sulje(suljeAsukasButton));
    }

    // Käsitellään nimi-, sahkoposti- ja ikaFieldein syötettyä tietoa ja tarkistetaan onko annettu syöte oikeanlaista.
    public void lisaaAsukasListaan() {
        String nimi = nimiField.getText().trim();
        String sahkoposti = sahkopostiField.getText();
        if (!validoiAsukas(nimi)) {
            return;
        }
        int ika = Integer.parseInt(ikaField.getText());
        asukas = new Asukas(nimi, ika, sahkoposti);
        klikattuasunto.lisaaAsukas(asukas);
        sulje(suljeAsukasButton);
    }

    private boolean validoiAsukas(String nimi) {
        nimiField.setStyle("");
        sahkopostiField.setStyle("");
        ikaField.setStyle("");

        String sahkoposti = sahkopostiField.getText();
        String ikaString = ikaField.getText();

        if (nimi.isBlank() || nimi.length() > 20) {
            virheIlmoitus(nimiField, "nimi puuttuu tai liian pitkä!");
            return false;
        }
        try {
            if (ikaString.isBlank()) {
                virheIlmoitus(ikaField, "Aseta ika!");
                return false;
            }

            int ika = Integer.parseInt(ikaField.getText());

            if (ika < 0 || ika > 125) {
                virheIlmoitus(ikaField, "Anna oikea ikä!");
                return false;
            }
        } catch (NumberFormatException e) {
            virheIlmoitus(ikaField, "Iän pitää olla kokonaisluku!");
            return false;
        }
        if (sahkoposti.isBlank() || sahkoposti.length() > 25) {
            virheIlmoitus(sahkopostiField, "s-posti puuttuu tai liian pitkä!");
            return false;
        }

        return true;
    }

    public void setAsunto(Asunto asunto) {
        this.klikattuasunto = asunto;
    }

}
