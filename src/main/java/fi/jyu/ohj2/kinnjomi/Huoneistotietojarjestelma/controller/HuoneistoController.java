package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Yhteinen luokka kaikkien Controllerien yhteisille metodeille
public abstract class HuoneistoController {

    //Luodaan näytölle varoitus/kehote mikäli käyttäjä sitä tarvii
    public void varoitus(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    //Controllerien yhteinen sulje metodi
    public void sulje(Button nappi){
        Scene scene = nappi.getScene();
        Stage ikkuna = (Stage) scene.getWindow();
        ikkuna.close();
    }


    //Antaa käyttäjälle virheilmoituksen mikäli käyttäjän antama syöte on puutteelista
    public void virheIlmoitus(TextField syottoField, String virheTeksti){
        syottoField.setStyle("-fx-border-color: red; -fx-background-color: #ffcccc;");
        syottoField.clear();
        syottoField.setPromptText(virheTeksti);
    }
}
