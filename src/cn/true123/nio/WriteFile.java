package cn.true123.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteFile {
	static byte[] message="This is test message.".getBytes();
	public static void main(String[] args) {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream( "src/writesomebytes.txt" );
			FileChannel fc = fout.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			for(int i=0;i<message.length;i++){
				buffer.put(message[i]);
			}
			buffer.flip();
			
			fc.write(buffer);
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
