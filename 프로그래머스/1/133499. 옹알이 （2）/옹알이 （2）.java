import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String b : babbling) {
            StringBuilder sb = new StringBuilder();
            char[] bChar = b.toCharArray();
            int n = bChar.length;
            int before = 0;
            
            for (char c : bChar) {
                sb.append(c);
                int l = sb.length();
                if (l >= 2) {
                    if (sb.substring(l - 2, l).equals("ye") && before != 1) {
                        sb.setCharAt(l - 2, '0');
                        sb.setCharAt(l - 1, '0');
                        before = 1;
                    } else if (sb.substring(l - 2, l).equals("ma") && before != 2) {
                        sb.setCharAt(l - 2, '0');
                        sb.setCharAt(l - 1, '0');
                        before = 2;
                    }
                } 
                if (l >= 3) {
                    if (sb.substring(l - 3, l).equals("aya") && before != 3) {
                        sb.setCharAt(l - 3, '0');
                        sb.setCharAt(l - 2, '0');
                        sb.setCharAt(l - 1, '0');
                        before = 3;
                    } else if (sb.substring(l - 3, l).equals("woo") && before != 4) {
                        sb.setCharAt(l - 3, '0');
                        sb.setCharAt(l - 2, '0');
                        sb.setCharAt(l - 1, '0');
                        before = 4;
                    }
                }
            }
            StringBuilder compare = new StringBuilder();
            for (int i = 0; i < n; i++) {
                compare.append('0');
            }
            if (sb.toString().equals(compare.toString())) answer++;
        }
        return answer;
    }
}