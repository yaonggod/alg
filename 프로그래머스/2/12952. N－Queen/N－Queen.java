import java.util.*;

class Solution {
    static boolean[][] chess;
    static boolean[] visited;
    static Set<Integer> diag;
    static Set<Integer> diag2;
    static int N, answer;
    public int solution(int n) {
        chess = new boolean[n][n];
        visited = new boolean[n];
        diag = new HashSet<>();
        diag2 = new HashSet<>();
        N = n;
        answer = 0;
        nqueen(0);
        return answer;
    }
    
    static void nqueen(int row) {
        if (row == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            // 직선
            if (visited[i]) continue;
            
            // 대각선
            if (diag.contains(row - i)) continue;
            if (diag2.contains(row + i)) continue;
            
            // 넣어보자
            chess[row][i] = true;
            diag.add(row - i);
            diag2.add(row + i);
            visited[i] = true;
            nqueen(row + 1);
            diag.remove(row - i);
            diag2.remove(row + i);
            visited[i] = false;
        }
    }
}