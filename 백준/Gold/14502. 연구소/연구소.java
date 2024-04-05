import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m;
    static int empty = 0;
    static int virus = 0;
    static boolean[] visited;
    static int[][] walls;
    static int[][] viruses;
    static int maxSize = 0;
    static int[][] board;
    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 빈공간, 바이러스 위치 파악
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    empty++;
                } else if (board[i][j] == 2) {
                    virus++;
                }
            }
        }
        walls = new int[empty][2];
        viruses = new int[virus][2];
        visited = new boolean[empty];
        int wallIdx = 0;
        int vIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    walls[wallIdx][0] = i;
                    walls[wallIdx++][1] = j;
                } else if (board[i][j] == 2) {
                    viruses[vIdx][0] = i;
                    viruses[vIdx++][1] = j;
                }
            }
        }
        chooseWall(0, 0, -1, -1, -1);
        System.out.println(maxSize);
    }

    static int[][] copyBoard() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    // 벽 3개 고르기
    static void chooseWall(int idx, int count, int w1, int w2, int w3) {
        if (count == 3) {
            int[][] newBoard = copyBoard();
            newBoard[walls[w1][0]][walls[w1][1]] = 1;
            newBoard[walls[w2][0]][walls[w2][1]] = 1;
            newBoard[walls[w3][0]][walls[w3][1]] = 1;
            spread(newBoard);
            return;
        }

        if (idx == empty) return;

        // idx를 넣을까 말까
        if (count == 0) {
            chooseWall(idx + 1, count + 1, idx, w2, w3);
        } else if (count == 1) {
            chooseWall(idx + 1, count + 1, w1, idx, w3);
        } else if (count == 2) {
            chooseWall(idx + 1, count + 1, w1, w2, idx);
        }
        chooseWall(idx + 1, count, w1, w2, w3);

    }

    static void spread(int[][] board) {
        // 빈 곳에서 벽 3개를 세음
        int safe = empty - 3;
        for (int i = 0; i < virus; i++) {
            Queue<Integer[]> queue = new LinkedList<>();
            queue.offer(new Integer[] {viruses[i][0], viruses[i][1]});
            while (!queue.isEmpty()) {
                Integer[] now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0) {
                        board[nx][ny] = 2;
                        safe--;
                        queue.offer(new Integer[] {nx, ny});
                    }
                }
            }
        }
        if (safe > maxSize) maxSize = safe;
    }
}