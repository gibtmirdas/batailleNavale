import java.awt.List;
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
		queue.add(id);
		if (queue.size() % 2 == 0) {
			new Game(mapping.get(queue.remove(queue.size() - 1)),
					mapping.get(queue.remove(queue.size() - 1)));
		}
	}
}
