import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int day = 0;
    static int n, l, r;
    static int[][] nation;
    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        nation = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                nation[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int moved = move();
            if (moved == 0) break;
            day++;
        }
        System.out.println(day);
    }

    static int move() {
        // 오늘 하루 이동한 사람
        int count = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // 전체 인구를
                    int total = nation[i][j];
                    // div로 나누는데, div가 1이면 열린 국경선이 없음
                    int div = 1;
                    visited[i][j] = true;
                    Queue<Integer[]> queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});
                    // 이 좌표들의 인구를 통일시킨다
                    List<Integer[]> unite = new ArrayList<>();
                    unite.add(new Integer[] {i, j});
                    while (!queue.isEmpty()) {
                        Integer[] from = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                                int gap = Math.abs(nation[from[0]][from[1]] - nation[nx][ny]);
                                // 다음 갈 곳이 차이가 범위 내
                                if (gap >= l && gap <= r) {
                                    visited[nx][ny] = true;
                                    total += nation[nx][ny];
                                    div++;
                                    queue.offer(new Integer[] {nx, ny});
                                    unite.add(new Integer[] {nx, ny});
                                }
                            }
                        }
                    }
                    // 이동이 있다
                    if (div != 1) {
                        count += div;
                        int target = total / div;
                        for (int c = 0; c < unite.size(); c++) {
                            int x = unite.get(c)[0];
                            int y = unite.get(c)[1];
                            nation[x][y] = target;
                        }
                    }
                }
            }
        }
        return count;
    }
}