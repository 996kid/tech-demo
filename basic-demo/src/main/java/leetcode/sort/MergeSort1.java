package leetcode.sort;

/**
 * @Description MergeSort1
 * @Date 2020/9/4 17:09
 */
public class MergeSort1 {

        public static void main(String[] args) {
            int[] array = {1,2,31,4,54,9,12};
            mergeSort(array, 0, array.length - 1);
            print(array);
        }

        private static void mergeSort(int[] array, int left, int right) {
            if (left < right) {
                // 找出中间索引
                int mid = (left + right) / 2;
                // 对左边数组进行递归
                mergeSort(array, left, mid);
                // 对右边数组进行递归
                mergeSort(array, mid + 1, right);
                // 合并
                merge(array, left, mid, right);
            }
        }

        private static void merge(int[] array, int left, int mid, int right) {
            // 临时数组
            int[] temp = new int[right - left + 1];
            // 左指针
            int i = left;
            // 右指针
            int j = mid + 1;
            // 临时数组指针
            int k = 0;
            // 把较小的数先移到新数组中
            while (i <= mid && j <= right) {
                // 如果左边的数小于右边的数
                if (array[i] < array[j]) {
                    // 把左边的数放到临时数组中
                    temp[k++] = array[i++];
                } else {
                    // 把右边的数放到临时数组中
                    temp[k++] = array[j++];
                }
            }
            // 把左边剩余的数移入数组
            while (i <= mid) {
                temp[k++] = array[i++];
            }
            // 把右边剩余的数移入数组
            while (j <= right) {
                temp[k++] = array[j++];
            }
            // 把新数组中的数覆盖nums数组
            for (int k2 = 0; k2 < temp.length; k2++) {
                array[k2 + left] = temp[k2];
            }
        }

        private static void print(int[] array) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + "\t");
            }
        }
}
