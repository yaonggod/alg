import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());
            // i까지 가는 방법
            int[] dp = new int[m + 1];
            for (int c : coin) {
                // 그냥 c로 바로 간다
                if (c <= m) dp[c]++;
                for (int i = c + 1; i <= m; i++) {
                    // i - c에서 c만큼 건너온다
                    dp[i] += dp[i - c];
                }
            }
            System.out.println(dp[m]);
        }

    }

}