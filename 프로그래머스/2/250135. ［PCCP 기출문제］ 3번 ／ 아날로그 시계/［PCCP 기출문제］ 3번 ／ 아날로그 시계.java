class Solution {
    static int h, m, s;
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        h = h1;
        m = m1;
        s = s1;
        // 알람
        int alarm = 0;
        while (true) {
            // 목표 시간 도달
            if (h == h2 && m == m2 && s == s2) {
                if ((h == 0 || h == 12) && m == 0 && s == 0) {
                    alarm++;
                } 
                else if ((h % 12) * 3600 + m * 60 + s == s * 720 || m * 720 + s * 12 == s * 720) {
                    alarm++;
                }
                break;
            }
            
            if ((h == 0 || h == 12) && m == 0 && s == 0) {
                alarm++;
            } else {
                alarm += meets();
            }
            
            timeLapse();
        }
        return alarm;
    }
    
    // 시간 흐름
    public static void timeLapse() {
        s++;
        if (s == 60) {
            s = 0;
            m++;
        }
        if (m == 60) {
            m = 0;
            h++;
        }
    }
    // 겹치는지 확인
    public static int meets() {
        int result = 0;
        // 위치
        // 시는 1초에 1씩, 분은 1초에 12씩, 초는 1초에 720씩
        int hp = (h % 12) * 3600 + m * 60 + s;
        int mp = m * 720 + s * 12;
        int sp = s * 720;
        
        // 시 - 초
        if (hp >= sp && hp + 1 < sp + 720) {
            result++;
        }
        // 분 - 초
        if (mp >= sp && mp + 12 < sp + 720) {
            result++;
        }
        
        return result;
    }
}