import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
            // 할 수 있는 업무다
            if (i + t[i] <= n) dp[i] = p[i];
            else p[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            // i로부터 t일 뒤 아무때나
            for (int j = i + t[i]; j < n; j++) {
                if (p[j] != 0 && dp[i] + p[j] > dp[j]) {
                    dp[j] = dp[i] + p[j];
                }
            }
        }
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxVal) maxVal = dp[i];
        }
        System.out.println(maxVal);
    }
}