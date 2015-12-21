package flashcardapp.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pakka implements Iterable<Kortti>, Serializable {

    private String nimi;
    private List<Kortti> kortit;

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
    //todo poista

    public void sekoita() {
        Collections.shuffle(kortit);
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
