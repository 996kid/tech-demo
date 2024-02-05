package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/5
 */
public class FindPoisonedDuration {

    // timeSeries = [1,2], duration = 2
    public static void main(String[] args) {
    
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int total = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (i == timeSeries.length - 1 || timeSeries[i + 1] - timeSeries[i] > duration) {
                total += duration;
            } else {
                total += timeSeries[i + 1] - timeSeries[i];
            }
        }
        return total;
    }
}
