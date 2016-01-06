package flashcardapp.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * PakkaPaneeli listaa Pakat käyttöliittymässä.
 */
public class PakkaPaneeli extends JPanel  {

    private final Kayttoliittyma kayttoliittyma;
    private final EventHandler handler;
    private final PakkaPopupMenu popup;

    public PakkaPaneeli(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;
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
        paneeli.setMaximumSize(kayttoliittyma.getFrame().getSize());

        super.add(paneeli);
        super.add(Box.createHorizontalGlue());
        return paneeli;
    }

    public void lisaaPakkaNappi(String nimi) {
        JPanel paneeli = (JPanel) super.getComponent(super.getComponentCount() - 2);

        int max = kayttoliittyma.getFrame().getHeight() / 12; //
        if (paneeli.getComponentCount() > max) {
            paneeli = lisaaPaneeli();
        }

        paneeli.add(Box.createRigidArea(new Dimension(0, 5)));
        paneeli.add(new PakkaNappi(nimi, handler));
        paneeli.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public void tyhjenna() {
        super.removeAll();
        super.add(Box.createHorizontalGlue());
        lisaaPaneeli();
    }

    private class EventHandler implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JMenuItem) {
                JMenuItem item = (JMenuItem) e.getSource();
                if (item.getText().equals("Delete")) {
                    kayttoliittyma.poistaPakkaNimella(popup.getNappi().getNimi());
                    kayttoliittyma.paivita();
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof PakkaNappi) {
                PakkaNappi nappi = (PakkaNappi) e.getSource();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    kayttoliittyma.harjoitaPakkaa(nappi.getNimi());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.setNappi(nappi);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() instanceof PakkaNappi) {
                PakkaNappi nappi = (PakkaNappi) e.getSource();
                nappi.vaihdaAlleviivausNapille();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() instanceof PakkaNappi) {
                PakkaNappi nappi = (PakkaNappi) e.getSource();
                nappi.vaihdaAlleviivausNapille();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}
