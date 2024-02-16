import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        int[] visited = new int[n + 1];
        visited[1] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (int to : graph[from]) {
                if (visited[to] == 0) {
                    visited[to] = visited[from] + 1;
                    queue.offer(to);
                }
            }
        }
        int maxLen = 0;
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (visited[i] > maxLen) maxLen = visited[i];
        }
        for (int i = 2; i <= n; i++) {
            if (visited[i] == maxLen) count++;
        }
        return count;
    }
}