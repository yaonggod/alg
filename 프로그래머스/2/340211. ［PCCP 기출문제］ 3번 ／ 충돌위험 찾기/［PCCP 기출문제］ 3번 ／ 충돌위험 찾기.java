import java.util.*;

class Solution {
    // 무적권 최단 경로로 세로 이동 먼저하는 경로로
    // 그러면 [3, 2] -> [4, 7] 면 3 -> 4 먼저 한 다음에 2 -> 7 하는거임
    
    // 로봇마다 이동 계획
    // x * 100 + y 해시해서 위치 집어넣을거임 해시값 같으면 같은 위치
    static List<Integer[]>[] robotRoute;
    
    // 좌표, 방문할 좌표
    static int[][] P, R;
    
    // 맵에 있는 로봇
    static int rCount;
    public int solution(int[][] points, int[][] routes) {
        P = points;
        R = routes;
        rCount = routes.length;
        robotRoute = new ArrayList[rCount];
        
        for (int i = 0; i < rCount; i++) {
            // 로봇마다 루트 만들어줌
            robotRoute[i] = new ArrayList<>();
            makeRoute(i);
        }
        
        // 지금 방문하고 있는 좌표 인덱스
        int v = 0;
        // 위험
        int danger = 0;
        while (true) {
            // 지금 움직이고 있는 로봇
            int current = 0;
            // 좌표마다 로봇 몇개있음
            Map<Integer, Integer> crash = new HashMap<Integer, Integer>();
            for (int i = 0; i < rCount; i++) {
                // 이동 다 끝난 로봇은 빼고
                if (v >= robotRoute[i].size()) continue;
                current++;
                
                int dotx = robotRoute[i].get(v)[0];
                int doty = robotRoute[i].get(v)[1];
                int hashDot = dotx * 100 + doty;
                crash.put(hashDot, crash.getOrDefault(hashDot, 0) + 1);
            }
            // 이동 다 끝났음
            if (current == 0) break;
            
            for (Integer dot : crash.keySet()) {
                // 이 좌표에 두 개 이상이 모였다
                if (crash.get(dot) > 1) {
                    danger++;
                }
            }
            v++;
        }
        return danger;
    }
    
    static void makeRoute(int robot) {

        for (int i = 0; i < R[robot].length - 1; i++) {
            
            int from = R[robot][i] - 1;
            int to = R[robot][i + 1] - 1;
            
            int sx = P[from][0];
            int ex = P[to][0];
            int sy = P[from][1];
            int ey = P[to][1];
            
            // 시작점이면 시작위치도 넣어줌 시작할때도 충돌할 수 있음
            if (i == 0) robotRoute[robot].add(new Integer[] {sx, sy});
            
            // 위로가자
            if (sx > ex) {
                for (int x = sx - 1; x >= ex; x--) {
                    robotRoute[robot].add(new Integer[] {x, sy});
                }
            // 아래로
            } else {
                for (int x = sx + 1; x <= ex; x++) {
                    robotRoute[robot].add(new Integer[] {x, sy});
                }
            }
            
            // 왼쪽으로
            if (sy > ey) {
                for (int y = sy - 1; y >= ey; y--) {
                    robotRoute[robot].add(new Integer[] {ex, y});
                }
            // 오른쪽으로
            } else {
                for (int y = sy + 1; y <= ey; y++) {
                    robotRoute[robot].add(new Integer[] {ex, y});
                }
            }
        }
    }
}