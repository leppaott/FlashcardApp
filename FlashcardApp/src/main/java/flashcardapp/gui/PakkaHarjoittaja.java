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
 * Class creates a view for deck training.
 */
public class PakkaHarjoittaja extends JPanel {

    private final Kayttoliittyma gui;
    private JTextField textField;
    private JPanel alapaneeli;
    private JButton nappi, backbutton, score;
    private Pakka pakka;
    private int kortti;

    public PakkaHarjoittaja(Kayttoliittyma gui) {
        super();
        this.gui = gui;
        luoKomponentit();
    }

    private void luoKomponentit() {
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setBackground(null);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setFont(new Font("Arial", Font.PLAIN, 30));

        alapaneeli = new JPanel(new BorderLayout());
        alapaneeli.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        alapaneeli.setMaximumSize(new Dimension(1000, 20));

        backbutton = new JButton("Go Back");
        backbutton.addActionListener(e -> {
            gui.asetaMenuBarNakyvyys(true);
            gui.asetaMainContainer();
            gui.paivitaFrame();
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
     *Initializes training for a given deck.
     * @param pakka a deck to be trained
     */
    public void alusta(Pakka pakka) {
        this.pakka = pakka;
        this.kortti = 0;

        nappi.setText("Show Answer");
        score.setText("1/" + pakka.getKoko());
        textField.setText(pakka.getKortti(kortti).getEtupuoli());
        backbutton.setVisible(true);

        gui.asetaMenuBarNakyvyys(false);
        gui.paivitaFrame();
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
                textField.setText(pakka.getKortti(kortti++).getKaantopuoli());
                break;
            case "Next":
                nappi.setText("Show Answer");
                textField.setText(pakka.getKortti(kortti).getEtupuoli());
                score.setText((kortti + 1) + "/" + pakka.getKoko());
                break;
            case "Go Back":
                gui.asetaMenuBarNakyvyys(true);
                gui.asetaMainContainer();
                break;
        }

        gui.paivitaFrame();
    }
}
