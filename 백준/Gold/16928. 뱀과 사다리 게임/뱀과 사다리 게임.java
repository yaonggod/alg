import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 이동 정보
        Map<Integer, Integer> move = new HashMap<>();
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            move.put(f, t);
        }

        Queue<Integer[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        // i번 칸에 j번 굴려서 갔음
        queue.offer(new Integer[] {1, 0});
        visited[1] = true;
        while (true) {
            Integer[] from = queue.poll();
            if (from[0] == 100) {
                System.out.println(from[1]);
                return;
            }

            for (int d = 1; d <= 6; d++) {
                int next = from[0] + d;
                if (move.containsKey(next)) {
                    next = move.get(next);
                }
                if (next <= 100 && !visited[next]) {
                    visited[next] = true;
                    queue.offer(new Integer[] {next, from[1] + 1});
                }
            }
        }
    }
}