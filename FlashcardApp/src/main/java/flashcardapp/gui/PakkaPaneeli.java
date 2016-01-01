package flashcardapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PakkaPaneeli extends JPanel implements ActionListener {

    private final Kayttoliittyma kayttoliittyma;
    private GroupLayout layout;
    private ParallelGroup horizontalGroup;
    private SequentialGroup verticalGroup;

    public PakkaPaneeli(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;

        JLabel otsikko = new JLabel("Pakat");
        PakkaNappi pakka1 = new PakkaNappi("Pakka1", this);

        layout = new GroupLayout(this);
        super.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(otsikko)
                                .addGroup(horizontalGroup = layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(pakka1)
                                ))));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(verticalGroup = layout.createSequentialGroup()
                        .addComponent(otsikko)
                        .addComponent(pakka1)
                ));
    }

    private void lisaaNappi(String nimi) {
        PakkaNappi pakka = new PakkaNappi(nimi, this);
        horizontalGroup.addComponent(pakka);
        verticalGroup.addComponent(pakka);
        kayttoliittyma.getFrame().revalidate();
        kayttoliittyma.getFrame().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof PakkaNappi) {
            PakkaNappi nappi = (PakkaNappi) e.getSource();
            nappi.setText(nappi.getText() + " 1 ");
            lisaaNappi("asd");
        }
    }
}
