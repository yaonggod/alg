import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static int maxSize = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                tetromino(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(maxSize);
    }

    // 단일 방향으로 쭉 가기 1 2 3 4
    static void tetromino(int x, int y, int count, int total) {
        // 완성
        if (count == 4) {
            if (total > maxSize) maxSize = total;
            return;
        }

        // 단일 방향으로 쭉 가기 1 2 3 4는 가능
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (canGo(nx, ny)) {
                visited[nx][ny] = true;
                tetromino(nx, ny, count + 1, total + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }

        // 2개 모였을 때 ㅗ 만들기 위해 지금 위치에서 갈 수 있는 곳 3개 중에서 2개 고르기
        if (count == 2) {
            for (int d = 0; d < 3; d++) {
                int ax = x + dx[d];
                int ay = y + dy[d];
                if (canGo(ax, ay)) {
                    visited[ax][ay] = true;
                    for (int d2 = d + 1; d2 < 4; d2++) {
                        int bx = x + dx[d2];
                        int by = y + dy[d2];
                        if (canGo(bx, by)) {
                            visited[bx][by] = true;
                            tetromino(bx, by, 4, total + board[ax][ay] + board[bx][by]);
                            visited[bx][by] = false;
                        }
                    }
                    visited[ax][ay] = false;
                }
            }
        }
    }

    static boolean canGo(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && !visited[x][y];
    }
}