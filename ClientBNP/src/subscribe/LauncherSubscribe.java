package subscribe;

import connection.ClientConnection;

public class LauncherSubscribe {
	public static void main(String[] args) {
		new WinSubscribe(new ClientConnection());
	}
}
