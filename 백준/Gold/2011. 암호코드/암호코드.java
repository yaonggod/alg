import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] code = br.readLine().toCharArray();
        int n = code.length;
        int[] dp = new int[n];

        if (code[0] == '0') {
            System.out.println(0);
            return;
        }

        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 갑자기 0이 나옴 -> 무효
            if (code[i - 1] != '1' && code[i - 1] != '2' && code[i] == '0') {
                System.out.println(0);
                return;
            }

            // 123456789 -> 코드 하나 ㅇㅈ
            if (code[i] != '0') {
                dp[i] = dp[i - 1];
            }

            int add = i - 2 >= 0 ? dp[i - 2] : 1;

            // 10, 20
            if (code[i] == '0') {
                dp[i] = (add + dp[i]) % 1000000;
            // 11 12 13 14 15 16 17 18 19
            } else if (code[i - 1] == '1') {
                dp[i] = (add + dp[i]) % 1000000;
            // 21 22 23 24 25 26
            } else if (code[i - 1] == '2' && code[i] - '0' <= 6) {
                dp[i] = (add + dp[i]) % 1000000;
            }

        }

        System.out.println(dp[n - 1]);
    }


}