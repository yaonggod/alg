import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] virus = new int[n][n];
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                // 초가 빠른것부터
                if (o1[2] < o2[2]) return -1;
                // 초가 같으면 바이러스 앞번호부터
                if (o1[2] == o2[2] && o1[3] < o2[3]) return -1;
                return 1;
            }
        });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                virus[i][j] = Integer.parseInt(st.nextToken());
                if (virus[i][j] != 0) {
                    queue.offer(new Integer[] {i, j, 0, virus[i][j]});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        int[] dx = new int[] {0, -1, 0, 1};
        int[] dy = new int[] {1, 0, -1, 0};


        for (int sec = 0; sec < s; sec++) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Integer[] v = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = v[0] + dx[d];
                    int ny = v[1] + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && virus[nx][ny] == 0) {
                        virus[nx][ny] = v[3];
                        queue.offer(new Integer[] {nx, ny, v[2] + 1, v[3]});
                    }
                }
            }
        }

        System.out.println(virus[x][y]);
    }
}