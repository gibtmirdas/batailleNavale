package panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carte extends JPanel{

	private static final long serialVersionUID = 1L;
	private int id ;
	private String type;
	private JLabel label;
	private int width = 50;
	private int height = 100;
	
	public Carte(int id, String type){
		this.type = type;
		this.id   =  id;
		setSize(width, height);
		label = new JLabel(type);
		add(label);
		addMouseListener(new MouseAdapter() {
			//implementation des methodes qu'on a juste besoin
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Carte appuyée id :["+Carte.this.id+"]\ttype:["+Carte.this.type+"]");
				super.mousePressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
			}
		});
	}
}
