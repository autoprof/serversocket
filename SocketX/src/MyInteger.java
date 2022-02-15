import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyInteger implements Serializable {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		putObj();
		getObj();
		System.out.println("OK");
	}
	
	private static void getObj() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/myint"));
		MyInteger myInteger = (MyInteger) ois.readObject();
		ois.close();
	}
	
	private static void putObj() throws IOException {
		MyInteger myInteger = new MyInteger(65);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/myint"));
		oos.writeObject(myInteger);
		oos.close();
	}
	
	private static final long serialVersionUID = 2L;
	
	private int x;
	
	@SuppressWarnings("unused")
	private MyInteger() {
	}
	
	public MyInteger(int x)  {
		this.x = x;
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		x = in.readInt();
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(x);
		//out.write
	}
	
}
