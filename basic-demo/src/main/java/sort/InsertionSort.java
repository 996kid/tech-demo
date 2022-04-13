package sort;

/**
 * 插入排序demo
 */
public class InsertionSort {

    public static void main( String args[] ) {
        int [] arr = {5,2,12,12,1};
        sortInsertion(arr);
        print(arr);
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * 1. 算法步骤
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     *
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     * @param sort_arr
     */
    public static void sortInsertion(int [] sort_arr){
        for (int i = 0;i < sort_arr.length; i++) {
            int j = i;
            while (j > 0 && sort_arr[j-1] > sort_arr[j]) {
                int key = sort_arr[j];
                sort_arr[j] = sort_arr[j-1];
                sort_arr[j-1] = key;
                j = j-1;
            }
        }
    }

    public static void sortInsertion1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int j = i - 1;
            while (j > 0 && num < nums[j]) {
                //交换位置

                j--;
            }
        }
    }
}