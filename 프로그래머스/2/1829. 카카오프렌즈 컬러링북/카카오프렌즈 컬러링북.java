import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[] {0, 0};
        boolean[][] visited = new boolean[m][n];
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, -1, 0, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    visited[i][j] = true;
                    int area = picture[i][j];
                    int size = 1;
                    Queue<Integer[]> queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});
                    while (!queue.isEmpty()) {
                        Integer[] from = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && picture[nx][ny] == area && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                queue.offer(new Integer[] {nx, ny});
                                size++;
                            }
                        }
                    }
                    answer[0]++;
                    if (answer[1] < size) answer[1] = size;
                }
            }
        }
        return answer;
    }
}