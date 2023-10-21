import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int con = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                con++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                
                while(!queue.isEmpty()) {
                    int s = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (s != j && computers[s][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                }
                
            }
        }
        return con;
    }
}