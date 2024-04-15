import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int A, B, N;
    static int[][] map;
    static int[][] robots;

    // NESW
    static int[] dy = new int[] {1, 0, -1, 0};
    static int[] dx = new int[] {0, 1, 0, -1};
    static boolean happen = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B + 1][A + 1];
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        robots = new int[N + 1][3];
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            if (d.equals("N")) robots[i][2] = 0;
            if (d.equals("E")) robots[i][2] = 1;
            if (d.equals("S")) robots[i][2] = 2;
            if (d.equals("W")) robots[i][2] = 3;
            // 세로
            robots[i][0] = y;
            // 가로
            robots[i][1] = x;
            map[y][x] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String toDo = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            if (toDo.equals("F")) {
                forward(num, count);
            } else if (toDo.equals("R")) {
                for (int c = 0; c < count; c++) {
                    robots[num][2]++;
                    if (robots[num][2] == 4) robots[num][2] = 0;
                }

            } else if (toDo.equals("L")) {
                for (int c = 0; c < count; c++) {
                    robots[num][2]--;
                    if (robots[num][2] == -1) robots[num][2] = 3;
                }
            }
            if (happen) break;
        }
        if (!happen) System.out.println("OK");
    }

    static void forward(int r, int count) {
        int y = robots[r][0];
        int x = robots[r][1];
        int sy = y;
        int sx = x;
        for (int c = 0; c < count; c++) {
            sy += dy[robots[r][2]];
            sx += dx[robots[r][2]];
            if (sy >= 1 && sy <= B && sx >= 1 && sx <= A) {
                if (map[sy][sx] != 0) {
                    System.out.println("Robot " + r + " crashes into robot " + map[sy][sx]);
                    happen = true;
                    return;
                }
            } else {
                System.out.println("Robot " + r + " crashes into the wall");
                happen = true;
                return;
            }
        }
        map[y][x] = 0;
        map[sy][sx] = r;
        robots[r][0] = sy;
        robots[r][1] = sx;
    }
}