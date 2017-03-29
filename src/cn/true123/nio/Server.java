package cn.true123.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
	private static Integer[] port = { 12012, 12013, 12014, 12015 };
	ByteBuffer buffer = ByteBuffer.allocate(1024);

	public Server() {
		init();
	}

	private void init() {
		try {
			Selector selector = Selector.open();
			for (int i = 0; i < port.length; i++) {
				System.out.println("port=" + port[i]);
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				serverSocketChannel.configureBlocking(false);
				ServerSocket serverSocket = serverSocketChannel.socket();
				serverSocket.bind(new InetSocketAddress(port[i]));
				SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				System.out.println("selectionKey.readyOps()=" + selectionKey.readyOps());
			}

			while (true) {
				System.out.println("Waiting connection!");
				int num = selector.select();
				System.out.println("num=" + num);
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					System.out.println(key.readyOps());
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
						ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
						SocketChannel sc = ssc.accept();
						sc.configureBlocking(false);
						ByteBuffer byteBuffer = ByteBuffer.wrap("Connect to the server".getBytes());
						sc.write(byteBuffer);
						sc.register(selector, SelectionKey.OP_READ);
						System.out.println("Got connection from " + sc);
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						SocketChannel sc = (SocketChannel) key.channel();
						buffer.clear();
						int n = sc.read(buffer);

						byte[] data = buffer.array();
						System.out.println("Message from Client is: " + new String(data));

						System.out.println();
						sc.register(selector, SelectionKey.OP_WRITE);
						// iterator.remove();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server();

	}

}
