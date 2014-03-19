package window;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LauncherTabUserPanel extends JPanel {
	private static final long serialVersionUID = -78596758792477352L;
		int nbrVictories=100,nbrDefeats=35,nbrCards=321;
		public LauncherTabUserPanel(){
			JLabel userNameLabel = new JLabel();
			JLabel victoriesLabel = new JLabel();
			JLabel defeatsLabel = new JLabel();
			JLabel possessedCards = new JLabel();
			userNameLabel.setText("My User Name");
			victoriesLabel.setText("Victories : "+nbrVictories);
			defeatsLabel.setText("Defeats : "+nbrDefeats);
			possessedCards.setText("Possessed cards : "+nbrCards);
			this.setLayout(new GridLayout(4,1));
			this.add(userNameLabel);
			this.add(victoriesLabel);
			this.add(defeatsLabel);
			this.add(possessedCards);
		}
}
