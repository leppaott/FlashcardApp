package flashcardapp.gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

/**
 * Luokka laajentaa JButton pakoille.
 */
public class PakkaNappi extends JButton {

    public PakkaNappi(String pakanNimi, MouseListener mouseListener) {
        super(pakanNimi);
        super.setBorder(null);
        super.setBorderPainted(false);
        super.setContentAreaFilled(false);
        super.setCursor(new Cursor(Cursor.HAND_CURSOR));
        super.setAlignmentX(JButton.CENTER_ALIGNMENT);
        super.addMouseListener(mouseListener);
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
    
    public String getNimi() {
        return super.getText();
    }
    
    public void setNimi(String nimi) {
        super.setText(nimi);
    }
}
