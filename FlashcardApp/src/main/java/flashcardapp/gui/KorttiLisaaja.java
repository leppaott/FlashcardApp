package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 * Card adder view lets the user add cards.
 */
public class KorttiLisaaja extends JPanel {

    private final Kayttoliittyma gui;
    private final FlashcardApp app;
    private JPanel right;
    private PakkaTable table;
    private JScrollPane scrollPane1, scrollPane2, scrollPane3;
    private JButton addButton;
    private JButton cancelButton;
    private JComboBox jComboBox1;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;

    public KorttiLisaaja(Kayttoliittyma gui, FlashcardApp app) {
        super();
        this.gui = gui;
        this.app = app;
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        luoKomponentit();
    }

    private void luoKomponentit() {
        table = new PakkaTable();
        jTextArea1 = new JTextArea(100, 5);
        jTextArea2 = new JTextArea(100, 5);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        jComboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane(jTextArea1);
        scrollPane2 = new JScrollPane(jTextArea2);
        scrollPane3 = new JScrollPane(table);

        addButton.addActionListener(e -> addButtonActionPerformed(e));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));

        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Question"));
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Answer"));

        jComboBox1.setModel(new DefaultComboBoxModel());
        jComboBox1.setBorder(BorderFactory.createTitledBorder("Deck"));
        jComboBox1.addActionListener(e -> comboBoxAction(e));

        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        right.add(jComboBox1);
        right.add(scrollPane1);
        right.add(scrollPane2);
        right.add(addButton);
        right.add(cancelButton);

        super.add(scrollPane3);
        super.add(right);
    }

    /**
     * Method initializes the card adder to be used.
     */
    public void alusta() {
        jComboBox1.removeAllItems();
        
        for (Pakka pakka : app.getPakat()) {
            jComboBox1.addItem(pakka.getNimi());
        }

        if (!app.getPakat().isEmpty()) {
            alustaTable(app.getPakat().get(0));
        }
        gui.asetaMenuBarNakyvyys(false);
    }

    private void alustaTable(Pakka pakka) {
        table.alusta(pakka);
        gui.paivitaFrame();
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        if (jTextArea1.getText().isEmpty() || jTextArea2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(gui.getFrame(), "Please fill both question and answer fields.");
            return;
        }
        
        String nimi = (String) jComboBox1.getSelectedItem();
        Pakka pakka = app.haePakka(nimi);
        if (pakka != null) {
            pakka.lisaaKortti(new Kortti(jTextArea1.getText(), jTextArea2.getText()));
            alustaTable(pakka);
        }
        jTextArea1.setText(null);
        jTextArea2.setText(null);
        gui.paivitaFrame();
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        app.tallennaPakat();
        gui.asetaMenuBarNakyvyys(true);
        gui.asetaMainContainer();
    }

    private void comboBoxAction(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String valittu = (String) cb.getSelectedItem();
        alustaTable(app.haePakka(valittu));
        gui.paivitaFrame();
    }
}
