import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] arr = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            max = Math.max(max, arr[i]);
        }
        // 1 ~ m명 * max 초 범위 내
        long s = 1;
        long e = m * max;

        while (s <= e) {
            long mid = (s + e) / 2;
            long count = 0;

            // mid초동안 심사대가 처리하는 사람 수
            for (int i = 0; i < n; i++) {
                count += mid / arr[i];
                if (count >= m) break;
            }

            if (count >= m) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(s);
    }


}