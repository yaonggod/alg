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

        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 앞의 숫자보다 작다 == 수열을 이을 수 있다
                if (arr[i] < arr[j]) {
                    answer[i] = Math.max(answer[i], answer[j] + 1);
                }
            }
        }

        int soldier = n;
        for (int i = 0; i < n; i++) {
            soldier = Math.min(soldier, n - answer[i]);
        }

        System.out.println(soldier);
    }

}