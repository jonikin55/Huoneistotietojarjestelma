package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma;

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
        // Write initialization code here
    }
}
