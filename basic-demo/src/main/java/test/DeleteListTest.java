package test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 996kid@gmail.com
 * @Description DeleteListTest
 * @Date 2022/8/8 14:36
 */
public class DeleteListTest {

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList();
        arr.add("1");
        arr.add("2");
        arr.add("3");
//        for (int i = 0; i < arr.size(); i++) {
//            arr.remove(i);
//        }
        Iterator<String> i = arr.iterator();
        while (i.hasNext()) {
            String item = i.next();
            i.remove();
        }

        System.out.println(arr);
    }
}
