import java.util.*;

class Solution {
    static char[][] b;
    static int h, w;
    static int[] dx, dy;
    
    public int solution(String[] board) {
        h = board.length;
        w = board[0].length();
        int startx = 0;
        int starty = 0;
        int endx = 0;
        int endy = 0;
        b = new char[h][w];
        for (int i = 0; i < h; i++) {
            b[i] = board[i].toCharArray();
            for (int j = 0; j < w; j++) {
                if (b[i][j] == 'R') {
                    startx = i;
                    starty = j;
                } else if (b[i][j] == 'G') {
                    endx = i;
                    endy = j;
                }
            }
        }
        Queue<Integer[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        visited[startx][starty] = true;
        
        dx = new int[] {-1, 0, 1, 0};
        dy = new int[] {0, -1, 0, 1};
        
        queue.offer(new Integer[] {startx, starty, 0});
        int turn = -1;
        
        while (!queue.isEmpty()) {
            Integer[] current = queue.poll();
            if (current[0] == endx && current[1] == endy) {
                turn = current[2];
                break;
            }
            for (int d = 0; d < 4; d++) {
                Integer[] next = go(current[0], current[1], dx[d], dy[d]);
                // 만약 얘가 이동했고, 안가본곳이면
                if (next[2] == 1 && !visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.offer(new Integer[] {next[0], next[1], current[2] + 1});
                }
            }
        }
        
        return turn;
    }
    
    public static Integer[] go(int x, int y, int dx, int dy) {
        int sx = x;
        int sy = y;
        // d방향으로 갈데까지 가라
        while (true) {
            x += dx;
            y += dy;
            // 범위를 넘었다
            if (x < 0 || x >= h || y < 0 || y >= w) {
                // 원래대로 돌리고 리턴
                x -= dx;
                y -= dy;
                break;
            // 
            } else {
               if (b[x][y] == 'D') {
                   x -= dx;
                   y -= dy;
                   break;
               } 
            }
        }
        boolean moved = true;
        if (sx == x && sy == y) moved = false;
        return new Integer[] {x, y, moved ? 1 : 0};
    }
}