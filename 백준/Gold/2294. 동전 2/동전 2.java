import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= k) {
                for (int j = coin; j <= k; j++) {
                    if (j == coin) {
                        dp[j] = 1;
                    } else {
                        if (dp[j - coin] != 0) {
                            if (dp[j] == 0) dp[j] = dp[j - coin] + 1;
                            else dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                        }
                    }
                }
            }
        }
        if (dp[k] == 0) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}