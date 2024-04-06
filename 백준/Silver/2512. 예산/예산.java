import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] budget = new int[n];
        int sum = 0;
        int maxB = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            sum += budget[i];
            if (budget[i] > maxB) maxB = budget[i];
        }
        int m = Integer.parseInt(br.readLine());

        // 모두 배정 가능
        if (m >= sum) {
            System.out.println(maxB);
            return;
        }

        // 모두 배정 불가능
        int start = 0;
        int end = maxB;
        int fixed = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            sum = 0;
            for (int i = 0; i < n; i++) {
                if (mid < budget[i]) {
                    sum += mid;
                } else {
                    sum += budget[i];
                }
            }
            if (sum <= m) {
                if (fixed < mid) {
                    fixed = mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(fixed);
    }
}