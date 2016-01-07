package flashcardapp.gui;

import flashcardapp.domain.Pakka;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * PakkaHarjoittaja-paneeli luo pakan harjoitusta varten näkymän.
 */
public class PakkaHarjoittaja extends JPanel {

    private final Kayttoliittyma kayttoliittyma;
    private JTextField textField;
    private JPanel alapaneeli;
    private JButton nappi, backbutton, score;
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

        score = new JButton();
        score.setBorder(null);
        score.setBorderPainted(false);
        score.setContentAreaFilled(false);
        score.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel top = new JPanel(new BorderLayout());
        top.setMaximumSize(new Dimension(1000, 20));
        top.add(score, BorderLayout.WEST);

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.add(top);
        super.add(textField);
        super.add(alapaneeli);
    }

    /**
     * Alustaa PakkaHarjoittajan annetulle pakalle.
     *
     * @param pakka Harjoitettava pakka.
     */
    public void alusta(Pakka pakka) {
        this.pakka = pakka;
        this.kortti = 0;

        nappi.setText("Show Answer");
        backbutton.setVisible(true);
        score.setText("1/" + pakka.getKoko());
        kayttoliittyma.asetaMenuBarNakyvyys(false);

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
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setEditable(false);
        field.setBackground(null);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setFont(new Font("Arial", Font.PLAIN, 30));

        return field;
    }

    //TODO lisaTiedot()

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
                score.setText((kortti + 1) + "/" + pakka.getKoko());
                break;
            case "Go Back":
                kayttoliittyma.asetaMenuBarNakyvyys(true);
                kayttoliittyma.asetaMainContainer();
                break;
        }

        kayttoliittyma.paivitaFrame();
    }
}
