import java.util.*;

class Solution {
    public int solution(String s) {
        char[] gwalho = s.toCharArray();
        int n = gwalho.length;
        boolean[] result = new boolean[n];
        Stack<Integer[]> stack = new Stack<>();
        int turn = 0;
        // 0 1 2 3 4 5 6 7 8 9 10
        //             0 1 2 3  4
        // 5 ~ 10 검사하기
        while (turn < n * 2 - 1) {
            // 괄호 푸시하거나 팝하거나
            if (stack.isEmpty()) {
                stack.push(new Integer[] {(int) gwalho[turn % n], turn});
            } else {
                if (match(stack.peek()[0], (int) gwalho[turn % n])) {
                    stack.pop();
                } else {
                    stack.push(new Integer[] {(int) gwalho[turn % n], turn});
                }
            }
            if (turn >= n - 1) {
                // 모든 괄호가 정리되었다
                if (stack.isEmpty()) {
                    result[turn - n + 1] = true;
                // 스택에 남아있는 괄호도 이번 턴에 쓰는게 아니다
                } else if (stack.peek()[1] + n <= turn) {
                    result[turn - n + 1] = true;
                }
            }
            turn++;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (result[i]) answer++;
        }
        return answer;
    }
    
    static boolean match(int a, int b) {
        if (a == 40 && b == 41) return true;
        if (a == 91 && b == 93) return true;
        if (a == 123 && b == 125) return true;
        return false;
    }
}