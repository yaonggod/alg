import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] leftmin = new int[n];
        int[] rightmin = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (i == 0) leftmin[i] = a[i];
            else {
                if (a[i] < leftmin[i - 1]) leftmin[i] = a[i];
                else leftmin[i] = leftmin[i - 1];
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) rightmin[i] = a[i];
            else {
                if (a[i] < rightmin[i + 1]) rightmin[i] = a[i];
                else rightmin[i] = rightmin[i + 1];
            }
        }
        int count = 2;
        for (int i = 1; i < n - 1; i++) {
            if (leftmin[i - 1] > a[i] || rightmin[i + 1] > a[i]) count++;
        }
        return count;
    }
}