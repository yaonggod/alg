import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] board = new String[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().split("");
        }
        // 가로, 세로, 대각선으로 지금까지 온 사이즈 중 최소값 + 1 저장
        int[][] size = new int[n][m];
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j].equals("1")) {
                        size[i][j] = 1;
                        maxSize = 1;
                    }
                }
            } else {
                if (board[i][0].equals("1")) {
                    size[i][0] = 1;
                    maxSize = 1;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j].equals("1")) {
                    int a = Math.min(size[i - 1][j - 1], size[i - 1][j]);
                    a = Math.min(a, size[i][j - 1]);
                    size[i][j] = a + 1;
                    if (size[i][j] > maxSize) maxSize = size[i][j];
                }
            }
        }
        System.out.println(maxSize * maxSize);
    }
}