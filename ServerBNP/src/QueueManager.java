import java.util.ArrayList;
import java.util.Map;

public class QueueManager {

	Map<String, Connection> mapping;
	ArrayList<String> queue;

	public QueueManager(Map<String, Connection> mapping) {
		this.mapping = mapping;
		queue = new ArrayList<String>();
	}

	public void notifyQueue(String id) {
		if (!queue.contains(id))
			queue.add(id);
		// if (queue.size() % 2 == 0) {
		// new Game(mapping.get(queue.remove(queue.size() - 1)),
		// mapping.get(queue.remove(queue.size() - 1)));
		// }
	}

	public void createGame(int id, String username) {
		Connection p1 = null, p2 = null;
		for (Connection c : mapping.values()) {
			if (Integer.parseInt(c.id) == id) {
				removeIdFromQueue(c.id);
				p1 = c;
			}
			if (c.player.getPseudo().equals(username)) {
				removeIdFromQueue(c.id);
				p2 = c;
			}
			if (p1 != null && p2 != null) {
				new Game(p1, p2);
				break;
			}
		}
	}

	private void removeIdFromQueue(String id) {
		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i) == id) {
				queue.remove(i);
			}
		}
	}
}
