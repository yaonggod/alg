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

        int[] maxVal = new int[n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            // 마지막에 할 수 있는거 
            if (i + t[i] == n) {
                dp[i] = p[i];
            // 건너와서 할 수 있는거 
            } else if (i + t[i] < n) {
                // 뒷날에서 건너온 이익을 추가할 수 있다 
                if (dp[i] < maxVal[i + t[i]] + p[i]) {
                    dp[i] = maxVal[i + t[i]] + p[i];
                }
            }
            // i - 1이 나와도 i가 더 크면 거기로 가는게 이득이다 
            if (dp[i] > max) max = dp[i];
            maxVal[i] = max;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (maxVal[i] > answer) answer = maxVal[i];
        }
        System.out.println(answer);
    }
}