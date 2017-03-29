package cn.true123.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MappedBuffer {

	public static void main(String[] args) {
		//FileInputStream fileInputStream;
		try {
			
			RandomAccessFile randomAccessFile = new RandomAccessFile("src/testReadFile.txt", "rw");
			//fileInputStream = new FileInputStream(randomAccessFile);
			FileChannel fileChannel = randomAccessFile.getChannel();
			MappedByteBuffer mappedByteBuffer=fileChannel.map(MapMode.READ_WRITE, 0, 1024);
			mappedByteBuffer.put(0, (byte)97);
			mappedByteBuffer.put(495, (byte)98);
			randomAccessFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
