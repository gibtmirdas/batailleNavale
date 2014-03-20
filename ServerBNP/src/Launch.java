
import db.TJoueurs;

public class Launch {

	public static void main(String[] args) {
                TJoueurs tj = new TJoueurs();

                tj.printAll();
		new Thread(new Server(12345)).start();
		System.out.println("Server is launched!");
	}
}
