import java.io.*;
import java.util.*;


public class Main {
    static int n;
    // 나무
    static PriorityQueue<Integer>[][] tree;
    // 죽음 - nut[i][j]에 age / 2만큼 양분 추가하기
    static Queue<Integer[]> dead = new LinkedList<>();
    // 번식
    static Queue<Integer[]> baby = new LinkedList<>();
    // 양분
    static int[][] nut;
    // 겨울에 로봇이 추가하는 양분
    static int[][] robot;
    // 번식
    static int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 나무 나이 어린 순서대로
        tree = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tree[i][j] = new PriorityQueue<>();
            }
        }
        // 땅에 있는 영양분
        nut = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nut[i], 5);
        }

        robot = new int[n][n];
        // 로봇이 뿌리는 양분
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            tree[x][y].offer(z);
        }

        // k년동안
        for (int i = 0; i < k; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += tree[i][j].size();
            }
        }
        System.out.println(count);
    }

    static void spring() {
        // 양분을 먹는다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PriorityQueue<Integer> newTree = new PriorityQueue<>();
                while (!tree[i][j].isEmpty()) {
                    int t = tree[i][j].poll();
                    // 양분이 있다 - 한살 먹음
                    if (nut[i][j] >= t) {
                        nut[i][j] -= t;
                        newTree.offer(t + 1);
                        if ((t + 1) % 5 == 0) {
                            baby.offer(new Integer[]{i, j});
                        }
                    // 죽음
                    } else {
                        dead.offer(new Integer[]{i, j, t / 2});
                    }
                }
                tree[i][j] = newTree;
            }
        }
    }
    static void summer() {
        // 양분 추가
        while (!dead.isEmpty()) {
            Integer[] data = dead.poll();
            int x = data[0];
            int y = data[1];
            int n = data[2];
            nut[x][y] += n;
        }
    }

    static void autumn() {
        // 번식
        while (!baby.isEmpty()) {
            Integer[] data = baby.poll();
            int x = data[0];
            int y = data[1];
            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    tree[nx][ny].offer(1);
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nut[i][j] += robot[i][j];
            }
        }
    };
}