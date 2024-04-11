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
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        List<Integer>[] city = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            city[i] = new ArrayList<>();
        }

        // 경로
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a -> b만
            city[a].add(b);
        }

        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        Set<Integer> visited = new HashSet<>();
        visited.add(x);
        for (int l = 1; l <= k; l++) {
            // x로부터 거리가 l인 도시들을 큐에 넣음
            int size = queue.size();
            // 큐 끝남
            if (size == 0) break;
            for (int i = 0; i < size; i++) {
                int from = queue.poll();
                for (int to : city[from]) {
                    if (!visited.contains(to)) {
                        visited.add(to);
                        queue.offer(to);
                    }
                }
            }
            
        }
        if (queue.isEmpty()) {
            System.out.println(-1);
        } else {
            while (!queue.isEmpty()) {
                answer.add(queue.poll());
            }
            Collections.sort(answer);
            StringBuilder sb = new StringBuilder();
            for (Integer integer : answer) {
                sb.append(integer).append("\n");
            }
            System.out.println(sb);
        }
    }


}