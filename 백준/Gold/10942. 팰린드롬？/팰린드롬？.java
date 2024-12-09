import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];

        // 대각선으로, 1글자는 다 팰린드롬임
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // [i][i]에서 한 칸, 두 칸 뒤가 같은지 확인
        for (int i = 0; i < n; i++) {
            if (i + 1 < n) {
                if (arr[i] == arr[i + 1]) {
                    dp[i][i + 1] = 1;
                }
            }
            if (i + 2 < n) {
                if (arr[i] == arr[i + 2]) {
                    dp[i][i + 2] = 1;
                }
            }
        }

        // i ~ j : arr[i] == arr[j]이며,
        // 바로 안에 있는 수열도 팰린드롬임
        // dp[i + 1][j - 1] = true

        for (int k = 3; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i < n - 1 && i + k < n && arr[i] == arr[i + k]) {
                    if (dp[i + 1][i + k - 1] == 1) {
                        dp[i][i + k] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[s][e]).append("\n");
        }

        System.out.println(sb);
    }

}