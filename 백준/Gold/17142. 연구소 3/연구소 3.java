import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m;
    static int virus = 0;
    static int target = 0;
    static boolean possible = false;
    static boolean[] visited;
    static int[][] viruses;
    static int minSec = Integer.MAX_VALUE;
    static int[][] board;
    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 바이러스 위치 파악
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    virus++;
                }
                // 오염시킬 대상 
                if (board[i][j] == 0) {
                    target++;
                }
            }
        }

        viruses = new int[virus][2];
        visited = new boolean[virus];
        int vIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    viruses[vIdx][0] = i;
                    viruses[vIdx++][1] = j;
                }
            }
        }
        activate(0, 0);

        System.out.println(!possible ? -1 : minSec);
    }

    static int[][] copyBoard() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    // 활성화하기
    static void activate(int idx, int count) {
        if (count == m) {
            int[][] newBoard = copyBoard();
            for (int i = 0; i < virus; i++) {
                if (visited[i]) {
                    newBoard[viruses[i][0]][viruses[i][1]] = 3;
                }
            }
            spread(newBoard);
            return;
        }

        if (idx == virus) return;

        // idx를 넣자
        visited[idx] = true;
        activate(idx + 1, count + 1);
        visited[idx] = false;

        // 넣지 말자
        activate(idx + 1, count);

    }

    static void spread(int[][] newBoard) {
        int sec = 0;
        int contaminated = target;
        Queue<Integer[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newBoard[i][j] == 3) {
                    queue.offer(new Integer[] {i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            Integer[] v = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = v[0] + dx[d];
                int ny = v[1] + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (newBoard[nx][ny] == 0) {
                        newBoard[nx][ny] = 4;
                        queue.offer(new Integer[] {nx, ny, v[2] + 1});
                        contaminated--;
                        if (v[2] + 1 > sec) sec = v[2] + 1;
                    // 죽은 바이러스는 시간 카운팅에 포함 ㄴㄴ 
                    } else if (newBoard[nx][ny] == 2) {
                        newBoard[nx][ny] = 4;
                        queue.offer(new Integer[] {nx, ny, v[2] + 1});
                    }
                }
            }
            if (sec > minSec) break;
            if (contaminated == 0) break;
        }

        if (contaminated == 0) {
            possible = true;
            if (sec < minSec) minSec = sec;
        }
    }
}