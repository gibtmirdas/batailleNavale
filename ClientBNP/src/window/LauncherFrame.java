package window;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LauncherFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    int WIDTH = 600, HEIGHT = 500;

    public LauncherFrame() {
        this.setTitle("Launcher v0.1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //Add content to the window.
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    public void cleanView() {
        this.getContentPane().removeAll();
    }

    public void setView(JPanel panel) {
        this.add(panel);
        this.validate();
        this.repaint();
    }

}
