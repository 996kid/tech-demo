package leetcode.array;

import java.util.*;

public class FindRestaurant {

    /**
     * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     *
     * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
     * 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
     * @param args
     */
    public static void main(String[] args) {
        String[] s1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] s2 = {"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"};
        Arrays.stream(findRestaurant(s1, s2)).forEach(System.out::println);
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        /**
         * 问题分解:
         *  1. 遍历两个列表找到相同的餐厅, 记录index
         *  2. 找到最小的index
         */
        int minIndex = list2.length;
        Map<Integer, List<String>> map = new HashMap<>();
        for (String s1 : list1) {
            for (int i = 0; i < list2.length; i++) {
                if (list2[i].equals(s1)) {
                    minIndex = Math.min(minIndex, i);
                    if (map.containsKey(minIndex)) {
                        map.get(minIndex).add(list2[i]);
                    } else {
                        map.put(minIndex, new ArrayList<>());
                    }
                }
            }
        }
        return map.get(minIndex).toArray(new String[0]);
    }
}
