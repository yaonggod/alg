import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int n1 = char1.length;
        int n2 = char2.length;
        
        // 소문자 97~122
        // 대문자 65~90
    
        int[][] strings = new int[26][26];
        int[][] visited = new int[26][26];
        
        int union = 0;
        int cross = 0;
        for (int i = 1; i < n1; i++) {
            char t0 = char1[i - 1];
            char t1 = char1[i];
            if (alphabet(t0) && alphabet(t1)) {
                strings[index(t0)][index(t1)]++;
                union++;
            }        
            
        }
        for (int i = 1; i < n2; i++) {
            char t0 = char2[i - 1];
            char t1 = char2[i];
            if (alphabet(t0) && alphabet(t1)) {
                union++;
                if (strings[index(t0)][index(t1)] > 0) {
                    strings[index(t0)][index(t1)]--;
                    cross++;
                }
            }
        }
        union -= cross;
        if (union == 0) return 65536;
        double jakad = cross / (double) union;
        return (int) Math.floor(jakad * 65536);
    }
    
    public boolean alphabet(char c) {
        int ci = (int) c; 
        return (ci >= 97 && ci <= 122) || (ci >= 65 && ci <= 90);
    }
    
    public int index(char c) {
        if ((int) c >= 97) return (int) c - 97;
        return (int) c - 65;
    }
}