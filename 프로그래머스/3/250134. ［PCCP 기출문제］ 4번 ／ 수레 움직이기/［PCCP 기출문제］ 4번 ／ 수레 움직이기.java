import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] maze1;
    static boolean[][] redVisited, blueVisited;
    static int h, w, rx, ry, erx, ery, bx, by, ebx, eby;
    static int result = 17;
    public int solution(int[][] maze) {
        h = maze.length;
        w = maze[0].length;
        maze1 = maze;
        redVisited = new boolean[h][w];
        blueVisited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                    redVisited[rx][ry] = true;
                }
                else if (maze[i][j] == 2) {
                    bx = i;
                    by = j;
                    blueVisited[bx][by] = true;
                }
                else if (maze[i][j] == 3) {
                    erx = i;
                    ery = j;
                }
                else if (maze[i][j] == 4) {
                    ebx = i;
                    eby = j;
                }
            }
        }
        backtracking(0);
        if (result == 17) return 0;
        return result;
    }
    
    static void backtracking(int turn) {
        // 완성
        if (rx == erx && ry == ery && bx == ebx && by == eby) {
            if (turn < result) {
                result = turn;
            }
            return;
        }
        int nrx = 0;
        int nry = 0;
        boolean redDone = false;
        int nbx = 0;
        int nby = 0;
        boolean blueDone = false;
        // 더 갈 필요가 없다
        if (rx == erx && ry == ery) {
            redDone = true;
        }
        if (bx == ebx && by == eby) {
            blueDone = true;
        }
        
        if (redDone) {
            for (int d = 0; d < 4; d++) {
                nbx = bx + dx[d];
                nby = by + dy[d];
                if (canGo(nbx, nby, false)) {
                    move(nbx, nby, false);
                    backtracking(turn + 1);
                    remove(d, false);
                }
            }
        } else if (blueDone) {
            for (int d = 0; d < 4; d++) {
                nrx = rx + dx[d];
                nry = ry + dy[d];
                if (canGo(nrx, nry, true)) {
                    move(nrx, nry, true);
                    backtracking(turn + 1);
                    remove(d, true);
                }
            }
        } else {
            for (int d1 = 0; d1 < 4; d1++) {
                nrx = rx + dx[d1];
                nry = ry + dy[d1];
                for (int d2 = 0; d2 < 4; d2++) {
                    nbx = bx + dx[d2];
                    nby = by + dy[d2];
                    if (canGoTogether(nrx, nry, nbx, nby)) {
                        move(nrx, nry, true);
                        move(nbx, nby, false);
                        backtracking(turn + 1);
                        remove(d1, true);
                        remove(d2, false);
                    }
                }
                
            }
        }
        
        
        
    }
    
    static boolean canGo(int x, int y, boolean red) {
        boolean result = x >= 0 && x < h && y >= 0 && y < w && maze1[x][y] != 5;
        if (!result) return result;
        if (red) {
            return !redVisited[x][y] && (bx != x || by != y);
        } else {
            return !blueVisited[x][y] && (rx != x || ry != y);
        }
    }
    
    static boolean canGoTogether(int nrx, int nry, int nbx, int nby) {
        boolean result = (nrx != nbx || nry != nby);
        result = result && !(nrx == bx && nry == by && nbx == rx && nby == ry);
        result = result && (nrx >= 0 && nrx < h && nry >= 0 && nry < w && maze1[nrx][nry] != 5 && !redVisited[nrx][nry]);
        result = result && (nbx >= 0 && nbx < h && nby >= 0 && nby < w && maze1[nbx][nby] != 5 && !blueVisited[nbx][nby]);
        return result;
    }
    
    static void move(int x, int y, boolean red) {
        if (red) {
            rx = x; 
            ry = y;
            redVisited[rx][ry] = true;
        } else {
            bx = x;
            by = y;
            blueVisited[bx][by] = true;
        }
    }
    
    static void remove(int d, boolean red) {
        if (red) {
            redVisited[rx][ry] = false;
            rx -= dx[d];
            ry -= dy[d];
        } else {
            blueVisited[bx][by] = false;
            bx -= dx[d];
            by -= dy[d];
        }
        
    }
}