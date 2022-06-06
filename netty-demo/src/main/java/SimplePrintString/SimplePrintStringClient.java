package SimplePrintString;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author 996kid@gmail.com
 * @Description SimplePrintStringClient
 * @Date 2022/5/26 23:51
 */
public class SimplePrintStringClient {

    public static void main(String[] args) {
        try {
            new Bootstrap()
                    .group(new NioEventLoopGroup(1))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    })
                    .connect("127.0.0.1", 8080)
                    .sync()
                    .channel().writeAndFlush(new Date() + ": hello world!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
