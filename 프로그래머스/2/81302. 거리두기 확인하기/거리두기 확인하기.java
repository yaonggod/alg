import java.util.*;

class Solution {
    // POP, PP를 만드는데
    // visited 처리가 어떻게 돼야하는지

    // O O 2 O O
    // O 2 1 2 O
    // 2 1 P 1 2
    // O 2 1 2 O
    // O O 2 O O
    
    // 위 왼 아래 오
    // 위로 갔으면 아래로 가지말고
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i = 0; i < 5; i++) {
            answer[i] = result(places[i]);
            System.out.println();
        }
        
        return answer;
    }
    
    static int result(String[] place) {
        char[][] room = new char[5][5];
        for (int i = 0; i < 5; i++) {
            room[i] = place[i].toCharArray();
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                if (room[i][j] == 'P') {
                    for (int d = 0; d < 4; d++) {
                        // d방향으로 가서 탐색하면 거기서 (d + 2) % 4 방향은 안가도됨
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        // 갈 수 있는 곳이다
                        if (border(nx, ny)) {
                            // 1트에 사람 만남 == 끝
                            if (room[nx][ny] == 'P') return 0;
                            // 공간이라 다음번에 조심해야함
                            if (room[nx][ny] == 'O') {
                                for (int d2 = 0; d2 < 4; d2++) {
                                    if (d2 == (d + 2) % 4) continue;
                                    // 2트
                                    int nnx = nx + dx[d2];
                                    int nny = ny + dy[d2];
                                    // 사람만남
                                    if (border(nnx, nny) && room[nnx][nny] == 'P') {
                                        return 0;
                                    }
                                }
                            }
                        }
                        
                    }
                }
            }
        }
        return 1;
    }
    
    static boolean border(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
}