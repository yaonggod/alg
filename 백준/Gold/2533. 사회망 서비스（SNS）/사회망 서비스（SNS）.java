import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    
    // 지금까지 모은 얼리어답터 수, 내가 얼리어답터면 나를 포함하므로 += 1
    static int[][] dp;
    static int n;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][2];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int parent) {
        visited[parent] = true;
        // 나는 얼리어답터가 아니에요
        dp[parent][0] = 0;
        // 나는 얼리어답터에요
        dp[parent][1] = 1;

        for (int child : graph[parent]) {
            if (visited[child]) continue;
            dfs(child);

            // 나는 얼리어답터가 아니라 자식은 무조건 얼리어답터야 함
            dp[parent][0] += dp[child][1];
            // 상관없음
            dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}