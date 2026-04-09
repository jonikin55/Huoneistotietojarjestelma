import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.controller.LisaaAsuntoController;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asukas;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Asunto;
import fi.jyu.ohj2.kinnjomi.Huoneistotietojarjestelma.model.Yhtio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class lisaaAsuntoTest {

    @Test
    void lisaaAsunto_lisaaAsuntoListaan() {
        Yhtio yhtio = new Yhtio();
        yhtio.lisaaAsunto(new Asunto("A6"));

        assertEquals(1, yhtio.getAsunnot().size());
    }

    @Test
    void lisaaAsunto_eiLisaaAsuntoListaan(){
        Yhtio yhtio = new Yhtio();
        yhtio.lisaaAsunto(new Asunto(""));
        yhtio.lisaaAsunto(new Asunto("   "));

        assertEquals(0, yhtio.getAsunnot().size());
    }

    @Test
    void poistaAsunto_poistaAsuntoListasta(){
        Yhtio yhtio = new Yhtio();
        Asunto asunto = new Asunto("A6");
        yhtio.lisaaAsunto(asunto);
        yhtio.poistaAsunto(asunto);

        assertEquals(0, yhtio.getAsunnot().size());

    }
}
