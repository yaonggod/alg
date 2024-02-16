import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];
            matrix[win][lose] = 1;
            matrix[lose][win] = -1;
        }
        // A가 B를 이기고 B가 C를 이기면 A는 C를 이김
        for (int c = 1; c <= n; c++) {
            for (int b = 1; b <= n; b++) {
                for (int a = 1; a <= n; a++) {
                    if (a != c && b != c && matrix[a][c] == 0) {
                        if (matrix[a][b] == 1 && matrix[b][c] == 1) {
                            matrix[a][c] = 1;
                            matrix[c][a] = -1;
                        } else if (matrix[a][b] == -1 && matrix[b][c] == -1) {
                            matrix[a][c] = -1;
                            matrix[c][a] = 1;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int result = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && matrix[i][j] != 0) {
                    result++;
                }
            }
            if (result == n - 1) count++; 
        }
        return count;
    }
}