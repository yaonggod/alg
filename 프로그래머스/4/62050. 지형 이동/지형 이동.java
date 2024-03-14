import java.util.*;

class Solution {
    static int n, h;
    static int[] parent; 
    static int[][] number, L;
    public int solution(int[][] land, int height) {
        // bfs로 땅따먹기 한 다음에
        n = land.length;
        h = height;
        L = land;
        number = new int[n][n];
        int index = 0;
        
        int[] dx = new int[] {0, -1, 0, 1};
        int[] dy = new int[] {1, 0, -1, 0};
        
        // 어디에서 어디로 가는 경로가 있다
        // a[0] -> a[1]로 가는 경로가 x길이만큼 있다
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[2] - b[2];
            }
        });
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 땅이다
                if (number[i][j] == 0) {
                    // 새 땅으로 편입시키기
                    index++;
                    number[i][j] = index;
                    Queue<Integer[]> queue = new LinkedList<>();
                    queue.offer(new Integer[] {i, j});
                    while (!queue.isEmpty()) {
                        Integer[] data1 = queue.poll();
                        int x = data1[0];
                        int y = data1[1];
                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            // 내 땅이다
                            if (canGo(x, y, nx, ny)) {
                                number[nx][ny] = index;
                                queue.offer(new Integer[] {nx, ny});
                            // 다른 땅이다
                            } else if (route(x, y, nx, ny, index)) {
                                int another = number[nx][ny];
                                int cost = Math.abs(L[x][y] - L[nx][ny]);
                                pq.offer(new Integer[] {index, another, cost});
                                pq.offer(new Integer[] {another, index, cost});
                            }
                        }
                    }
                }
            }
        }
        
        // 땅끼리 잇는 가장 작은 경로로 최소신장트리
        
        // index번 땅까지 있음
        parent = new int[index + 1];
        for (int i = 1; i <= index; i++) {
            parent[i] = i;
        }
        int ladder = 0;
        int length = 0;
        
        // pq 하나씩 꺼내면서 사다리 세우기
        // 사다리는 index - 1개
        while (ladder < index - 1) {
            Integer[] canBeLadder = pq.poll();
            int from = canBeLadder[0];
            int to = canBeLadder[1];
            int cost = canBeLadder[2];
            
            if (find(from) != find(to)) {
                union(from, to);
                length += cost;
                ladder++;
            }
        }
        
        return length;
    }
    
    static boolean canGo(int x, int y, int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n && number[nx][ny] == 0 && Math.abs(L[x][y] - L[nx][ny]) <= h;
    }
    
    static boolean route(int x, int y, int nx, int ny, int landNum) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n && number[nx][ny] != 0 && number[nx][ny] != landNum;
    }
    
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}