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

        int answer = 0;
        for (int i = 0; i < n; i++) {
            // 나
            int me = arr[i];
            // 이분탐색
            int start = 0;
            int end = n - 1;

            while (start < end) {
                // 나말고
                if (start == i) start++;
                else if (end == i) end--;
                if (start >= end) break;

                long sum = arr[start] + arr[end];
                // 줄이기
                if (sum > me) end--;
                // 키우기
                else if (sum < me) start++;
                else {
                    answer++;
                    break;
                }

            }
        }
        System.out.println(answer);
    }


}