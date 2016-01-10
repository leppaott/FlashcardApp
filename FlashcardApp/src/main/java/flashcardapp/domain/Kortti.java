package flashcardapp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Class represents a card.
 */
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
    
    /**
     * Method reverses card sides.
     */
    public void kaannaPuolet() {
        String etu = etupuoli;
        etupuoli = kaantopuoli;
        kaantopuoli = etu;
    }
    
    /**
     * Returns always null.
     * 
     * @return null
     * @see LaajennettuKortti
     */
    public List<String> getLisatiedot() {
        return null;
    }
    
    @Override
    public String toString() {
        return etupuoli + " - " + kaantopuoli;
    }
}
