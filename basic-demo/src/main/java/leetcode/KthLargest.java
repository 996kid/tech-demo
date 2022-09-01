package leetcode;

import java.util.PriorityQueue;

class KthLargest {

    private PriorityQueue<Integer> priorityQueue;

    private int k;

    public static void main(String[] args) {

        /*PriorityQueue<Integer> pq = new PriorityQueue(1);
        pq.offer(1);
        pq.offer(2);
        System.out.println(pq.poll());*/
        KthLargest obj = new KthLargest(3, new int[] {4, 5, 8, 2});
        int param_1 = obj.add(10);
        System.out.println(param_1);
    }


    public KthLargest(int k, int[] nums) {
        this.k = k;
        priorityQueue = new PriorityQueue(k);
        for (int i : nums)
//            priorityQueue.offer(i);
            add(i);
    }
    
    public int add(int val) {
       if (priorityQueue.size() < k) {
           priorityQueue.offer(val);
       } else if (val > priorityQueue.peek()) {
           priorityQueue.poll();
           priorityQueue.offer(val);
       }
       return priorityQueue.peek();
    }
}