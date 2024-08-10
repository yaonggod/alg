import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int range = c;
        int[] price = new int[n];
        int[] plus = new int[n];
        int add = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            plus[i] = Integer.parseInt(st.nextToken());

            // 적어도 c명이니까 더 늘려도 됨
            add = Math.max(add, plus[i]);
        }

        range += add;
        int[] possible = new int[range + 1];
        Arrays.fill(possible, -1);
        possible[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = plus[i]; j <= range; j++) {
                if (possible[j - plus[i]] >= 0) {
                    if (possible[j] == -1) {
                        possible[j] = possible[j - plus[i]] + price[i];
                    } else {
                        possible[j] = Math.min(possible[j - plus[i]] + price[i], possible[j]);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = c; i <= range; i++) {
            if (possible[i] != -1) {
                ans = Math.min(ans, possible[i]);
            }
        }
        System.out.println(ans);
    }

}