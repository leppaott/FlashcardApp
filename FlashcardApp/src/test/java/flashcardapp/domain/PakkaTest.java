package flashcardapp.domain;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakkaTest {

    static final String NIMI = "Ensimmainen";
    private Pakka pakka;

    @Before
    public void setUp() {
        pakka = new Pakka(NIMI);
    }

    @Test
    public void testaaPakanNimi() {
        assertEquals(NIMI, pakka.getNimi());
    }

    @Test
    public void pakanKokoMuuttuuKunKorttiLisataan() {
        assertEquals(0, pakka.getKoko());
        pakka.lisaaKortti(new Kortti("Etu", "Kaanto"));
        assertEquals(1, pakka.getKoko());
    }
    
    @Test
    public void kortinOttoToimii() {
        Kortti kortti = new Kortti("Etu", "Kaanto");
        pakka.lisaaKortti(kortti);
        Kortti palautettu = pakka.getKortti(0);
        assertEquals(kortti, palautettu);
    }

    @Test
    public void nullPalautetaanKunOtetunKortinIndeksiYliKoon() {
        Kortti kortti = pakka.getKortti(0);
        assertEquals(null, kortti);
    }

    private void lisaaPakkaanKortit(Kortti[] kortit) {
        for (Kortti kortti : kortit) {
            pakka.lisaaKortti(kortti);
        }
    }

    private Kortti[] lisaaPakkaanKasaKortteja() {
        Kortti[] kortit = {new Kortti("a", "b"), new Kortti("c", "d"), new Kortti("e", "f"),
            new Kortti("g", "h"), new Kortti("i", "j"), new Kortti("k", "l"), new Kortti("m", "n")};
        lisaaPakkaanKortit(kortit);
        return kortit;
    }
    
    @Test
    public void kortitSekoittuvat() {
        Kortti[] kortit = lisaaPakkaanKasaKortteja();
        assertEquals(true, Arrays.equals(kortit, pakka.getKortit().toArray()));
        pakka.sekoita(); //oletetaan että jotain on muuttunut 
        assertEquals(false, Arrays.equals(kortit, pakka.getKortit().toArray()));
    }
    
    @Test
    public void pakkaPalautuuAlkuperaiseenTilaan() {
        Kortti[] kortit = lisaaPakkaanKasaKortteja();
        pakka.sekoita();
        assertEquals(false, Arrays.equals(kortit, pakka.getKortit().toArray()));
        pakka.palautaAlkutilaan();
        assertEquals(true, Arrays.equals(kortit, pakka.getKortit().toArray()));
    }
    
    @Test
    public void sekoittamatonPakkaPysyyMuuttumattomanaKunPalautetaanAlkutilaan() {
        Kortti[] kortit = lisaaPakkaanKasaKortteja();
        assertEquals(true, Arrays.equals(kortit, pakka.getKortit().toArray()));
        pakka.palautaAlkutilaan();
        assertEquals(true, Arrays.equals(kortit, pakka.getKortit().toArray()));
    }
}
