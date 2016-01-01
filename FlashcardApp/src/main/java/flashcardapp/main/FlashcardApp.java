package flashcardapp.main;

import flashcardapp.database.Tietokanta;
import flashcardapp.domain.Pakka;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlashcardApp {
    private Tietokanta tietokanta;
    private Map<String, Pakka> pakat;
    
    public FlashcardApp(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
        lataaPakat();
    }
    
    public void lataaPakat() { //1.8 required
        tietokanta.lataaPakat().forEach(pakka -> pakat.put("", pakka));
    }
    
    public void tallennaPakat() {
        tietokanta.tallennaPakat(new ArrayList<>(pakat.values()));
    }
    
    public Pakka lisaaPakka(String nimi) {
        Pakka pakka = new Pakka(nimi);
        pakat.put(nimi, pakka);
        return pakka;
    }
    
    public Pakka haePakka(String nimi) {
        return pakat.get(nimi);
    }
    //Kenen vastuu
    public void lisaaKortti(Pakka pakka, String etupuoli, String kaantopuoli) {
        
    }
}
