package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Yhtio {
    private final ObservableList<Asunto> asunnot = FXCollections.observableArrayList();

    public ObservableList<Asunto> getAsunnot(){return asunnot;
    }

    public void lisaaAsunto(Asunto asunto){ asunnot.add(asunto);
    }

    public void poistaAsunto(Asunto asunto){}
}
