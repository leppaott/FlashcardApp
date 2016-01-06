package flashcardapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {

    private Kortti kortti;

    @Before
    public void setUp() {
        kortti = new Kortti("Suomi", "Finland");
    }

    @Test
    public void getteritToimivat() {
        assertEquals("Suomi", kortti.getEtupuoli());
        assertEquals("Finland", kortti.getKaantopuoli());
    }
    
    @Test
    public void setteritToimivat() {
        kortti.setEtupuoli("Finland");
        kortti.setKaantopuoli("Suomi");
        assertEquals("Finland", kortti.getEtupuoli());
        assertEquals("Suomi", kortti.getKaantopuoli());
    }
    
    @Test
    public void puolienKaantaminenToimii() {
        kortti.kaannaPuolet();
        assertEquals("Finland", kortti.getEtupuoli());
        assertEquals("Suomi", kortti.getKaantopuoli());
    }
    
    @Test
    public void lisaTiedotNull() {
        assertEquals(null, kortti.getLisatiedot());
    }
}
