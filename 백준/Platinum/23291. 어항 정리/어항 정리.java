import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[][] aquarium;
    static int trial = 0;

    // 물고기가 존재하기 시작하는 시점
    static int fx = 0;
    static int fy = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        aquarium = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            aquarium[n - 1][i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            if (checkGap() <= k) break;

            addFish();
            stackAquarium();
            controlFish();
            flat();
            fold();
            controlFish();
            flat();

            trial++;
        }

        System.out.println(trial);

    }

    static int checkGap() {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (aquarium[n - 1][i] < min) min = aquarium[n - 1][i];
            if (aquarium[n - 1][i] > max) max = aquarium[n - 1][i];
        }

        return max - min;
    }

    static void addFish() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (aquarium[n - 1][i] < min) min = aquarium[n - 1][i];
        }

        for (int i = 0; i < n; i++) {
            if (aquarium[n - 1][i] == min) aquarium[n - 1][i]++;
        }
    }

    static void stackAquarium() {
        int start = 0;
        int end = 0;
        while (true) {
            int height = 1;
            for (int i = n - 2; i >= 0; i--) {
                if (aquarium[i][start] == 0) break;
                else {
                    height++;
                }
            }

            if (start == 0 && height == 1) {
                int temp = aquarium[n - 1][0];
                aquarium[n - 2][1] = temp;
                aquarium[n - 1][0] = 0;
                start = 1;

                fx = n - 2;
                fy = 1;
            } else {
                for (int j = start; j < n; j++) {
                    if (aquarium[n - height][j] != 0 && aquarium[n - height - 1][j] == 0) {
                        end = j;
                    } else {
                        break;
                    }
                }

                // start, end, height가 있음
                int length = end - start + 1;

                // length가 열 개수, height가 행 개수

                // 오른쪽 꼬리가 높이보다 짧으면 안됨
                int tail = n - end - 1;
                if (tail < height) return;

                // 기존 어항 뭉치의 시작점
                int sx = n - height;
                int sy = start;

                // 새로운 어항 뭉치의 시작점
                int nx = n - length - 1;
                int ny = end + 1;



                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < length; j++) {

                        // 원래 위치
                        int ox = sx + i;
                        int oy = sy + j;

                        // 새 위치
                        int px = nx + j;
                        int py = ny + height - i - 1;

                        int temp = aquarium[ox][oy];
                        aquarium[px][py] = temp;
                        aquarium[ox][oy] = 0;
                    }
                }

                start = end + 1;
                fx = nx;
                fy = ny;

            }


        }
    }

    static void controlFish() {

        int[][] plus = new int[n - fx][n - fy];
        int[][] minus = new int[n - fx][n - fy];

        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};

        for (int i = fx; i < n; i++) {
            for (int j = fy; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (aquarium[i][j] != 0 && aquarium[nx][ny] != 0) {
                            if (aquarium[i][j] > aquarium[nx][ny]) {
                                int toss = (aquarium[i][j] - aquarium[nx][ny]) / 5;
                                plus[nx - fx][ny - fy] += toss;
                                minus[i - fx][j - fy] += toss;
                            }
                        }
                    }
                }

            }
        }

        for (int i = fx; i < n; i++) {
            for (int j = fy; j < n; j++) {
                aquarium[i][j] += plus[i - fx][j - fy];
                aquarium[i][j] -= minus[i - fx][j - fy];
            }
        }

    }

    static void flat() {
        int sx = n - 1;
        int sy = fy;

        int tox = n - 1;
        int toy = 0;

        while (toy < n) {
            if (aquarium[tox][toy] != 0) break;

            int temp = aquarium[sx][sy];
            aquarium[tox][toy] = temp;
            aquarium[sx][sy] = 0;

            toy++;
            sx--;
            if (sx == fx - 1) {
                sx = n - 1;
                sy++;
            }
        }

        fx = n - 1;
        fy = 0;

    }

    static void fold() {
        for (int i = 0; i < n / 2; i++) {
            int sx = n - 1;
            int sy = i;
            int tox = n - 2;
            int toy = n - i - 1;

            int temp = aquarium[sx][sy];
            aquarium[tox][toy] = temp;
            aquarium[sx][sy] = 0;
        }

        int length = n / 4;
        int height = 2;

        int bx = n - 2;
        int by = n / 2;
        int px = n - 4;
        int py = n - n / 4;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                int sx = bx + i;
                int sy = by + j;
                int tox = px + height - i - 1;
                int toy = py + length - j - 1;

                int temp = aquarium[sx][sy];
                aquarium[tox][toy] = temp;
                aquarium[sx][sy] = 0;
            }
        }

        fx = px;
        fy = py;
    }
}