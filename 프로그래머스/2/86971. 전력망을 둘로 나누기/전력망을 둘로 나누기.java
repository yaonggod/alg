import java.util.*;

class Solution {
    static int gap, N;
    static List<Integer>[] map;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        gap = 100;
        N = n;
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < wires.length; i++) {
            map[wires[i][0]].add(wires[i][1]);
            map[wires[i][1]].add(wires[i][0]);
        }
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1);
        
        return gap;
    }
    
    static int dfs(int current) {
        // 나의 자식 노드의 개수
        int total = 0;
        for (int to : map[current]) {
            if (!visited[to]) {
                visited[to] = true;
                int count = dfs(to);
                gap = Math.min(Math.abs(N - count - count), gap);
                total += count;
            }
        }
        // 내 자식 노드 + 나
        return total + 1;
    }
}