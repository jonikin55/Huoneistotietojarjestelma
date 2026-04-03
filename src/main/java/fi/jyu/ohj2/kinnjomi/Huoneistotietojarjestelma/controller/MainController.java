package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TableView asuntoTiedotTable;

    @FXML
    private Button lisaaAsuntoButton;

    @FXML
    private Button muokkaaButton;

    @FXML
    private Button poistaButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lisaaAsuntoButton.setOnAction(actionEvent -> avaaLisaaAsunto());
        muokkaaButton.setOnAction(actionEvent -> avaaMuokkaaAsunto());
        poistaButton.setOnAction(actionEvent -> poistaAsunto());
    }

    private void avaaLisaaAsunto(){
        IO.println("Avasit lisää asunto näkymän");
    }

    private void avaaMuokkaaAsunto(){
        IO.println("Avasit muokkaa asuntoa näkymän");
    }

    private void poistaAsunto(){
        IO.println("Poistit asunnon");
    }
}
