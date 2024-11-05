import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        int[] eat = new int[d + 1];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // 쿠폰은 무조건 먹음
        int ate = 1;
        for (int i = 0; i < k; i++) {
            eat[sushi[i]]++;
            if (sushi[i] != c && eat[sushi[i]] == 1) {
                ate++;
            }
        }

        // start에서 k개씩만 더해줌
        int start = 0;

        int maxKey = ate;
        while (start < n - 1) {
            // 앞에걸 빼고 뒤에걸 넣음
            int delete = sushi[start];
            int add = sushi[(start + k) % n];

            // 뺏더니 사라졌음
            eat[delete]--;
            if (delete != c && eat[delete] == 0) {
                ate--;
            }
            // 더했더니 새로 생겼음
            eat[add]++;
            if (add != c && eat[add] == 1) {
                ate++;
            }
            maxKey = Math.max(maxKey, ate);
            start++;
        }
        System.out.println(maxKey);
    }

}