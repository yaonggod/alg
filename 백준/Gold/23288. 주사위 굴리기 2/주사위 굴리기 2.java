import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static int maxlen;

    // 위 아래 앞 뒤 우 좌
    static int[] dice = new int[] {1, 6, 5, 2, 3, 4};

    // 1 - 6 5 - 2 3 - 4
    // 왼쪽
    // 3 - 4 5 - 2 6 - 1
    // 오른쪽
    // 4 - 3 5 - 2 1 - 6
    // 위쪽
    // 5 - 2 6 - 1 3 - 4
    // 아래쪽
    // 2 - 5 1 - 6 3 - 4

    // 동 남 서 북
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    static int d = 0;

    // 처음 위치
    static int x = 0;
    static int y = 0;

    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (k > 0) {
            rollDice();
            point();

            if (dice[1] > map[x][y]) {
                d++;
                if (d == 4) d = 0;
            } else if (dice[1] < map[x][y]) {
                d--;
                if (d == -1) d = 3;
            }

            k--;
        }

        System.out.println(answer);
    }

    static void rollDice() {
        int nx = x + dx[d];
        int ny = y + dy[d];

        // 이동하지 않는다
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            nx -= dx[d];
            ny -= dy[d];

            d -= 2;
            if (d < 0) d += 4;

            nx += dx[d];
            ny += dy[d];
        }

        x = nx;
        y = ny;
        int[] newDice = new int[6];

        // 1 - 6 5 - 2 3 - 4
        // 왼쪽
        // 3 - 4 5 - 2 6 - 1
        // 오른쪽
        // 4 - 3 5 - 2 1 - 6
        // 위쪽
        // 5 - 2 6 - 1 3 - 4
        // 아래쪽
        // 2 - 5 1 - 6 3 - 4

        // 동 남 서 북
        if (d == 2) {
            newDice[0] = dice[4];
            newDice[1] = dice[5];
            newDice[2] = dice[2];
            newDice[3] = dice[3];
            newDice[4] = dice[1];
            newDice[5] = dice[0];
        } else if (d == 0) {
            newDice[0] = dice[5];
            newDice[1] = dice[4];
            newDice[2] = dice[2];
            newDice[3] = dice[3];
            newDice[4] = dice[0];
            newDice[5] = dice[1];
        } else if (d == 3) {
            newDice[0] = dice[2];
            newDice[1] = dice[3];
            newDice[2] = dice[1];
            newDice[3] = dice[0];
            newDice[4] = dice[4];
            newDice[5] = dice[5];
        } else if (d == 1) {
            newDice[0] = dice[3];
            newDice[1] = dice[2];
            newDice[2] = dice[0];
            newDice[3] = dice[1];
            newDice[4] = dice[4];
            newDice[5] = dice[5];
        }
        dice = newDice;
    }

    static void point() {
        // dfs
        int count = 1;
        visited = new boolean[n][m];
        visited[x][y] = true;

        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {x, y});
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = from[0] + dx[d];
                int ny = from[1] + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && map[x][y] == map[nx][ny]) {
                        visited[nx][ny] = true;
                        count++;
                        queue.offer(new Integer[] {nx, ny});
                    }
                }
            }

        }


        answer += count * map[x][y];
    }
}