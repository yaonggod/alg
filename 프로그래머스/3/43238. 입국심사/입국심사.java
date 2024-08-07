import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long maxTime = 0;
        for (int t : times) {
            maxTime = Math.max(maxTime, t);
        }
        maxTime *= n;
        long minTime = 1;
        
        long answer = maxTime;
        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) / 2;
            long count = 0;
            // 한 심사관이 처리할 수 있는 사람
            for (int t : times) {
                count += (midTime / t);
            }
            if (count >= n) {
                answer = Math.min(answer, midTime);
                maxTime = midTime - 1;
            } else {
                minTime = midTime + 1;
            }
        }
        return answer;
    }
}