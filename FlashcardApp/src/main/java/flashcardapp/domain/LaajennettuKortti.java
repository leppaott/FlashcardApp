package flashcardapp.domain;

import java.util.ArrayList;
import java.util.List;

public class LaajennettuKortti extends Kortti {
    private List<String> lisatiedot;
    
    public LaajennettuKortti(String etupuoli, String kaantopuoli) {
        super(etupuoli, kaantopuoli);
        lisatiedot = new ArrayList<>();
    }

    public void lisaaTietoa(String tieto) { //nimeäminen
        lisatiedot.add(tieto);
    }
    
    @Override
    public List<String> getLisatiedot() {
        return lisatiedot;
    }
}
