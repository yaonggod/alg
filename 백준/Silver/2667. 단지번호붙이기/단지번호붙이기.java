import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] house;
    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};
    static boolean valid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    static PriorityQueue<Integer> ans = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        house = new char[n][n];
        for (int i = 0; i < n; i++) {
            house[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (house[i][j] == '1') {
                    int count = 1;
                    house[i][j] = '0';
                    Queue<Integer[]> queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});
                    while (!queue.isEmpty()) {
                        Integer[] from = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            if (valid(nx, ny) && house[nx][ny] == '1') {
                                house[nx][ny] = '0';
                                count++;
                                queue.offer(new Integer[] {nx, ny});
                            }
                        }
                    }
                    ans.offer(count);
                }
            }
        }
        System.out.println(ans.size());
        while (!ans.isEmpty()) {
            System.out.println(ans.poll());
        }
    }
}