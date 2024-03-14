import java.util.*;

class Solution {
    public String solution(String s) {
        String[] str = s.toLowerCase().split("");
        
        for (int i = 0; i < str.length; i++) {
            if (i == 0) str[i] = str[i].toUpperCase();
            else if (str[i - 1].equals(" ")) str[i] = str[i].toUpperCase();
        }
        String answer = "";
        for (String st : str) {
            answer += st;
        }
        return answer;
    }
    
}