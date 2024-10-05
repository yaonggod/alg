import java.io.*;
import java.util.*;

public class Main {
    static int t, m, n, k;
    static boolean[][] cabbage;
    static boolean[][] visited;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    static boolean valid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            cabbage = new boolean[n][m];
            visited = new boolean[n][m];
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                cabbage[y][x] = true;
            }

            int worm = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cabbage[i][j] && !visited[i][j]) {
                        visited[i][j] = true;
                        Queue<Integer[]> queue = new LinkedList<>();
                        queue.offer(new Integer[] {i, j});
                        while (!queue.isEmpty()) {
                            Integer[] from = queue.poll();
                            for (int d = 0; d < 4; d++) {
                                int nx = from[0] + dx[d];
                                int ny = from[1] + dy[d];
                                if (valid(nx, ny) && cabbage[nx][ny] && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    queue.offer(new Integer[] {nx, ny});
                                }
                            }
                        }
                        worm++;
                    }
                }
            }
            System.out.println(worm);
        }

    }
}