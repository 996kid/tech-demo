package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description YangHui3jiaoGenerator   杨辉三角生成
 * @date 2023/12/12
 */

public class YangHui3jiaoGenerator {

    public static void main(String[] args) {
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> r = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            if (i == 1) {
                List<Integer> list = new ArrayList<>(1);
                list.add(1);
                r.add(list);
            } else if (i == 2) {
                List<Integer> list = new ArrayList<>(2);
                list.add(1);
                list.add(1);
                r.add(list);
            } else {
                List<Integer> list = new ArrayList<>(i);
                list.add(1);
                for (int j = 1; j <= i - 2; j++) {
                    list.add(r.get(i - 2).get(j-1) + r.get(i - 2).get(j));
                }
                list.add(1);
                r.add(list);
            }
        }
        return r;
    }
}
