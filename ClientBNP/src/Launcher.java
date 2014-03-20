import GUIManager.GUIManager;
import connection.ClientConnection;
import java.awt.Component;
import javax.swing.JFrame;
import window.ConnectWindow;
import window.LoginFrame;



public class Launcher{

	public static void main(String[] args) {
            GUIManager gui = GUIManager.getInstance();
            ClientConnection conn = new ClientConnection();	
	}
}
