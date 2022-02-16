import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;

public class MyServerThread extends Thread {
	private ArrayList<SocketChannel> clients = new ArrayList<SocketChannel>();
	private ArrayList<SelectionKey> reads = new ArrayList<SelectionKey>();
	private ArrayList<SelectionKey> writes = new ArrayList<SelectionKey>();
	private Selector selector;
	
	public MyServerThread() throws IOException {
		selector = SelectorProvider.provider().openSelector();
	}
	
	public void addClient(SocketChannel sc) throws IOException {
		SelectionKey readKey = sc.register(selector, SelectionKey.OP_READ);
		SelectionKey writeKey = sc.register(selector, SelectionKey.OP_WRITE);
		reads.add(readKey);
		writes.add(writeKey);
//		clients.add(sc);
	}
	
	public void run() {
		try {
			
			while(true) {
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();
					if (!key.isValid()) {
	                    continue;
	                }
					key.channel().
				}
			}
			
			/*
			ServerSocket server = channel.socket();
			server.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0").getHostName(), 35000));
			server.setReuseAddress(true);
			
			SelectionKey acceptKey = channel.register(selector, SelectionKey.OP_ACCEPT);
			//acceptKey.interestOps(SelectionKey.OP_ACCEPT);
			while(true) {
				selector.select();
	            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			}
			*/
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}
}
