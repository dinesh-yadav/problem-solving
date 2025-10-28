import java.util.*;

/**
 * Course Schedule II
 * Solved
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * There are a total of numCourses courses you have to take,
 * labeled from 0 to numCourses - 1. You are given an array prerequisites
 * where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 */
public class CourseSchedule2 {
    public static void main(String[] args) {
        //int[][] prerequisites = { {0,1}};
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}, {3,2}};
        int numOfCourses = 4; //2;
        System.out.println(Arrays.toString(findOrder(prerequisites, numOfCourses)));
        System.out.println(Arrays.toString(findOrderDfs(prerequisites, numOfCourses)));
    }

    public static int[] findOrderDfs(int[][] prerequisites, int numOfCourses) {
        // here we can have a single array state with
        // three states 0:unvisited 1:visiting 2:visited
        int []visiting = new int[numOfCourses];
        int []visited = new int[numOfCourses];
        Arrays.fill(visited, -1);
        Arrays.fill(visiting, -1);
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int []pre: prerequisites) {
            // here we will have reverse edge so that
            // prereq will be processed first.
            // we can use correct edge but in the end reverse the order.
            adj.get(pre[0]).add(pre[1]);
        }

        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            if (!dfs(i, adj, visited, visiting, order)) {
                return new int[0];
            }
        }

        return order.stream()
                .mapToInt(Integer::intValue) // Maps the stream of Integer objects to an IntStream of primitives
                .toArray();                  // Collects the IntStream into a primitive int[]
    }

    public static boolean dfs(int course, List<List<Integer>> adj,
                              int[] visited, int[] visiting, List<Integer> order) {
        if (visiting[course] != -1) {
            return false;
        }

        if (visited[course] != -1) {
            return true;
        }

        visiting[course] = course;
        for (int crs: adj.get(course)) {
            if (!dfs(crs, adj, visited, visiting, order)) {
                return false;
            }
        }
        visiting[course] = -1;
        visited[course] = course;
        order.add(course);
        return true;
    }

    public static int[] findOrder(int[][] prerequisites, int numOfCourses) {
        int []indegree = new int[numOfCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] preq: prerequisites) {
            // pre[1] -> pre[0]
            indegree[preq[0]]++;
            adj.get(preq[1]).add(preq[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numOfCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] order = new int[numOfCourses];
        int count = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            order[count] = course;
            count++;
            for (int crs: adj.get(course)) {
                indegree[crs]--;
                if (indegree[crs] == 0) {
                    queue.add(crs);
                }
            }
        }

        return count == numOfCourses?order:new int[0];
    }
}
