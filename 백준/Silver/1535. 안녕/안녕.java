import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        // 체력 소모
        int[] hp = new int[101];

        int[] minus = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            minus[i] = Integer.parseInt(st.nextToken());
        }
        int[] plus = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            plus[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 100; j >= minus[i]; j--) {
                hp[j] = Math.max(hp[j - minus[i]] + plus[i], hp[j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            ans = Math.max(ans, hp[i]);
        }
        System.out.println(ans);
    }

}