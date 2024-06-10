import java.io.*;
import java.util.*;


public class Main {
    static int r, c, t;
    static int[][] room;
    // 이번 턴에 각 칸에 추가되는 미세먼지
    static int[][] add;

    // 위 공청기
    static int ux = -1;
    static int uy = -1;
    // 순환 방향
    static int ud = 0;
    static int[] udx = new int[] {0, -1, 0, 1};
    static int[] udy = new int[] {1, 0, -1, 0};

    // 아래 공청기
    static int lx = -1;
    static int ly = -1;
    // 순환 방향
    static int ld = 0;
    static int[] ldx = new int[] {0, 1, 0, -1};
    static int[] ldy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        room = new int[r][c];
        add = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (ux == -1 && uy == -1) {
                        ux = i;
                        uy = j;
                    } else {
                        lx = i;
                        ly = j;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            spread();
            clean();
        }

        int dust = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] != -1) {
                    dust += room[i][j];
                }
            }
        }
        System.out.println(dust);
    }

    static void spread() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] >= 5) {
                    int s = room[i][j] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + udx[d];
                        int ny = j + udy[d];
                        if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                            if (room[nx][ny] != -1) {
                                add[nx][ny] += s;
                                count++;
                            }
                        }
                    }

                    room[i][j] -= s * count;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                room[i][j] += add[i][j];
                add[i][j] = 0;
            }
        }

    }

    // 한칸씩 뒤로 민다
    static void clean() {
        ud = 0;
        int sx = ux + udx[ud];
        int sy = uy + udy[ud];

        int now = room[sx][sy];
        room[sx][sy] = 0;

        while (true) {
            // 한 칸 옮긴다
            sx += udx[ud];
            sy += udy[ud];

            if (sx < 0 || sx >= r || sy < 0 || sy >= c) {
                sx -= udx[ud];
                sy -= udy[ud];
                ud++;
                if (ud == 4) ud = 0;
                sx += udx[ud];
                sy += udy[ud];
            }

            if (sx == ux && sy == uy) break;

            int temp = room[sx][sy];
            room[sx][sy] = now;
            now = temp;
        }

        ld = 0;
        sx = lx + ldx[ld];
        sy = ly + ldy[ld];

        now = room[sx][sy];
        room[sx][sy] = 0;

        while (true) {
            // 한 칸 옮긴다
            sx += ldx[ld];
            sy += ldy[ld];

            if (sx < 0 || sx >= r || sy < 0 || sy >= c) {
                sx -= ldx[ld];
                sy -= ldy[ld];
                ld++;
                if (ld == 4) ld = 0;
                sx += ldx[ld];
                sy += ldy[ld];
            }
            if (sx == lx && sy == ly) break;

            int temp = room[sx][sy];
            room[sx][sy] = now;
            now = temp;
        }
    }


}