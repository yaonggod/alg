import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 둘 다 양수
        if (arr[0] >= 0 && arr[1] >= 0) {
            System.out.println(arr[0] + arr[1]);
            return;
        }

        // 둘 다 음수
        if (arr[n - 2] < 0 && arr[n - 1] < 0) {
            System.out.println(arr[n - 2] + arr[n - 1]);
            return;
        }

        int start = 0;
        int end = n - 1;

        long ans = arr[start] + arr[end];
        while (start < end) {
            long feat = arr[start] + arr[end];

            if (feat == 0) {
                ans = feat;
                break;
            }

            if (Math.abs(feat) < Math.abs(ans)) {
                ans = feat;
            }

            if (feat < 0) {
                start++;
            } else if (feat > 0) {
                end--;
            }
        }

        System.out.println(ans);
    }
}