package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Luokka laajentaa JMenuBar.
 */
public class MenuBar extends JMenuBar {

    private final Kayttoliittyma kayttoliittyma;
    private final FlashcardApp app;

    public MenuBar(Kayttoliittyma kayttoliittyma, FlashcardApp app) {
        super();
        this.kayttoliittyma = kayttoliittyma;
        this.app = app;

        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        super.add(menu);

        JMenuItem item = new JMenuItem("Open Database...");
        item.addActionListener(ae -> avaaTietokanta(ae));
        menu.add(item);
        item = new JMenuItem("Save");
        item.addActionListener(ae -> tallennaTietokanta(ae));
        menu.add(item);
        item = new JMenuItem("Save As...");
        item.addActionListener(ae -> tallennaTietokantaNimella(ae));
        menu.add(item);

        menu = new JMenu("Add");
        menu.setMnemonic('A');
        super.add(menu);

        item = new JMenuItem("New Deck...");
        item.addActionListener(ae -> lisaaPakka(ae));
        menu.add(item);
        item = new JMenuItem("New Cards...");
        item.addActionListener(ae -> lisaaKortteja(ae));
        menu.add(item);
    }

    public void avaaTietokanta(ActionEvent ae) {
        //new AvaaTiedosto()
        app.lataaPakat();
        kayttoliittyma.paivita();
    }

    public void tallennaTietokanta(ActionEvent ae) {
        app.tallennaPakat();
    }

    public void tallennaTietokantaNimella(ActionEvent ae) {
        //new SaveAs()
        app.tallennaPakat("uusi");
    }

    public void lisaaPakka(ActionEvent ae) {
        //new Dialog
        app.lisaaPakka("UUSIPAKKA");
        kayttoliittyma.paivita();
    }

    public void lisaaKortteja(ActionEvent ae) {

    }
}
