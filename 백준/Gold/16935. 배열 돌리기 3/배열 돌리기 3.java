import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, h, w;
    static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        h = n;
        w = m;
        ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ans[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            doCommand(Integer.parseInt(st.nextToken()));
        }
        printMatrix();
    }
    static void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void doCommand(int c) {
        switch (c) {
            case 1:
                ans = one();
                break;
            case 2:
                ans = two();
                break;
            case 3:
                ans = three();
                break;
            case 4:
                ans = four();
                break;
            case 5:
                ans = five();
                break;
            case 6:
                ans = six();
                break;
        }
    }

    static int[][] one() {
        // 상하 반전
        int[][] newM = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newM[i][j] = ans[h - i - 1][j];
            }
        }
        return newM;
    }

    static int[][] two() {
        // 좌우 반전
        int[][] newM = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newM[i][j] = ans[i][w - j - 1];
            }
        }
        return newM;
    }

    static int[][] three() {
        // 오른쪽 90도
        int[][] newM = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newM[j][h - i - 1] = ans[i][j];
            }
        }
        int temp1 = h;
        int temp2 = w;
        h = temp2;
        w = temp1;

        return newM;
    }

    static int[][] four() {
        // 왼쪽 90도
        int[][] newM = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newM[w - j - 1][i] = ans[i][j];
            }
        }
        int temp1 = h;
        int temp2 = w;
        h = temp2;
        w = temp1;
        return newM;
    }

    static int[][] five() {
        // 영역을 4분할해서 오른쪽 90도
        // 1 -> 2
        int[][] newM = new int[h][w];
        for (int i = 0; i < h / 2; i++) {
            for (int j = 0; j < w / 2; j++) {
                newM[i][j + w / 2] = ans[i][j];
            }
        }
        // 2 -> 3
        for (int i = 0; i < h / 2; i++) {
            for (int j = w / 2; j < w; j++) {
                newM[i + h / 2][j] = ans[i][j];
            }
        }
        // 3 -> 4
        for (int i = h / 2; i < h; i++) {
            for (int j = w / 2; j < w; j++) {
                newM[i][j - w / 2] = ans[i][j];
            }
        }
        // 4 -> 1
        for (int i = h / 2; i < h; i++) {
            for (int j = 0; j < w / 2; j++) {
                newM[i - h / 2][j] = ans[i][j];
            }
        }
        return newM;
    }
    static int[][] six() {
        // 영역을 4분할해서 왼쪽 90도
        // 1 -> 4
        int[][] newM = new int[h][w];
        for (int i = 0; i < h / 2; i++) {
            for (int j = 0; j < w / 2; j++) {
                newM[i + h / 2][j] = ans[i][j];
            }
        }
        // 2 -> 1
        for (int i = 0; i < h / 2; i++) {
            for (int j = w / 2; j < w; j++) {
                newM[i][j - w / 2] = ans[i][j];
            }
        }
        // 3 -> 2
        for (int i = h / 2; i < h; i++) {
            for (int j = w / 2; j < w; j++) {
                newM[i - h / 2][j] = ans[i][j];
            }
        }
        // 4 -> 3
        for (int i = h / 2; i < h; i++) {
            for (int j = 0; j < w / 2; j++) {
                newM[i][j + w / 2] = ans[i][j];
            }
        }
        return newM;
    }

}