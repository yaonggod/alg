import java.util.*;

class Solution {
    public int solution(String s) {
        char[] sArr = s.toCharArray();
        Stack<Character> stack = new Stack();
        stack.push(sArr[0]);
        
        for (int i = 1; i < sArr.length; i++) {
            if (stack.size() > 0 && stack.peek() == sArr[i]) {
                stack.pop();
            } else {
                stack.push(sArr[i]);
            }
        }
        if (stack.size() == 0) return 1;
        return 0;
    }
}