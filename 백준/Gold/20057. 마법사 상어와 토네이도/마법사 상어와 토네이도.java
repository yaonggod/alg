import java.io.*;
import java.util.*;


public class Main {

    static int n;

    static int[][] add = new int[][] {
            {-1, -1, 1},
            {-1, 1, 1},
            {0, -2, 2},
            {0, -1, 7},
            {0, 1, 7},
            {0, 2, 2},
            {1, -1, 10},
            {1, 1, 10},
            {2, 0, 5}
    };

    // d = 3 위쪽으로 갈 때 -1, 1
    // d = 1 아래쪽으로 갈 때 1, 1
    // d = 0 왼쪽으로 갈 때 -1, 1하고 좌우반전
    // d = 2 오른쪽으로 갈 때 1, 1하고 좌우반전

    // 1 1 2 2 3 3 4 4 4

    // 좌 아래 우 위
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {-1, 0, 1, 0};

    static int d = 0;
    static int sx, sy;
    // 나간 모래
    static long out = 0;
    static int[][] sand;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        sx = n / 2;
        sy = n / 2;
        sand = new int[n][n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int l = 1; l < n; l++) {
            for (int i = 0; i < l; i++) {
                movesand();
                sx += dx[d];
                sy += dy[d];
            }
            d++;
            if (d == 4) d = 0;
            for (int i = 0; i < l; i++) {
                movesand();
                sx += dx[d];
                sy += dy[d];
            }
            d++;
            if (d == 4) d = 0;
        }

        for (int i = 0; i < n - 1; i++) {
            movesand();
            sx += dx[d];
            sy += dy[d];
        }

        System.out.println(out);

    }

    // d = 3 위쪽으로 갈 때 -1, 1
    // d = 1 아래쪽으로 갈 때 1, 1
    // d = 0 왼쪽으로 갈 때 -1, 1하고 좌우반전
    // d = 2 오른쪽으로 갈 때 1, 1하고 좌우반전
    static void movesand() {
        int sandx = sx + dx[d];
        int sandy = sy + dy[d];
        // 처음 모래
        int totalSand = sand[sandx][sandy];
        int left = totalSand;
        sand[sandx][sandy] = 0;

        int targetx = -1;
        int targety = -1;

        for (int i = 0; i < 9; i++) {
            if (d == 0) {
                targetx = add[i][1];
                targety = -add[i][0];
            } else if (d == 1) {
                targetx = add[i][0];
                targety = add[i][1];
            } else if (d == 2) {
                targetx = add[i][1];
                targety = add[i][0];
            } else if (d == 3) {
                targetx = -add[i][0];
                targety = add[i][1];
            }
            int amount = totalSand * add[i][2] / 100;
            int tox = sandx + targetx;
            int toy = sandy + targety;
            if (tox >= 0 && tox < n && toy >= 0 && toy < n) {
                sand[tox][toy] += amount;
            } else {
                out += amount;
            }
            left -= amount;

        }
        int leftx = sandx + dx[d];
        int lefty = sandy + dy[d];
        if (leftx >= 0 && leftx < n && lefty >= 0 && lefty < n) {
            sand[leftx][lefty] += left;
        } else {
            out += left;
        }

    }

}