package flashcardapp.gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 * Luokka laajentaa JButton pakoille.
 */
public class PakkaNappi extends JButton implements MouseListener {

    public PakkaNappi(String pakanNimi, ActionListener actionListener) {
        super(pakanNimi);
        super.setBorder(null);
        super.setBorderPainted(false);
        super.setContentAreaFilled(false);
        super.setCursor(new Cursor(Cursor.HAND_CURSOR));
        super.setAlignmentX(JButton.CENTER_ALIGNMENT);
        super.addActionListener(actionListener);
        super.addMouseListener(this);
    }

    /**
     * Metodi vaihtaa alleviivauksen päälle tai pois napille.
     */
    public void vaihdaAlleviivausNapille() {
        Font font = super.getFont();
        Map<TextAttribute, Object> attribuutit = new HashMap(font.getAttributes());

        if (attribuutit.get(TextAttribute.UNDERLINE) instanceof Integer) {
            attribuutit.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE);
        } else {
            attribuutit.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }

        super.setFont(font.deriveFont(attribuutit));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        vaihdaAlleviivausNapille();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vaihdaAlleviivausNapille();
    }
}
