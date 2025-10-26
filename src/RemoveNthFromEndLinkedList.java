/**
 * Remove Node From End of Linked List
 * Solved
 * You are given the beginning of a linked list head, and an integer n.
 *
 * Remove the nth node from the end of the list and return the beginning of the list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4], n = 2
 *
 * Output: [1,2,4]
 * Example 2:
 *
 * Input: head = [5], n = 1
 *
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 2
 *
 * Output: [2]
 */
public class RemoveNthFromEndLinkedList {
    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        head.printList();
        int n = 2;
        removeNthFromEnd(head, n).printList();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        int i = 0;
        while (first != null) {
            first = first.next;
            if (i > n) {
                second = second.next;
            }
            i++;
        }
        second.next = second.next.next;

        return dummy.next;
    }
}
