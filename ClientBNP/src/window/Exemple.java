package window;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exemple extends JPanel {
	
    ArrayList<ExampleComponent> components;

    public Exemple() {
        components = new ArrayList<ExampleComponent>();
    }


    public void addComponent(ExampleComponent j) {
        components.add(j);
        add(j);
    }

    public static void main(String[] args) {
        JFrame app = new JFrame("Staff Prototype");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Exemple s = new Exemple();
        s.setLayout(null);
        app.getContentPane().add(s);
        s.addComponent(s.new ExampleComponent(new Rectangle(0,0,100,100)));
        s.addComponent(s.new ExampleComponent(new Rectangle(45,45,100,100)));
//        app.pack();
        app.setPreferredSize(new Dimension(400, 400));
        
        app.setVisible(true);
    }

    class ExampleComponent extends JPanel implements MouseMotionListener {

    	private int posX = 0;
    	private int posy = 0;
//    	private JLabel label;    	
        public ExampleComponent(Rectangle bounds) {
        	posX = (int)bounds.getX();
        	posy = (int)bounds.getY();
    		ImageIcon icon = new ImageIcon("src/imgs/boat.png");
//    		label = new JLabel("KAKAKAK");
//    		add
            add(new JLabel("KAKAKAK"));
            addMouseMotionListener(this);
            setBounds(bounds);
        }

        public void mouseMoved(MouseEvent m) {
            System.out.println("Mouse Moved");
        }

        public void mouseDragged(MouseEvent m) {
            System.out.println("Mouse Dragged");
            posX =  m.getX();
            posy = m.getX();
            setBounds(new Rectangle(posX, posy));
            
        }
    }
}