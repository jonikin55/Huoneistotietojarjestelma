package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.App;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller.MuokkaaAsuntoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaaAsunto.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            LisaaAsuntoController controller = loader.getController();

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Lisää asunto");

            dialogi.initModality(Modality.APPLICATION_MODAL);
            dialogi.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IO.println("Avasit lisää asunto näkymän");
    }

    private void avaaMuokkaaAsunto(){
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("muokkaaAsunto.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            MuokkaaAsuntoController controller = loader.getController();

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Muokkaa asuntoa ");
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IO.println("Avasit muokkaa asuntoa näkymän");
    }

    private void poistaAsunto(){
        IO.println("Poistit asunnon");
    }

}
