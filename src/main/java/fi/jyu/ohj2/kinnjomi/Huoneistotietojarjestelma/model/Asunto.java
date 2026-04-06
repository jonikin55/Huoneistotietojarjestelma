package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Asunto {
    private final String tunnus;
    private final int asukasMaara;
    private final ObservableList<Asukas> asukkaat= FXCollections.observableArrayList();

    public Asunto(String tunnus){
        this.tunnus = tunnus;
        this.asukasMaara = 0;
    }

    public String getTunnus() {
        return tunnus;
    }

    public int getAsukasMaara() {
        return asukkaat.size();
    }

    public void lisaaAsukas(Asukas asukas){
        asukkaat.add(asukas);
    }

}
