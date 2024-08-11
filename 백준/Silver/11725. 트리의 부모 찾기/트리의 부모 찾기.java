import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] root;
    static ArrayList<Integer>[] tree;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        root = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // 트리 탐색
        visited[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            Integer from = queue.poll();
            for (Integer to : tree[from]) {
                if (!visited[to]) {
                    visited[to] = true;
                    root[to] = from;
                    queue.offer(to);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(root[i]).append("\n");
        }
        System.out.println(sb);
    }

}