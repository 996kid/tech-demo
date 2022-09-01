package jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author 996kid@gmail.com
 * @Description MetaSpaceOOMTest
 * @Date 2022/3/4 21:24
 */
public class MetaSpaceOOMTest extends ClassLoader {

    public static void main(String[] args) {
        MetaSpaceOOMTest test = new MetaSpaceOOMTest();
        for (int i= 0; i < 1000; i++) {
            //
            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i,
                    null, "java/lang/Object", null);
            byte[] code = classWriter.toByteArray();
            test.defineClass("Class" + i, code, 0, code.length);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
