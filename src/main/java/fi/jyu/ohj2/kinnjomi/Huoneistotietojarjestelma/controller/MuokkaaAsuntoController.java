package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller.LisaaAsuntoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;


public class MuokkaaAsuntoController implements Initializable {
    @FXML
    private TableView asuntoTable;

    @FXML
    private Button lisaaAsukasButton;

    @FXML
    private Button  poistaAsukasButton;

    @FXML
    private Button suljeMuokkaaButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lisaaAsukasButton.setOnAction(actionEvent -> avaaAsukkaanTiedot());
        poistaAsukasButton.setOnAction(actionEvent -> poistaAsukas());
        suljeMuokkaaButton.setOnAction(actionEvent -> sulje());
    }

    private void avaaAsukkaanTiedot(){
        IO.println("Avasit asukkaan tietojen lisäys ikkunan");
    }

    private void poistaAsukas(){
        IO.println("Poistit asukaan asunnosta");
    }

    private void sulje(){
        IO.println("Suljit muokkaa asuntoa ikkunan");
    }
}
