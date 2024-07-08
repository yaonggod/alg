import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        HashMap<Long, Long> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i == 0) {
                arr[i] = num % m;
            } else {
                arr[i] = (arr[i - 1] + num) % m;
            }
            // 지금까지의 합을 m으로 나눈 나머지
            // 나머지가 같은 뒤의 구간에서 앞의 구간을 뺀 길이 구하기
            // 0은 예외, 맨 앞에서부터 0이 마지막으로 나오는 구간까지
            map.put(arr[i], map.getOrDefault(arr[i], (long) 0) + 1);
        }

        long answer = 0;
        for (Long key : map.keySet()) {
            long num = map.get(key);
            if (key == 0) num++;
            answer += num * (num - 1) / 2;
        }
        System.out.println(answer);

    }

}