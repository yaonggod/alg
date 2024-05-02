import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        int[][] tree = new int[n][2];
        for (int[] e : edges) {
            int parent = e[0];
            int son = e[1];
            if (tree[parent][0] == 0) {
                tree[parent][0] = son;
            } else {
                tree[parent][1] = son;
            }
        }
        
        Queue<Integer[]> queue = new LinkedList<>();
        int answer = 0;
        // 지금 a[0]에 있고, 양이 a[1]마리 늑대가 a[2]마리 있고, 앞으로 가야할 곳을 비트마스킹 할거
        queue.offer(new Integer[] {0, 1, 0, 0});
        
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            answer = Math.max(from[1], answer);
            
            // 다음에 갈 수 있는 곳
            // 0에서 1과 8을 색칠해서 1을 가더라도 나중에 8에 방문할 여지를 줌 
            for (int to : tree[from[0]]) {
                if (to != 0) from[3] = from[3] | (1 << to);
            }
            
            for (int b = 0; b < n; b++) {
                if ((from[3] & 1 << b) != 0) {
                    int next = from[3] & ~(1 << b);
                    
                    // 늑대다
                    if (info[b] == 1) {
                        if (from[1] != from[2] + 1) {
                            queue.offer(new Integer[] {b, from[1], from[2] + 1, next});
                        }
                    }
                    
                    // 양이다
                    else {
                        queue.offer(new Integer[] {b, from[1] + 1, from[2], next});
                    }
                }
            }
        }
        return answer;
    }
}