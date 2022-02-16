import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
	
	static boolean running = true;
	static ArrayList<SocketChannel> sockets = new ArrayList<SocketChannel>();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocketChannel server = ServerSocketChannel.open();
		server.bind(new InetSocketAddress(35000));
		server.configureBlocking(false);
			
		new MyServerThread(sockets).start();
		
		while (true) {
			if ( !running)
				break;
			SocketChannel client = server.accept();
			if (client != null)
				sockets.add(client);
			java.lang.Thread.sleep(100);
		}
		
		/*
		ServerSocket server = channel.socket();
		server.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0").getHostName(), 35000));
		server.setReuseAddress(true);
		Selector selector = SelectorProvider.provider().openSelector();
		SelectionKey acceptKey = channel.register(selector, SelectionKey.OP_ACCEPT);
		//acceptKey.interestOps(SelectionKey.OP_ACCEPT);
		while(true) {
			selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
		}
		*/
	}

}

