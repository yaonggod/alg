import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 뱀 == 1 사과 == -1

        // 보드
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        board[0][0] = 1;

        // 사과
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = -1;
        }

        // 방향 - +하면 오른쪽으로
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};


        // 방향 변환
        int l = Integer.parseInt(br.readLine());
        Map<Integer, String> change = new HashMap<>();
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            change.put(x, c);
        }

        // 오른쪽을 보고 있음
        int d = 0;
        // 초
        int sec = 1;
        // 뱀
        LinkedList<Integer[]> snake = new LinkedList<>();
        snake.offer(new Integer[] {0, 0});

        while (true) {
            Integer[] data = snake.peekLast();
            int headx = data[0];
            int heady = data[1];
            int nextx = headx + dx[d];
            int nexty = heady + dy[d];

            // 범위 밖
            if (!range(n, nextx, nexty)) break;

            // 사과
            if (board[nextx][nexty] == -1) {
                // 먹는다
                board[nextx][nexty] = 1;
                snake.offer(new Integer[] {nextx, nexty});
            }

            // 뱀이랑 충돌
            else if (board[nextx][nexty] == 1) break;

            // 평범한 칸
            else {
                // 뱀 옮기고
                board[nextx][nexty] = 1;
                snake.offer(new Integer[] {nextx, nexty});
                // 꼬리 한칸 없애기 
                Integer[] delete = snake.poll();
                board[delete[0]][delete[1]] = 0;
            }

            // n초가 끝난 뒤에 방향 전환
            if (change.containsKey(sec)) {
                String toDo = change.get(sec);
                if (toDo.equals("L")) {
                    d--;
                    if (d == -1) d = 3;
                } else if (toDo.equals("D")) {
                    d++;
                    if (d == 4) d = 0;
                }
            }
            sec++;
        }

        System.out.println(sec);
    }

    static boolean range(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}