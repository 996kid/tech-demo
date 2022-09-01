package leetcode.linklist;

import leetcode.common.ListNode;

/** 有序链表合并
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的
 * @author 996kid@gmail.com
 * @Description MergeList
 * @Date 2022/7/21 15:56
 */
public class MergeList {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 哨兵节点
        ListNode prehead = new ListNode(-1);
        ListNode head = prehead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prehead.next = list1;
                list1 = list1.next;
            } else {
                prehead.next = list2;
                list2 = list2.next;
            }
            prehead = prehead.next;
        }
        if (list1 == null) {
            prehead.next = list2;
        } else {
            prehead.next = list1;
        }
        return head.next;
    }
}
