package flashcardapp.domain;

import flashcardapp.util.ListMemento;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class Pakka implements Iterable<Kortti>, Serializable {

    private String nimi;
    private List<Kortti> kortit;
    private ListMemento<Kortti> memento;
    
    public Pakka(String pakanNimi) {
        nimi = pakanNimi;
        kortit = new ArrayList<>();
    }

    public String pakanNimi() {
        return nimi;
    }
    
    public int pakanKoko() {
        return kortit.size();
    }

    public void lisaaKortti(Kortti kortti) {
        kortit.add(kortti);
    }

    public void sekoita() {
        if (memento == null) {
            memento = new ListMemento<>(kortit);
        }
        Collections.shuffle(kortit);
    }
    
    public void palautaAlkutilaan() {
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
