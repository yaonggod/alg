import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int length = n + 1;

        int sum = A[0];

        while (start < n && end < n) {
            // 너무 작다
            if (sum < s) {
                end++;
                if (end < n) sum += A[end];
            }

            // 맞다
            else {
                if (end - start + 1 < length) {
                    length = end - start + 1;
                }
                sum -= A[start];
                start++;
            }

            if (length == 1) break;
        }

        if (length == n + 1) System.out.println(0);
        else System.out.println(length);

    }

}