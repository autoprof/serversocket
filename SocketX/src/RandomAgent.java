import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class RandomAgent {
	
	private static String[] moves = {"move north\n", "move south\n", "move east\n", "move west\n"};
	
	private static int decision() {
		return 0;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// 1ere etape : connection au localhost sur le port 62342
		Socket socket = new Socket("localhost", 62342);
		byte[] buff = new byte[1024];
		
		// 2eme etape : lire le message bonjour ( pour recuperer le symbole du joueur )
		socket.getInputStream().read(buff);
		System.out.println(new String(buff, StandardCharsets.UTF_8));
		
		OutputStream os = socket.getOutputStream();
		
		for(int i = 0; i < 10; i++) {
			// 3eme etape : envoyer une commande de deplacement (move north, move south, move east, move west)
			os.write(moves[(int) Math.round(Math.random() * 3)].getBytes());	
			// 4eme etape : "lire" ( se debarrasser ) la reponse du serveur contenant le contexte
			socket.getInputStream().read(buff);
			// 5eme etape : marquer un temps d'arret
			//java.lang.Thread.currentThread().sleep(100);
			// 6eme etape : aller à l'etape 3
		}
		System.out.println("Fin");
	}

}
