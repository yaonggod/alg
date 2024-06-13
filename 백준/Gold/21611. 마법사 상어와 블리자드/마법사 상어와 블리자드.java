import java.io.*;
import java.util.*;


public class Main {
    static int n, m;
    static int[][] blizzard;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    // 구슬 방향
    static int[] dirx = new int[] {0, 1, 0, -1};
    static int[] diry = new int[] {-1, 0, 1, 0};
    static int[][] moves;
    static int[][] toGo;
    static int[][] before;
    static int[] counts = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        blizzard = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                blizzard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moves = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // d
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1;
            // s
            moves[i][1] = Integer.parseInt(st.nextToken());
        }

        // 이 칸의 다음칸은 여기다
        toGo = new int[n][n];
        int sx = n / 2;
        int sy = n / 2;
        int d = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < i; k++) {
                    toGo[sx][sy] = d;
                    sx += dirx[d];
                    sy += diry[d];
                }
                d++;
                if (d == 4) d = 0;
            }
        }
        for (int k = 0; k < n - 1; k++) {
            toGo[sx][sy] = d;
        }

        // 이 칸의 이전 칸은 여기다
        before = new int[n][n];
        sx = 0;
        sy = 0;
        d = 2;
        for (int k = 0; k < n - 1; k++) {
            before[sx][sy] = d;
            sx += dirx[d];
            sy += diry[d];
        }
        d--;
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < i; k++) {
                    before[sx][sy] = d;
                    sx += dirx[d];
                    sy += diry[d];
                }
                d--;
                if (d == -1) d = 3;
            }
        }

        for (int i = 0; i < m; i++) {
            shoot(moves[i][0], moves[i][1]);
            moveBalls();
            while (true) {
                int e = explode();
                if (e == 0) break;
                moveBalls();
            }
            changeBalls();
        }

        int answer = counts[1] + counts[2] * 2 + counts[3] * 3;
        System.out.println(answer);
    }

    static void shoot(int d, int s) {
        int sx = n / 2;
        int sy = n / 2;
        for (int i = 1; i <= s; i++) {
            sx += dx[d];
            sy += dy[d];
            blizzard[sx][sy] = 0;
        }
    }

    static void moveBalls() {
        int sx = n / 2;
        int sy = n / 2;
        boolean noBalls = false;
        while (true) {
            int d = toGo[sx][sy];
            sx += dirx[d];
            sy += diry[d];
            if (sx == 0 && sy == 0) break;
            // 지금 위치가 비어있으면
            if (blizzard[sx][sy] == 0) {
                int x = sx;
                int y = sy;
                // 가장 가까운 위치의 0이 아닌 공을 찾아서 끌어당김
                while (true) {
                    int tempd = toGo[x][y];
                    x += dirx[tempd];
                    y += diry[tempd];
                    // 이제 공이 없음
                    if (x == 0 && y == -1) {
                        noBalls = true;
                        break;
                    }
                    if (blizzard[x][y] != 0) break;
                }
                if (noBalls) break;
                int temp = blizzard[x][y];
                blizzard[sx][sy] = temp;
                blizzard[x][y] = 0;
            }
            if (noBalls) break;
        }


    }

    static int explode() {
        int total = 0;
        int sx = n / 2;
        int sy = n / 2;

        int d = toGo[sx][sy];
        sx += dirx[d];
        sy += diry[d];

        int number = blizzard[sx][sy];
        int length = 1;
        List<Integer[]> connected = new ArrayList<>();
        connected.add(new Integer[] {sx, sy});

        while (true) {
            d = toGo[sx][sy];
            sx += dirx[d];
            sy += diry[d];

            if (sx == 0 && sy == -1) break;

            // 번호 바뀜
            if (number != blizzard[sx][sy]) {
                // 폭파
                if (length >= 4) {
                    for (Integer[] c : connected) {
                        blizzard[c[0]][c[1]] = 0;
                    }
                    total += length;
                    counts[number] += length;
                }
                // 새 구슬 시작하기
                connected = new ArrayList<>();
                number = blizzard[sx][sy];
                length = 1;
                connected.add(new Integer[] {sx, sy});
            } else {
                length++;
                connected.add(new Integer[] {sx, sy});
            }

            // 끝
            if (blizzard[sx][sy] == 0) break;
        }
        return total;
    }

    static void changeBalls() {
        int[][] newBlizzard = new int[n][n];
        int sx = n / 2;
        int sy = n / 2;

        // 상어에서 한 칸 떨어져서 시작하기
        int d = toGo[sx][sy];
        sx += dirx[d];
        sy += diry[d];

        int number = blizzard[sx][sy];
        int length = 1;

        int nx = n / 2;
        int ny = n / 2;


        while (true) {
            // 한칸씩 옮겨서 비교
            d = toGo[sx][sy];
            sx += dirx[d];
            sy += diry[d];
            if (sx == 0 && sy == -1) break;

            // 번호 바뀜
            if (number != blizzard[sx][sy]) {
                int nd = toGo[nx][ny];
                nx += dirx[nd];
                ny += diry[nd];

                if (nx == 0 && ny == -1) break;

                newBlizzard[nx][ny] = length;

                nd = toGo[nx][ny];
                nx += dirx[nd];
                ny += diry[nd];

                if (nx == 0 && ny == -1) break;

                newBlizzard[nx][ny] = number;

                number = blizzard[sx][sy];
                length = 1;
            } else {
                length++;
            }

            if (blizzard[sx][sy] == 0) break;
        }

        blizzard = newBlizzard;
    }
}