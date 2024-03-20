import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        
        Arrays.sort(citations);
        
        int hIndex = 0;
        for (int i = 0; i < n; i++) {
            int count = n - i;
            int h = citations[i] > count ? count : citations[i];
            if (hIndex < h) hIndex = h;
        }
        return hIndex;
    }
}