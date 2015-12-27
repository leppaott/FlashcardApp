package flashcardapp.domain;

import java.io.Serializable;

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
    
    @Override
    public String toString() {
        return etupuoli + " - " + kaantopuoli;
    }
}
