import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static StringTokenizer st;
    static char[][] map;
    static boolean[][] visited;
    // 가지고 있는 열쇠
    static boolean[] keys;
    static int[] doors;
    static boolean[] current;
    static boolean[] canuse;
    static char[] givenKeys;
    static List<Integer[]>[] doorxy;

    static int h, w;
    static int dollar;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    static Queue<Integer> keyQueue;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 97 122 65 90 32
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];
            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }
            for (int i = 1; i < h + 1; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 1; j < w + 1; j++) {
                    map[i][j] = line[j - 1];
                }
            }
            keys = new boolean[26];
            doorxy = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                doorxy[i] = new ArrayList<>();
            }

            // 초기에 주어진 열쇠
            givenKeys = br.readLine().toCharArray();
            if (givenKeys[0] != '0') {
                for (char k : givenKeys) {
                    keys[(int) k - 97] = true;
                }
            }

            // 맵의 문 위치 파악하기
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (map[i][j] - 'A' >= 0 && map[i][j] - 'A' < 26) {
                        doorxy[map[i][j] - 'A'].add(new Integer[] {i, j});
                    }
                }
            }

            // 열쇠 있는 문 열어주기
            for (int i = 0; i < 26; i++) {
                if (keys[i]) {
                    for (Integer[] ds : doorxy[i]) {
                        map[ds[0]][ds[1]] = '.';
                    }
                }
            }

            dollar = 0;
            go();

            sb.append(dollar);
            if (tc != T - 1) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void go() {
        int sx = 0;
        int sy = 0;
        visited[sx][sy] = true;
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {0, 0});
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = from[0] + dx[d];
                int ny = from[1] + dy[d];
                if (nx >= 0 && nx < h + 2 && ny >= 0 && ny < w + 2 && !visited[nx][ny]) {
                    if (map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        queue.offer(new Integer[] {nx, ny});
                    } else if (map[nx][ny] - 'a' >= 0 && map[nx][ny] - 'a' < 26) {
                        // 새 키를 찾음
                        if (!keys[map[nx][ny] - 'a']) {
                            keys[map[nx][ny] - 'a'] = true;
                            // 모든 문 프리패스
                            for (Integer[] ds : doorxy[map[nx][ny] - 'a']) {
                                map[ds[0]][ds[1]] = '.';
                            }
                            // visited 초기화
                            visited = new boolean[h + 2][w + 2];
                        }
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        queue.offer(new Integer[] {nx, ny});
                    } else if (map[nx][ny] == '$') {
                        dollar++;
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        queue.offer(new Integer[] {nx, ny});
                    }
                }
            }
        }
    }
}