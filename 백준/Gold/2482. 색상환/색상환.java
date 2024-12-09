import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        if (k > n / 2) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[k][n];
        // 1개 선택했을 때
        for (int i = 0; i < n; i++) {
            dp[0][i] = i + 1;
        }

        for (int i = 1; i < k; i++) {
            for (int j = 3; j < n; j++) {
                dp[i][j] = (dp[i - 1][j - 2] + dp[i][j - 1]) % 1000000003;
            }
        }

        System.out.println(dp[k - 1][n - 1]);
    }

}