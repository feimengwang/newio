package cn.true123.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FastCopyFile {

	public static void main(String[] args) {
		FileInputStream fileInputStream;
		FileOutputStream fileOutputStream;
		try {
			fileInputStream = new FileInputStream("src/testReadFile.txt");
			fileOutputStream = new FileOutputStream("src/CopyTestReadFile.txt");
			FileChannel fileReadChannel = fileInputStream.getChannel();
			FileChannel fileWriteChannel = fileOutputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) {
				buffer.clear();
				int i = fileReadChannel.read(buffer);
				if (i == -1) {
					break;
				}
				buffer.flip();
				fileWriteChannel.write(buffer);

			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
