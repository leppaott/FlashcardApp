package flashcardapp.gui;

import flashcardapp.domain.Kortti;
import flashcardapp.domain.Pakka;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A table of cards.
 *
 * @see KorttiLisaaja
 */
public class PakkaTable extends JTable {

    public PakkaTable() {
        super(); //sadly cards can't be edited or deleted atm
        alustaTable(20, 2);
    }

    private void alustaTable(int row, int col) {
        super.setModel(new DefaultTableModel(row, col));
        super.getTableHeader().setReorderingAllowed(false);
        super.getColumn("A").setHeaderValue("Question");
        super.getColumn("B").setHeaderValue("Answer");
    }
    /**
     * Initiliazes the table for a given deck.
     *
     * @param pakka deck
     */
    public void alusta(Pakka pakka) {
        if (pakka == null) {
            alustaTable(0, 2);
            return;
        } else {
            alustaTable(pakka.getKoko(), 2);
        }
        
        for (int i = 0; i < pakka.getKoko(); i++) {
            Kortti kortti = pakka.getKortti(i);
            super.setValueAt(kortti.getEtupuoli(), i, 0);
            super.setValueAt(kortti.getKaantopuoli(), i, 1);
        }
    }
}
