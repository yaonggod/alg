import java.util.*;

class Solution {
    static int ans;
    // 이분탐색
    static int left = 1;
    static int right = 0;
    static int mid;
    static int[] DIFFS;
    static int[] TIMES;
    public int solution(int[] diffs, int[] times, long limit) {
        // 난이도 최대값
        for (int i = 0; i < diffs.length; i++) {
            right = Math.max(right, diffs[i]);
        }
        ans = right;
        DIFFS = diffs;
        TIMES = times;
        
        while (left <= right) {
            mid = (left + right) / 2;
            long t = totalTime(mid);
            if (t <= limit) {
                // 줄여보자
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    static long totalTime(int level) {
        long result = TIMES[0];
        for (int i = 1; i < DIFFS.length; i++) {
            if (DIFFS[i] <= level) {
                result += TIMES[i];
            } else {
                result += (TIMES[i] + TIMES[i - 1]) * (DIFFS[i] - level) + TIMES[i];
            }
        }
        
        return result;
    }
}