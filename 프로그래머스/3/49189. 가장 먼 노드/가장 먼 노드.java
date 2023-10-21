import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]][edge[i][1]] = true;
            graph[edge[i][1]][edge[i][0]] = true;
        }
        int[] visited = new int[n + 1];
        visited[1] = 1;
        int maxLen = 0;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (graph[s][i] == true && visited[i] == 0) {
                    visited[i] = visited[s] + 1;
                    queue.offer(i);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (visited[i] > maxLen) {
                maxLen = visited[i];
            }
        }
        for (int i = 0; i <= n; i++) {
            if (visited[i] == maxLen) {
                count++;
            }
        }
        return count;
    }
}