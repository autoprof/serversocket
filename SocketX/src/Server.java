import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	static int running = 3;
	static ArrayList<Socket> sockets = new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(35000);
		
		while(--running > 0) 
			(new MyServerThread(serverSocket.accept())).start();

		serverSocket.close();
		System.out.println("done");
	}

}
