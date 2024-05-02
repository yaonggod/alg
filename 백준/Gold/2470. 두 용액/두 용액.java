import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] liquid = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquid);

        int start = 0;
        int end = n - 1;

        // 다 양수
        if (liquid[start] >= 0 && liquid[end] >= 0) {
            System.out.println(liquid[start] + " " + liquid[start + 1]);
            return;
        }

        // 다 음수
        if (liquid[start] < 0 && liquid[end] < 0) {
            System.out.println(liquid[end - 1] + " " + liquid[end]);
            return;
        }

        int gap = Integer.MAX_VALUE;
        int a1 = 0;
        int a2 = 0;

        while (start < end) {
            int feat = liquid[start] + liquid[end];

            // 0에 더 가까움
            if (Math.abs(feat) < gap) {
                a1 = start;
                a2 = end;
                gap = Math.abs(feat);
                if (feat == 0) break;
            }

            // 너무 크다
            if (feat > 0) {
                end--;
            // 너무 작다
            } else if (feat < 0) {
                start++;
            }
        }
        System.out.println(liquid[a1] + " " + liquid[a2]);
    }

}