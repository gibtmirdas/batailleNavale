import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import packet.Packet;

public class Connection implements Runnable {
	Socket s;
	OutputStream os;
	BufferedReader br;
	Game g;
	Map<String, Connection> mapping;
	String id;

	public Connection(Socket s, Map<String, Connection> mapping) {
		this.s = s;
		this.mapping = mapping;
		try {
			os = s.getOutputStream();
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO gerer reconnection
	@Override
	public void run() {
		Packet p = null;
		try {
			p = new Packet(br.readLine().getBytes());
			// TODO get mac
			// mapping.put(p.mac, this);
			while (true) {
				p = new Packet(br.readLine().getBytes());
				if (g != null) {
					g.traiterPacket(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Packet p) {
		try {
			os.write(p.encodedPacket);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setG(Game g) {
		this.g = g;
	}

}
