/**
 * Swap Linked List Nodes In Pairs
 * Given a singly linked list, reorder its nodes such that pairs of consecutive nodes are interchanged.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:  'a' -> 'b' -> 'c' -> 'd' -> 'e' -> 'f' -> X
 * Output: 'b' -> 'a' -> 'd' -> 'c' -> 'f' -> 'e' -> X
 */
public class SwapLinkedListInPairs {
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
        ListNode node = swapLinkedListInPairs(head);
        node.printList();
    }

    public static ListNode swapLinkedListInPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode first = head;
        ListNode second = first.next;
        head = head.next;
        while (true) {
            prev = second.next;
            second.next = first;
            if (prev == null || prev.next == null) {
                first.next = prev;
                return head;
            }

            first.next = prev.next;

            first = prev;
            second = prev.next;
        }
    }
}
