import java.io.*;
import java.util.*;


public class Main {
    static class Shark {
        int speed;
        // 1 위 2 아래 3 오른쪽 4 왼쪽
        int dir;
        int size;
        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public String toString() {
            return this.speed + " " + this.dir + " " + this.size;
        }
    }
    static int r, c;
    // 낚시왕의 위치
    static int fisher = 0;
    static int totalShark = 0;
    static Shark[][] water;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        water = new Shark[r][c];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            water[x][y] = new Shark(s, d, z);
        }

        while (fisher < c) {
            catchShark();
            sharkMove();
            fisher++;
        }
        System.out.println(totalShark);
    }

    static void catchShark() {
        for (int i = 0; i < r; i++) {
            if (water[i][fisher] != null) {
                totalShark += water[i][fisher].size;
                water[i][fisher] = null;
                break;
            }
        }
    }

    static void sharkMove() {
        Shark[][] newWater = new Shark[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (water[i][j] == null) continue;
                int dir = water[i][j].dir;
                int move = water[i][j].speed;
                int nx = i;
                int ny = j;
                while (move > 0) {
                    // 위
                    if (dir == 1) {
                        nx -= 1;
                        if (nx < 0) {
                            nx += 2;
                            dir = 2;
                        }
                    } else if (dir == 2) {
                        nx += 1;
                        if (nx >= r) {
                            nx -= 2;
                            dir = 1;
                        }
                    } else if (dir == 3) {
                        ny += 1;
                        if (ny >= c) {
                            ny -= 2;
                            dir = 4;
                        }
                    } else if (dir == 4) {
                        ny -= 1;
                        if (ny < 0) {
                            ny += 2;
                            dir = 3;
                        }
                    }
                    move--;
                }
                if (newWater[nx][ny] != null) {
                    if (newWater[nx][ny].size < water[i][j].size) {
                        newWater[nx][ny] = water[i][j];
                        newWater[nx][ny].dir = dir;
                    }
                } else {
                    newWater[nx][ny] = water[i][j];
                    newWater[nx][ny].dir = dir;
                }
            }
        }
        water = newWater;
    }

}