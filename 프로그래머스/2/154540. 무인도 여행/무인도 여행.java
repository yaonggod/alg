import java.util.*;

class Solution {
    static char[][] map;
    static int[] dx, dy;
    static int h, w;
    static boolean[][] visited;
    
    public int[] solution(String[] maps) {
        h = maps.length;
        w = maps[0].length();
        map = new char[h][w];
        for (int i = 0; i < h; i++) {
            map[i] = maps[i].toCharArray();
        }
        visited = new boolean[h][w];
        
        dx = new int[] {-1, 0, 1, 0};
        dy = new int[] {0, -1, 0, 1};
        
        List<Integer> island = new ArrayList<>();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && map[i][j] != 'X') {
                    int size = (int) map[i][j] - 48;
                    visited[i][j] = true;
                    Queue<Integer[]> connected = new LinkedList<>();
                    connected.offer(new Integer[] {i, j});
                    while (!connected.isEmpty()) {
                        Integer[] from = connected.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = from[0] + dx[d];
                            int ny = from[1] + dy[d];
                            int s = landSize(nx, ny);
                            if (s != -1) {
                                size += s;
                                connected.offer(new Integer[] {nx, ny});
                            }
                        }
                    }
                    island.add(size);
                }
            }
        }
        Collections.sort(island);
        if (island.size() == 0) return new int[] {-1};
        int[] answer = new int[island.size()];
        for (int i = 0; i < island.size(); i++) {
            answer[i] = island.get(i);
        }
        return answer;
    }
    
    static int landSize(int x, int y) {
        if (x >= 0 && x < h && y >= 0 && y < w && !visited[x][y]) {
            if (map[x][y] != 'X') {
                visited[x][y] = true;
                return (int) map[x][y] - 48;
            }
        }
        return -1;
    }
}