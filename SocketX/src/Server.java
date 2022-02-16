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
	
	static int running = 3;
	static ArrayList<SocketChannel> sockets = new ArrayList<SocketChannel>();
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		
		while (true) {
			SocketChannel client = server.accept();
			client.configureBlocking(false);
			sockets.add(client);
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

