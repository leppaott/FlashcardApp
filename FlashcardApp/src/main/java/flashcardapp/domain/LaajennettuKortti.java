package flashcardapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Extended card with extra information.
 */
public class LaajennettuKortti extends Kortti {
    private final List<String> lisatiedot;
    
    public LaajennettuKortti(String etupuoli, String kaantopuoli) {
        super(etupuoli, kaantopuoli);
        lisatiedot = new ArrayList<>();
    }

    /**
     * Methods adds extra info.
     * @param tieto info
     */
    public void lisaaTietoa(String tieto) {
        lisatiedot.add(tieto);
    }
    
    @Override
    public List<String> getLisatiedot() {
        return lisatiedot;
    }
}
