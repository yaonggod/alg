import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 세로 가로
        // 집 1 1 학교 n m
        int[][] map = new int[n + 1][m + 1];
        
        map[1][1] = 1;
        for (int[] p : puddles) {
            map[p[1]][p[0]] = -1;
        }
        
        // 나머지 부분
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 갈 수 있다
                if (!(i == 1 && j == 1) && map[i][j] != -1) {
                    int up = map[i - 1][j] == -1 ? 0 : map[i - 1][j];
                    int left = map[i][j - 1] == -1 ? 0 : map[i][j - 1];
                    
                    map[i][j] = (up + left) % 1000000007;
                }
            }
        }
        
        return map[n][m];
    }
}