package flashcardapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka laajentaa Korttia sallimalla lisätiedon käyttämisen.
 */
public class LaajennettuKortti extends Kortti {
    private List<String> lisatiedot;
    
    public LaajennettuKortti(String etupuoli, String kaantopuoli) {
        super(etupuoli, kaantopuoli);
        lisatiedot = new ArrayList<>();
    }

    /**
     * Metodilla lisää tiedon listaan.
     * @param tieto
     */
    public void lisaaTietoa(String tieto) {
        lisatiedot.add(tieto);
    }
    
    @Override
    public List<String> getLisatiedot() {
        return lisatiedot;
    }
}
