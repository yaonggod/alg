import java.io.*;
import java.util.*;

public class Main {
    // 지도
    static int[][] map;
    static int m, n;

    // 방향
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    // 디피
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[m - 1][n - 1] = 1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(go(0, 0));
    }

    // (x, y)에서 출발해서 종점까지 가는 경우의 수
    static int go(int x, int y) {
        // 와본적이 있다
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 아니다 처음와봄
        dp[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (valid(nx, ny) && map[x][y] > map[nx][ny]) {
                // (nx, ny)에서 종점까지 갔을때 경우의 수를 더해줌
                dp[x][y] += go(nx, ny);
            }
        }
        return dp[x][y];
    }

    static boolean valid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}