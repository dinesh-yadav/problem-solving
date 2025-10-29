import java.util.HashMap;
import java.util.Map;

class DllNode {
    private int key;
    private int value;
    private DllNode prev;
    private DllNode next;

    public DllNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DllNode getPrev() {
        return prev;
    }

    public void setPrev(DllNode prev) {
        this.prev = prev;
    }

    public DllNode getNext() {
        return next;
    }

    public void setNext(DllNode next) {
        this.next = next;
    }
}

class LRUCache {
    private int capacity;
    private DllNode head;
    private DllNode tail;
    private Map<Integer, DllNode> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DllNode(-1, -1);
        tail = new DllNode(-1, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    private void remove(DllNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    private void add(DllNode node) {
        node.setNext(tail);
        node.setPrev(tail.getPrev());
        tail.getPrev().setNext(node);
        tail.setPrev(node);
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DllNode node = cache.get(key);
            remove(node);
            add(node);
            return node.getValue();
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
            cache.remove(key);
        }
        DllNode node = new DllNode(key, value);
        cache.put(key, node);
        add(node);

        if (cache.size() > capacity) {
            DllNode lru = head.getNext();
            remove(lru);
            cache.remove(lru.getKey());
        }
    }
}

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        //["LRUCache", [2], "put", [1, 10],  "get", [1], "put", [2, 20], "put", [3, 30], "get", [2], "get", [1]]
        lruCache.put(1, 10);
        System.out.println(lruCache.get(1));
        lruCache.put(2, 20);
        lruCache.put(3, 30);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));
    }
}
