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
	
	private void broadcast(ByteBuffer msg) throws IOException {
		for(int i = 0; i < als.size(); i++)
			als.get(i).write(msg);
	}
	
	public void run() {
		SocketChannel s;
		ByteBuffer b;
		try {
			while(true) {
				for (int i = 0; i < als.size(); i++) {
					s = als.get(i);
					b = buffers.get(s);
					if ( b == null ) {
						b = ByteBuffer.allocate(1024);
						buffers.put(s, b);
					}
					s.read(b);
				}
				
				Iterator<ByteBuffer> messages = buffers.values().iterator();
				while(messages.hasNext()) {
					ByteBuffer msg = messages.next();
					if (new String(msg.array()).contains("\n")) {
						broadcast(msg);
						msg.clear();
					}
				}
				java.lang.Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
