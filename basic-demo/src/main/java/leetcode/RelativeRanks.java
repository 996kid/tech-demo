package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author 996kid@gmail.com
 * @Description RelativeRanks
 * @Date 2022/8/18 14:33
 */
public class RelativeRanks {

    public static void main(String[] args) {
        int[] score = {5,4,3,2,1};
        System.out.println(Arrays.toString(findRelativeRanks(score)));
    }


    public static String[] findRelativeRanks(int[] score) {
        List<Integer> scoreList = new ArrayList();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int s : score) {
            scoreList.add(s);
        }
        scoreList = scoreList.stream().sorted(((o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            } else {
                return 0;
            }
        })).collect(Collectors.toList());


        for (int i = 0; i < scoreList.size(); i ++) {
            treeMap.put(scoreList.get(i), i + 1);
        }


        List<String> result = new ArrayList<>();
        for (int s : score) {
            int rank = treeMap.get(s);
            if ( rank <= 3) {
                switch (rank) {
                    case 1:
                        result.add("Gold Medal");
                        break;
                    case 2:
                        result.add("Silver Medal");
                        break;
                    case 3:
                        result.add("Bronze Medal");
                        break;
                }
            } else {
                result.add(rank + "");
            }
        }

        return result.toArray(new String[] {});
    }
}
