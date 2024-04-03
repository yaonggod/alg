import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int n = board[0].length;
        Stack<Integer>[] st = new Stack[n];
        for (int i = 0; i < n; i++) {
            st[i] = new Stack<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    st[i].push(board[j][i]);
                } else {
                    break;
                }
            }
        }
        
        Stack<Integer> game = new Stack<>();
        int answer = 0;
        for (int m : moves) {
            if (!st[m - 1].isEmpty()) {
                int emo = st[m - 1].pop();
                if (!game.isEmpty() && emo == game.peek()) {
                    game.pop();
                    answer += 2;
                } else {
                    game.push(emo);
                }
            }
        }
        return answer;
    }
}