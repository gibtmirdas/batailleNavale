package panels;

import java.util.ArrayList;

import javax.swing.JPanel;

public class ContainerCartes extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private ArrayList<Carte> listcards;
	
	public ContainerCartes() {
		listcards = new ArrayList<Carte>();
	}
	
	public void addCarte(Carte carte){
		listcards.add(carte);
	}
	
	public void addCartesContent(){
		for (int i = 0; i < listcards.size(); i++) {
			add(listcards.get(i));
		}
	}
}
