package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.util.List;

public interface Tietokanta {
    List<Pakka> lataaPakat();
    void tallennaPakat(List<Pakka> pakat);
}
