/**
 * Linked List Weave
 * Given a singly linked list with nodes L₀ → L₁ → L₂ → L₃ → … → Lₙ₋₁,
 * return the list reordered into form L₀ → Lₙ₋₁ → L₁ → Lₙ₋₂ → L₂ → Lₙ₋₃ → … and so on.
 *
 * Note: You must rewire node .next references only - mutation of node values is not permitted.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> x
 * Output: 1 -> 7 -> 2 -> 6 -> 3 -> 5 -> 4 -> x
 */
public class LinkedListWeave {
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
        ListNode node = linkedListWeave(head);
        node.printList();
    }

    public static ListNode linkedListWeave(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode saveHead = head;
        ListNode mid = getMidElement(head);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode list2 = reverse(midNext);
        list2.printList();
        head.printList();
        while (list2 != null) {
            ListNode next1 = head.next;
            ListNode next2 = list2.next;
            head.next = list2;
            list2.next = next1;
            head = next1;
            list2 = next2;
        }
        return saveHead;

    }

    public static ListNode getMidElement(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}
