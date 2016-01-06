package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Yksinkertainen tietokanta.
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
     * Metodi lataa pakat tiedostosta.
     *
     * @return Lista pakoista.
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
        }
        
        return pakat;
    }

    /**
     * Metodi tallentaa pakat tiedostoon.
     *
     * @param pakat
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
        }
    }

}
