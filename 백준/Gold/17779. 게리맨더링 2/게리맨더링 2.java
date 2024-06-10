import java.io.*;
import java.util.*;


public class Main {
    static int n;
    static int[][] jaehyun;
    static int gap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        jaehyun = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                jaehyun[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n - x; d1++) {
                    for (int d2 = 1; d2 < n - x - d1; d2++) {
                        divide(x, y, d1, d2);
                    }
                }

            }
        }

        System.out.println(gap);
    }

    static void divide(int x, int y, int d1, int d2) {
        int[][] area = new int[n][n];

        int[][] division = new int[n][2];
        for (int i = 0; i < n; i++) {
            division[i][0] = -1;
            division[i][1] = -1;
        }

        for (int d = 0; d <= d1; d++) {
            if (x + d < n && y - d >= 0) {
                division[x + d][0] = y - d;
            }
        }

        for (int d = 0; d <= d2; d++) {
            if (x + d < n && y + d < n) {
                division[x + d][1] = y + d;
            }
        }

        for (int d = 0; d <= d2; d++) {
            if (x + d1 + d < n && y - d1 + d >= 0 && y - d1 + d < n) {
                division[x + d1 + d][0] = y - d1 + d;
            }
        }

        for (int d = 0; d <= d1; d++) {
            if (x + d2 + d < n && y + d2 - d >= 0 && y + d2 - d < n) {
                division[x + d2 + d][1] = y + d2 - d;
            }
        }

        for (int i = 0; i < n; i++) {
            if (division[i][0] != -1 && division[i][1] != -1) {
                for (int j = division[i][0]; j <= division[i][1]; j++) {
                    area[i][j] = 5;
                }
            } else if (division[i][0] != -1) {
                for (int j = division[i][0]; j < n; j++) {
                    area[i][j] = 5;
                }
            } else if (division[i][1] != -1) {
                for (int j = 0; j <= division[i][1]; j++) {
                    area[i][j] = 5;
                }
            }
        }



        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] == 0) {
                    if (i < x + d1 && j <= y) {
                        area[i][j] = 1;
                    } else if (i <= x + d2 && j > y) {
                        area[i][j] = 2;
                    } else if (i >= x + d1 && j < y - d1 + d2) {
                        area[i][j] = 3;
                    } else if (i > x + d2 && j >= y - d1 + d2) {
                        area[i][j] = 4;
                    }
                }
            }
        }

        int[] people = new int[6];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                people[area[i][j]] += jaehyun[i][j];
            }
        }



        int minp = Integer.MAX_VALUE;
        int maxp = 0;
        for (int i = 1; i <= 5; i++) {
            if (people[i] > maxp) maxp = people[i];
            if (people[i] < minp) minp = people[i];
        }

        if (minp != 0) {
            gap = Math.min(maxp - minp, gap);
        }
    }
}