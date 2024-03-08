import java.util.*;

class Solution {
    // 우 하 좌 상
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    static int[][] matrix;
    static int[] answer;
    public int[] solution(int rows, int columns, int[][] queries) {
        matrix = new int[rows][columns];
        int fill = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = fill++;
            }
        }
        answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = turn(queries[i]);
        }
        return answer;
    }
    
    static int turn(int[] query) {
        int minNum = Integer.MAX_VALUE;
        int sx = query[0] - 1;
        int sy = query[1] - 1;
        int ex = query[2] - 1;
        int ey = query[3] - 1;
        int d = 0;
        
        int nx = sx;
        int ny = sy;
        int temp1 = matrix[sx][sy];
        int temp2 = 0;
        
        while (true) {
            // 값 체크하기
            if (temp1 < minNum) minNum = temp1;
            // 가야할 곳
            nx += dx[d];
            ny += dy[d];
            // 방향 전환
            if (!canGo(nx, ny, sx, sy, ex, ey)) {
                nx -= dx[d];
                ny -= dy[d];
                d++;
                nx += dx[d];
                ny += dy[d];
            }
            // 다왔다
            if (nx == sx && ny == sy) {
                matrix[nx][ny] = temp1;
                break;
            }
            // temp 넣기
            temp2 = matrix[nx][ny];
            matrix[nx][ny] = temp1;
            temp1 = temp2;
            
        }
        return minNum;
    }
    
    static boolean canGo(int nx, int ny, int sx, int sy, int ex, int ey) {
        return nx >= sx && nx <= ex && ny >= sy && ny <= ey;
    }
}