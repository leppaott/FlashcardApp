package flashcardapp.domain;

import flashcardapp.util.ListMemento;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

/**
 * Luokka sisältää Pakan toiminnallisuuden.
 */
public class Pakka implements Iterable<Kortti>, Serializable {

    private String nimi;
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

    public void lisaaKortti(Kortti kortti) {
        kortit.add(kortti);
    }

    /**
     * Metodi sekoittaa pakan.
     */
    public void sekoita() {
        if (memento == null) {
            memento = new ListMemento<>(kortit);
        }
        Collections.shuffle(kortit);
    }
    
    /**
     * Metodi palauttaa pakan ensimmäistä sekoitusta edeltävään tilaan. 
     */
    public void palautaAlkutilaan() {
        if (memento == null) {
            return;
        }
        kortit = memento.getAlkutila();
    }
    
    public Kortti getKortti(int indeksi) {
        if (kortit.size() > indeksi) {
            return kortit.get(indeksi);
        }
        return null;
    }

    public List<Kortti> getKortit() {
        return kortit;
    }
    
    @Override
    public Iterator<Kortti> iterator() {
        return kortit.iterator();
    }
}
