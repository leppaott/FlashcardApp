package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.util.List;

public interface Tietokanta {
    public void setTiedosto(String tiedosto);
    List<Pakka> lataaPakat();
    void tallennaPakat(List<Pakka> pakat);
}
