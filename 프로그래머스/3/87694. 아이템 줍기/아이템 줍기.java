import java.util.*;

class Solution {
    static int maxx, maxy;
    static boolean[][] visited, map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        maxx = 0;
        maxy = 0;
        // 크기 정하기
        for (int[] r : rectangle) {
            int lx = r[0];
            int ly = r[1];
            int rx = r[2];
            int ry = r[3];
            if (rx > maxx) maxx = rx;
            if (ry > maxy) maxy = ry;
        }
        // 채우기
        // 1, 1은 1, 1부터 2, 2까지 크기가 1인 정사각형
        map = new boolean[maxx + 2][maxy + 2];
        for (int[] r : rectangle) {
            int lx = r[0];
            int ly = r[1];
            int rx = r[2];
            int ry = r[3];
            for (int x = lx; x < rx; x++) {
                for (int y = ly; y < ry; y++) {
                    map[x][y] = true;
                }
            }
        }
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[] {characterX, characterY, 0});
        visited = new boolean[maxx + 2][maxy + 2];
        visited[characterX][characterY] = true;
        int result = 0;
        while (!queue.isEmpty()) {
            Integer[] from = queue.poll();
            int x = from[0];
            int y = from[1];
            if (x == itemX && y == itemY) {
                result = from[2];
                break;
            }
            if (!visited[x][y + 1] && goUp(from[0], from[1])) {
                visited[x][y + 1] = true;
                queue.offer(new Integer[] {x, y + 1, from[2] + 1});
            }
            if (!visited[x][y - 1] && goDown(from[0], from[1])) {
                visited[x][y - 1] = true;
                queue.offer(new Integer[] {x, y - 1, from[2] + 1});
            }
            if (!visited[x - 1][y] && goLeft(from[0], from[1])) {
                visited[x - 1][y] = true;
                queue.offer(new Integer[] {x - 1, y, from[2] + 1});
            }
            if (!visited[x + 1][y] && goRight(from[0], from[1])) {
                visited[x + 1][y] = true;
                queue.offer(new Integer[] {x + 1, y, from[2] + 1});
            }
        }
        return result;
    }
    
    // 위로 갈 수 있냐
    static boolean goUp(int x, int y) {
        int nx = x;
        int ny = y + 1;
        if (ny > maxy) return false;
        // 기준점에서 왼쪽 오른쪽 상태가 달라야댐
        // 0 1
        // 1 1
        if (map[x - 1][y] != map[x][y]) return true;
        return false;
    }
    
    // 아래로 갈 수 있냐
    static boolean goDown(int x, int y) {
        int nx = x;
        int ny = y - 1;
        if (ny <= 0) return false;
        if (map[x - 1][y - 1] != map[x][y - 1]) return true;
        return false;
    }
    
    // 왼
    static boolean goLeft(int x, int y) {
        int nx = x - 1;
        int ny = y;
        if (nx <= 0) return false;
        if (map[x - 1][y] != map[x - 1][y - 1]) return true;
        return false;
    }
    // 오
    static boolean goRight(int x, int y) {
        int nx = x + 1;
        int ny = y;
        if (nx > maxx) return false;
        if (map[x][y] != map[x][y - 1]) return true;
        return false;
    }
}