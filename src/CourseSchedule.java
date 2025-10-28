import java.util.*;

/**
 * Course Schedule
 * You are given an array prerequisites where prerequisites[i] = [a, b]
 * indicates that you must take course b first if you want to take course a.
 *
 * The pair [0, 1], indicates that must take course 1 before taking course 0.
 *
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 *
 * Return true if it is possible to finish all courses, otherwise return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[0,1]]
 *
 * Output: true
 * Explanation: First take course 1 (no prerequisites) and then take course 0.
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int[][] prerequisites = { {0,1}};
        int numOfCourses = 2;
        System.out.println(canFinish(prerequisites, numOfCourses));
        System.out.println(canFinishDfs(prerequisites, numOfCourses));
    }

    // indegree + bfs
    public static boolean canFinish(int[][] prerequisites, int numOfCourses) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] indegrees = new int[numOfCourses];
        for (int[] pre: prerequisites) {
            // pre[1] -> pre[0]
            indegrees[pre[0]]++;
            adjList.get(pre[1]).add(pre[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numOfCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int neigh: adjList.get(course)) {
                indegrees[neigh]--;
                if (indegrees[neigh] == 0) {
                    queue.add(neigh);
                }
            }
        }

        return count == numOfCourses;
    }

    // Detect cycle DFS
    public static boolean canFinishDfs(int[][] prerequisites, int numOfCourses) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] pre: prerequisites) {
            // pre[1] -> pre[0]
            adjList.get(pre[1]).add(pre[0]);
        }

        for(int i = 0; i < numOfCourses; i++) {
            if (!dfs(adjList, visited, visiting, i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(List<List<Integer>> adjList, Set<Integer> visited, Set<Integer> visiting, int course) {
        if (visiting.contains(course)) {
            return false;
        }

        if (visited.contains(course)) {
            return true;
        }

        visiting.add(course);
        for (int crs : adjList.get(course)) {
            if (!dfs(adjList, visited, visiting, crs)) {
                return false;
            }
        }
        visiting.remove(course);
        visited.add(course);
        return true;
    }
}
