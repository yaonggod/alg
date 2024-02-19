import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        stack.push(numbers[numbers.length - 1]);
        
        for (int i = numbers.length - 2; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int n = stack.pop();
                // 뒤에 있는 큰 수 찾았음
                // pop한 숫자들은 numbers[i]보다 어차피 작거나 같아서 쓸 일 없음
                if (n > numbers[i]) {
                    // 나중에 또 쓰기 위해 도로 넣기
                    stack.push(n);
                    answer[i] = n;
                    break;
                }
            }
            // 나도 넣기
            stack.push(numbers[i]);
        }
        
        return answer;
    }
}