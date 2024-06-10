import java.io.*;
import java.util.*;


public class Main {
    static int n, m;
    static int[][] room;
    // 지금 위치에서 위, 오른쪽, 아래, 왼쪽을 모두 커버하시오
    static boolean[][][] dir;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    static List<Integer[]> cctv = new ArrayList<>();
    static int ccCount;
    static int[] ccdir;
    static boolean[][] cover;
    static int minCover;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        minCover = n * m;

        room = new int[n][m];
        dir = new boolean[n][m][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] >= 1 && room[i][j] <= 5) {
                    cctv.add(new Integer[] {i, j, room[i][j]});
                }
            }
        }

        ccCount = cctv.size();
        ccdir = new int[ccCount];

        putCCTV(0);
        System.out.println(minCover);
    }

    static void putCCTV(int idx) {
        if (idx == ccCount) {
            minCover = Math.min(nonCover(), minCover);
            return;
        }

        // 위 아래
        if (cctv.get(idx)[2] == 2) {
            ccdir[idx] = 0;
            putCCTV(idx + 1);
            ccdir[idx] = 1;
            putCCTV(idx + 1);
        // 4방향
        } else if (cctv.get(idx)[2] == 5) {
            ccdir[idx] = 0;
            putCCTV(idx + 1);
        } else {
            ccdir[idx] = 0;
            putCCTV(idx + 1);
            ccdir[idx] = 1;
            putCCTV(idx + 1);
            ccdir[idx] = 2;
            putCCTV(idx + 1);
            ccdir[idx] = 3;
            putCCTV(idx + 1);
        }
    }

    static int nonCover() {
        // 실제로 커버해보자
        cover = new boolean[n][m];

        for (int c = 0; c < ccCount; c++) {
            if (cctv.get(c)[2] == 2) {
                // 가로
                if (ccdir[c] == 0) {
                    coverup(cctv.get(c)[0], cctv.get(c)[1], 1);
                    coverup(cctv.get(c)[0], cctv.get(c)[1], 3);
                } else {
                    coverup(cctv.get(c)[0], cctv.get(c)[1], 0);
                    coverup(cctv.get(c)[0], cctv.get(c)[1], 2);
                }
            } else if (cctv.get(c)[2] == 5) {
                for (int i = 0; i < 4; i++) {
                    coverup(cctv.get(c)[0], cctv.get(c)[1], i);
                }
            } else if (cctv.get(c)[2] == 1) {
                coverup(cctv.get(c)[0], cctv.get(c)[1], ccdir[c]);
            } else if (cctv.get(c)[2] == 3) {
                coverup(cctv.get(c)[0], cctv.get(c)[1], ccdir[c]);
                coverup(cctv.get(c)[0], cctv.get(c)[1], ccdir[c] + 1 == 4 ? 0 : ccdir[c] + 1);
            } else if (cctv.get(c)[2] == 4) {
                for (int i = 0; i < 4; i++) {
                    if (ccdir[c] != i) {
                        coverup(cctv.get(c)[0], cctv.get(c)[1], i);
                    }
                }
            }
        }
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] == 0 && !cover[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    static void coverup(int i, int j, int d) {
        while (true) {
            i += dx[d];
            j += dy[d];
            if (i < 0 || i >= n || j < 0 || j >= m) break;
            if (room[i][j] == 6) break;
            cover[i][j] = true;
        }
    }
}