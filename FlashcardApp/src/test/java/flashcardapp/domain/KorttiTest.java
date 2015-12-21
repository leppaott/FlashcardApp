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
    public void testaaToimivatkoGetterit() {
        assertEquals("Suomi", kortti.getEtupuoli());
        assertEquals("Finland", kortti.getKaantopuoli());
    }
}
