import java.util.List;

/**
 * Right Shift a Singly Linked List
 * Given the head of a singly linked list, rotate the list k steps to the right.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input:
 * k = 2
 * 1 -> 2 -> 3 -> 4 -> 5 -> X
 * Output: 4 -> 5 -> 1 -> 2 -> 3 -> X
 */
public class RightShiftLinkedList {
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

        int k = 2;
        head.printList();
//        ListNode node = rightShiftLinkedList(head, k);
//        node.printList();
        ListNode node = rightShiftLinkedListCorrect(head, 9);
        node.printList();
    }

    public static ListNode rightShiftLinkedListCorrect(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        int length = 1;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        k = k%length;

        // add head into the tail
        curr.next = head;

        // break the loop
        for (int i = 1; i < length - k; i++) {
            head = head.next;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }

    // if k is less than the length of the linkedList.
    public static ListNode rightShiftLinkedList(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
       ListNode breakNode = head;
       ListNode curr = head;
       int count = 0;
       while (curr.next != null) {
           curr = curr.next;
           count++;
           if (count > k) {
                breakNode = breakNode.next;
           }
       }

       ListNode newHead = breakNode.next;
       breakNode.next = null;
       curr.next = head;

       return newHead;
    }
}
