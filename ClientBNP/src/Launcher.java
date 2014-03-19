import GUIManager.GUIManager;
import connection.ClientConnection;
import window.ConnectWindow;



public class Launcher{

	public static void main(String[] args) {
		
            ClientConnection conn = new ClientConnection();	
            GUIManager gui = new GUIManager();
            gui.launchConnectWindow(conn);
            
	}
}
