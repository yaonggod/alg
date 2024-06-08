import java.io.*;
import java.util.*;


public class Main {
    // 초
    static int sec = 0;
    // 어장 크기
    static int n;
    // 상어 크기
    static int size = 2;
    // 몇개 더 먹어야함
    static int left = 2;
    // 상어 위치
    static int sx = 0;
    static int sy = 0;
    // 어장
    static int[][] water;
    // 먹었나
    static boolean[][] ate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        water = new int[n][n];
        ate = new boolean[n][n];

        sx = 0;
        sy = 0;

        size = 2;
        left = 2;

        // 어장
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
                if (water[i][j] == 9) {
                    sx = i;
                    sy = j;
                    water[i][j] = 0;
                }
            }
        }

        while (true) {
            eat();
            if (sx == -1 && sy == -1) {
                break;
            }
        }

        System.out.println(sec);
    }

    // 먹을거를 찾자
    static void eat() {
        int nx = -1;
        int ny = -1;
        int dist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 먹을 수 있나?
                if (water[i][j] != 0 && water[i][j] < size) {
                    if (!ate[i][j]) {
                        int newDist = distance(sx, sy, i, j);
                        // 근데 거리도 작다
                        if (newDist != -1 && newDist < dist) {
                            nx = i;
                            ny = j;
                            dist = newDist;
                        }
                    }
                }
            }
        }

        // 먹을거 못찾음
        if (nx == -1 && ny == -1) {
            sx = -1;
            sy = -1;
            return;
        }

        // 이동
        sec += dist;
        sx = nx;
        sy = ny;
        water[nx][ny] = 0;
        ate[nx][ny] = true;
        left--;
        // 몸집 키우기
        if (left == 0) {
            size++;
            left = size;
        }
    }

    // 거리
    static int distance(int x, int y, int fx, int fy) {
        boolean[][] visited = new boolean[n][n];

        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {x, y, 0});
        visited[x][y] = true;

        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Integer[] data = queue.poll();
            int cx = data[0];
            int cy = data[1];
            int length = data[2];
            if (cx == fx && cy == fy) {
                return length;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (water[nx][ny] <= size) {
                        visited[nx][ny] = true;
                        queue.offer(new Integer[] {nx, ny, length + 1});
                    }
                }
            }
        }

        return -1;
    }
}