public class Launch {

	public static void main(String[] args) {
		new Thread(new Server(12345)).start();
		System.out.println("Server is launched!");
	}
}
