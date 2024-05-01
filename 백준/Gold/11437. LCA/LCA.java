import java.io.*;
import java.util.*;


public class Main {
    static int n;
    static int[] parent, depth;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        // 부모
        parent = new int[n + 1];
        Arrays.fill(parent, -1);
        parent[1] = 0;
        // 깊이
        depth = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = null;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // 트리 연결하기
        depth[1] = 1;
        dfs(1, 1);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int now, int length) {
        for (Integer to : tree[now]) {
            if (depth[to] == 0) {
                depth[to] = length + 1;
                parent[to] = now;
                dfs(to, length + 1);
            }
        }
    }

    static int lca(int a, int b) {
        int da = depth[a];
        int db = depth[b];
        // 둘의 높이를 똑같이 맞춰준다
        if (da > db) {
            while (da != db) {
                a = parent[a];
                da--;
            }
        } else if (da < db) {
            while (db != da) {
                b = parent[b];
                db--;
            }
        }
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

}