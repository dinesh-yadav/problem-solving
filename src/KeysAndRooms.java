import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Keys & Rooms
 *
 * We are given a 2D array arr where each element (item arr(i)) represents a room.
 * Each "room" contains a set of "keys" that unlock other rooms in arr.
 *
 * You cannot open a room unless you have a key to it.
 * By default, room 0 will be unlocked for you to enter at first.
 *
 * Given arr, return true if all rooms can be opened, otherwise return false.
 *
 * Input-Output
 *
 * Example 1
 *
 * Input: [[1], [2], [3], []]
 * Output: true
 * Explanation: Room 0 has the key to room 1. Room 1 has the key to room 2.
 * Room 2 has the key to room 3. All rooms have been opened.
 * Example 2
 *
 * Input: [[], [2], [3], []]
 * Output: false
 * Explanation: Room 0 has no keys and all other rooms are locked.
 * Constraints
 *
 * 0 <= arr[i][j] <= len(arr) - 1 (all indexes will be inbounds)
 */
public class KeysAndRooms {
    public static void main(String[] args) {
        int[][] rooms = {{1}, {2}, {3}, {}};
        //int[][] rooms = {{}, {2}, {3}, {}};
        System.out.println(canAllRoomsBeOpened(rooms));
    }

    public static boolean canAllRoomsBeOpened(int[][] rooms) {
        if (rooms == null || rooms.length == 1)
            return true;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> unlocked = new HashSet<>();
        int numberOfRooms = rooms.length;
        queue.add(0);
        unlocked.add(0);

        while(!queue.isEmpty()) {
            int[] currRoomKeys = rooms[queue.poll()];
            for (int key: currRoomKeys) {
                if (!unlocked.contains(key)) {
                    unlocked.add(key);
                    queue.add(key);

                    if (unlocked.size() == numberOfRooms) {
                        return true;
                    }
                }
            }

        }

        return numberOfRooms == unlocked.size();
    }
}
