package flashcardapp.domain;

import flashcardapp.util.ListMemento;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class represents a card deck.
 */
public class Pakka implements Iterable<Kortti>, Serializable {

    private final String nimi;
    private List<Kortti> kortit;
    private ListMemento<Kortti> memento;

    public Pakka(String pakanNimi) {
        nimi = pakanNimi;
        kortit = new ArrayList<>();
    }

    public String getNimi() {
        return nimi;
    }

    public int getKoko() {
        return kortit.size();
    }

    /**
     * Method adds a card.
     * @param kortti card
     */
    public void lisaaKortti(Kortti kortti) {
        kortit.add(kortti);
    }

    /**
     * Method shuffles the deck.
     */
    public void sekoita() {
        if (memento == null) {
            memento = new ListMemento<>(kortit);
        }
        Collections.shuffle(kortit);
    }

    /**
     * Methods restores the initial deck state before the first shuffling.
     */
    public void palautaAlkutilaan() {
        if (memento == null) {
            return;
        }
        kortit = memento.getAlkutila();
    }

    /**
     * Method returns a card for given index.
     * @param indeksi index
     * @return card or null if index larger than size of the deck
     */
    public Kortti getKortti(int indeksi) {
        if (kortit.size() > indeksi) {
            return kortit.get(indeksi);
        }
        return null;
    }

    /**
     * Method returns a list of all cards.
     * @return cards
     */
    public List<Kortti> getKortit() {
        return kortit;
    }

    @Override
    public Iterator<Kortti> iterator() {
        return kortit.iterator();
    }
}
