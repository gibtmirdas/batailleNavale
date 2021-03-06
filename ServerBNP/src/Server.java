import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {
	ServerSocket ss;
	QueueManager queue;
	Map<String, Connection> mapping;

	public Server(int port) {
		mapping = new HashMap<String,Connection>();
		queue = new QueueManager(mapping);
		try {
			ss = new ServerSocket(port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket s = ss.accept();
				System.out.println("New client !");
				Connection connect = new Connection(s, this);
				new Thread(connect).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
