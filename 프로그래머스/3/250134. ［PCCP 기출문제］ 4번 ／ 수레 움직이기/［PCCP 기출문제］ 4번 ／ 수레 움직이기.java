import java.util.*;

class Solution {
    // 방향
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    // maze 가져와서 static으로 만든 배열
    static int[][] maze1;
    // 여기에 온 적이 있습니다. 
    static boolean[][] redVisited, blueVisited;
    // 높이, 너비, 레드 현재 위치, 종착점, 블루 현재 위치, 종착점
    static int h, w, rx, ry, erx, ery, bx, by, ebx, eby;
    // 4 * 4면 최소 16번만에 갈 수가 있음
    static int result = 17;
    public int solution(int[][] maze) {
        h = maze.length;
        w = maze[0].length;
        maze1 = maze;
        redVisited = new boolean[h][w];
        blueVisited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 빨간거
                if (maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                    redVisited[rx][ry] = true;
                }
                // 파란거
                else if (maze[i][j] == 2) {
                    bx = i;
                    by = j;
                    blueVisited[bx][by] = true;
                }
                // 빨간거 도착
                else if (maze[i][j] == 3) {
                    erx = i;
                    ery = j;
                }
                // 파란거 도착
                else if (maze[i][j] == 4) {
                    ebx = i;
                    eby = j;
                }
            }
        }
        // 빽트 ㄱㄱㄱㄱㄱㄱ
        backtracking(0);
        if (result == 17) return 0;
        return result;
    }
    
    static void backtracking(int turn) {
        // 완성
        if (rx == erx && ry == ery && bx == ebx && by == eby) {
            // 갱신
            if (turn < result) {
                result = turn;
            }
            return;
        }
        // 다음 위치, 도착함? 
        int nrx = 0;
        int nry = 0;
        boolean redDone = false;
        int nbx = 0;
        int nby = 0;
        boolean blueDone = false;
        // 이미 도착해서 더 갈 필요가 없다
        if (rx == erx && ry == ery) {
            redDone = true;
        }
        if (bx == ebx && by == eby) {
            blueDone = true;
        }
        
        // 빨간거가 도착했다
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
        // 파란거 도착했다 
        } else if (blueDone) {
            for (int d = 0; d < 4; d++) {
                nrx = rx + dx[d];
                nry = ry + dy[d];
                // 파란색이 여기를 갈 수 있습니까?
                if (canGo(nrx, nry, true)) {
                    // 네 ㄱㄱ
                    move(nrx, nry, true);
                    backtracking(turn + 1);
                    remove(d, true);
                }
            }
        // 둘이 같이 가야댐
        } else {
            for (int d1 = 0; d1 < 4; d1++) {
                nrx = rx + dx[d1];
                nry = ry + dy[d1];
                for (int d2 = 0; d2 < 4; d2++) {
                    nbx = bx + dx[d2];
                    nby = by + dy[d2];
                    // 같이 갈 수 있습니까?
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
    
    // 여기를 갈 수 있습니까?
    static boolean canGo(int x, int y, boolean red) {
        // 범위에 있고 벽이 아니다
        boolean result = x >= 0 && x < h && y >= 0 && y < w && maze1[x][y] != 5;
        if (!result) return result;
        // 간 적이 없고 지금 이 곳에 다른 수레가 없다 
        if (red) {
            return !redVisited[x][y] && (bx != x || by != y);
        } else {
            return !blueVisited[x][y] && (rx != x || ry != y);
        }
    }
    
    static boolean canGoTogether(int nrx, int nry, int nbx, int nby) {
        // 교차가 아니다
        boolean result = (nrx != nbx || nry != nby);
        // 지금 다른 수레가 있는 곳에 가는게 아니다
        result = result && !(nrx == bx && nry == by && nbx == rx && nby == ry);
        // 범위 안이고 벽이 아니고 안 가본 곳이다 
        result = result && (nrx >= 0 && nrx < h && nry >= 0 && nry < w && maze1[nrx][nry] != 5 && !redVisited[nrx][nry]);
        result = result && (nbx >= 0 && nbx < h && nby >= 0 && nby < w && maze1[nbx][nby] != 5 && !blueVisited[nbx][nby]);
        return result;
    }
    
    // 실제로 ㄱㄱㄱㄱㄱ
    static void move(int x, int y, boolean red) {
        // rx ry bx by에 실제 값을 넣어둠 
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
    
    // 취소 
    static void remove(int d, boolean red) {
        // 원래 위치로 돌아가고, 방문 경험을 초기화함 
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