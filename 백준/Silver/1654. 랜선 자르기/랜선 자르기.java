import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] lan = new int[k];
        int maxlan = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            maxlan = Math.max(maxlan, lan[i]);
        }

        // 길이가 end인 랜선으로 만들겠다
        long start = 1;
        long end = maxlan;

        while (start <= end) {
            long mid = (start + end) / 2;
            long count = 0;
            for (int i = 0; i < k; i++) {
                count += lan[i] / mid;
            }

            // 달성
            if (count >= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }


}