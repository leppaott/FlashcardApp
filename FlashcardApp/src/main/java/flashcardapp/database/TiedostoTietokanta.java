package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple flat file database
 */
public class TiedostoTietokanta implements Tietokanta {

    private String tiedosto;

    public TiedostoTietokanta() {
        this("flashcardapp.db");
    }

    public TiedostoTietokanta(String tiedosto) {
        this.tiedosto = tiedosto;
    }


    @Override
    public void setTiedosto(String tiedosto) {
        if (!tiedosto.endsWith(".db")) {
            tiedosto += ".db";
        }
        this.tiedosto = tiedosto;
    }

    /**
     * Method loads decks from a file and returns a list of them.
     *
     * @return decks
     */
    @Override
    public List<Pakka> lataaPakat() {
        List<Pakka> pakat = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(tiedosto);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Pakka pakka = (Pakka) ois.readObject();
            while (pakka != null) {
                pakat.add(pakka);
                pakka = (Pakka) ois.readObject();
            }
        } catch (Exception e) {
            return pakat;
        }
        
        return pakat;
    }

    /**
     * Method saves decks to a file.
     *
     * @param pakat decks
     */
    @Override
    public void tallennaPakat(List<Pakka> pakat) {
        try (FileOutputStream fos = new FileOutputStream(tiedosto);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Pakka pakka : pakat) {
                oos.writeObject(pakka);
            }
            oos.writeObject(null);
        } catch (Exception e) {
            return;
        }
    }
}
