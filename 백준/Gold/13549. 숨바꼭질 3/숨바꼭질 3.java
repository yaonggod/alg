import java.io.*;
import java.util.*;

public class Main {
    static int[] arrive = new int[100001];
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Arrays.fill(arrive, -1);
        queue.offer(n);
        arrive[n] = 0;
        tp(n);


        while (!queue.isEmpty()) {
            Integer from = queue.poll();

            if (from == k) break;

            if (from - 1 >= 0 && arrive[from - 1] == -1) {
                arrive[from - 1] = arrive[from] + 1;
                tp(from - 1);
                queue.offer(from - 1);
            }
            if (from - 1 == k) break;

            if (from + 1 <= 100000 && arrive[from + 1] == -1) {
                arrive[from + 1] = arrive[from] + 1;
                tp(from + 1);
                queue.offer(from + 1);
            }
            if (from - 1 == k) break;
        }

        System.out.println(arrive[k]);
    }
    static void tp(int x) {
        if (x == 0) return;
        int sec = arrive[x];
        x *= 2;
        while (x <= 100000) {
            if (arrive[x] == -1) {
                arrive[x] = sec;
                queue.offer(x);
            }
            x *= 2;
        }
    }
}