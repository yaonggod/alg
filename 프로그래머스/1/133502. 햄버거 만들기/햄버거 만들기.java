import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int count = 0;
        
        StringBuilder sb = new StringBuilder();
        for (int i : ingredient) {
            sb.append(i);
            
            if (sb.length() > 3 && sb.substring(sb.length() - 4, sb.length()).equals("1231")) {
                sb.replace(sb.length() - 4, sb.length(), "");
                count++;
            }
        }
        return count;
    }
}