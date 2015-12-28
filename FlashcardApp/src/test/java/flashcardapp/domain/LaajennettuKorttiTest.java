package flashcardapp.domain;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaajennettuKorttiTest {

    private LaajennettuKortti kortti;

    @Before
    public void setUp() {
        kortti = new LaajennettuKortti("Minä vuonna Suomi itsenäistyi?", "1917");
    }

    public void vihjeKorttiToimii() {
        kortti.lisaaTietoa("VIHJE: Se oli valkoinen talvi.");

        List<String> tiedot = kortti.getLisatiedot();
        assertEquals(1, tiedot.size()); //VIHJE siis, ei ehkä paras tapa
        assertTrue(tiedot.get(0).contains("VIHJE:"));
    }

    @Test
    public void monivalintaKorttiToimii() {
        kortti.lisaaTietoa("1899");
        kortti.lisaaTietoa("1914");
        kortti.lisaaTietoa("1917");
        kortti.lisaaTietoa("1919");

        List<String> tiedot = kortti.getLisatiedot();
        assertTrue(tiedot.size() > 1); //Monivalintakortti, käyttöliittymä näyttää vaihtoehdot    
    }
}
