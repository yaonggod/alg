import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        ans.add(arr[0]);
        int last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != last) {
                ans.add(arr[i]);
                last = arr[i];
            }
        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}