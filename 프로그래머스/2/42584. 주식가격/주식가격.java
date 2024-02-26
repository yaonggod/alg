import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 뒤에 있는 애들 중 가장 가까이 있는 작은 수를 리턴하기
        Stack<Integer[]> stack = new Stack<>();
        for (int i = prices.length - 1; i >= 0; i--) {
            boolean renew = false;
            while (!stack.isEmpty()) {
                Integer[] c = stack.pop();
                // 찾음
                if (c[0] < prices[i]) {
                    renew = true;
                    // 다시 넣어서 나중에 또 쓰기
                    stack.push(c);
                    answer[i] = c[1] - i;
                    break;
                }
            }
            // 떨어진 적 없다
            if (!renew) {
                answer[i] = prices.length - i - 1;
            }
            // 나도 넣기
            stack.push(new Integer[] {prices[i], i});
        }
        
        return answer;
    }
}