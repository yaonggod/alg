import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        int MIN = -100 * n * m;

        dp[0][0] = map[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[] left = new int[m];
            int[] right = new int[m];
            Arrays.fill(left, MIN);
            Arrays.fill(right, MIN);
            int[] up = new int[m];

            for (int j = 0; j < m; j++) {
                up[j] = dp[i - 1][j];
            }

            // 위에서 내려오는거 vs 왼쪽에서 오는거
            left[0] = up[0] + map[i][0];
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(left[j - 1], up[j]) + map[i][j];
            }

            // 위에서 내려오는거 vs 오른쪽에서 오는거
            right[m - 1] = up[m - 1] + map[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], up[j]) + map[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }

        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[n - 1][m - 1]);
    }


}