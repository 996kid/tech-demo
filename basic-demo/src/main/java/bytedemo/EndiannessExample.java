package bytedemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndiannessExample {
    public static void main(String[] args) {
        int value = 0x12345678;

        // 使用 ByteBuffer 将 int 转换为字节数组
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(value);

        // 大端序
        buffer = buffer.order(ByteOrder.BIG_ENDIAN);
        byte[] bigEndianBytes = buffer.array();
        System.out.print("Big Endian: ");
        printBytes(bigEndianBytes);

        // 小端序
        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);
        byte[] littleEndianBytes = buffer.array();
        System.out.print("Little Endian: ");
        printBytes(littleEndianBytes);
    }

    private static void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(String.format("%02X ", b));
        }
        System.out.println();
    }
}
