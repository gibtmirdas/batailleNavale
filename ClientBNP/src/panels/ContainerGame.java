package panels;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ContainerGame extends JPanel{

	private static final long serialVersionUID = 1L;
	private Canvas canva;
    public static ArrayList<ElementContainer> ShipList;
    private ArrayList<String> listboat;
    private Container containCanva;
    private ContainerCartes containCarte;
    private GridBagConstraints gridBag;
	
	public ContainerGame(Container containCanva){
		this.containCanva = containCanva;
		this.containCanva.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		gridBag = new GridBagConstraints();
		containCarte = new ContainerCartes();
		listboat = new ArrayList<String>();
		ShipList = new ArrayList<ElementContainer>();
		gridBag.fill = GridBagConstraints.BOTH;
		this.containCanva.setLayout(new GridBagLayout());
	    addCanva();
//	    addCartes();
//		addShips();
	}
	
	private void addCanva(){
	    canva = new Canvas(40);
	    gridBag.weightx = 0.65;
	    gridBag.weighty = 3.2;
	    gridBag.gridx = 1;
	    gridBag.gridy = 0;
	    containCanva.add(canva, gridBag);
	}
	
	//On ajoute une carte à la liste de cartes
	public void addCarte(int cardId, String type){
		Carte carte = new Carte(cardId, type);
		containCarte.addCarte(carte);
	}
	
	
	//update des cartes
	//on parcours notre liste de cartes on met dans un panel 
	//et apres on affiche tous nos cartes
	public void addCartes(){
		containCarte.addCartesContent();
	    gridBag.gridx = 0;
	    gridBag.gridy = 2;
	    gridBag.gridwidth = 3;
	    containCanva.add(containCarte, gridBag);
	}
	
	public void addBoat(){
		
	}
	
	private void addTextField(){
	    JTextArea field = new JTextArea(5, 20);	    
	    field.setEditable(false);
	    gridBag.weightx = 0.2;
	    gridBag.weighty = 0.7;
	    gridBag.gridx = 0;
	    gridBag.gridy = 0;
	    containCanva.add(field, gridBag);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public void addShips(int x, int y){
	    //TODO recuperer infoboat et ajouter le navire
		ElementContainer s = new ElementContainer(x,  y, 1, 1, Color.CYAN, true);
		ShipList.add(s);
	}

	public ArrayList<ElementContainer> getShipList() {
		return ShipList;
	}

	public Canvas getCanva() {
		return canva;
	}
}
