import java.util.*;

class Solution {
    public int solution(String t, String p) {
        int l = p.length();
        int count = 0;
        long compare = Long.parseLong(p);
        for (int i = 0; i <= t.length() - l; i++) {
            long sub = Long.parseLong(t.substring(i, i + l));
            if (sub <= compare) count++;
        }
        return count;
    }
}