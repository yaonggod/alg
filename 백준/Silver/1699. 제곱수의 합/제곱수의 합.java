import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[n + 1];
        // 1 ^ 2
        for (int i = 1; i <= n; i++) {
            count[i] = i;
        }
        for (int i = 2; i * i <= n; i++) {
            count[i * i] = 1;
            for (int j = i * i + 1; j <= n; j++) {
                count[j] = Math.min(count[j - (i * i)] + 1, count[j]);
            }
        }
        System.out.println(count[n]);
    }

}