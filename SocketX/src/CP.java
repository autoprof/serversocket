import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CP {

	public static void main(String[] args) throws IOException {
		buffMan();
		System.out.println("Fini 😁 !");
	}
	
	public static void buffMan() throws IOException {
		
		FileInputStream fin = new FileInputStream("/tmp/file1");
		FileOutputStream fout = new FileOutputStream("/tmp/file2");
		
		int bufferSize = 1024 * 1024 * 10;
		byte[] buffer = new byte[bufferSize];
		while ( fin.available() > 0 ) {
			fin.read(buffer);
			fout.write(buffer);
		}
		
		fin.close();
		fout.close();
	}
	
	public static void buffAuto() throws IOException {
		int bufferSize = 10240;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/tmp/file1"), bufferSize);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/tmp/file2"), bufferSize);
		bis.transferTo(bos);
		bis.close();
		bos.close();
	}

}
