package cn.true123.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

	public static void main(String[] args) {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("src/testReadFile.txt");
			FileChannel fileChannel = fileInputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			fileChannel.read(buffer);
			buffer.flip();
			 int i=0;
			    while (buffer.remaining()>0) {
			      byte b = buffer.get();
			      System.out.println( "Character "+i+": "+((char)b) );
			      i++;
			    }

			    fileInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
