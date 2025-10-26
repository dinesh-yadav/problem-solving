import java.util.PriorityQueue;

/**
 * Find Median From Data Stream
 * The median is the middle value in a sorted list of integers.
 * For lists of even length, there is no middle value, so the median is the mean of the two middle values.
 *
 * For example:
 *
 * For arr = [1,2,3], the median is 2.
 * For arr = [1,2], the median is (1 + 2) / 2 = 1.5
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Example 1:
 *
 * Input:
 * ["MedianFinder", "addNum", "1", "findMedian", "addNum", "3" "findMedian", "addNum", "2", "findMedian"]
 *
 * Output:
 * [null, null, 1.0, null, 2.0, null, 2.0]
 *
 * Explanation:
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.findMedian(); // return 1.0
 * medianFinder.addNum(3);    // arr = [1, 3]
 * medianFinder.findMedian(); // return 2.0
 * medianFinder.addNum(2);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * Constraints:
 *
 * -100,000 <= num <= 100,000
 * findMedian will only be called after adding at least one integer to the data structure.
 *
 * Time & Space Complexity
 * Time complexity:
 * O(m∗logn) for addNum(), O(m) for findMedian().
 * Space complexity:
 * O(n)
 * Where m is the number of function calls and n is the length of the array.
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        System.out.println(medianFinder.findMedian()); // return 1.0
        medianFinder.addNum(3);    // arr = [1, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
        medianFinder.addNum(2);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}

class MedianFinder {

    private PriorityQueue<Integer> largeHeap;
    private PriorityQueue<Integer> smallHeap;
    public MedianFinder() {
        largeHeap = new PriorityQueue<>(); // min heap
        smallHeap = new PriorityQueue<>((a, b) -> b - a); // max heap
    }

    public void addNum(int num) {
        smallHeap.add(num);
        if (smallHeap.size() - largeHeap.size() > 1 ||
                !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) {
            largeHeap.add(smallHeap.poll());
        }
        if (largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
    }

    public double findMedian() {
        if (smallHeap.size() == largeHeap.size()) {
            return (largeHeap.peek() + smallHeap.peek())*1.0 / 2;
        } else if (smallHeap.size() > largeHeap.size()) {
            return smallHeap.peek();
        } else {
            return largeHeap.peek();
        }
    }
}
