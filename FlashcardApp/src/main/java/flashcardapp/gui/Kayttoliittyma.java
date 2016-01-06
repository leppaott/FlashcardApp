package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import flashcardapp.domain.Pakka;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//TODO RESIZE

/**
 * Luokka huolehtii käyttöliittymästä.
 */
public class Kayttoliittyma implements Runnable {

    private final FlashcardApp app;
    private JFrame frame;
    private Container mainContainer;
    private MenuBar menuBar;
    private PakkaPaneeli pakkaPaneeli;
    private PakkaHarjoittaja harjoittaja;

    public Kayttoliittyma(FlashcardApp app) {
        this.app = app;
        app.lataaPakat();
    }

    @Override
    public void run() {
        frame = new JFrame("FlashcardApp");
        frame.setPreferredSize(new Dimension(500, 440));
        frame.setMinimumSize(new Dimension(300, 300)); //tune
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luoKomponentit();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void messageBox(String text) {
        JOptionPane.showMessageDialog(frame, text);
    }

    public void luoKomponentit() {
        menuBar = new MenuBar(this, app);
        frame.setJMenuBar(menuBar);

        JLabel otsikko = new JLabel("Decks");
        otsikko.setFont(otsikko.getFont().deriveFont(16.0f));
        otsikko.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JPanel top = new JPanel();
        top.add(otsikko);
        frame.add(top, BorderLayout.NORTH);
        
        pakkaPaneeli = new PakkaPaneeli(this);
        paivitaPakkaPaneeli();
        frame.add(pakkaPaneeli);
        
        harjoittaja = new PakkaHarjoittaja(this);
        mainContainer = frame.getContentPane();
    }

    /**
     * Tyhjentää ja täyttää PakkaPaneelin.
     */
    public void paivitaPakkaPaneeli() {
        pakkaPaneeli.tyhjenna();
        for (Pakka pakka : app.getPakat()) {
            pakkaPaneeli.lisaaPakkaNappi(pakka.getNimi());
        }
        app.tallennaPakat();
    }
    
    /**
     *
     * @param nimi
     */
    public void poistaPakkaNimella(String nimi) {
        app.poistaPakka(nimi);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void paivita() {
        paivitaPakkaPaneeli();
        paivitaFrame();
    }
    
    /**
     * Päivittää pääikkunan.
     */
    public void paivitaFrame() {
        frame.revalidate();
        frame.repaint();
    }

     /**
     * Asettaa PakkaHarjoittajan näytettäväksi ja alustaa sen.
     */
    public void harjoitaPakkaa(String nimi) {
        harjoittaja.alusta(app.haePakka(nimi));
        frame.setContentPane(harjoittaja);
        paivitaFrame();
    }
    
    /**
     * Asettaa päänäkymän näytettäväksi.
     */
    public void asetaMainContainer() {
        frame.setContentPane(mainContainer);
    }
    
    /**
     * Asettaa MenuBarin näkyvyyden
     * @param visible
     */
    public void asetaMenuBarNakyvyys(boolean visible) {
        menuBar.setVisible(visible);
    }
}
