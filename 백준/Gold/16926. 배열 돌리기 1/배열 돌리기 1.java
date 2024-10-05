import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, s;
    static int d = 0;
    static int[][] ans;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Math.min(n / 2, m / 2);
        r = Integer.parseInt(st.nextToken());
        ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ans[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (r-- > 0) {
            turn();
        }

        printMatrix(ans);

    }
    static void printMatrix(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(mat[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void turn() {
        int[][] newM = new int[n][m];
        for (int i = 0; i < s; i++) {
            d = 0;
            int cx = i;
            int cy = i;
            int nx = cx;
            int ny = cy;
            while (true) {
                // (cx, cy)에 있는거를 (nx, ny)에다가 가져다놓기
                // 다음 위치
                nx += dx[d];
                ny += dy[d];

                // 틀렸으면 회전하기
                if (!boundary(i, nx, ny)) {
                    nx -= dx[d];
                    ny -= dy[d];
                    d = (d + 1) % 4;
                    nx += dx[d];
                    ny += dy[d];
                }

                newM[nx][ny] = ans[cx][cy];

                // 원점임
                if (nx == i && ny == i) {
                    break;
                }

                cx = nx;
                cy = ny;
            }
        }
        ans = newM;

    }

    static boolean boundary(int sp, int x, int y) {
        // (sp, sp)를 시작점으로 해서 회전하는 좌표들은 어떤 좌표들인지
        return x >= sp && x < n - sp && y >= sp && y < m - sp;
    }

}