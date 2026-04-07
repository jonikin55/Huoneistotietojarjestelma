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

    private ObservableList<Asukas> asukkaat;
    private Asukas asukas;
    private Asunto klikattuasunto;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nimiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        ikaField.setOnAction(actionEvent -> lisaaAsukasListaan());
        sahkopostiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        lisaaTiedotButton.setOnAction(actionEvent -> lisaaAsukasListaan());
        suljeAsukasButton.setOnAction(actionEvent -> sulje());
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


    // Käsitellään nimi-, sahkoposti- ja ikaFieldein syötettyä tietoa ja tarkistetaan onko annettu syöte oikeanlaista.
    public void lisaaAsukasListaan() {
        String nimi = nimiField.getText();
        String sahkoposti = sahkopostiField.getText();
        int ika = 0;
        try {
            ika = Integer.parseInt(ikaField.getText());
        } catch (NumberFormatException e) {
            IO.println("Iän pitää olla kokonaisluku");
            return;
        }

        if (!nimi.isEmpty() && !sahkoposti.isEmpty()) {
            asukas = new Asukas(nimi, ika, sahkoposti);
            klikattuasunto.getAsukkaat().add(asukas);
            sulje();
        } else {
            IO.println("Asukkaan kaikki tiedot tulee täyttää");
        }
    }
    public void setAsunto(Asunto asunto) {
        this.klikattuasunto = asunto;
    }
}
