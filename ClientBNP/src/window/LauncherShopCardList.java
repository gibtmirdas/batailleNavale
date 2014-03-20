package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private int cardWidth=120,cardHeight=160,margin=15;
	public LauncherShopCardList(ClientConnection conn) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c=conn;
		
		p = new JPanel();
		
		p.setLayout(new FlowLayout(FlowLayout.LEADING,margin,margin));
		
		p.setBackground(Color.BLACK);
		
		setViewportView(p);
		
		vp = getViewport();
		vp.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				int width = vp.getWidth();
				int comp_per_lines= width/(cardWidth+margin*2)+1;
				if(comp_per_lines !=lastCount){
					lastCount = comp_per_lines;
					resizeMyAssNigger(width,comp_per_lines);
				}
			}
		});
	}
	public void init(){
		for(int i = 0; i < 200; i ++){
			addCard(new CardPanel(i));
		}
	}
	public void addCard(CardPanel cp){
		p.add(cp);
	}
	
	public void resizeMyAssNigger(int width, int comp_per_lines){
		p.setPreferredSize(new Dimension(width,(int)(Math.ceil((float)p.getComponentCount()/comp_per_lines)*(cardHeight+margin)+margin)));
	}
	

	@Override
	public void receivePacket(Packet p) {
		// TODO Auto-generated method stub
		
	}
	private class CardPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		String[] ids = {"Pute","Chatte","Negre","Yassine","Abstract"};
		public CardPanel(int id){
			JLabel l = new JLabel(ids[id%ids.length]+' '+id);
			
			this.setPreferredSize(new Dimension(cardWidth,cardHeight));
			this.setBackground(Color.WHITE);
			add(l);
		}
		
	}

}
