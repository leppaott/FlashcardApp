package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Paneeli, jossa voi lisata kortteja.
 */
public class KorttiLisaaja extends JPanel {

    private final Kayttoliittyma kayttoliittyma;
    private FlashcardApp app;
    private Pakka pakka;

    /**
     * Creates new form KorttiLisaajaBuilder
     */
    public KorttiLisaaja(Kayttoliittyma kayttoliittyma, FlashcardApp app) {
        this.kayttoliittyma = kayttoliittyma;
        this.app = app;
        initComponents();
    }

    public void alusta() {
        jPanel1.removeAll();
        jComboBox1.removeAllItems();

        for (Pakka pakka : app.getPakat()) {
            if (this.pakka == null) {
                this.pakka = pakka;
            }
            jComboBox1.addItem(pakka.getNimi());
        }
        alustaPaneeli(pakka.getNimi());
        kayttoliittyma.asetaMenuBarNakyvyys(false);
    }

    private void alustaPaneeli(String pakanNimi) {
        jPanel1.removeAll();

        pakka = app.haePakka(pakanNimi);

        if (pakka != null) {
            for (Kortti kortti : pakka) {
                JTextField field = new JTextField(kortti.toString());
                field.setMaximumSize(new Dimension(1000, 20));
                jPanel1.add(field);
            }
        }

        kayttoliittyma.paivitaFrame();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 154, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 182, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Question"));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Answer"));
        jScrollPane3.setViewportView(jTextArea2);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel());
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder("Deck"));
        jComboBox1.addActionListener(e -> comboBoxAction(e));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3))))
                        .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(addButton)
                                                .addComponent(cancelButton))))
                        .addContainerGap(48, Short.MAX_VALUE))
        );
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String nimi = (String) jComboBox1.getSelectedItem();
        pakka = app.haePakka(nimi);

        if (pakka != null) {
            pakka.lisaaKortti(new Kortti(jTextArea1.getText(), jTextArea2.getText()));
            jTextArea1.setText(null);
            jTextArea2.setText(null);
            alustaPaneeli(pakka.getNimi());
            kayttoliittyma.paivitaFrame();
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        pakka = null;
        app.tallennaPakat();
        kayttoliittyma.asetaMenuBarNakyvyys(true);
        kayttoliittyma.asetaMainContainer();
    }

    private void comboBoxAction(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String valittu = (String) cb.getSelectedItem();
        alustaPaneeli(valittu);
        kayttoliittyma.paivitaFrame();
    }

    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
}
