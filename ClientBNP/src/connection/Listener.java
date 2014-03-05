package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

class Listener extends Thread {
	private Socket socket;
	private BufferedReader br;
	
//	public Listener(Socket s, Contain c){
//		socket = s;
//		try {
//			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void run() {
//			Packet p = null;
//			try {
//				while (true) {
//					p = PacketBuilder.build(br.readLine().getBytes());
//					c.traiterPacket(p);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException ex) {
//				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
//						null, ex);
//			}
//		}
}
