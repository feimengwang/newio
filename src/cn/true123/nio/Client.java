package cn.true123.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
	private static Integer[] port = { 12012, 12013, 12014, 12015 };

	public static void main(String[] args) {
		new Client().sendAndReceiveMessage();

	}

	private void sendAndReceiveMessage() {
		try {
			Selector selector = Selector.open();

			System.out.println("port=" + port[0]);
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", port[1]));
			socketChannel.register(selector, SelectionKey.OP_CONNECT);

			while (true) {
				int num = selector.select();
				Set<SelectionKey> set = selector.selectedKeys();

				for (SelectionKey selectionKey : set) {
					// SelectionKey selectionKey = iterable.next();
					set.remove(selectionKey);
					if (selectionKey.isConnectable()) {
						if (((SocketChannel) selectionKey.channel()).finishConnect()) {
							ByteBuffer byteBuffer = ByteBuffer.wrap("hello world".getBytes());
							// byteBuffer.flip();
							((SocketChannel) selectionKey.channel()).write(byteBuffer);

							selectionKey.interestOps(SelectionKey.OP_READ);
						}

					}
					if (selectionKey.isReadable()) {
						SocketChannel ssc = (SocketChannel) selectionKey.channel();
						ssc.configureBlocking(false);
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

						int n = ssc.read(byteBuffer);

						byte[] data = byteBuffer.array();
						System.out.println("Message from Server is: "+new String(data));

						// ssc.register(selector, SelectionKey.OP_WRITE);

						System.out.println("Got connection from " + ssc);
					}

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
