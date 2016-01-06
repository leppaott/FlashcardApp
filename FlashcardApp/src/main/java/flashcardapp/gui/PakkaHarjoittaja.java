package flashcardapp.gui;

import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PakkaHarjoittaja extends JPanel {

    private final Kayttoliittyma kayttoliittyma;
    private JTextField textField;
    private JPanel alapaneeli;
    private JButton nappi, backbutton;
    private Pakka pakka;
    private int kortti;

    /**
     *
     * @param kayttoliittyma
     */
    public PakkaHarjoittaja(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;
        luoKomponentit();
    }

    private void luoKomponentit() {
        textField = getTextField();
        alapaneeli = new JPanel(new BorderLayout());
        alapaneeli.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        alapaneeli.setMaximumSize(new Dimension(1000, 20));

        backbutton = new JButton("Go Back");
        backbutton.addActionListener(e -> {
            kayttoliittyma.asetaMenuBarNakyvyys(true);
            kayttoliittyma.asetaMainContainer();
            kayttoliittyma.paivitaFrame();
        });
        alapaneeli.add(backbutton, BorderLayout.WEST);
        
        nappi = new JButton();
        nappi.addActionListener(e -> nappiaPainettu(e));
        alapaneeli.add(nappi, BorderLayout.CENTER);

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.add(textField);
        super.add(alapaneeli);
    }

    public void alusta(Pakka pakka) {
        this.pakka = pakka;
        this.kortti = 0;

        nappi.setText("Show Answer");
        backbutton.setVisible(true);
        kayttoliittyma.asetaMenuBarNakyvyys(false);

        pakka.lisaaKortti(new Kortti("Helppo", "Easy"));
        pakka.lisaaKortti(new Kortti("ruotsalainen", "Swedish"));
        pakka.lisaaKortti(new Kortti("saksalainen", "German"));
        
        if (pakka.getKoko() != 0) {
            textField.setText(pakka.getKortti(kortti).getEtupuoli());
        } else {
            nappi.setText("Go Back");
        }

        kayttoliittyma.paivitaFrame();
    }

    private JTextField getTextField() {
        if (textField != null) {
            return textField;
        }

        JTextField field = new JTextField();
        //field.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        //field.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setEditable(false);
        field.setBackground(null);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setFont(new Font("Arial", Font.PLAIN, 20));

        return field;
    }

    private void nappiaPainettu(ActionEvent e) {
        switch (nappi.getText()) {
            case "Show Answer":
                if (kortti < pakka.getKoko() - 1) {
                    nappi.setText("Next");
                } else {
                    nappi.setText("Go Back");
                    backbutton.setVisible(false);
                }
                textField.setText(pakka.getKortti(kortti).getKaantopuoli());
                break;
            case "Next":
                nappi.setText("Show Answer");
                textField.setText(pakka.getKortti(++kortti).getEtupuoli());
                break;
            case "Go Back":
                kayttoliittyma.asetaMenuBarNakyvyys(true);
                kayttoliittyma.asetaMainContainer();
                break;
        }

        kayttoliittyma.paivitaFrame();
    }
}
