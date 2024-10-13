import java.util.*;

class Solution {
    static String[] no = new String[] {"ayaaya", "yeye", "woowoo", "mama"};
    static String[] yes = new String[] {"aya", "ye", "woo", "ma"};
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String b : babbling) {
            answer += possible(b);
        }
        return answer;
    }
    
    static int possible(String bab) {
        for (String n : no) {
            if (bab.contains(n)) return 0;
        }
        
        for (String y : yes) {
            bab = bab.replace(y, " ");
        }
        bab = bab.replace(" ", "");
        
        if (bab.length() == 0) return 1;
        return 0;
    }
}