import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import packet.Packet;
import packet.PacketBuilder;
import packet.PacketHello;

public class Connection implements Runnable {
	Socket s;
	OutputStream os;
	BufferedReader br;
	Game g;
	Server serv;
	Map<String, Connection> mapping;
	String id;

	public Connection(Socket s, Server srv) {
		this.s = s;
		this.serv = srv;
		this.mapping = serv.mapping;
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
			p = PacketBuilder.build(br.readLine().getBytes());
			PacketHello ph = (PacketHello) (p);
			mapping.put(Arrays.toString(ph.getMacAddress()), this);
			serv.queue.notifyQueue(Arrays.toString(ph.getMacAddress()));
			// TODO get mac
			// mapping.put(p.mac, this);

			while (true) {
				p = PacketBuilder.build(br.readLine().getBytes());
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
