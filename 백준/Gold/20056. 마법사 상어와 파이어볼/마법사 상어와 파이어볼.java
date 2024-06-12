import java.io.*;
import java.util.*;


public class Main {
    static int n, m, k, turn;
    static Queue<Integer[]>[][] fireball;
    static int[] dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        fireball = new LinkedList[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (fireball[r][c] == null) {
                fireball[r][c] = new LinkedList<>();
                fireball[r][c].offer(new Integer[] {weight, d, s, -1});
            }
        }

        for (int i = 0; i < k; i++) {
            turn = i;
            moveball();
        }

        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireball[i][j] != null) {
                    while (!fireball[i][j].isEmpty()) {
                        Integer[] fb = fireball[i][j].poll();
                        totalWeight += fb[0];
                    }
                }
            }
        }
        System.out.println(totalWeight);
    }

    static void moveball() {
        // 이동
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireball[i][j] != null) {
                    while (!fireball[i][j].isEmpty()) {
                        // 이번 턴에 움직였던거다
                        if (fireball[i][j].peek()[3] == turn) break;

                        Integer[] fb = fireball[i][j].poll();

                        int fx = i;
                        int fy = j;
                        int fs = fb[2];
                        int fd = fb[1];
                        int fw = fb[0];

                        while (fs > 0) {
                            fx += dx[fd];
                            fy += dy[fd];

                            if (fx >= n) fx -= n;
                            if (fx < 0) fx += n;
                            if (fy >= n) fy -= n;
                            if (fy < 0) fy += n;

                            fs--;
                        }

                        if (fireball[fx][fy] == null) {
                            fireball[fx][fy] = new LinkedList<>();
                        }
                        fireball[fx][fy].offer(new Integer[] {fw, fd, fb[2], turn});
                    }
                }
            }
        }

        // 합체 + 나누기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireball[i][j] != null && fireball[i][j].size() > 1) {
                    int finalw = 0;
                    int finals = 0;
                    int count = fireball[i][j].size();

                    int odd = 0;
                    int even = 0;

                    while (!fireball[i][j].isEmpty()) {
                        Integer[] fb = fireball[i][j].poll();
                        finalw += fb[0];
                        finals += fb[2];
                        if (fb[1] % 2 == 0) even++;
                        else odd++;
                    }

                    finalw /= 5;
                    finals /= count;


                    if (finalw == 0) continue;

                    if (odd == 0 || even == 0) {
                        for (int dir = 0; dir < 8; dir += 2) {
                            fireball[i][j].offer(new Integer[]{finalw, dir, finals, turn});
                        }
                    } else {
                        for (int dir = 1; dir < 8; dir += 2) {
                            fireball[i][j].offer(new Integer[]{finalw, dir, finals, turn});
                        }
                    }

                }
            }
        }

    }

}