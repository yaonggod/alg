import java.io.*;
import java.util.*;

public class Main {
    static int[] village;
    static List<Integer>[] graph;

    // 우수 마을 ㅇㅇ -> 부모 우수 마을 ㄴㄴ
    // 우수 마을 ㄴㄴ -> 부모 우수 마을 ㅇㅇ ㄴㄴ 둘 다
    // 100000000
    static int[][] dp;
    static int n;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 마을
        n = Integer.parseInt(br.readLine());

        // 마을 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        village = new int[n];
        for (int i = 0; i < n; i++) {
            village[i] = Integer.parseInt(st.nextToken());
        }

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
            // 양방향
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int parent) {
        visited[parent] = true;
        // 우수마을 ㄴㄴ
        dp[parent][0] = 0;
        // 우수마을 ㅇㅇ
        dp[parent][1] = village[parent - 1];

        for (int child : graph[parent]) {
            if (visited[child]) continue;
            dfs(child);

            // 자식은 우수할수도 아닐수도 있음
            dp[parent][0] += Math.max(dp[child][0], dp[child][1]);
            // 내가 우수해서 자식이 우수하면 안됨
            dp[parent][1] += dp[child][0];
        }
    }
}