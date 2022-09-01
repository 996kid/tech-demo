package leetcode;

import leetcode.common.ListNode;

import java.util.LinkedList;

/** 两两交换链表中的节点
 * @author 996kid@gmail.com
 * @Description SwapPairs
 * @Date 2022/7/17 13:38
 */
public class SwapPairs {


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
        print(swapPairs2(head1));
    }

    private static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }
    }

    // 循环
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        // 将头指针指向第二个节点
        ListNode newHead = head.next;

        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            // 保存后一次交换操作的当前节点
            ListNode p = next.next;
            if (next.next != null && next.next.next != null) {
                // 如果后面的一对存在 将后一个节点的next.next 赋值给前一个节点的next
                cur.next = next.next.next;
            } else {
                // 不存在 没有交换操作 直接指向next.next
                cur.next = next.next;
            }
            // 将后一个节点的next 指向前一个节点
            next.next = cur;

            // 移动指针到后一次交换的两个节点的前一个节点
            cur = p;
        }
        return newHead;
    }

    //递归解法
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }

}
