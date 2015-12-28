package flashcardapp.domain;

import java.io.Serializable;
import java.util.List;

public class Kortti implements Serializable {

    private String etupuoli, kaantopuoli;

    public Kortti(String etupuoli, String kaantopuoli) {
        this.etupuoli = etupuoli;
        this.kaantopuoli = kaantopuoli;
    }

    public String getEtupuoli() {
        return etupuoli;
    }

    public String getKaantopuoli() {
        return kaantopuoli;
    }
    
    public void setEtupuoli(String etupuoli) {
        this.etupuoli = etupuoli;
    }
    
    public void setKaantopuoli(String kaantopuoli) {
        this.kaantopuoli = kaantopuoli;
    }
    
    public void kaannaPuolet() {
        String etu = etupuoli;
        etupuoli = kaantopuoli;
        kaantopuoli = etu;
    }
    
    public List<String> getLisatiedot() {
        return null;
    }
    
    //to be removed
    @Override
    public String toString() {
        return etupuoli + " - " + kaantopuoli;
    }
}
