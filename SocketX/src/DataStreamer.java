import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamer {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("/tmp/cible2.txt");
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeChars("😁");
		// \xF0\x9F\x98\x81
		//dos.write(240);
		//dos.write(159);
		//dos.write(152);
		//dos.write(129);
		dos.close();
		System.out.println("OK");
	}

}

