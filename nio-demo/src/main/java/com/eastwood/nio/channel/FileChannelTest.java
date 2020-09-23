package com.eastwood.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileChannelTest
 * @Description FileChannelTest
 * @Author 996kid
 * @Date 2020/4/26 9:11
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:/IDEA/github.com/tech-demo/nio-demo/src/main/resources/data/nio-data.txt", "rw");
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
             */
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
