import java.io.*;
import java.util.*;

public class Main {

    static int r, c, k, w;
    static int[][] room;

    static int[][] add;
    static int[][] addList;
    static boolean added = false;
    static List<Integer[]> check = new ArrayList<>();

    // 오른쪽 왼쪽 위 아래
    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {1, -1, 0, 0};

    // cango[i][j]에서 d방향으로 갈 수 없다
    static boolean[][][] cantgo;

    // i, j에 d방향으로 쏘는 에어컨이 있음
    static List<Integer[]> aircon = new ArrayList<>();

    static int trial = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        room = new int[r][c];
        cantgo = new boolean[r][c][4];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(st.nextToken());

                // 온도 조사
                if (num == 5) {
                    check.add(new Integer[] {i, j});
                }

                // 에어컨
                else if (num > 0) {
                    aircon.add(new Integer[] {i, j, num - 1});
                }
            }
        }

        w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            // 오 왼 위 아래
            // 위아래
            if (t == 0) {
                cantgo[x][y][2] = true;
                cantgo[x - 1][y][3] = true;
            }

            // 좌우
            else if (t == 1) {
                cantgo[x][y][0] = true;
                cantgo[x][y + 1][1] = true;
            }

        }

        while (true) {
            trial++;
            if (trial == 101) break;

            spreadWind();

            controlTemp();

            decreaseTemp();

            if (checkTemp()) break;
        }


        System.out.println(trial);

    }

    static boolean checkTemp() {
        for (Integer[] c : check) {
            if (room[c[0]][c[1]] < k) return false;
        }
        return true;
    }
    static void spreadWind() {
        // 바람은 세 가지 방법으로 이동할 수 있음
        // 오른쪽으로 직진하거나
        // 오른쪽으로 가기 전 위로/아래로 이동했다가 오른쪽으로 가거나

        if (added) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    room[i][j] += addList[i][j];
                }
            }
            return;
        }
        added = true;
        addList = new int[r][c];
        for (Integer[] ac : aircon) {
            add = new int[r][c];
            int ax = ac[0];
            int ay = ac[1];
            int ad = ac[2];

            Queue<Integer[]> queue = new LinkedList<>();

            // 처음 바람
            int nx = ax + dx[ad];
            int ny = ay + dy[ad];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!cantgo[ax][ay][ad]) {
                    add[nx][ny] = 5;
                    queue.offer(new Integer[] {nx, ny, 4});
                }
            }

            while (!queue.isEmpty()) {
                Integer[] data = queue.poll();
                int fx = data[0];
                int fy = data[1];
                int fn = data[2];

                // 추가할 바람이 있다
                if (fn > 0) {
                    // 직진
                    nx = fx + dx[ad];
                    ny = fy + dy[ad];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        // 갈 수 있고, 바람이 없다
                        if (!cantgo[fx][fy][ad] && add[nx][ny] == 0) {
                            add[nx][ny] = fn;
                            queue.offer(new Integer[] {nx, ny, fn - 1});
                        }
                    }

                    if (ad == 0 || ad == 1) {
                        nx = fx + dx[2];
                        ny = fy + dy[2];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                            // 갈 수 있다
                            if (!cantgo[fx][fy][2]) {
                                nx += dx[ad];
                                ny += dy[ad];
                                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                                    // 갈 수 있고, 바람이 없다
                                    if (!cantgo[nx - dx[ad]][ny - dy[ad]][ad] && add[nx][ny] == 0) {
                                        add[nx][ny] = fn;
                                        queue.offer(new Integer[] {nx, ny, fn - 1});
                                    }
                                }
                            }
                        }

                        nx = fx + dx[3];
                        ny = fy + dy[3];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                            // 갈 수 있다
                            if (!cantgo[fx][fy][3]) {
                                nx += dx[ad];
                                ny += dy[ad];
                                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                                    // 갈 수 있고, 바람이 없다
                                    if (!cantgo[nx - dx[ad]][ny - dy[ad]][ad] && add[nx][ny] == 0) {
                                        add[nx][ny] = fn;
                                        queue.offer(new Integer[] {nx, ny, fn - 1});
                                    }
                                }
                            }
                        }
                    } else if (ad == 2 || ad == 3) {
                        nx = fx + dx[0];
                        ny = fy + dy[0];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                            // 갈 수 있다
                            if (!cantgo[fx][fy][0]) {
                                nx += dx[ad];
                                ny += dy[ad];
                                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                                    // 갈 수 있고, 바람이 없다
                                    if (!cantgo[nx - dx[ad]][ny - dy[ad]][ad] && add[nx][ny] == 0) {
                                        add[nx][ny] = fn;
                                        queue.offer(new Integer[] {nx, ny, fn - 1});
                                    }
                                }
                            }
                        }

                        nx = fx + dx[1];
                        ny = fy + dy[1];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                            // 갈 수 있다
                            if (!cantgo[fx][fy][1]) {
                                nx += dx[ad];
                                ny += dy[ad];
                                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                                    // 갈 수 있고, 바람이 없다
                                    if (!cantgo[nx - dx[ad]][ny - dy[ad]][ad] && add[nx][ny] == 0) {
                                        add[nx][ny] = fn;
                                        queue.offer(new Integer[] {nx, ny, fn - 1});
                                    }
                                }
                            }
                        }
                    }

                }
            }


            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    addList[i][j] += add[i][j];
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                room[i][j] += addList[i][j];
            }
        }
    }

    static void controlTemp() {
        int[][] plus = new int[r][c];
        int[][] minus = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (!cantgo[i][j][d]) {
                            if (room[i][j] > room[nx][ny]) {
                                int control = (room[i][j] - room[nx][ny]) / 4;
                                minus[i][j] += control;
                                plus[nx][ny] += control;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                room[i][j] += (plus[i][j] - minus[i][j]);
            }
        }
    }

    static void decreaseTemp() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) {
                    room[i][j] = room[i][j] > 0 ? room[i][j] - 1 : room[i][j];
                }
            }
        }
    }

}