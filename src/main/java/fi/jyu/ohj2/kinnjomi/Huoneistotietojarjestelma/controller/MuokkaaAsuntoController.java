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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MuokkaaAsuntoController  extends HuoneistoController implements Initializable {
    @FXML
    private TableView<Asukas> asuntoTable;

    @FXML
    private Label asuntoLabel;

    @FXML
    private Button lisaaAsukasButton;

    @FXML
    private Button  poistaAsukasButton;

    @FXML
    private Button suljeMuokkaaButton;

    private Asunto klikattuAsunto;
    private Asukas valittuAsukas;

    private ObservableList<Asukas> asukkaat = FXCollections.observableArrayList();
    private Yhtio yhtio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lisätään asukkaan nimi sarake ja siihen asukkaiden nimet
        TableColumn<Asukas, String> nimiSarake = new TableColumn<>("Nimi");
        nimiSarake.setPrefWidth(125);
        nimiSarake.setCellValueFactory(cd -> cd.getValue().nimiProperty());
        asuntoTable.getColumns().add(nimiSarake);

        //Lisätään asukkaan ikä sarake ja siihen asukkaiden iät
        TableColumn<Asukas, Number> ikaSarake = new TableColumn<>("Ikä");
        ikaSarake.setPrefWidth(50);
        ikaSarake.setCellValueFactory(cd -> cd.getValue().ikaProperty());
        asuntoTable.getColumns().add(ikaSarake);

        //Lisätään asukkaan sahkoposti sarake ja siihen asukkaiden sahkopostit
        TableColumn<Asukas, String> sahkoPostiSarake = new TableColumn<>("Sähköposti");
        sahkoPostiSarake.setPrefWidth(225);
        sahkoPostiSarake.setCellValueFactory(cd -> cd.getValue().sahkopostiProperty());
        asuntoTable.getColumns().add(sahkoPostiSarake);
        asuntoTable.setItems(asukkaat);



        lisaaAsukasButton.setOnAction(actionEvent -> avaaAsukkaanTiedot());
        poistaAsukasButton.setOnAction(actionEvent -> poistaAsukas());
        suljeMuokkaaButton.setOnAction(actionEvent -> sulje(suljeMuokkaaButton));

        asuntoTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
                valittuAsukas = newVal
        );
    }

    public void setAsunto(Asunto asunto){
        klikattuAsunto = asunto;
        asuntoTable.setItems(klikattuAsunto.getAsukkaatObservable());
        asuntoLabel.setText("Asunto: " + asunto.getTunnus());
    }

    private void avaaAsukkaanTiedot(){
        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("asukkaanTiedot.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            AsukkaanTiedotController controller = loader.getController();
            //controller.lisaaAsukasMuokkaaIkkunaan(klikattuAsunto);
            controller.setAsunto(klikattuAsunto);
            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("lisää asukkaalle tiedot");
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();

            yhtio.tallenna();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void poistaAsukas(){
        Asukas poistettavaAsukas = asuntoTable.getSelectionModel().getSelectedItem();

        if (poistettavaAsukas != null) {
            varoitus("Poiston vahvistaminen",
                    "Paina OK poistaaksesti Asukas: " + poistettavaAsukas.getNimi(),
            poistettavaAsukas.getNimi() + " ja hänen tiedot poistetaan pysyvästi!");

            klikattuAsunto.poistaAsukas(poistettavaAsukas);
            yhtio.tallenna();
        } else {
            varoitus("Poistettava asukas",
                    "",
                    "Valitse poistettava asukas");
        }
    }

    public void setYhtio(Yhtio yhtio){
        this.yhtio = yhtio;
    }
}
