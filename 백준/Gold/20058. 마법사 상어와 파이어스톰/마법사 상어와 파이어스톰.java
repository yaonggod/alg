import java.io.*;
import java.util.*;


public class Main {
    static int n, q, size;
    static int[] L;

    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static int[][] ice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, n);

        ice = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = new int[q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < q; i++) {
            ice = spin(ice, (int) Math.pow(2, L[i]));
            ice = decrease(ice);
        }

        int total = 0;
        int largest = 0;

        Queue<Integer[]> queue;
        boolean[][] visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j] && ice[i][j] > 0) {
                    int iceSize = 1;
                    total += ice[i][j];
                    visited[i][j] = true;
                    queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});
                    while (!queue.isEmpty()) {
                        Integer[] from = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                                if (ice[nx][ny] > 0 && !visited[nx][ny]) {
                                    visited[nx][ny] = true;
                                    iceSize++;
                                    total += ice[nx][ny];
                                    queue.offer(new Integer[] {nx, ny});
                                }
                            }
                        }
                    }
                    if (iceSize > largest) largest = iceSize;
                }
            }
        }

        System.out.println(total);
        System.out.println(largest);
    }


    static int[][] spin(int[][] board, int part) {
        int[][] newBoard = new int[size][size];

        for (int i = 0; i < size; i += part) {
            for (int j = 0; j < size; j += part) {
                for (int x = 0; x < part; x++) {
                    for (int y = 0; y < part; y++) {
                        int nx = i + y;
                        int ny = j + part - x - 1;
                        newBoard[nx][ny] = board[i + x][j + y];
                    }
                }
            }
        }

        return newBoard;
    }

    static int[][] decrease(int[][] board) {
        int[][] newBoard = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                        if (board[nx][ny] > 0) count++;
                    }
                }
                if (count < 3) {
                    newBoard[i][j] = board[i][j] == 0 ? 0 : board[i][j] - 1;
                } else {
                    newBoard[i][j] = board[i][j];
                }
            }
        }

        return newBoard;
    }
}