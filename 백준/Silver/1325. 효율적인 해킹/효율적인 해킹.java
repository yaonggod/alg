import java.io.*;
import java.util.*;

public class Main {
    static int[] treeSize;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        treeSize = new int[n + 1];

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 애기
            int b = Integer.parseInt(st.nextToken());
            // 부모
            int p = Integer.parseInt(st.nextToken());
            // 애기 -> 부모 타고들어가서 부모의 애기 하나씩 더해줌
            tree[b].add(p);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            bfs(i);
        }


        int maxCom = 0;
        for (int i = 1; i <= n; i++) {
            if (treeSize[i] > maxCom) {
                maxCom = treeSize[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (treeSize[i] == maxCom) {
               sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
    static void bfs(int p) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(p);
        visited[p] = true;
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (int to : tree[from]) {
                if (!visited[to]) {
                    visited[to] = true;
                    treeSize[to]++;
                    queue.add(to);
                }
            }
        }
    }
}