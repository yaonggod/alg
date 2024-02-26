import java.util.*;

class Solution {
    static int gap = 100;
    static List<Integer>[] map;
    static int[][] w;
    static int tower; 
    public int solution(int n, int[][] wires) {
        w = wires;
        tower = n;
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < wires.length; i++) {
            map[wires[i][0]].add(wires[i][1]);
            map[wires[i][1]].add(wires[i][0]);
        }
        
        for (int i = 0; i < wires.length; i++) {
            disconnect(i);
        }
        
        return gap;
    }
    
    // index번째 전선 끊기
    static void disconnect(int index) {
        int a = w[index][0];
        int b = w[index][1];
        
        boolean[] visited = new boolean[tower + 1];
        visited[1] = true;
        
        // 1번을 중심으로 연결된 모든 곳 탐색
        int size = 1;
        Queue<Integer> connect = new LinkedList<>();
        connect.offer(1);
        while (!connect.isEmpty()) {
            int from = connect.poll();
            for (int to : map[from]) {
                // 끊어진 곳이 아니면 가라
                if (!(from == a && to == b) && !(from == b && to == a) && !visited[to]) {
                    visited[to] = true;
                    connect.offer(to);
                    size++;
                }
            }
        }
        
        // 크기 차이
        if (Math.abs(size * 2 - tower) < gap) {
            gap = Math.abs(size * 2 - tower);
        }
    }
}