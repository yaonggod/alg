import java.io.*;
import java.util.*;


public class Main {
    static int m, s;
    // 물고기 이동은 8방향 다 가능
    // 물고기는 이동할 곳이 없으면 d-- 해서 마저 이동하기
    static int[] dx = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    // 상어이동 상 좌 하 우
    static int[] sdx = new int[] {-1, 0, 1, 0};
    static int[] sdy = new int[] {0, -1, 0, 1};
    // 물고기가 죽으면 그 자리에 3의 냄새가 생기고, 상어가 다 이동하면 냄새 1씩 빼주기
    static int[][] smell = new int[4][4];
    // 물고기 큐
    static Queue<Integer>[][] fish = new LinkedList[4][4];
    // 복제될 물고기
    static Queue<Integer[]> copyfish = new LinkedList<>();
    // 상어 위치
    static int sx, sy;
    static int removedFish;
    static int[] removeRoute = new int[3];
    static int[] removeResult = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fish[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int fd = Integer.parseInt(st.nextToken()) - 1;
            fish[fx][fy].offer(fd);
        }
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < s; i++) {
            saveFish();
            moveFish();
            removedFish = -1;
            removeRoute = new int[3];
            removeResult = new int[3];
            moveShark(sx, sy, 0);
            removeFish();
            removeSmell();
            pasteFish();
        }

        long answer = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                answer += fish[i][j].size();
            }
        }
        System.out.println(answer);
    }

    static void saveFish() {
        // 지금 물고기들 큐에 담아서 복제 준비
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!fish[i][j].isEmpty()) {
                    for (Integer f : fish[i][j]) {
                        copyfish.offer(new Integer[] {i, j, f});
                    }
                }
            }
        }
    }

    static void moveFish() {
        // 물고기 움직이기
        Queue<Integer>[][] newFish = new LinkedList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newFish[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!fish[i][j].isEmpty()) {
                    while (!fish[i][j].isEmpty()) {
                        Integer fd = fish[i][j].poll();
                        int fail = 0;
                        // 지금 이동할 수 있는 칸
                        int nx = i + dx[fd];
                        int ny = j + dy[fd];
                        while (fail < 8) {
                            nx = i + dx[fd];
                            ny = j + dy[fd];
                            boolean cango = true;
                            // 밖
                            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                                cango = false;
                            // 냄새
                            } else if (smell[nx][ny] > 0) {
                                cango = false;
                            // 상어
                            } else if (nx == sx && ny == sy) {
                                cango = false;
                            }
                            if (cango) break;

                            fail++;
                            fd--;
                            if (fd < 0) fd += 8;
                        }

                        // 갈수있다
                        if (fail < 8) {
                            newFish[nx][ny].offer(fd);
                        } else {
                            newFish[i][j].offer(fd);
                        }
                    }
                }
            }
        }
        fish = newFish;
    }

    static void moveShark(int x, int y, int count) {
        if (count == 3) {
            int total = tryRemoveFish();
            if (total > removedFish) {
                removedFish = total;
                removeResult[0] = removeRoute[0];
                removeResult[1] = removeRoute[1];
                removeResult[2] = removeRoute[2];
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            // 다음 위치
            int nx = x + sdx[d];
            int ny = y + sdy[d];
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                removeRoute[count] = d;
                moveShark(nx, ny, count + 1);
            }
        }
    }

    static int tryRemoveFish() {
        boolean[][] visited = new boolean[4][4];
        int total = 0;
        int x = sx;
        int y = sy;
        for (int i = 0; i < 3; i++) {
            x += sdx[removeRoute[i]];
            y += sdy[removeRoute[i]];
            total += visited[x][y] ? 0 : fish[x][y].size();
            visited[x][y] = true;
        }
        return total;
    }

    static void removeFish() {
        for (int i = 0; i < 3; i++) {
            sx += sdx[removeResult[i]];
            sy += sdy[removeResult[i]];
            if (fish[sx][sy].size() > 0) {
                fish[sx][sy].clear();
                smell[sx][sy] = 3;
            }

        }
    }

    static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                smell[i][j] = smell[i][j] > 0 ? smell[i][j] - 1 : 0;
            }
        }
    }

    static void pasteFish() {
        while (!copyfish.isEmpty()) {
            Integer[] cpf = copyfish.poll();
            fish[cpf[0]][cpf[1]].offer(cpf[2]);
        }
    }
}