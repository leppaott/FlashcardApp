package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TiedostoTietokanta implements Tietokanta {

    public static final String tiedosto = "flashcardappDb.db";

    @Override
    public List<Pakka> lataaPakat() {
        List<Pakka> pakat = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedosto))) {
            Pakka pakka = (Pakka)ois.readObject();
            while (pakka != null) {
                pakat.add(pakka);
                pakka = (Pakka)ois.readObject();
            }
        } catch (Exception e) {
            return pakat;
        }
        return pakat;
    }

    @Override
    public void tallennaPakat(List<Pakka> pakat) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tiedosto))) {
            for (Pakka pakka : pakat) {
                oos.writeObject(pakka);
            }
        } catch (Exception e) {
            return;
        }
    }

}
