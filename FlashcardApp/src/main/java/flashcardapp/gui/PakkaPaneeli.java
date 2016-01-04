package flashcardapp.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka laajentaa JPanel.
 */
public class PakkaPaneeli extends JPanel implements ActionListener {

    private final Kayttoliittyma kayttoliittyma;
    private List<JPanel> paneelit;

    private JPanel lisaaPaneeli() {
        JPanel paneeli = new JPanel(); 
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));   
        paneeli.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        paneeli.setAlignmentY(JPanel.TOP_ALIGNMENT);
        
        paneelit.add(paneeli);   
        super.add(paneeli);
        super.add(Box.createHorizontalGlue());
        
        return paneeli;
    }

    private void lisaaNappi() {     
        JPanel paneeli = paneelit.get(paneelit.size() - 1);
        
        if (paneeli.getComponentCount() > 20) { //TODO: calculate 20 with height()
            paneeli = lisaaPaneeli();
        }
        
        paneeli.add(new PakkaNappi("Pakka1", this));
        paneeli.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public PakkaPaneeli(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;
        this.paneelit = new ArrayList<>();

        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.add(Box.createHorizontalGlue());
        
        lisaaPaneeli();
        lisaaNappi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof PakkaNappi) {
            PakkaNappi nappi = (PakkaNappi) e.getSource();
            lisaaNappi();
        }
    }
}
