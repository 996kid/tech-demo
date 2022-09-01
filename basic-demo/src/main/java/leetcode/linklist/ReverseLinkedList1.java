package leetcode.linklist;

import leetcode.common.ListNode;

import java.util.LinkedList;

/** 翻转链表
 * @author 996kid@gmail.com
 * @Description ReverseLinkedList
 * @Date 2022/7/16 20:10
 */
public class ReverseLinkedList1 {

    public static void main(String[] args) {
        LinkedList<ListNode> linkedList = new LinkedList();
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        print(head1);
        ListNode result = reverseList(head1);
        print(result);
    }

    private static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }
    }

        // 循环解法
        public static ListNode reverseList(ListNode head) {
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

    // 循环解法
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //递归解决
    public static ListNode reverseList3(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList3(head.next);
        head.next.next = head;
        // 结束条件
        head.next = null;
        return newHead;
    }

}
