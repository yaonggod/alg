import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 에어컨
        List<Integer[]> ac = new ArrayList<>();

        int[][] lab = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 9) {
                    ac.add(new Integer[] {i, j});
                }
            }
        }

        // 우 하 좌 상
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};

        // 1 { 0, 2 } { 1, 1 } { 2, 0 } { 3, 3 }
        // 2 { 0, 0 } { 1, 3 } { 2, 2 } { 3, 1 }
        // 3 { 0, 3 } { 1, 2 } { 2, 1 } { 3, 0 }
        // 4 { 0, 1 } { 1, 0 } { 2, 3 } { 3, 2 }

        int[][][] go = new int[5][4][2];
        go[1] = new int[][] {{ 0, 2 }, { 1, 1 }, { 2, 0 }, { 3, 3 }};
        go[2] = new int[][] {{ 0, 0 }, { 1, 3 }, { 2, 2 }, { 3, 1 }};
        go[3] = new int[][] {{ 0, 3 }, { 1, 2 }, { 2, 1 }, { 3, 0 }};
        go[4] = new int[][] {{ 0, 1 }, { 1, 0 }, { 2, 3 }, { 3, 2 }};

        // 에어컨 통하는데 칠함
        boolean[][] seat = new boolean[n][m];

        for (Integer[] a : ac) {
            int cx = a[0];
            int cy = a[1];
            seat[cx][cy] = true;

            // 계속 가다가 1이나 2를 만나서 튕겨나오면 그만 돌아도 됨
            // 아니면 영역 밖으로 나가거나
            for (int d = 0; d < 4; d++) {
                // 지금 방향
                int cd = d;
                // 다음에 갈 곳
                int nx = cx;
                int ny = cy;
                while (true) {
                    nx += dx[cd];
                    ny += dy[cd];

                    // 맵 나갔음
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;

                    // 에어컨 칠해주기
                    seat[nx][ny] = true;

                    // 위나 아래로 가고 있는데 2를 만났음
                    if ((cd == 1 || cd == 3) && lab[nx][ny] == 2) break;

                    // 좌 우 가고 있는데 1을 만났음
                    if ((cd == 0 || cd == 2) && lab[nx][ny] == 1) break;

                    // 방향 전환
                    if (lab[nx][ny] != 0 && lab[nx][ny] != 9) cd = go[lab[nx][ny]][cd][1];
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count += seat[i][j] ? 1 : 0;
            }
        }

        System.out.println(count);
    }
}