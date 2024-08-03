import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int result = -1;
    // (i, j)까지 w개의 벽을 부수고 이동했다 == 남은 벽은 k - w개
    static int[][][] visited;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new int[n][m][k + 1];
        visited[0][0][0] = 1;
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        travel();
        System.out.println(result);
    }

    static void travel() {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {0, 0, 0});

        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            int x = from[0];
            int y = from[1];
            int z = from[2];
            if (x == n - 1 && y == m - 1) {
                result = visited[x][y][z];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 뚫려있음
                    if (map[nx][ny] == '0' && visited[nx][ny][z] == 0) {
                        visited[nx][ny][z] = visited[x][y][z] + 1;
                        queue.offer(new Integer[] {nx, ny, z});
                    }
                    // 막혀있음
                    if (map[nx][ny] == '1' && z < k && visited[nx][ny][z + 1] == 0) {
                        visited[nx][ny][z + 1] = visited[x][y][z] + 1;
                        queue.offer(new Integer[] {nx, ny, z + 1});
                    }
                }

            }

        }
    }
}