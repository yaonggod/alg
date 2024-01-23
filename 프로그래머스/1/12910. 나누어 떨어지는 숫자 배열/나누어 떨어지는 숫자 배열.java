import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                ans.add(arr[i]);
            }
        }
    
        if (ans.size() == 0) return new int[]{-1};
        
        Collections.sort(ans);
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}