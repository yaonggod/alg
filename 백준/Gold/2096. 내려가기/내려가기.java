import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[][] dp1, dp2;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp1 = new int[n][3];
        dp2 = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp1[i][0] = a;
            dp2[i][0] = a;
            dp1[i][1] = b;
            dp2[i][1] = b;
            dp1[i][2] = c;
            dp2[i][2] = c;
        }

        for (int i = 1; i < n; i++) {
            dp1[i][0] += Math.max(dp1[i - 1][0], dp1[i - 1][1]);
            dp1[i][1] += Math.max(Math.max(dp1[i - 1][0], dp1[i - 1][1]), dp1[i - 1][2]);
            dp1[i][2] += Math.max(dp1[i - 1][1], dp1[i - 1][2]);

            dp2[i][0] += Math.min(dp2[i - 1][0], dp2[i - 1][1]);
            dp2[i][1] += Math.min(Math.min(dp2[i - 1][0], dp2[i - 1][1]), dp2[i - 1][2]);
            dp2[i][2] += Math.min(dp2[i - 1][1], dp2[i - 1][2]);
        }

        int max = Math.max(Math.max(dp1[n - 1][0], dp1[n - 1][1]), dp1[n - 1][2]);
        int min = Math.min(Math.min(dp2[n - 1][0], dp2[n - 1][1]), dp2[n - 1][2]);

        System.out.println(max + " " + min);
    }

}