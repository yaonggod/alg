class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};
        
        int answer = 0;
        int n = board.length;
        for (int d = 0; d < 4; d++) {
            int nx = h + dx[d];
            int ny = w + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (board[h][w].equals(board[nx][ny])) answer++;
            }
        }
        return answer;
    }
}