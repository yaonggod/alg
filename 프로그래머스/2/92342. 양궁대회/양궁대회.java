import java.util.*;

class Solution {
    static int[] ryan = new int[11];
    static int[] apeach = new int[11];
    static int[] answer = new int[] {-1};
    static int scoreGap = 0;
    static int s;
    public int[] solution(int n, int[] info) {
        s = n;
        apeach = info;
        makeRyan(10, s);
        return answer;
    }
    
    // shoot개의 화살을 배치한다
    static void makeRyan(int index, int shoot) {
        if (shoot == 0) {
            for (int i = index; i >= 0; i--) {
                ryan[i] = 0;
            }
            compareScore();   
        }
        else if (index == 0 && shoot > 0) {
            ryan[0] = shoot;
            makeRyan(-1, 0);
        }
        // index에다가 화살 배치하기
        else {
            for (int i = 0; i <= shoot; i++) {
                ryan[index] = i;
                makeRyan(index - 1, shoot - i);
            }
        }
    }
    
    static void compareScore() {
        int r = 0;
        int a = 0;
        for (int i = 0; i < 11; i++) {
            // 둘 다 0개
            if (apeach[i] == 0 && ryan[i] == 0) {
                continue;
            } 
            // 어피치
            else if (apeach[i] >= ryan[i]) {
                a += 10 - i;
            }
            // 라이언
            else {
                r += 10 - i;
            }
        }
        if (r - a > 0 && r - a >= scoreGap) {
            scoreGap = r - a;
            answer = new int[11];
            for (int i = 0; i < 11; i++) {
                answer[i] = ryan[i];
            }
        }
    }
}