package sort;

/**
 * @author yyh 冒泡排序
 * @Description BubbleSortTest
 * @Date 2020/9/4 17:09
 */
public class BubbleSortTest {

    public static void main(String[] args) {
        int[] array = {1,2,31,4,54,9,12};
        bubbleSort(array);
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j ++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
