package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    private JFileChooser getChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Database", "db"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser;
        }
        return null;
    } 
    
    private void avaaTietokanta(ActionEvent ae) {
        JFileChooser chooser = getChooser();
        if (chooser != null) {
            app.lataaPakat(chooser.getSelectedFile().getPath());
            kayttoliittyma.paivita();
        }
    }

    private void tallennaTietokantaNimella(ActionEvent ae) {
        JFileChooser chooser = getChooser();
        if (chooser != null) {
            app.tallennaPakat(chooser.getSelectedFile().getPath());
        }
    }

    private void lisaaPakka(ActionEvent ae) {
        String pakanNimi = (String) JOptionPane.showInputDialog(kayttoliittyma.getFrame(),
                "Give the deck a unique name", "New Deck", JOptionPane.PLAIN_MESSAGE);

        if (pakanNimi == null || pakanNimi.isEmpty()) {
            JOptionPane.showMessageDialog(kayttoliittyma.getFrame(), "Please give a deck name.");
        } else if (!pakanNimi.isEmpty() && app.haePakka(pakanNimi) == null) {
            app.lisaaPakka(pakanNimi);
            kayttoliittyma.paivita();
        } else {
            JOptionPane.showMessageDialog(kayttoliittyma.getFrame(), "Please give a deck unique name.");
        }
    }

    private void lisaaKortteja(ActionEvent ae) {
        kayttoliittyma.lisaaPakkoja();
    }
}
