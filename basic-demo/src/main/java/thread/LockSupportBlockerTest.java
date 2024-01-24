package thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportBlockerTest {

    public static void main(String[] args) {
        System.out.println("main begin");
        LockSupportBlockerTest test = new LockSupportBlockerTest();
        test.testPark();
    }

    void testPark() {
        System.out.println("testPark begin");
        LockSupport.park();
        System.out.println("testPark end");
    }
}
