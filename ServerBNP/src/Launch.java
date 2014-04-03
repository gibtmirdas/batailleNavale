import db.TJoueurs;

public class Launch {

	public static void main(String[] args) {
		new Thread(new Server(Integer.parseInt(args[0]))).start();
		System.out.println("Server is launched!");
	}
}
