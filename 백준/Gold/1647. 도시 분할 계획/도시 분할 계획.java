import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // MST 만들고 가장 긴 간선을 끊기
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[2] < o2[2]) return -1;
                return 1;
            }
        });

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Integer[] {a, b, c});
        }

        // n - 1개로 연결
        int count = 0;
        int len = 0;
        int maxLen = 0;
        while (count < n - 1) {
            Integer[] edge = pq.poll();
            int a = findParent(edge[0]);
            int b = findParent(edge[1]);
            if (a != b) {
                union(a, b);
                if (maxLen < edge[2]) maxLen = edge[2];
                count++;
                len += edge[2];
            }
        }
        System.out.println(len - maxLen);
    }

    static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}