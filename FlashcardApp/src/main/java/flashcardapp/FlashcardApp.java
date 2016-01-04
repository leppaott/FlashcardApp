package flashcardapp;

import flashcardapp.database.Tietokanta;
import flashcardapp.domain.Pakka;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Luokka ylläpitää tietokantaa ja tarjoaa metodeita pakkojen hakemiseen ja tallentamiseen.
 */
public class FlashcardApp {
    private final Tietokanta tietokanta;
    private List<Pakka> pakat;
    
    public FlashcardApp(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
        lataaPakat();
    }
    
    /**
     * Metodi lataa pakat tietokannasta sisäiseen säiliöön.
     */
    public void lataaPakat() {
        pakat = tietokanta.lataaPakat();
    }
    
    /**
     * Metodi tallentaa pakat tietokantaan.
     */
    public void tallennaPakat() {
        tietokanta.tallennaPakat(pakat);
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
        return pakat;
    }
    
    public Pakka lisaaPakka(String nimi) {
        Pakka pakka = new Pakka(nimi);
        pakat.add(pakka);
        return pakka;
    }
}
