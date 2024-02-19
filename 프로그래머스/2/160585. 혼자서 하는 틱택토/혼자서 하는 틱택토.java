class Solution {
    public int solution(String[] board) {
        char[][] ttt = new char[3][3];
        for (int i = 0; i < 3; i++) {
            ttt[i] = board[i].toCharArray();
        }
        
        int o = 0;
        int x = 0;
        boolean owin = false;
        boolean xwin = false;
        // 이겼나
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ttt[i][j] == 'O') o++;
                if (ttt[i][j] == 'X') x++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (ttt[i][0] == 'O' && ttt[i][0] == ttt[i][1] && ttt[i][0] == ttt[i][2]) owin = true;
            if (ttt[0][i] == 'O' && ttt[0][i] == ttt[1][i] && ttt[0][i] == ttt[2][i]) owin = true;
            if (ttt[i][0] == 'X' && ttt[i][0] == ttt[i][1] && ttt[i][0] == ttt[i][2]) xwin = true;
            if (ttt[0][i] == 'X' && ttt[0][i] == ttt[1][i] && ttt[0][i] == ttt[2][i]) xwin = true;
        }
        if (ttt[0][0] == 'O' && ttt[0][0] == ttt[1][1] && ttt[0][0] == ttt[2][2]) owin = true;
        if (ttt[0][2] == 'O' && ttt[0][2] == ttt[1][1] && ttt[0][2] == ttt[2][0]) owin = true;
        if (ttt[0][0] == 'X' && ttt[0][0] == ttt[1][1] && ttt[0][0] == ttt[2][2]) xwin = true;
        if (ttt[0][2] == 'X' && ttt[0][2] == ttt[1][1] && ttt[0][2] == ttt[2][0]) xwin = true;
        
        // X가 더 많음
        if (x > o) return 0;
        // O가 이겼는데 X가 O만큼 있다
        if (owin && x == o) return 0;
        // X가 이겼는데 O가 더 많다
        if (xwin && o > x) return 0;
        // O가 2개 이상 더 많다
        if (o - x > 1) return 0;
        return 1;
    }
}