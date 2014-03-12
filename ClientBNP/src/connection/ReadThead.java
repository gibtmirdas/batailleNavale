package connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadThead implements Runnable{
	
	private InputStream is;
	private ClientConnection client;
	
	
	public ReadThead(Socket s, ClientConnection client) {
		try {
			is = s.getInputStream();
			this.client = client;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		byte opcodeID, size;
		try {
			while (true) {
				opcodeID = (byte)is.read();
				size = (byte)is.read();
				byte[] datas = new byte[size+2];
				datas[0] = opcodeID;
				datas[1] = size;
				for (int i = 2; i < datas.length; i++) {
					datas[i] = (byte) is.read();
				}
				client.analysePacket(datas);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
