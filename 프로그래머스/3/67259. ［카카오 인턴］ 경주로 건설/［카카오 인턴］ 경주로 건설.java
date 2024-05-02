import java.util.*;

class Solution {
    // 우 하 좌 상
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    public int solution(int[][] board) {
        int n = board.length;
        
        int[][][] cost = new int[n][n][4];
        
        // board[i][j]를 d방향으로 들어온 흔적이 있다
        Queue<Integer[]> queue = new LinkedList<>();
        if (board[0][1] == 0) {
            queue.offer(new Integer[] {0, 1, 2, 100});
            cost[0][1][2] = 100;
        }
        if (board[1][0] == 0) {
            queue.offer(new Integer[] {1, 0, 3, 100});
            cost[1][0][3] = 100;
        }
        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Integer[] info = queue.poll();
            int x = info[0];
            int y = info[1];
            int prevd = info[2];
            int currCost = info[3];
            if (x == n - 1 && y == n - 1) {
                minCost = Math.min(minCost, cost[x][y][prevd]);
            } else {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx == 0 && ny == 0) continue;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;
                    
                    // 되돌아가는거
                    if (prevd == d) continue;
                    
                    // 직선
                    if (Math.abs(prevd - d) == 2) {
                        int newCost = currCost + 100;
                        // 받아들이는 위치에서 cost가 갱신될까
                        if (cost[nx][ny][prevd] == 0 || cost[nx][ny][prevd] > newCost) {
                            cost[nx][ny][prevd] = newCost;
                            queue.offer(new Integer[] {nx, ny, prevd, newCost});
                        }
                    } else {
                        int newCost = currCost + 600;
                        int newd = d - 2 < 0 ? d + 2 : d - 2;
                        if (cost[nx][ny][newd] == 0 || cost[nx][ny][newd] > newCost) {
                            cost[nx][ny][newd] = newCost;
                            queue.offer(new Integer[] {nx, ny, newd, newCost});
                        }
                    }
                }
            }
        }
        
        
        return minCost;
    }
}