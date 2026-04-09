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
    private Yhtio yhtio;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nimiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        ikaField.setOnAction(actionEvent -> lisaaAsukasListaan());
        sahkopostiField.setOnAction(actionEvent -> lisaaAsukasListaan());
        lisaaTiedotButton.setOnAction(actionEvent -> lisaaAsukasListaan());
        suljeAsukasButton.setOnAction(actionEvent -> sulje());
    }

    // Käsitellään nimi-, sahkoposti- ja ikaFieldein syötettyä tietoa ja tarkistetaan onko annettu syöte oikeanlaista.
    public void lisaaAsukasListaan() {
        String nimi = nimiField.getText();
        String sahkoposti = sahkopostiField.getText();
        if (!validoiAsukas()) {
            return;
        }
        int ika = Integer.parseInt(ikaField.getText());
        asukas = new Asukas(nimi, ika, sahkoposti);
        klikattuasunto.lisaaAsukas(asukas);
        sulje();
    }

    private boolean validoiAsukas(){
        nimiField.setStyle("");
        sahkopostiField.setStyle("");
        ikaField.setStyle("");

        String nimi = nimiField.getText();
        String sahkoposti = sahkopostiField.getText();
        String ikaString = ikaField.getText();

        if(nimi.isBlank() || nimi.isEmpty() || nimi.length() > 20){
            virheIlmoitus(nimiField, "nimi puuttuu tai liian pitkä!");
            return false;
        }
        try{
            if(ikaString.isEmpty() || ikaString.isBlank()){
                virheIlmoitus(ikaField, "Aseta ika!");
                return false;
            }

            int ika = Integer.parseInt(ikaField.getText());

            if(ika < 0 || ika > 125){
                virheIlmoitus(ikaField, "Anna oikea ikä!");
                return false;
            }
        } catch (NumberFormatException e) {
            virheIlmoitus(ikaField, "Iän pitää olla kokonaisluku!");
            return false;
        }
        if(sahkoposti.isBlank() || sahkoposti.isEmpty() || sahkoposti.length() > 20){
            virheIlmoitus(sahkopostiField, "s-posti puuttuu tai liian pitkä!");
            return false;
        }

        return true;
    }

    public void virheIlmoitus(TextField syottoField, String virheTeksti){
        syottoField.setStyle("-fx-border-color: red; -fx-background-color: #ffcccc;");
        syottoField.clear();
        syottoField.setPromptText(virheTeksti);
    }

    public void setAsunto(Asunto asunto) {
        this.klikattuasunto = asunto;
    }

    public void sulje(){
        Scene scene = nimiField.getScene();
        Stage ikkuna = (Stage) scene.getWindow();
        ikkuna.close();
        IO.println("Suljit Asukkaan tiedot ikkunan");
    }

}
