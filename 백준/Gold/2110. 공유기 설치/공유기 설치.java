import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        // 집 간 거리
        int s = 1;
        int e = house[n - 1] - house[0];

        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;
            int count = 1;
            int prev = house[0];
            for (int i = 1; i < n; i++) {
                if (house[i] - prev >= mid) {
                    count++;
                    prev = house[i];
                }
            }
            if (count >= c) {
                result = Math.max(mid, result);
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        System.out.println(result);
    }


}