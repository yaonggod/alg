import java.util.*;

class Solution {
    static char[][] miro;
    static int[][][] visited;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};
    static int h, w;
    public int solution(String[] maps) {
        h = maps.length;
        w = maps[0].length();
        int startx = 0;
        int starty = 0;
        int leverx = 0;
        int levery = 0;
        int endx = 0;
        int endy = 0;
        miro = new char[h][w];
        for (int i = 0; i < h; i++) {
            miro[i] = maps[i].toCharArray();
            for (int j = 0; j < w; j++) {
                if (miro[i][j] == 'S') {
                    startx = i;
                    starty = j;
                } else if (miro[i][j] == 'E') {
                    endx = i;
                    endy = j;
                } else if (miro[i][j] == 'L') {
                    leverx = i;
                    levery = j;
                }
            } 
        }
        visited = new int[2][h][w];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < h; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }
        visited[0][startx][starty] = 0;
        // 레버 찾기
        Queue<Integer[]> queueToLever = new LinkedList<>();
        boolean foundLever = false;
        queueToLever.offer(new Integer[] {startx, starty});
        while (!queueToLever.isEmpty()) {
            Integer[] current = queueToLever.poll();
            if (current[0] == leverx && current[1] == levery) {
                foundLever = true;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (cango(0, nx, ny)) {
                    visited[0][nx][ny] = visited[0][current[0]][current[1]] + 1;
                    queueToLever.offer(new Integer[] {nx, ny});
                }
            }
        }
        if (!foundLever) return -1;
        // 출구 찾기
        Queue<Integer[]> queueToEnd = new LinkedList<>();
        queueToEnd.offer(new Integer[] {leverx, levery});
        visited[1][leverx][levery] = visited[0][leverx][levery];
        while (!queueToEnd.isEmpty()) {
            Integer[] current = queueToEnd.poll();
            if (current[0] == endx && current[1] == endy) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (cango(1, nx, ny)) {
                    visited[1][nx][ny] = visited[1][current[0]][current[1]] + 1;
                    queueToEnd.offer(new Integer[] {nx, ny});
                }
            }
        }
        return visited[1][endx][endy];
        
    }
    public static boolean cango(int layer, int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w && miro[x][y] != 'X' && visited[layer][x][y] == -1;
    }
}