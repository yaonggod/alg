import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int minLen = 10001;
        int rows = maps.length;
        int cols = maps[0].length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited = new boolean[rows][cols];
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {0, 0, 1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Integer[] current = queue.poll();
            if (current[0] == rows - 1 && current[1] == cols - 1) {
                minLen = current[2];
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && maps[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Integer[] {nx, ny, current[2] + 1});
                }
            } 
        }
        
        if (minLen == 10001) {
            return -1;
        } return minLen;
        
        
        
    }
    
    
    
}