import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m;
    static int[][] board, visited;
    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        int sx = 0;
        int sy = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        visited = new int[n][m];
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {sx, sy, 0});
        while (!queue.isEmpty()) {
            Integer[] current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (board[nx][ny] == 1 && visited[nx][ny] == 0) {
                        visited[nx][ny] = current[2] + 1;
                        queue.offer(new Integer[] {nx, ny, visited[nx][ny]});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && visited[i][j] == 0) {
                    visited[i][j] = -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(visited[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}