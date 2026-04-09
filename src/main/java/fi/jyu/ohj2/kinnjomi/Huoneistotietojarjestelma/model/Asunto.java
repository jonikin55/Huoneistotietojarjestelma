package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Asunto {
    private final StringProperty tunnus = new SimpleStringProperty();
    private final ReadOnlyIntegerWrapper asukasMaara = new ReadOnlyIntegerWrapper();
    private final ObservableList<Asukas> asukkaat = FXCollections.observableArrayList();

    public Asunto(){
        bindAsukasMaara();
    }

    public Asunto(String tunnus){
        setTunnus(tunnus);
        bindAsukasMaara();
    }

    public String getTunnus() { return this.tunnus.get();}
    public void setTunnus(String tunnus){ this.tunnus.set(tunnus);}
    public StringProperty tunnusProperty(){ return this.tunnus;}

    public void bindAsukasMaara() { asukasMaara.bind(javafx.beans.binding.Bindings.size(asukkaat));}
    public ReadOnlyIntegerWrapper asukasMaaraProperty(){ return asukasMaara;}
    public int getAsukasMaara(){ return this.asukasMaara.get();}

    public void lisaaAsukas(Asukas asukas){
        if(asukas == null || asukas.getNimi().isBlank()){
            return;
        }
        if(asukas.getSahkoposti().length() > 25 || asukas.getSahkoposti().isBlank()) {
            return;
        }
        if(asukas.getIka() < 0 || asukas.getIka() > 125){
            return;
        }
        asukkaat.add(asukas);}
    public void poistaAsukas(Asukas asukas){ asukkaat.remove(asukas);}
    public ObservableList<Asukas> getAsukkaatObservable(){ return asukkaat;}

    // Apumetodeja, joilla Tallenna metodi aktivoituu, kun Asuntoon lisätään tai poistetaan Asukkaita
    public List<Asukas> getAsukkaat() { return new ArrayList<>(asukkaat); }
    public void setAsukkaat(List<Asukas> asukkaatLista) { asukkaat.setAll(asukkaatLista); }


}
