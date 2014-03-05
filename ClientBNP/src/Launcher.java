
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import window.ConnectWindow;

public class Launcher extends Thread{
	
	private ConnectWindow connectWin;
	private Socket s;
	private String ADDRESS = "localhost";
	private int PORT = 12345;
	private BufferedReader br;
	
	public Launcher() {
		System.out.println("Client is launched!"); 
		try {
			s = new Socket(ADDRESS, PORT);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));			
			connectWin = new ConnectWindow(this);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
//		Packet p = null;
//		try {
//			while (true) {
//				p = PacketBuilder.build(br.readLine().getBytes());
//				c.traiterPacket(p);
//				
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException ex) {
//			Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
//					null, ex);
//		}
	}
	
	
	public static void main(String[] args) {
		new Launcher();		
	}

}
