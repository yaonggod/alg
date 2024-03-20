import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        // 지금 말하고 있는 숫자
        int current = 0;
        // 진법으로 변환
        int c = current;
        // 순서 - 튜브는 turn % m == p일 경우에만 말하면 됨
        int turn = 1;
        
        String result = "";
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (turn <= t * m) {
            // 스택에서 하나씩 가져다가 쓰기
            if (!stack.isEmpty()) {
                int num = stack.pop();
                // 내 차례가 왔다 
                if ((m == p && turn % m == 0) || (turn % m == p)) {
                    if (num >= 10) result += (char) (num + 55);
                    else result += num;
                }
                turn++;
            // 스택 새로 만들기
            } else {
                current++;
                c = current;
                while (c != 0) {
                    stack.push(c % n);
                    c /= n;
                }
            }
            if (result.length() == t) break;
        }
        return result;
    }
}