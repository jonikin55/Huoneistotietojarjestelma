package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class Yhtio {
    private final ObservableList<Asunto> asunnot = FXCollections.observableArrayList();
    private final Path tiedostoPolku = Path.of("asunnot.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public Yhtio(){
        asunnot.addListener((ListChangeListener<Asunto>) change -> {
            tallenna();
        });
    }

    public ObservableList<Asunto> getAsunnot(){return asunnot;}

    public void tallenna(){ mapper.writeValue(tiedostoPolku, asunnot);}
    public void lataa(){
        if(Files.notExists(tiedostoPolku)){
            IO.println("lataamisessa häikkää");
            return;
        }
        try {
            List<Asunto> kaikkiAsunnot = mapper.readValue(tiedostoPolku, new TypeReference<>() {
            });
            asunnot.addAll(kaikkiAsunnot);
        } catch (JacksonException je){
            IO.println("JSONin lukeminen epäonnistui: " + je.getMessage());
        }
    }


    public void lisaaAsunto(Asunto asunto){
        if(asunto == null || asunto.getTunnus().isBlank()){
            return;
        }
        asunnot.add(asunto);
    }
    public void poistaAsunto(Asunto asunto){ asunnot.remove(asunto);}
}
