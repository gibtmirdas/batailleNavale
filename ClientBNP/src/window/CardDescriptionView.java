package window;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CardDescriptionView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopContainer topContainer;
	private BottomContainer bottomContainer;
	
	public CardDescriptionView(){
		setLayout(new GridLayout(2,1));
		/*description = new JTextPane();
		description.setEditable(false);
		StyledDocument doc = description.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);*/
		topContainer = new TopContainer();
		add(topContainer);
		bottomContainer = new BottomContainer();
		add(bottomContainer);
		
		
	}
	
	public void changeCardFocus(int idCard){
		this.topContainer.setTitle("NAME CARD ["+idCard+"]");
	}
	
	private class TopContainer extends JPanel{
		private static final long serialVersionUID = 1L;
		private BufferedImage image;
		private JLabel title;
		private JLabel imageContainer;
		GridBagConstraints c;
		public TopContainer(){
			setLayout(new GridBagLayout());
			c = new GridBagConstraints();
			c.fill = GridBagConstraints.CENTER;
			c.insets = new Insets(0,10,0,0);
			c.gridwidth = 1;
			c.gridheight=1;
			c.weightx = 1;
			c.weighty=0.1;
			c.gridx = 0;
			c.gridy = 0;
			title = new JLabel();
			add(title,c);
			try {
				image = ImageIO.read(new File("/Users/olivierbelli/Desktop/ship.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageContainer = new JLabel(new ImageIcon(image));
			c.weighty = 0.9;
			c.gridy = 1;
			imageContainer.setBorder(BorderFactory.createLineBorder(Color.black));
			add(imageContainer,c);
		}
		public void setTitle(String t){
			title.setText(t);
		}
		public void setImage(){
			remove(imageContainer);
			try {
				image = ImageIO.read(new File("/Users/olivierbelli/Desktop/ship.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageContainer = new JLabel(new ImageIcon(image));
			add(imageContainer,c);
		}
	}
	private class BottomContainer extends JPanel{
		private static final long serialVersionUID = 1L;
		private JTextArea description;
		private JLabel cardPrice;
		private JButton buyButton;
		GridBagConstraints c;
		public BottomContainer(){
			setLayout(new GridBagLayout());
			c = new GridBagConstraints();
			c.fill = GridBagConstraints.CENTER;
			c.insets = new Insets(0,10,0,0);
			c.gridwidth = 1;
			c.gridheight=1;
			c.weightx = 1;
			c.weighty=0.8;
			c.gridx = 0;
			c.gridy = 0;
			description = new JTextArea(0,0);
			description.setEditable(false);
			add(description,c);
			c.gridy = 1;
			c.weighty = 0.1;
			cardPrice = new JLabel();
			add(cardPrice,c);
			c.gridy = 2;
			buyButton = new JButton("Buy!");
			add(buyButton,c);
			setPrice("1000 $");
			setDescription("La description la plus ouf de l'univers ! Ta mere en reve souvent, sisi je t'assure !");
			
		}
		public void setDescription(String t){
			String splits[] = t.split(" ");
			String r = "";
			int max = 50, current = 0;
			for(String s : splits){
				current+= s.length()+1;
				if(current > max)
				{
					current = s.length()+1;
					r+="\n";
				}
				r+=s+" ";
			}
			
			description.setText(r);
		}
		public void setPrice(String p){
			cardPrice.setText(p);
		}
	}
}
