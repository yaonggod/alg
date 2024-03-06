class Solution {
    public int[] solution(int n) {
        // 아래, 오른쪽, 대각선
        int[] dx = new int[] {1, 0, -1};
        int[] dy = new int[] {0, 1, -1};
        int d = 0;
        int x = 0;
        int y = 0;
        int[][] matrix = new int[n][n];
        int number = 1;
        while (true) {
            // 넣는다
            matrix[x][y] = number;
            // 다음 갈 곳
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 못간다 == 벗어난다 || 이미 숫자가 있다
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || matrix[nx][ny] != 0) {
                nx -= dx[d];
                ny -= dy[d];
                d++;
                if (d == 3) d = 0;
                // 그래도 못가면 이제 끝
                nx += dx[d];
                ny += dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || matrix[nx][ny] != 0) {
                    break;
                }
            }
            x = nx;
            y = ny;
            number++;
        }
        int[] answer = new int[number];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    answer[idx] = matrix[i][j];
                    idx++;
                }
            }
        }
        return answer;
    }
}