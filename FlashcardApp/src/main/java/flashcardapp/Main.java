package flashcardapp;

import flashcardapp.database.TiedostoTietokanta;
import flashcardapp.gui.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        FlashcardApp app = new FlashcardApp(new TiedostoTietokanta());
        Kayttoliittyma gui = new Kayttoliittyma(app);
        SwingUtilities.invokeLater(gui);
    }
}
