import java.io.*;
import java.util.*;


public class Main {
    static int n, m;
    static int[][] water;
    static int[] dx = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] cloud;
    static int[][] moves;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        water = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moves = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1;
            moves[i][1] = Integer.parseInt(st.nextToken());
        }

        cloud = new boolean[n][n];
        cloud[n - 1][0] = true;
        cloud[n - 1][1] = true;
        cloud[n - 2][0] = true;
        cloud[n - 2][1] = true;

        for (int i = 0; i < m; i++) {
            moveCloud(moves[i][0], moves[i][1]);
            rain();
            waterIncrease();
            makeCloud();
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalWater += water[i][j];
            }
        }
        System.out.println(totalWater);
    }

    static void moveCloud(int d, int s) {
        boolean[][] newCloud = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nx = i + dx[d] * s;
                int ny = j + dy[d] * s;

                if (nx >= n) {
                    while (nx >= n) {
                        nx -= n;
                    }
                }
                if (nx < 0) {
                    while (nx < 0) {
                        nx += n;
                    }
                }
                if (ny >= n) {
                    while (ny >= n) {
                        ny -= n;
                    }
                }
                if (ny < 0) {
                    while (ny < 0) {
                        ny += n;
                    }
                }
                newCloud[nx][ny] = cloud[i][j];
            }
        }
        cloud = newCloud;
    }

    static void rain() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cloud[i][j]) water[i][j]++;

            }
        }
    }

    static void waterIncrease() {
        int[][] newWater = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newWater[i][j] = water[i][j];
                if (cloud[i][j]) {
                    int count = 0;
                    // 대각선 칸
                    for (int d = 1; d < 8; d += 2) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            if (water[nx][ny] > 0) count++;
                        }
                    }
                    newWater[i][j] += count;
                }
            }
        }

        water = newWater;
    }

    static void makeCloud() {
        boolean[][] newCloud = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!cloud[i][j] && water[i][j] >= 2) {
                    newCloud[i][j] = true;
                }
            }
        }
        cloud = newCloud;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cloud[i][j]) {
                    water[i][j] -= 2;
                }
            }
        }
    }
}