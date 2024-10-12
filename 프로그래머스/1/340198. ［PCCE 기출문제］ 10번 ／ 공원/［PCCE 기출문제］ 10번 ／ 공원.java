import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        HashSet<Integer> can = new HashSet<>();
        
        // 가로 세로
        int n = park.length;
        int m = park[0].length;
        int[][] dp = new int[n][m];
        
        // 깔 수 있는 곳
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (park[i][j].equals("-1")) {
                    dp[i][j] = 1;
                }                
            }
        }
        
        int maxSize = 0;
        
        // 사각형 크기
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] > 0) {
                    int fill = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = fill + 1;
                    maxSize = Math.max(dp[i][j], maxSize);
                }             
            }
        }
        
        Arrays.sort(mats);
        int answer = -1;
        for (int i = 0; i < mats.length; i++) {
            if (mats[i] > maxSize) break;
            answer = Math.max(answer, mats[i]);
        }
        return answer;
    }
}