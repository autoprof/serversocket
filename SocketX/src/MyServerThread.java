import java.net.Socket;

public class MyServerThread extends Thread {
	private Socket s;
	public MyServerThread(Socket s) {
		this.s = s;
	}
	public void run() {
		try {
			s.getInputStream().transferTo(s.getOutputStream());
			s.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}
}
