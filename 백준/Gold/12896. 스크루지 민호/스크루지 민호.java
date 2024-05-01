import java.io.*;
import java.util.*;


public class Main {
    static int n, start, maxlen;
    static List<Integer>[] route;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        route = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            route[i] = new ArrayList<>();
        }
        StringTokenizer st = null;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            route[u].add(v);
            route[v].add(u);
        }

        // 임의의 도시에서 가장 멀리 떨어져 있는 도시 찾기
        start = 1;
        maxlen = 0;
        visited = new boolean[n + 1];
        visited[start] = true;
        dfs(start, 0);
        // 그 도시에서 또 가장 멀리 떨어져 있는 도시 찾기
        maxlen = 0;
        visited = new boolean[n + 1];
        visited[start] = true;
        dfs(start, 0);
        // 두 도시 간 (거리 + 1) / 2
        System.out.println((maxlen + 1) / 2);
    }

    static void dfs(int from, int length) {
        if (length > maxlen) {
            maxlen = length;
            start = from;
        }
        for (int to : route[from]) {
            if (!visited[to]) {
                visited[to] = true;
                dfs(to, length + 1);
            }
        }
    }
}