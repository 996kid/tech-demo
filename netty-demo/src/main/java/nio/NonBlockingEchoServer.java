package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 996kid NIO.1
 * @Description NonBlockingEchoServer
 * @Date 2021/1/23 11:14
 */
public class NonBlockingEchoServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel;
        Selector selector;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress socketAddress = new InetSocketAddress(7000);
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.printf("NonBlockingEchoServer has been started. Listening at %d.", 7000);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                System.out.println("NonBlockingEchoServer异常!" + e.getMessage());
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    // 可连接
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = channel.accept();

                        System.out.println("NonBlokingEchoServer接受客户端的连接：" + socketChannel);

                        // 设置为非阻塞
                        socketChannel.configureBlocking(false);

                        // 客户端注册到Selector
                        SelectionKey clientKey = socketChannel.register(selector,
                                SelectionKey.OP_WRITE | SelectionKey.OP_READ);

                        // 分配缓存区
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);
                    }

                    // 可读
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        client.read(output);

                        System.out.println(client.getRemoteAddress()
                                + " -> NonBlokingEchoServer：" + output.toString());

                        key.interestOps(SelectionKey.OP_WRITE);
                    }

                    // 可写
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        client.write(output);

                        System.out.println("NonBlokingEchoServer  -> "
                                + client.getRemoteAddress() + "：" + output.toString());

                        output.compact();

                        key.interestOps(SelectionKey.OP_READ);
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        System.out.println(
                                "NonBlockingEchoServer异常!" + cex.getMessage());
                    }
                }
            }
        }
    }
}
