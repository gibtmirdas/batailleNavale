import lib.Constante;
import GUIManager.GUIManager;
import connection.ClientConnection;



public class Launcher{

	public static void main(String[] args) {
            
			Constante.PORT = Integer.parseInt(args[0]);
			Constante.ADDRESS  = args[1];
            GUIManager gui = GUIManager.getInstance();
            ClientConnection conn = new ClientConnection();	
            
	}
}
