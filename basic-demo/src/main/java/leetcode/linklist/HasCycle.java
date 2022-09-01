package leetcode.linklist;

import leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/** 环形链表1 判断链表中是否有环
 * @author 996kid@gmail.com
 * @Description HasCycle
 * @Date 2022/7/17 14:53
 */
public class HasCycle {

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }

    // 快慢指针  Floyd 判圈算法 迟早套圈
    public static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
