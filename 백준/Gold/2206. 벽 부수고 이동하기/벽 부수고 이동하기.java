import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 0 벽 안부숨 1 벽 부숨
        int[][] board = new int[n][m];
        int[][][] visited = new int[2][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[0][i][j] = -1;
                visited[1][i][j] = -1;
            }
        }
        visited[0][0][0] = 1;
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};

        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {0, 0, 0});

        while (!queue.isEmpty()) {
            // 저는 지금 x, y 에 있고요, 벽을 부수거나 안부쉈어요
            Integer[] c = queue.poll();
            if (c[0] == n - 1 && c[1] == m - 1) continue;

            for (int d = 0; d < 4; d++) {
                int nx = c[0] + dx[d];
                int ny = c[1] + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 벽이 아니에요
                    if (board[nx][ny] == 0 && visited[c[2]][nx][ny] == -1) {
                        visited[c[2]][nx][ny] = visited[c[2]][c[0]][c[1]] + 1;
                        queue.offer(new Integer[] {nx, ny, c[2]});
                    // 벽이에요
                    } else if (board[nx][ny] == 1 && c[2] == 0) {
                        visited[1][nx][ny] = visited[0][c[0]][c[1]] + 1;
                        queue.offer(new Integer[] {nx, ny, 1});
                    }
                }
            }
        }

        if (visited[0][n - 1][m - 1] == -1 && visited[1][n - 1][m - 1] == -1) {
            System.out.println(-1);
        } else if (visited[0][n - 1][m - 1] == -1) {
            System.out.println(visited[1][n - 1][m - 1]);
        } else if (visited[1][n - 1][m - 1] == -1) {
            System.out.println(visited[0][n - 1][m - 1]);
        } else {
            System.out.println(Math.min(visited[0][n - 1][m - 1], visited[1][n - 1][m - 1]));
        }
    }
}