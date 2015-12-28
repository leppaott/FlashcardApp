package flashcardapp.database;

import flashcardapp.domain.Pakka;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TiedostoTietokanta implements Tietokanta {

    public static final String TIEDOSTO = "flashcardapp.db";

    //konstruktori tiedosto.. save as, save, 
    
    @Override
    public List<Pakka> lataaPakat() {
        List<Pakka> pakat = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(TIEDOSTO);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Pakka pakka = (Pakka) ois.readObject();
            while (pakka != null) {
                pakat.add(pakka);
                pakka = (Pakka) ois.readObject();
            }
        } catch (Exception e) {
            System.out.println("Tiedostokanta poikkeus lataamisessa");
        }
        return pakat;
    }

    @Override
    public void tallennaPakat(List<Pakka> pakat) {
        try (FileOutputStream fos = new FileOutputStream(TIEDOSTO);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Pakka pakka : pakat) {
                oos.writeObject(pakka);
            }
            oos.writeObject(null);
        } catch (Exception e) {
            System.out.println("Tiedostokanta poikkeus tallentamisessa");
        }
    }

}
