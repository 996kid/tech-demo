package netty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 996kid@gmail.com
 * @Description Client
 * @Date 2022/5/16 22:54
 */
public class Client {

    public static void main(String[] args) {
        Channel channel = null;
        try {
            channel = new Bootstrap()
                    .group(new NioEventLoopGroup(1))
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            System.out.println("init...");
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        }
                    })
                    .channel(NioSocketChannel.class).connect("localhost", 8080)
                    .sync()
                    .channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("333".getBytes()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("333".getBytes()));
    }
}
