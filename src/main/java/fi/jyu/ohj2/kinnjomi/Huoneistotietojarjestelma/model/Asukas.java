package fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model;

public class Asukas {
    private final String nimi;
    private final String sahkoposti;
    private final int ika;

    public Asukas(String nimi, String sahkoposti, int ika) {
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        this.ika = ika;
    }

    public String getNimi() {
        return nimi;
    }
    public String getSahkoposti() {
        return sahkoposti;
    }

    public int getIka() {
        return ika;
    }
}
