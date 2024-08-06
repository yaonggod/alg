import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] cheese;
    // 치즈 칸 갯수
    static int cc = 0;
    // 시도 횟수
    static int tc = 0;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    // 치즈 위치 저장
    static LinkedList<Integer[]> cheeseDot = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                if (cheese[i][j] == 1) {
                    cc += 1;
                    cheeseDot.offer(new Integer[] {i, j});
                }
            }
        }

        // 외부 공기와 내부 공기 구별
        cheese[0][0] = 2;

        while (cc > 0) {
            outside();
            meltCheese();
            tc++;
        }
        System.out.println(tc);
    }

    // 외부공기로 만들기
    static void outside() {
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{0, 0});
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = from[0] + dx[d];
                int ny = from[1] + dy[d];
                if (range(nx, ny) && !visited[nx][ny] && cheese[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    cheese[nx][ny] = 2;
                    queue.offer(new Integer[]{nx, ny});
                }
            }
        }
    }

    static void meltCheese() {
        int cheeseSize = cheeseDot.size();
        for (int i = 0; i < cheeseSize; i++) {
            Integer[] dot = cheeseDot.poll();
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nx = dot[0] + dx[d];
                int ny = dot[1] + dy[d];
                // 외부 공기랑 접촉
                if (range(nx, ny) && cheese[nx][ny] == 2) count++;
            }
            if (count >= 2) {
                // 아직은 외부공기 아님
                cheese[dot[0]][dot[1]] = 0;
                cc--;
            } else {
                cheeseDot.offer(dot);
            }
        }
    }

    static boolean range(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}