package window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


public class Description extends JPanel{


	private static final long serialVersionUID = 1L;
	private static final Insets insets = new Insets(0, 0, 0, 0);
	private JTextArea jtAreaOutput;
	private JLabel price;
	private JLabel amount;
	private JButton button;
	private BufferedImage image;
	private JLabel picLabel;
	private JLabel desc;
	
	
	public Description(){
		
		this.setLayout(new GridBagLayout());
	    
		// Adding description field
        //setDescriptionCarte("Description of carte super......");
        
		// Field description
		new JTextField(100);
		jtAreaOutput = new JTextArea(0,0);
		jtAreaOutput.setLocation(300, 450);
		jtAreaOutput.setText("Description of carte super......");
		jtAreaOutput.setEditable(false);

		addComponent(this, jtAreaOutput, 3, 1, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        // Scroll in text area
     	JScrollPane scrollPane = new JScrollPane(jtAreaOutput);
        scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        addComponent(this, scrollPane, 3, 1, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);        
        
        // Adding an image
	    createImage();
	    addComponent(this, picLabel, 3, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    
	    // Label price
	    price = new JLabel("Price: ");
        addComponent(this, price, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        // Label amount
        amount = new JLabel("                 0");
        addComponent(this, amount, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
        // Button for buying
        button = new JButton("Buy");
	    button.setSize(10, 10);
	    addComponent(this, button, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.LAST_LINE_END);
	    
	    // Label of carte
	    desc = new JLabel("Carte Description");
	    desc.setHorizontalAlignment(SwingConstants.CENTER);
	    //desc.setFont(new Font("Serif", Font.PLAIN, 18));
	    addComponent(this, desc, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	   
	}
	
	// Adding components to the JPanel
	public static void addComponent(Container container, Component component, int gridx, int gridy,
		      int gridwidth, int gridheight, int anchor, int fill) {
		    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
		        anchor, fill, insets, 0, 0);
		    container.add(component, gbc);
		  }

	public BufferedImage getImage() {
		return image;
	}
	
	public void changeDescription(int id){
		System.out.println(id);
		desc.setText("[CARTE "+id+"]");
		jtAreaOutput.setText("DESCRIPTION DE LA CARTE "+id);
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	// Getting the image from file
	public void createImage(){
		try {
			image = ImageIO.read(new File("/Users/olivierbelli/Desktop/ship.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Setting image as a Label
		picLabel = new JLabel(new ImageIcon(image));
	}

}
