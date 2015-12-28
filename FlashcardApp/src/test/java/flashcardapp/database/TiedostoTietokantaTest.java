package flashcardapp.database;

import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TiedostoTietokantaTest {
    private Tietokanta db;
    
    @Before
    public void setUp() {
        db = new TiedostoTietokanta();
    }
    
    private void lisaaPakkaanKortit(Pakka pakka, Kortti[] kortit) {
        for (Kortti kortti : kortit) {
            pakka.lisaaKortti(kortti);
        }
    }
        
    @Test
    public void tallennusJaLukeminenOnnistuu() {
        List<Pakka> pakat = new ArrayList<>();
        pakat.add(new Pakka("0"));
        pakat.add(new Pakka("1"));
        
        Kortti[] kortit = {new Kortti("a", "b"), new Kortti("c", "d"), new Kortti("e", "f"),
            new Kortti("g", "h"), new Kortti("i", "j"), new Kortti("k", "l"), new Kortti("m", "n")};
        Kortti[] kortit1 = {new Kortti("b", "b"), new Kortti("g", "d")};
        
        lisaaPakkaanKortit(pakat.get(0), kortit);
        lisaaPakkaanKortit(pakat.get(1), kortit1);
        
        db.tallennaPakat(pakat);
        List<Pakka> luetut = db.lataaPakat();

        assertEquals(2, luetut.size());
        for (int i = 0; i < 2; i++) {
            Pakka a = pakat.get(i);
            Pakka b = luetut.get(i);
            assertTrue(a.pakanKoko() == b.pakanKoko());
            for (int j = 0; j < a.pakanKoko(); j++) {
                assertEquals(a.getKortti(j).toString(), b.getKortti(j).toString());
            }
        }
        //assertEquals(pakat, luetut) //nope
        //assertEquals(true, Arrays.equals(pakat.toArray(), luetut.toArray())); //nope
    }
}
