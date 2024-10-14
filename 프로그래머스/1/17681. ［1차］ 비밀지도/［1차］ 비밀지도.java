import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        char[][] map = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], ' ');
            int a1 = arr1[i];
            int idx = n - 1;
            while (a1 > 0) {
                if (a1 % 2 == 1) {
                    map[i][idx] = '#';
                }
                a1 /= 2;
                idx--;
            }
            int a2 = arr2[i];
            idx = n - 1;
            while (a2 > 0) {
                if (a2 % 2 == 1) {
                    map[i][idx] = '#';
                }
                a2 /= 2;
                idx--;
            }
        }
        
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]);
            }
            ans[i] = sb.toString();
        }
        
        return ans;
    }
}