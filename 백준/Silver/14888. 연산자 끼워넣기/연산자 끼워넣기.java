import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int maxVal = -Integer.MAX_VALUE;
    static int minVal = Integer.MAX_VALUE;
    static int n;
    static int[] A, ys;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        ys = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ys[i] = Integer.parseInt(st.nextToken());
        }

        calc(A[0], 1);
        System.out.println(maxVal);
        System.out.println(minVal);
    }

    static void calc(int total, int idx) {
        if (idx == n) {
            if (total > maxVal) maxVal = total;
            if (total < minVal) minVal = total;
            return;
        }
        // idx번째 수를 연산하기
        // +
        if (ys[0] > 0) {
            ys[0]--;
            calc(total + A[idx], idx + 1);
            ys[0]++;
        }
        // -
        if (ys[1] > 0) {
            ys[1]--;
            calc(total - A[idx], idx + 1);
            ys[1]++;
        }
        // +
        if (ys[2] > 0) {
            ys[2]--;
            calc(total * A[idx], idx + 1);
            ys[2]++;
        }
        // /
        if (ys[3] > 0) {
            ys[3]--;
            if (total < 0 && A[idx] > 0) {
                calc(-(-total / A[idx]), idx + 1);
            } else {
                calc(total / A[idx], idx + 1);
            }
            ys[3]++;
        }
    }
}