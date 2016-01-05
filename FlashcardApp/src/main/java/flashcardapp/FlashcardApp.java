package flashcardapp;

import flashcardapp.database.Tietokanta;
import flashcardapp.domain.Pakka;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Luokka ylläpitää tietokantaa ja tarjoaa metodeita pakkojen hakemiseen ja tallentamiseen.
 */
public class FlashcardApp {
    private final Tietokanta tietokanta;
    private Map<String, Pakka> pakat;
    
    public FlashcardApp(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
        this.pakat = new LinkedHashMap<>();
    }
    
    /**
     * Metodi lataa pakat tietokannasta sisäiseen säiliöön.
     */
    public void lataaPakat() {
        pakat.clear();
        tietokanta.lataaPakat().forEach(pakka -> pakat.put(pakka.getNimi(), pakka));
    }
    
    public void lataaPakat(String tiedosto) {
        tietokanta.setTiedosto(tiedosto);
        lataaPakat();
    }
    
    /**
     * Metodi tallentaa pakat tietokantaan.
     */
    public void tallennaPakat() {
        tietokanta.tallennaPakat(getPakat());
    }
    
    /**
     * Metodi tallentaa pakat tiedosto-nimiseen tietokantaan.
     * @param tiedosto 
     */
    public void tallennaPakat(String tiedosto) {
        tietokanta.setTiedosto(tiedosto);
        tallennaPakat();
    }
    
    public List<Pakka> getPakat() {
        return new ArrayList<>(pakat.values());
    }
    
    public Pakka lisaaPakka(String nimi) {
        Pakka pakka = new Pakka(nimi);
        pakat.put(nimi, pakka);
        return pakka;
    }
    
    public void poistaPakka(String nimi) {
        if (pakat.containsKey(nimi)) {
            pakat.remove(nimi);
        }
    }
}
