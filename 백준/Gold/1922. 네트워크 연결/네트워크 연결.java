import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 컴퓨터
        int n = Integer.parseInt(br.readLine());
        // 선
        int m = Integer.parseInt(br.readLine());
        // 컴퓨터 연결 현황
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        // 비용 순으로 pq
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[2] - o2[2];
            }
        });
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Integer[] {a, b, c});
        }
        int edges = 0;
        int target = n - 1;
        int total = 0;
        while (!pq.isEmpty()) {
            if (edges == target) break;
            Integer[] data = pq.poll();
            int a = data[0];
            int b = data[1];
            int c = data[2];
            if (find(a) != find(b)) {
                union(a, b);
                total += c;
                edges++;
            }
        }
        System.out.println(total);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}