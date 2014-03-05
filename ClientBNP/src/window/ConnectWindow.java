package window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Unmarshaller.Listener;


public class ConnectWindow extends JFrame {
	
	private JPanel panel ;
	private JButton button;
	private int windowWidth = 300;
	private int windowHeight = 200;
	private Socket s;
	private Thread launcher;
	
	public ConnectWindow(Thread listener) {
		setDefaultCloseOperation(3);
		setSize(windowWidth, windowHeight);
		panel= new JPanel();
		button = new JButton("Start");
		this.launcher = listener;
		
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				setVisible(false);
				launcher.start();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		panel.add(button);
		add(panel);
		setVisible(true);	
	}
	
}
