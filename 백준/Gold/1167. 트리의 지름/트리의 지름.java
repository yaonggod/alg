import java.io.*;
import java.util.*;


public class Main {
    static int n, start, maxlen;
    static List<Integer[]>[] route;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        route = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            route[i] = new ArrayList<>();
        }
        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int length = Integer.parseInt(st.nextToken());
                route[v].add(new Integer[] {to, length});
            }
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
        
        System.out.println(maxlen);
    }

    static void dfs(int from, int length) {
        if (length > maxlen) {
            maxlen = length;
            start = from;
        }
        for (Integer[] to : route[from]) {
            if (!visited[to[0]]) {
                visited[to[0]] = true;
                dfs(to[0], length + to[1]);
            }
        }
    }
}