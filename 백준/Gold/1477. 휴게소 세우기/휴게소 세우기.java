import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] rest = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rest);

        int[] interval = new int[n + 1];
        int s = 0;

        // 간격
        for (int i = 0; i < n; i++) {
            interval[i] = rest[i] - s;
            s = rest[i];
        }
        interval[n] = l - s;

        int left = 1;
        int right = l - 1;
        int ans = l;

        while (left <= right) {
            // mid의 간격으로 나눴을 때 세워야 하는 휴게소 갯수
            int mid = (left + right) / 2;
            int count = 0;

            for (int i = 0; i < n + 1; i++) {
                int add = (interval[i] - 1) / mid;
                count += add;
            }

            if (count <= m) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }



}