import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        // 97 122
        // 65 90
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (i == 0 && lower((int) str[i])) {
                str[i] = (char) ((int) str[i] - 32);
            } else if (i > 0 && str[i - 1] == ' ' && lower((int) str[i])) {
                str[i] = (char) ((int) str[i] - 32);
            } else if (i > 0 && str[i - 1] != ' ' && upper((int) str[i])) {
                str[i] = (char) ((int) str[i] + 32);
            }
        }
        for (char st : str) {
            sb.append(st);
        }
        return sb.toString();
    }
    
    static boolean lower(int a) {
        return a >= 97 && a <= 122;
    }
    
    static boolean upper(int a) {
        return a >= 65 && a <= 90;
    }
}