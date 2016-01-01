package flashcardapp.gui;

import flashcardapp.main.FlashcardApp;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Kayttoliittyma implements Runnable {

    private FlashcardApp app;
    private JFrame frame;

    public Kayttoliittyma() {

    }

    @Override
    public void run() {
        frame = new JFrame("FlashcardApp");
        frame.setPreferredSize(new Dimension(500, 440));
        frame.setMinimumSize(new Dimension(300, 300)); //tune
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void messageBox(String text) {
        JOptionPane.showMessageDialog(frame, text);
    }

    public void luoKomponentit(Container container) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Tiedosto");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Avaa"); //Alkukirjain alleviivaus hotkey
        menu.add(menuItem);
        menuItem = new JMenuItem("Tallenna");
        menu.add(menuItem);

        menu = new JMenu("Lisää");
        menuBar.add(menu);

        menuItem = new JMenuItem("Lisää pakka");
        menu.add(menuItem);

        menuItem = new JMenuItem("Lisää kortteja");
        menu.add(menuItem);

        frame.setJMenuBar(menuBar);

        JPanel panel = new PakkaPaneeli(this);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public JFrame getFrame() {
        return frame;
    }
}
