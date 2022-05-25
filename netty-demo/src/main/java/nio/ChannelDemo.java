package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 996kid
 * @Description ChannelDemo
 * @Date 2021/1/25 13:26
 */
public class ChannelDemo {

    public static void main(String[] args) throws IOException {
//        fileChannel();
        channelTransfer();
    }

    private static void channelTransfer() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile(
                ChannelDemo.class.getClassLoader().getResource("data/nio-data.txt").getFile(), "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile(
                ChannelDemo.class.getClassLoader().getResource("data/toFile.txt").getFile(), "rw");
        FileChannel toChannel = toFile.getChannel();
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());

        for (long count = fromChannel.size() ;count>0 ;) {
            long transferred = fromChannel.transferTo(fromChannel.position(), count, toChannel);
            fromChannel.position(fromChannel.position() + transferred); count -= transferred;
        }
    }


    private static void fileChannel() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(
                ChannelDemo.class.getClassLoader().getResource("data/nio-data.txt").getFile(), "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            /** 写模式切换为读模式
             * limit = position;
             * position = 0;
             * mark = -1;
             * 设置读取的最大长度为之前写入的数据的长度
             * 重新将读写指针指向缓存区首部
             * buffer的3个属性：  capacity position limit
             * capacity: 容量固定值
             * position: 读写指针
             * limit: 写模式下limit等于capacity 表示最多能写入多少数据
             *        读模式下表示最多能读到多少数据 写模式切换到读模式时会设置成写模式下的position位置
             */
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
//            buf.compact();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
