package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Asukas {
    private final StringProperty nimi = new SimpleStringProperty("");
    private final StringProperty sahkoposti = new SimpleStringProperty("");
    private final IntegerProperty ika = new SimpleIntegerProperty(0);
    private Asunto asunto;

    public Asukas(String nimi, int ika, String sahkoposti) {
        setNimi(nimi);
        setSahkoposti(sahkoposti);
        setIka(ika);
    }

    public void setNimi(String nimi){ this.nimi.set(nimi);}
    public void setSahkoposti(String sahkoposti){ this.sahkoposti.set(sahkoposti);}
    public void setIka(int ika){ this.ika.set(ika);}
    public void setAsunto(Asunto asunto){ this.asunto = asunto;}

    public String getNimi(){return this.nimi.get(); }
    public String getSahkoposti(){return this.sahkoposti.get(); }
    public int getIka(){return this.ika.get(); }

    public StringProperty nimiProperty(){ return this.nimi;}
    public StringProperty sahkopostiProperty(){ return this.sahkoposti;}
    public IntegerProperty ikaProperty(){ return this.ika;}

}
