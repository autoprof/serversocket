import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MyServerThread extends Thread {
	
	private ArrayList<SocketChannel> als; 	
	private HashMap<SocketChannel, ByteBuffer> buffers = new HashMap<SocketChannel, ByteBuffer>();
	
	public MyServerThread(ArrayList<SocketChannel> als) {
		this.als = als;
	}
	
	private void broadcast(ByteBuffer msg, SocketChannel s) throws IOException {
		System.out.println("Nombre de clients : " + als.size());
		for(int i = 0; i < als.size(); i++) {
			msg.rewind();
			if ( s != als.get(i) )
				als.get(i).write(msg);
		}
	}
	
	public void run() {
		SocketChannel s;
		ByteBuffer b;
		try {
			while(true) {
				System.out.println("Loop !");
				for (int i = 0; i < als.size(); i++) {
					s = als.get(i);
					b = buffers.get(s);
					if ( b == null ) {
						b = ByteBuffer.allocate(1024);
						buffers.put(s, b);
					}
					System.out.println("Reading...");
					int length = s.read(b);
					System.out.println("Read " + length + " bytes");
				}
				
				Iterator<SocketChannel> sockets = buffers.keySet().iterator();
				int i = 0;
				while(sockets.hasNext()) {
					i++;
					System.out.println("Send buffer : " + i);
					SocketChannel key = sockets.next();
					ByteBuffer msg = buffers.get(key);
					if (new String(msg.array()).contains("\n")) {
						broadcast(msg, key);
						//msg.clear();
						buffers.put(key, ByteBuffer.allocate(1024));
					}
				}
				java.lang.Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
