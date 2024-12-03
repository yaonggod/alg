import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = -Integer.parseInt(st.nextToken());
            }
        }

        // 섬 갯수
        int island = 0;
        // 섬 번호
        int idx = 0;
        // 섬에다가 번호 붙이기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 새 섬임
                if (map[i][j] == -1) {
                    // 섬 개수, 섬 번호 하나 늘리고
                    island++;
                    idx++;

                    // 섬 인덱싱
                    map[i][j] = idx;

                    Queue<Integer[]> queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});

                    while (!queue.isEmpty()) {
                        Integer[] from = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                if (map[nx][ny] == -1) {
                                    map[nx][ny] = idx;
                                    queue.offer(new Integer[] {nx, ny});
                                }
                            }
                        }
                    }
                }
            }
        }

        // 섬 연결하기
        // 섬의 부모
        parent = new int[island + 1];
        for (int i = 0; i <= island; i++) {
            parent[i] = i;
        }

        // 섬에서 나오는 다리 찾기
        // 길이가 짧은 순서대로
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // 섬의 4방향에서 한 개의 다리만 뻗어나올 수 있음
        boolean[][][] visited = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 섬이다
                if (map[i][j] != 0) {

                    // 한 방향으로 다른 섬을 만날 때까지 이동하기
                    for (int d = 0; d < 4; d++) {

                        // 이미 다리가 있으면 안함
                        if (visited[i][j][d]) continue;

                        // 좌표 이동
                        int nx = i;
                        int ny = j;

                        // 시작, 끝, 길이
                        int from = map[i][j];
                        int to = -1;
                        int length = 0;
                        while (true) {
                            nx += dx[d];
                            ny += dy[d];

                            // 지도를 벗어났어
                            if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;

                            // 같은 섬을 만났어
                            if (map[nx][ny] == from) break;

                            // 다른 섬을 만났어
                            if (map[nx][ny] != 0) {
                                // 도착지
                                to = map[nx][ny];
                                break;
                            }

                            // 한 칸 전진했음
                            length++;
                        }

                        // 다리를 못이음
                        if (to == -1) continue;

                        // 길이가 짧아서 안됨
                        if (length == 1) continue;

                        // 시작점의 d방향과 도착점의 반대방향엔 다리가 채워짐
                        visited[i][j][d] = true;
                        visited[nx][ny][(d + 2) % 4] = true;
                        pq.offer(new Integer[] {length, from, to});
                    }
                }

            }
        }

        // 섬 개수 - 1개만큼 연결
        int bCnt = 0;
        int total = 0;
        while (!pq.isEmpty() && bCnt < island - 1) {
            Integer[] bridge = pq.poll();

            // 연결할 필요가 있다
            if (findParent(bridge[1]) != findParent(bridge[2])) {
                unite(bridge[1], bridge[2]);
                total += bridge[0];
                bCnt++;
            }
        }

        // 다 연결 못했음
        if (bCnt != island - 1) {
            System.out.println(-1);
            return;
        }

        System.out.println(total);
    }

    public static int findParent(int a) {
        if (a == parent[a]) return a;
        return parent[a] = findParent(parent[a]);
    }

    public static void unite(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }




}