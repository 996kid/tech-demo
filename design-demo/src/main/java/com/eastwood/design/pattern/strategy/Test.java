package com.eastwood.design.pattern.strategy;

import org.redisson.misc.Hash;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Test
 * @Description: 策略模式实战:
 *               假设有这样一个需求，希望写一个小程序，实现对一个文件进行排序的功能。
 *               文件中只包含整型数，并且，相邻的数字通过逗号来区隔
 * @Author: yyh
 * @Date: 2023/4/19 10:41
 */
public class Test {

    private static final Map<String, ISortAlg> sortAlgs = new HashMap<>();

    static {
        sortAlgs.put("quickSort", new QuickSort());
        // ...
    }

    interface ISortAlg {
        void sort(String filePath);
    }

    static class QuickSort implements ISortAlg {
        @Override
        public void sort(String filePath) {
            //...
            System.out.println("QuickSort");
        }
    }

    class ExternalSort implements ISortAlg {
        @Override
        public void sort(String filePath) {
            //...
            System.out.println("ExternalSort");
        }
    }

    class ConcurrentExternalSort implements ISortAlg {
        @Override
        public void sort(String filePath) {
            //...
            System.out.println("ConcurrentExternalSort");
        }
    }

    class MapReduceSort implements ISortAlg {
        @Override
        public void sort(String filePath) {
            //...
            System.out.println("MapReduceSort");
        }
    }

    public static void main(String[] args) {
        File file = new File("/..");
        long fileSize = file.length();
        ISortAlg sortAlg;
        // 根据文件大小选择合适的排序算法

    }
}
