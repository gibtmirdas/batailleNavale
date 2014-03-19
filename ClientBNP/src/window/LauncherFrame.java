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
        this.add(new LoginFrame(null));
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    public void cleanView() {
        this.remove(0);
    }

    public void setView(JPanel panel) {
        this.add(panel);
    }

    public static void main(String[] args) {
        LauncherFrame frame = new LauncherFrame();
    }
}
