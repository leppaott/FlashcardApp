package flashcardapp.gui;

import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PakkaHarjoittaja extends JPanel {
    private final Kayttoliittyma kayttoliittyma;
    private final JTextField textField;
    private Pakka pakka;
    
    /**
     *
     * @param kayttoliittyma
     */
    public PakkaHarjoittaja(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;
        this.textField = getTextField();
        super.add(textField);
    }
    
    public void alusta(Pakka pakka) {
        this.pakka = pakka;
        pakka.lisaaKortti(new Kortti("MIKÄ ON SUOMALAINEN", "asd"));
        textField.setText(pakka.getKortti(0).getEtupuoli());
        kayttoliittyma.paivitaFrame();
    }
    
    private JTextField getTextField() {
        if (textField != null) {
            return textField;
        }
        
        JTextField field = new JTextField();
        field.setEditable(false);
        field.setBackground(null);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setFont(new Font("Arial", Font.PLAIN, 20));
        
        return field;
    }
}
