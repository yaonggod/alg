import java.io.*;
import java.util.*;

public class Main {
    // MST인데 반대로 길이가 긴것부터 뽑아내는...

    static int[] parent;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // end의 부모가 start가 될때까지
        int start = Math.min(s, e);
        int end = Math.max(s, e);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o2[2], o1[2]);
            }
        });
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            pq.offer(new Integer[] {h1, h2, k});
        }

        // 무게제한 가장 많은거
        int maxWeight = 0;

        while (!pq.isEmpty()) {
            Integer[] bridge = pq.poll();
            int from = Math.min(bridge[0], bridge[1]);
            int to = Math.max(bridge[0], bridge[1]);

            union(from, to);

            // 연결됨
            if (findParent(start) == findParent(end)) {
                maxWeight = bridge[2];
                break;
            }
        }
        System.out.println(maxWeight);
    }

    static int findParent(int a) {
        if (a == parent[a]) return a;
        return parent[a] = findParent(parent[a]);
    }

    static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        parent[Math.max(a, b)] = Math.min(a, b);
    }
}