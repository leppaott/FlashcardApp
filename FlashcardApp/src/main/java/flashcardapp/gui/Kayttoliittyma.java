package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import flashcardapp.domain.Pakka;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
    private KorttiLisaaja korttiLisaaja;

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
        otsikko.setFont(otsikko.getFont().deriveFont(20.0f));
        otsikko.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JPanel top = new JPanel();
        top.add(otsikko);
        frame.add(top, BorderLayout.NORTH);

        pakkaPaneeli = new PakkaPaneeli(this, app);
        frame.add(pakkaPaneeli);
        taytaPakkaPaneeli();

        korttiLisaaja = new KorttiLisaaja(this, app);
        harjoittaja = new PakkaHarjoittaja(this);
        mainContainer = frame.getContentPane();

        frame.addComponentListener(new ResizeHandler());
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Päivittää pääikkunan.
     */
    public void paivitaFrame() {
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Asettaa päänäkymän näytettäväksi.
     */
    public void asetaMainContainer() {
        frame.setContentPane(mainContainer);
    }

    /**
     * Asettaa MenuBarin näkyvyyden
     *
     * @param visible
     */
    public void asetaMenuBarNakyvyys(boolean visible) {
        menuBar.setVisible(visible);
    }

    /**
     * Tyhjentää ja täyttää PakkaPaneelin.
     */
    public void taytaPakkaPaneeli() {
        pakkaPaneeli.tayta();
    }

    /**
     * Asettaa PakkaHarjoittajan näytettäväksi ja alustaa sen.
     *
     * @param nimi
     */
    public void harjoitaPakkaa(String nimi) {
        harjoittaja.alusta(app.haePakka(nimi));
        frame.setContentPane(harjoittaja);
        paivitaFrame();
    }

    public void lisaaPakkoja() {
        korttiLisaaja.alusta();
        frame.setContentPane(korttiLisaaja);
        paivitaFrame();
    }
    
    private class ResizeHandler implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            taytaPakkaPaneeli();
            paivitaFrame();
        }

        @Override
        public void componentMoved(ComponentEvent e) {
        }

        @Override
        public void componentShown(ComponentEvent e) {
        }

        @Override
        public void componentHidden(ComponentEvent e) {
        }
        
    }
}
