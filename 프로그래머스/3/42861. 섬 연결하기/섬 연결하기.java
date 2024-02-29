import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        // 비용 작은 순서대로
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int result = 0;
        int count = 0;
        for (int[] c : costs) {
            if (count == n - 1) break;
            int a = c[0];
            int b = c[1];
            int w = c[2];
            if (find(a) != find(b)) {
                union(a, b);
                result += w;
                count++;
            }
        }
        return result;
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x < y) {
            parent[y] = x;
        } else if (y < x) {
            parent[x] = y;
        }
    }
}