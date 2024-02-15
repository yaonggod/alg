import java.util.*;

class Solution {
    public double solution(int r1, int r2) {
        long answer = 0;
        // 경계값
        answer += (r2 - r1 + 1) * 4;
        
        for (int i = 1; i < r2; i++) {
            double max = Math.pow(Math.pow(r2, 2) - Math.pow(i, 2), 0.5);
            double min = Math.pow(Math.pow(r1, 2) - Math.pow(i, 2), 0.5);
            int maxb = (int) Math.floor(max);
            int minb = (int) Math.ceil(min);
            
            if (minb != 0) {
                answer += (maxb - minb + 1) * 4;
            } else {
                answer += (maxb - minb) * 4;
            }

        }
        return answer;
    }
}