import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should
 * support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a queue, which means that only push to back,
 * peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively.
 * You may simulate a queue using a list or deque (double-ended queue) as long
 * as you use only a queue's standard operations.
 *
 * Example 1:
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 *
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 *
 *
 * Constraints:
 *
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, top, and empty.
 * All the calls to pop and top are valid.
 */
public class StackUsingQueue {
    public static void main(String[] args) {
        String[] commands = {"MyStack", "push", "push", "top", "pop", "empty"};
        String[] input = { null, "1", "2", null, null, null};
        //output : [null, null, null, 2, 2, false]
//        MyStack myStack = null;
        MyStack1 myStack = null;

        int n = commands.length;
        for (int i = 0; i < n; i++) {
            String cmd = commands[i];
            switch(cmd) {
                case "MyStack":
//                    myStack = new MyStack();
                    myStack = new MyStack1();
                    break;
                case "push":
                    if (input[i] != null) {
                        myStack.push(Integer.parseInt(input[i]));
                    }
                    break;
                case "top":
                    System.out.println(myStack.top());
                    break;
                case "pop":
                    System.out.println(myStack.pop());
                    break;
                case "empty":
                    System.out.println(myStack.empty());
                    break;
                default:
                    System.out.println("invalid input");
            }
        }
    }
}


// Solution using two queues
class MyStack {
    private final Queue<Integer> queue1;
    private final Queue<Integer> queue2;

    MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int n) {
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        queue1.offer(n);
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new NullPointerException("stack is empty");
        }
        return queue1.poll();
    }

    public int top() {
        if (queue1.isEmpty()) {
            throw new NullPointerException("stack is empty");
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

// Solution using one Queue
class MyStack1 {
    private final Queue<Integer> queue1;

    MyStack1() {
        queue1 = new LinkedList<>();
    }

    public void push(int n) {
        queue1.offer(n);
        for (int i = 0; i < queue1.size() - 1; i++) {
            queue1.offer(queue1.poll());
        }
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new NullPointerException("stack is empty");
        }
        return queue1.poll();
    }

    public int top() {
        if (queue1.isEmpty()) {
            throw new NullPointerException("stack is empty");
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
