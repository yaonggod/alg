class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {0, 0};
        int h = park.length;
        int w = park[0].toCharArray().length;
        
        char[][] parkChar = new char[h][w];
        for (int i = 0; i < h; i++) {
            parkChar[i] = park[i].toCharArray();
        }
        
        // 시작
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (parkChar[i][j] == 'S') {
                    answer[0] = i;
                    answer[1] = j;
                    break;
                }
            }
        }
        
        // 이동
        for (int k = 0; k < routes.length; k++) {
            String[] r = routes[k].split("");
            String d = r[0];
            int l = Integer.parseInt(r[2]);
            boolean go = true;
            
            // 동쪽
            if (d.equals("E")) {
                int sy = answer[0];
                int sx = answer[1];
                if (sx + l >= w) {
                    go = false;
                }
                if (go) {
                    for (int i = 0; i < l; i++) {
                        if (parkChar[sy][sx + (i + 1)] == 'X') {
                            go = false;
                            break;
                        }
                    }
                }
                if (go) {
                    answer[1] = sx + l;
                }
            }
            // 서쪽
            else if (d.equals("W")) {
                int sy = answer[0];
                int sx = answer[1];
                if (sx - l < 0) {
                    go = false;
                }
                if (go) {
                    for (int i = 0; i < l; i++) {
                        if (parkChar[sy][sx - (i + 1)] == 'X') {
                            go = false;
                            break;
                        }
                    }
                }
                if (go) {
                    answer[1] = sx - l;
                }
            }
            // 남쪽
            else if (d.equals("S")) {
                int sy = answer[0];
                int sx = answer[1];
                if (sy + l >= h) {
                    go = false;
                }
                if (go) {
                    for (int i = 0; i < l; i++) {
                        if (parkChar[sy + (i + 1)][sx] == 'X') {
                            go = false;
                            break;
                        }
                    }
                }
                if (go) {
                    answer[0] = sy + l;
                }
            }
            // 북쪽
            else if (d.equals("N")) {
                int sy = answer[0];
                int sx = answer[1];
                if (sy - l < 0) {
                    go = false;
                }
                if (go) {
                    for (int i = 0; i < l; i++) {
                        if (parkChar[sy - (i + 1)][sx] == 'X') {
                            go = false;
                            break;
                        }
                    }
                }
                if (go) {
                    answer[0] = sy - l;
                }
            }
        }
        
        return answer;
    }
}