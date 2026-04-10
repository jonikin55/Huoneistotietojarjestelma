package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller;

import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends HuoneistoController implements Initializable {
    @FXML
    private TableView<Asunto> asuntoTiedotTable;

    @FXML
    private Button lisaaAsuntoButton;

    @FXML
    private Button muokkaaButton;

    @FXML
    private Button poistaButton;

    private final Yhtio yhtio = new Yhtio();
    private Asunto klikattuAsunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lisätään päänykämään columni, jossa näkyy Asuntojen tunnukset
        TableColumn<Asunto, String> tunnusSarake = new TableColumn<>("Asunnon tunnus");
        tunnusSarake.setPrefWidth(130);
        tunnusSarake.setCellValueFactory(cd -> cd.getValue().tunnusProperty());
        asuntoTiedotTable.getColumns().add(tunnusSarake);

        TableColumn<Asunto, Number> asukasMaaraSarake = new TableColumn<>("Asukkaiden maara");
        asukasMaaraSarake.setPrefWidth(130);
        asukasMaaraSarake.setCellValueFactory(cd -> cd.getValue().asukasMaaraProperty());
        asuntoTiedotTable.getColumns().add(asukasMaaraSarake);
        asuntoTiedotTable.setItems(yhtio.getAsunnot());

        asuntoTiedotTable.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) ->
                klikattuAsunto = newVal)
        ;

        yhtio.lataa();
        lisaaAsuntoButton.setOnAction(actionEvent -> avaaLisaaAsunto());
        muokkaaButton.setOnAction(actionEvent -> avaaMuokkaaAsunto());
        poistaButton.setOnAction(actionEvent -> poistaAsuntoa());
    }

    private void avaaLisaaAsunto() {
        try {
            //Haetaan LisaaAsuntoControllerin näykymä ja asetetaan se uuden ikkunan näkymäksi.
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaaAsunto.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Linkitetään asunnot lista MainControllerin ja lisaaAsuntoControllerin välille.
            LisaaAsuntoController controller = loader.getController();
            controller.lisaaAsuntoPaaIkkunaan(yhtio);

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

    private void avaaMuokkaaAsunto() {
        try {
            //Pakotetaan käyttäjä valitsemaan, jokin asunto, jotta MuokkaaAsuntoControlleri voidaan avata.
            Asunto klikattuAsunto = asuntoTiedotTable.getSelectionModel().getSelectedItem();
            if (klikattuAsunto == null) {
                varoitus("Asunto puuttuu",
                        "",
                        "Muokataksesi asuntoa sinun täytyy ensin valita asunto");
                return;
            }
            //Haetaan MuokkaaAsuntoControllerin näykymä ja asetetaan se uuden ikkunan näkymäksi.
            FXMLLoader loader = new FXMLLoader(App.class.getResource("muokkaaAsunto.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Linkitetään valittu asunto MuokkaaAsuntoControlleriin
            MuokkaaAsuntoController controller = loader.getController();
            controller.setAsunto(klikattuAsunto);
            controller.setYhtio(yhtio);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);

            dialogi.setTitle("Muokkaa asuntoa ");
            dialogi.initModality(Modality.APPLICATION_MODAL);

            dialogi.showAndWait();
            yhtio.tallenna();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IO.println("Avasit muokkaa asuntoa näkymän");
    }

    public void poistaAsuntoa() {
        Asunto poistettavaAsunto = asuntoTiedotTable.getSelectionModel().getSelectedItem();

        if (poistettavaAsunto != null) {
            varoitus("Poiston vahvistaminen",
                    "Paina OK poistaaksesi valittu asunto",
                    "Asunto ja sen asukkaat poistetaan pysyvästi");

            if (poistettavaAsunto.getAsukkaat() != null) {
                for (Asukas asukkaat : poistettavaAsunto.getAsukkaat()) {
                    asukkaat.setAsunto(null);
                }
            }
            yhtio.poistaAsunto(poistettavaAsunto);
        } else varoitus("Valitse poistettava asunto",
                "",
                "Poistaaksesi asunnon, sinun täytyy ensin valita asunto");
    }
}
