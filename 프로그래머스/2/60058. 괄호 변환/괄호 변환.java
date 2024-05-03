import java.util.*;

class Solution {
    public String solution(String p) {
        return go(p);
    }
    
    static String go(String str) {
        // 올바르지 않으면
        if (!matches(str)) {
            // u와 v로 나눔, u는 앞에서부터 가장 작은 단위의 균형 문자열
            String[] divided = uv(str);
            String u = divided[0];
            String v = divided[1];
            // u가 올바르면
            if (matches(u)) {
                // v에 대해 go 수행
                if (!v.equals("")) {
                    v = go(v);
                }
                // u + v
                StringBuilder sb = new StringBuilder();
                sb.append(u).append(v);
                return sb.toString();
            // u가 올바르지 않으면 
            } else {
                StringBuilder sb = new StringBuilder();
                if (!v.equals("")) {
                    v = go(v);
                }
                // 4단계 수행 
                sb.append("(").append(v).append(")");
                for (int i = 1; i < u.length() - 1; i++) {
                    sb.append(u.charAt(i) == '(' ? ")" : "(");
                }
                return sb.toString();
            }
        }
        return str;
    }
    
    // 올바른 == 괄호 짝이 맞는
    static boolean matches(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                }
            }
        }    
        return stack.size() == 0 ? true : false;
    }
    
    // 가장 작은 단위의 균형 문자열 나누기 
    static String[] uv(String str) {
        int left = str.charAt(0) == '(' ? 1 : 0;
        int right = str.charAt(0) == ')' ? 1 : 0;
        int divide = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                divide = i + 1;
                break;
            }
        }
        return new String[] {str.substring(0, divide), str.substring(divide, str.length())};
    }
}