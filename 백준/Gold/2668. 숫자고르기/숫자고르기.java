import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 사이클 만들기
        boolean[] visited = new boolean[n + 1];

        int[][] graph = new int[2][n];
        for (int i = 0; i < n; i++) {
            graph[0][i] = i + 1;
            graph[1][i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Set<Integer> set = new HashSet<>();
                set.add(i);
                int now = i;
                while (true) {
                    int next = graph[1][now - 1];
                    // 사이클의 시작점을 찾음 
                    if (set.contains(next)) {
                        int start = next;
                        pq.offer(start);
                        int end = now;
                        while (start != end) {
                            start = graph[1][start - 1];
                            pq.offer(start);
                            if (start == end) break;
                        }
                        break;
                    }
                    else if (visited[next]) break;
                    else {
                        visited[next] = true;
                        set.add(next);
                        now = next;
                    }
                }
            }
        }

        int[] answer = new int[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.length).append("\n");
        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}