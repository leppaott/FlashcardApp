package flashcardapp.gui;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * A context menu for deck buttons.
 * @see PakkaPaneeli
 */
public class PakkaPopupMenu  extends JPopupMenu {
    private PakkaNappi nappi;
    
    public PakkaPopupMenu(ActionListener actionListener) {
        JMenuItem item = new JMenuItem("Delete");
        item.addActionListener(actionListener);
        super.add(item);  
    }
    
    public void setNappi(PakkaNappi nappi) {
        this.nappi = nappi;
    }
    
    public PakkaNappi getNappi() {
        return nappi;
    }
}

