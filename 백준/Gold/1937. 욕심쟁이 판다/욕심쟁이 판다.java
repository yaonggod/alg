import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};
    static int answer = 0;
    static int[][] map;
    static int[][] canReach;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        canReach = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        // 이미 탐색해놓아서 탐색 안해도 됨
        if (canReach[x][y] != 0) return canReach[x][y];
        // 나만 탐색했다 치고
        int length = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 일단 나보다 크면 가기
            if (cango(x, y, nx, ny)) {
                // 기존에 탐색해놓은데 + 1
                length = Math.max(dfs(nx, ny) + 1, length);
            }
        }
        canReach[x][y] = length;
        // 차례대로 더해가기
        return length;
    }

    static boolean cango(int x, int y, int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n && map[x][y] < map[nx][ny];
    }

}