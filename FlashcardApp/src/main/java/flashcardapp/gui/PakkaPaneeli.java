package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import flashcardapp.domain.Pakka;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Default view with a list of decks.
 */
public class PakkaPaneeli extends JPanel {

    private final Kayttoliittyma gui;
    private final FlashcardApp app;
    private final EventHandler handler;
    private final PakkaPopupMenu popup;

    public PakkaPaneeli(Kayttoliittyma gui, FlashcardApp app) {
        super();
        this.gui = gui;
        this.app = app;
        this.handler = new EventHandler();
        this.popup = new PakkaPopupMenu(handler);

        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.add(Box.createHorizontalGlue());

        lisaaPaneeli();
    }

    private JPanel lisaaPaneeli() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        paneeli.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        paneeli.setAlignmentY(JPanel.TOP_ALIGNMENT);
        paneeli.setMaximumSize(gui.getFrame().getSize());

        super.add(paneeli);
        super.add(Box.createHorizontalGlue());
        return paneeli;
    }

    private int maxKomponenttejaSarakkeessa(int korkeus, int komponenttiKorkeus) {
        if (korkeus == 0 || komponenttiKorkeus == 0) { //korkeus zero upon launch
            return 10;
        } //26 "Decks" height
        return (korkeus - 26) / komponenttiKorkeus;
    }

    /**
     * Methods adds a new deck button for a given name.
     * @param nimi deck name
     */
    public void lisaaPakkaNappi(String nimi) {
        JPanel paneeli = (JPanel) super.getComponent(super.getComponentCount() - 2);
        PakkaNappi nappi = new PakkaNappi(nimi, handler);

        int max = maxKomponenttejaSarakkeessa(gui.getFrame().getContentPane().getHeight(),
                nappi.getHeight() + 10);
        if (paneeli.getComponentCount() >= max) {
            paneeli = lisaaPaneeli();
        }

        paneeli.add(Box.createRigidArea(new Dimension(0, 5)));
        paneeli.add(nappi);
        paneeli.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private void tyhjennaPakoista() {
        super.removeAll();
        super.add(Box.createHorizontalGlue());
        lisaaPaneeli();
    }

    /**
     * Method clears and fills the view
     */
    public void tayta() {
        tyhjennaPakoista();
        for (Pakka pakka : app.getPakat()) {
            lisaaPakkaNappi(pakka.getNimi());
        }
        app.tallennaPakat();
    }

    private class EventHandler implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            if (item.getText().equals("Delete")) {
                int vastaus = JOptionPane.showConfirmDialog(gui.getFrame(),
                        "Do you really want to delete this deck?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (vastaus == JOptionPane.YES_OPTION) {
                    app.poistaPakka(popup.getNappi().getNimi());
                    gui.taytaPakkaPaneeli();
                    gui.paivitaFrame();
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            PakkaNappi nappi = (PakkaNappi) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (app.haePakka(nappi.getNimi()).getKoko() > 0) {
                    nappi.vaihdaAlleviivausNapille();
                    gui.harjoitaPakkaa(nappi.getNimi());
                } else {
                    JOptionPane.showMessageDialog(gui.getFrame(),
                            "The deck is empty. Add cards before practicing.");
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                popup.setNappi(nappi);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            PakkaNappi nappi = (PakkaNappi) e.getSource();
            nappi.vaihdaAlleviivausNapille();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            PakkaNappi nappi = (PakkaNappi) e.getSource();
            nappi.vaihdaAlleviivausNapille();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}
