/**
 * 86. Partition List
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
public class PartitionList {
    public static void main(String[] args) {
        // head = [1,4,3,2,5,2], x = 3
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        head.printList();
        int x = 3;
        ListNode node = partitionList(head, x);
        node.printList();

        //Input: head = [2,1], x = 2
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(1);
        head1.printList();
        partitionList(head1, 2).printList();
    }

    static ListNode partitionList(ListNode head, int x) {
        ListNode sList = new ListNode(-1);
        ListNode bList = new ListNode(-1);
        ListNode small = sList;
        ListNode big = bList;

        while(head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        small.next = bList.next;
        big.next = null;
        return sList.next;
    }


}
