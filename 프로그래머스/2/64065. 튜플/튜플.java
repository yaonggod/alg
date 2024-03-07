import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 괄호 벗기기
        String[] tupleArr = s.replaceAll("[{}]", " ").trim().split(" , ");
        
        // 길이별로 정리하기
        Arrays.sort(tupleArr, (a, b) -> a.length() - b.length());
        
        // 배열 만들기
        Set<Integer> numSet = new HashSet<>();
        int n = tupleArr.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int[] tuple = Arrays.stream(tupleArr[i].split(",")).mapToInt(Integer::parseInt).toArray();
            for (int num : tuple) {
                if (!numSet.contains(num)) {
                    numSet.add(num);
                    result[i] = num;
                    break;
                }
            }
        }
        return result;
    }
}