package flashcardapp;

import flashcardapp.database.Tietokanta;
import flashcardapp.domain.Pakka;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class encapsulates database and provides methods for saving and loading decks.
 */
public class FlashcardApp {

    private final Tietokanta tietokanta;
    private Map<String, Pakka> pakat;

    public FlashcardApp(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
        this.pakat = new LinkedHashMap<>();
    }

    /**
     * Method loads decks into internal storage.
     */
    public void lataaPakat() {
        pakat.clear();
        tietokanta.lataaPakat().forEach(pakka -> pakat.put(pakka.getNimi(), pakka));
    }

    /**
     * Sets used file for a database and loads decks.
     * @see #lataaPakat()
     * @param tiedosto file name
     */
    public void lataaPakat(String tiedosto) {
        tietokanta.setTiedosto(tiedosto);
        lataaPakat();
    }

    /**
     * Method saves decks to a database.
     */
    public void tallennaPakat() {
        tietokanta.tallennaPakat(getPakat());
    }

    /**
     * Methods sets used file and saves decks.
     * @see #tallennaPakat()
     * @param tiedosto file name
     */
    public void tallennaPakat(String tiedosto) {
        tietokanta.setTiedosto(tiedosto);
        tallennaPakat();
    }

    public List<Pakka> getPakat() {
        return new ArrayList<>(pakat.values());
    }

    /**
     * Method returns a deck with given name.
     * @param nimi deck name
     * @return deck
     */
    public Pakka haePakka(String nimi) {
        return pakat.get(nimi);
    }

    /**
     * Method creates a new deck and stores it.
     * @param nimi deck name
     */
    public void lisaaPakka(String nimi) {
        pakat.put(nimi, new Pakka(nimi));
    }

    /**
     * Method removes a deck with given name.
     * @param nimi deck name
     */
    public void poistaPakka(String nimi) {
        pakat.remove(nimi);
    }
}
