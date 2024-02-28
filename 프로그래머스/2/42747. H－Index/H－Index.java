import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        
        Arrays.sort(citations);
        
        int hIndex = 0;
        for (int i = 0; i < n; i++) {
            // 논문
            int c = citations[i];
            // 길이
            int length = n - i;
            if (hIndex < Math.min(c, length)) {
                hIndex = Math.min(c, length);
            }
        }
        return hIndex;
    }
}