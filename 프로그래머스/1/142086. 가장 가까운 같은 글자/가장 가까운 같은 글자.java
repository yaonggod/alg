import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Arrays.fill(answer, -1);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (index.containsKey((int) s.charAt(i))) {
                answer[i] = i - index.get((int) s.charAt(i));
            }
            index.put((int) s.charAt(i), i);
        }
        return answer;
    }
}