package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description YangHui3jiaoGenerator2 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行
 * @date 2023/12/12
 */

public class YangHui3jiaoGenerator2 {

    public static void main(String[] args) {
        
    }

    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> r = new ArrayList<>(rowIndex + 1);
        for (int i = 1; i <= rowIndex + 1; i++) {
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
        return r.get(rowIndex);
    }
}
