package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main GUI class.
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

    private void luoKomponentit() {
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
     * Updates the main frame.
     */
    public void paivitaFrame() {
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Methods sets the default view.
     */
    public void asetaMainContainer() {
        frame.setContentPane(mainContainer);
    }

    /**
     * Method sets menu bar visibility.
     *
     * @param visible visibility
     */
    public void asetaMenuBarNakyvyys(boolean visible) {
        menuBar.setVisible(visible);
    }

    /**
     * Clears and fills the deck panel.
     */
    public void taytaPakkaPaneeli() {
        pakkaPaneeli.tayta();
    }

    /**
     * Method sets a deck training view to be shown.
     *
     * @param nimi deck name
     */
    public void harjoitaPakkaa(String nimi) {
        harjoittaja.alusta(app.haePakka(nimi));
        frame.setContentPane(harjoittaja);
        paivitaFrame();
    }

    /**
     *  Method sets a card adder view to be shown.
     */
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
