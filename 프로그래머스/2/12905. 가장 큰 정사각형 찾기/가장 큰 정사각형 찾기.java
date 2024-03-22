import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int maxSize = 0;
        // 복사하기
        int[][] square = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                square[i][j] = board[i][j];
                if (board[i][j] == 1) maxSize = 1;
            }
        }
        // 정사각형이 있으면 a, b, c 중 가장 작은거에 + 1
        // a, b, c == 가로, 세로, 대각선으로 이 길이만큼 채워서 왔다
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 1) {
                    int a = Math.min(square[i - 1][j - 1], square[i - 1][j]);
                    a = Math.min(a, square[i][j - 1]);
                    square[i][j] = a + 1;
                    if (square[i][j] > maxSize) maxSize = square[i][j];
                }
            }
        }
        return maxSize * maxSize;
    }
}