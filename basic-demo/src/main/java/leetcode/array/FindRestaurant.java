package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/18
 */
public class FindRestaurant {

    /**
     * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
     *
     * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
     *
     * 示例 1:
     *
     * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     * 输出: ["Shogun"]
     * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
     * 示例 2:
     *
     * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
     * 输出: ["Shogun"]
     * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * list1 =
     * ["Shogun","Tapioca Express","Burger King","KFC"]
     * list2 =
     * ["KFC","Shogun","Burger King"]
     * 输出
     * ["Shogun","Burger King","KFC"]
     * 预期结果
     * ["Shogun"]
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<Integer, List<String>> map = new HashMap<>();
        int minIndex = list1.length + list2.length - 2;
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    // 一致则计算索引和
                    if (i+j <= minIndex) {
                        minIndex = Math.min(i+j, minIndex);
                        if (map.containsKey(minIndex)) {
                            map.get(minIndex).add(list1[i]);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(list1[i]);
                            map.put(minIndex, list);
                        }
                    }
                }
            }
        }
        return map.get(minIndex).toArray(new String[0]);
    }
}
