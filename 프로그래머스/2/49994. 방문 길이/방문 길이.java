class Solution {
    static boolean[][][] visited = new boolean[11][11][4];
    static int x = 5, y = 5, count = 0;
    public int solution(String dirs) {
        char[] dirArr = dirs.toCharArray();
        for (char d : dirArr) {
            if (d == 'U') go(x, y, -1, 0, 0, 1);
            else if (d == 'D') go(x, y, 1, 0, 1, 0);
            else if (d == 'R') go(x, y, 0, 1, 2, 3);  
            else if (d == 'L') go(x, y, 0, -1, 3, 2);  
        }
        return count;
    }
    
    static void go(int cx, int cy, int dx, int dy, int dir, int rdir) {
        int nx = cx + dx;
        int ny = cy + dy;
        // 갈 수 있다
        if (nx >= 0 && nx <= 10 && ny >= 0 && ny <= 10) {
            // x, y에서 어떤 방향으로 가봤다 + 역방향도
            if (!visited[x][y][dir] && !visited[nx][ny][rdir]) {
                count++;
                visited[x][y][dir] = true;
                visited[nx][ny][rdir] = true;
            }
            x = nx;
            y = ny;
        }
    }
}