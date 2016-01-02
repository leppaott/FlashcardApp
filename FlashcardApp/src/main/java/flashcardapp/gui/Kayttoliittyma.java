package flashcardapp.gui;

import flashcardapp.FlashcardApp;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Luokka huolehtii käyttöliittymästä.
 */
public class Kayttoliittyma implements Runnable {

    private final FlashcardApp app;
    private JFrame frame;
    private MenuBar menuBar;
    private PakkaPaneeli pakkaPaneeli;

    public Kayttoliittyma(FlashcardApp app) {
        this.app = app;
    }

    @Override
    public void run() {
        frame = new JFrame("FlashcardApp");
        frame.setPreferredSize(new Dimension(500, 440));
        frame.setMinimumSize(new Dimension(300, 300)); //tune
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new MenuBar(this,app);
        frame.setJMenuBar(menuBar);
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void messageBox(String text) {
        JOptionPane.showMessageDialog(frame, text);
    }

    public void luoKomponentit(Container container) {
        pakkaPaneeli = new PakkaPaneeli(this);
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addComponent(pakkaPaneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addComponent(pakkaPaneeli, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public JFrame getFrame() {
        return frame;
    }

    public void paivita() {
        frame.revalidate();
        frame.repaint();    
    }
}
