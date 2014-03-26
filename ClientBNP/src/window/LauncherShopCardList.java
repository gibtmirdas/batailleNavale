package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import connection.ClientConnection;

import packet.Packet;
public class LauncherShopCardList  extends JScrollPane implements PanelNotifiable{
	private static final long serialVersionUID = 1L;
	ClientConnection c;
	JPanel p;
	private int lastCount=0;
	JViewport vp;
	ChangeListener cl;
	Description d;
	CardPanel currentCp= null;
	private int cardWidth=120,cardHeight=160,margin=15;
	public LauncherShopCardList(ClientConnection conn,Description d) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c=conn;
		this.d = d;
		p = new JPanel();
		
		p.setLayout(new FlowLayout(FlowLayout.LEADING,margin,margin));
		setViewportView(p);
		vp = getViewport();
		cl = new  ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				int width = vp.getWidth();
				int comp_per_lines= width/(cardWidth+margin);
				if(comp_per_lines !=lastCount){
					lastCount = comp_per_lines;
					resizeMyAssNigger(width,comp_per_lines);
				}
			};
		};
		vp.addChangeListener(cl);
	}
	public void init(){
		for(int i = 0; i < 20; i ++){
			addCard(new CardPanel(i));
		}
	}
	public void addCard(CardPanel cp){
		if(currentCp ==null){
			currentCp = cp;
			cardClicked(cp.cardId);
			currentCp.makeTargeted(true);
		}
		p.add(cp);
	}
	
	public void resizeMyAssNigger(int width, int comp_per_lines){
		int height = (int)(Math.ceil((double)p.getComponentCount()/(double)(comp_per_lines))*(cardHeight+margin)+margin);
		p.setPreferredSize(new Dimension(width,height));
	}
	

	@Override
	public void receivePacket(Packet p) {
		// TODO Auto-generated method stub
		
	}
	
	public void cardClicked(int id){
		System.out.println("CLICKED CARD : "+id);
		d.changeDescription(id);
		
		
	}
	
	private class CardPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		String[] ids = {"Carte Missile","Carte Radar","Carte de ouf","Yassine","Idiot"};
		int cardId;
		public CardPanel(int id){
			this.cardId = id;
			JLabel l = new JLabel(ids[id%ids.length]+' '+id);
			
			this.setPreferredSize(new Dimension(cardWidth,cardHeight));
			this.setBackground(Color.WHITE);
			add(l);
			this.addMouseListener(new MouseListener(){
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					cardClicked(cardId);
					currentCp.makeTargeted(false);
					currentCp = (CardPanel) e.getSource();
					currentCp.makeTargeted(true);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});	
		}
		public void makeTargeted(Boolean targeted){
			if(targeted)
				this.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
			else
				this.setBorder(null);
		}
	}

}
