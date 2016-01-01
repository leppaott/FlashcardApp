package flashcardapp.gui;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kali = new Kayttoliittyma();
        SwingUtilities.invokeLater(kali);
    }
}
