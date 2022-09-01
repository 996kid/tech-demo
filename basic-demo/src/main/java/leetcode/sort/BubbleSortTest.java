package leetcode.sort;

/**
 * @author yyh 冒泡排序
 * @Description BubbleSortTest
 * @Date 2020/9/4 17:09
 */
public class BubbleSortTest {

    public static void main(String[] args) {
        int[] array = {1,2,31,4,54,9,12};
        bubbleSort(array);
        print(array);
    }

    /**  依次把最大 第二大 ... 第n大的元素放到上面
     * @param array
     */
    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
    }
}
