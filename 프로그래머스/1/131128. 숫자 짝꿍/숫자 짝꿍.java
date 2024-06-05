import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[][] count = new int[10][2];
        
        for (int i = 0; i < X.length(); i++) {
            int num = Integer.parseInt(X.substring(i, i + 1));
            count[num][0]++;
        }
        for (int i = 0; i < Y.length(); i++) {
            int num = Integer.parseInt(Y.substring(i, i + 1));
            count[num][1]++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            int min = Math.min(count[i][0], count[i][1]);
            if (min > 0) {
                for (int j = 0; j < min; j++) {
                    sb.append(i);
                }
            }
        }
        
        int min = Math.min(count[0][0], count[0][1]);
        if (min > 0) {
            if (sb.length() == 0) sb.append(0);
            else {
                for (int i = 0; i < min; i++) {
                    sb.append(0);
                }
            }
        }
        
        
        if (sb.length() == 0) return "-1";
        return sb.toString();
    }
}