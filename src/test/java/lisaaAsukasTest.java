import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller.LisaaAsuntoController;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class lisaaAsukasTest {

    @Test
    void lisaaAsukas_lisaaAsukasListaan() {
        Asunto asunto = new Asunto();
        Asukas asukas = new Asukas("Kalle", 22, "kalle@gmail.com");
        asunto.lisaaAsukas(asukas);

        assertEquals(1, asunto.getAsukasMaara());
    }

    @Test
    void lisaaAsukas_eiLisaaAsukasListaan() {
        Asunto asunto = new Asunto();
        Asukas asukas1 = new Asukas("", 22, "kalle@gmail.com");
        Asukas asukas2 = new Asukas("Kalle", 1267, "kalle@gmail.com");
        Asukas asukas3 = new Asukas("Kalle", 12, "kalleNiinistö8213137381231dawdawdawdawdawda2@gmail.com");
        asunto.lisaaAsukas(asukas1);
        asunto.lisaaAsukas(asukas2);
        asunto.lisaaAsukas(asukas3);

        assertEquals(0, asunto.getAsukasMaara());
    }

    @Test
    void poistaAsukas_poistaAsukasListasta() {
        Asunto asunto = new Asunto();
        Asukas asukas = new Asukas("Kalle", 22, "kalle@gmail.com");
        asunto.lisaaAsukas(asukas);
        asunto.poistaAsukas(asukas);

        assertEquals(0, asunto.getAsukasMaara());
    }
}
