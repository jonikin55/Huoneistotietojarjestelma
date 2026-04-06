package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.App;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller.LisaaAsuntoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MuokkaaAsuntoController implements Initializable {
    @FXML
    private TableView asuntoTable;

    @FXML
    private Label asuntoLabel;

    @FXML
    private Button lisaaAsukasButton;

    @FXML
    private Button  poistaAsukasButton;

    @FXML
    private Button suljeMuokkaaButton;

    private ObservableList<Asukas> asukkaat = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lisätään asukkaan nimi sarake ja siihen asukkaiden nimet
        TableColumn<Asukas, String> nimiSarake = new TableColumn<>("Nimi");
        nimiSarake.setPrefWidth(125);
        nimiSarake.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        asuntoTable.getColumns().add(nimiSarake);
        asuntoTable.setItems(asukkaat);

        //Lisätään asukkaan ikä sarake ja siihen asukkaiden iät
        TableColumn<Asukas, Integer> ikaSarake = new TableColumn<>("Ikä");
        ikaSarake.setPrefWidth(50);
        ikaSarake.setCellValueFactory(new PropertyValueFactory<>("ika"));
        asuntoTable.getColumns().add(ikaSarake);
        asuntoTable.setItems(asukkaat);

        //Lisätään asukkaan sahkoposti sarake ja siihen asukkaiden sahkopostit
        TableColumn<Asukas, String> sahkoPostiSarake = new TableColumn<>("Sähköposti");
        sahkoPostiSarake.setPrefWidth(225);
        sahkoPostiSarake.setCellValueFactory(new PropertyValueFactory<>("sahkoposti"));
        asuntoTable.getColumns().add(sahkoPostiSarake);
        asuntoTable.setItems(asukkaat);



        lisaaAsukasButton.setOnAction(actionEvent -> avaaAsukkaanTiedot());
        poistaAsukasButton.setOnAction(actionEvent -> poistaAsukas());
        suljeMuokkaaButton.setOnAction(actionEvent -> sulje());
    }

    private void avaaAsukkaanTiedot(){
        IO.println("nappia painettu");
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("asukkaanTiedot.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            AsukkaanTiedotController controller = loader.getController();
            controller.lisaaAsukasMuokkaaIkkunaan(this.asukkaat);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("lisää asukkaalle tiedot");
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IO.println("Avasit asukkaan tietojen lisäys ikkunan");
    }

    private void poistaAsukas(){
        IO.println("Poistit asukaan asunnosta");
    }

    private void sulje(){
        Scene scene = asuntoTable.getScene();
        Stage ikkuna = (Stage) scene.getWindow();
        ikkuna.close();
        IO.println("Suljit muokkaa asuntoa ikkunan");
    }
}
