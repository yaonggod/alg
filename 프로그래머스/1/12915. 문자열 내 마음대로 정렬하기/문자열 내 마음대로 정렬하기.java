import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.charAt(n) != s2.charAt(n)) return s1.charAt(n) - s2.charAt(n);
                for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return s1.charAt(i) - s2.charAt(i);
                }
                if (s1.length() < s2.length()) return -1;
                return 1;
            }
        });
        return strings;
    }
}