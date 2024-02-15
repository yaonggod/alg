import java.util.*;

class Solution {
    static int h, w;
    
    public int solution(int[][] land) {
        // 모든 land를 돌면서 AL을 만들고 j를 넣은다음 j에다가 석유 크기 넣기
        h = land.length;
        w = land[0].length;
        
        // j에서 뚫을 시 석유 크기
        int[] oil = new int[w];
        
        boolean[][] visited = new boolean[h][w];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    // 갈 수 있는 j의 리스트
                    Set<Integer> where = new HashSet<>();
                    where.add(j);
                    // 석유 크기
                    int oilSize = 1;
                    // 크기 탐색하기
                    visited[i][j] = true;
                    Queue<Integer[]> q = new LinkedList<>();
                    q.offer(new Integer[] {i, j});
                    while (!q.isEmpty()) {
                        Integer[] start = q.poll();
                        int sx = start[0];
                        int sy = start[1];
                        for (int d = 0; d < 4; d++) {
                            int nx = sx + dx[d];
                            int ny = sy + dy[d];
                            if (range(nx, ny) && land[nx][ny] == 1 && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                oilSize++;
                                where.add(ny);
                                q.offer(new Integer[] {nx, ny});
                            }
                        }
                         
                    }
                    for (int k : where) {
                        oil[k] += oilSize;
                    }
                }
            }
        }
        int maxOilSize = 0;
        for (int i = 0; i < w; i++) {
            if (oil[i] > maxOilSize) maxOilSize = oil[i];
        }
        return maxOilSize;
        
    }
    
    public boolean range(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }
}