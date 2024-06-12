import java.io.*;
import java.util.*;


public class Main {
    static class BlockGroup {
        int size;
        int sx;
        int sy;
        int rb;
        List<Integer[]> blocks;

        public BlockGroup(int size, int sx, int sy, int rb, List<Integer[]> blocks) {
            this.size = size;
            this.sx = sx;
            this.sy = sy;
            this.rb = rb;
            this.blocks = blocks;
        }
    }
    static int n, m;
    static int point = 0;
    static boolean[][] used;

    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static int[][] game;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 검 -1 무지개 0 일반 1부터 m까지 공백 -2
        game = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            // 1
            BlockGroup lbg = null;
            used = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (game[i][j] > 0 && !used[i][j]) {
                        BlockGroup newbg = bg(i, j);
                        if (newbg == null) continue;
                        if (lbg == null) lbg = newbg;
                        else {
                            if (lbg.size < newbg.size) {
                                lbg = newbg;
                            } else if (lbg.size == newbg.size) {
                                if (lbg.rb < newbg.rb) {
                                    lbg = newbg;
                                } else if (lbg.rb == newbg.rb) {
                                    if (lbg.sx < newbg.sx) {
                                        lbg = newbg;
                                    } else if (lbg.sx == newbg.sx && lbg.sy < newbg.sy) {
                                        lbg = newbg;
                                    }
                                }
                            }
                        }

                    }
                }
            }
            if (lbg == null) break;

            // 2
            deleteBlock(lbg);
            // 3
            gravity();
            // 4
            spin();
            // 5
            gravity();
        }
        System.out.println(point);
    }

    static BlockGroup bg(int x, int y) {
        int size = 1;
        int number = game[x][y];
        List<Integer[]> blocks = new ArrayList<>();
        blocks.add(new Integer[] {x, y});
        int minx = x;
        int miny = y;
        int rainbow = 0;

        boolean[][] visited = new boolean[n][n];
        visited[x][y] = true;
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {x, y});

        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = from[0] + dx[d];
                int ny = from[1] + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && (game[nx][ny] == number || game[nx][ny] == 0)) {
                        visited[nx][ny] = true;
                        blocks.add(new Integer[] {nx, ny});
                        size++;
                        if (game[nx][ny] == number) {
                            used[nx][ny] = true;
                            if (minx > nx) {
                                minx = nx;
                                miny = ny;
                            } else if (minx == nx && miny > ny) {
                                miny = ny;
                            }
                        } else {
                            rainbow++;
                        }
                        queue.offer(new Integer[] {nx, ny});
                    }
                }
            }
        }
        if (size < 2) return null;
        return new BlockGroup(size, minx, miny, rainbow, blocks);
    }

    static void deleteBlock(BlockGroup bg) {
        for (Integer[] block : bg.blocks) {
            game[block[0]][block[1]] = -2;
        }
        point += bg.size * bg.size;
    }

    static void gravity() {
        // 0과 자연수만 이동함
        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {
                int now = game[i][j];
                int nowx = i;
                if (now >= 0) {
                    while (nowx < n - 1) {
                        if (game[nowx + 1][j] != -2) break;
                        nowx++;
                    }
                    if (i != nowx) {
                        game[nowx][j] = now;
                        game[i][j] = -2;
                    }
                }

            }
        }
    }

    static void spin() {
        int[][] newGame = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int newx = n - j - 1;
                int newy = i;
                newGame[newx][newy] = game[i][j];
            }
        }

        game = newGame;
    }
}