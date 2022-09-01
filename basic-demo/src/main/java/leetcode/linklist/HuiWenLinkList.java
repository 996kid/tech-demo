package leetcode.linklist;

import leetcode.common.ListNode;

import java.util.*;

/** 链表  回文字符串
 * @author 996kid@gmail.com
 * @Description HuiWenLinkList
 * @Date 2022/7/19 20:33
 */
public class HuiWenLinkList {

    public static void main(String[] args) {
        LinkedList<ListNode> linkedList = new LinkedList();
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(1);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        System.out.println(isPalindrome2(head1));
    }

    // 数组解法
    public static boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode left = head;
        while (left != null) {
            list.add(left);
            left = left.next;
        }

        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            ListNode listNode1 = list.get(l);
            ListNode listNode2 = list.get(r);
            if (listNode1.val != listNode2.val) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }


    // 快慢指针
    public static boolean isPalindrome2(ListNode head) {
        // 1 2 3 2 1      1 2 3 3 2 1
        // 快指针移动到末尾时，慢指针正好移动到中间节点
        // 奇数,  正好在中间节点
        // 偶数,  中间两个的前一个节点
        // 翻转后半部分链表
        // 遍历两个链表判断是否一致, 当后半部分遍历完成后，z则是回文链表

        ListNode slow = head;
        ListNode fast = head;

        // 移动快慢指针 直到快指针不能再往右移动为止
        slow = moveSlowFast(slow, fast);
        // 翻转链表
        ListNode halfHead = reverseLink(slow.next);

        // 分别遍历
        while (halfHead != null) {
            if (halfHead.val != head.val) {
                return false;
            }
            halfHead = halfHead.next;
            head = head.next;
        }
        return true;
    }

    private static ListNode reverseLink(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        // 移动指针 将当前节点的next 指向前一个节点
        while (cur != null) {
            ListNode oldNext = cur.next;
            cur.next = pre;

            pre = cur;
            cur = oldNext;
        }
        return pre;
    }

    private static ListNode moveSlowFast(ListNode slow, ListNode fast) {
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    // 双向链表实现
    public static boolean isPalindrome1(ListNode head) {
        // 将指针指向末尾节点
        ListNode left = head;
        ListNode right = head;
        while (right.next != null) {
            right = right.next;
        }

        // 同时移动左右节点
        // 怎么判断指针相遇
        // 左节点移动一位 将当前节点存入HashSet 右指针不存在集合中时 则未相遇
        Set<ListNode> set = new HashSet<>();
        while (!set.contains(right)) {
            left = left.next;
            // todo 双向链表
        }
        return false;
    }

}
