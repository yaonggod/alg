import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 학생
        int[] students = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            // b의 앞에 와야할 학생 수
            students[b]++;
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> row = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (students[i] == 0) {
                row.offer(i);
                sb.append(i);
                sb.append(" ");
            }
        }
        while (!row.isEmpty()) {
            int s = row.poll();
            for (int next : graph[s]) {
                students[next]--;
                if (students[next] == 0) {
                    row.offer(next);
                    sb.append(next);
                    sb.append(" ");
                }
            }
        }
        System.out.println(sb);
    }
}