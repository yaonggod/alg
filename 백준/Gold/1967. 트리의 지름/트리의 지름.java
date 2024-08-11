import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer[]>[] tree;
    static int depth = 0;
    static int target = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            tree[parent].add(new Integer[] {child, length});
            tree[child].add(new Integer[] {parent, length});
        }

        // 루트에서 가장 먼 곳
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);

        // 가장 먼 곳에서 또 가장 먼 곳
        visited = new boolean[n + 1];
        visited[target] = true;
        depth = 0;
        dfs(target, 0);

        System.out.println(depth);
    }

    static void dfs(int n, int d) {
        // 자식들
        for (Integer[] to : tree[n]) {
            if (!visited[to[0]]) {
                visited[to[0]] = true;
                if (depth < d + to[1]) {
                    target = to[0];
                    depth = d + to[1];
                }
                dfs(to[0], d + to[1]);
            }
        }
    }

}