import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 괄호 벗기기
        s = s.substring(1, s.length() - 1);
        String[] tupleArr = s.split("},");
        int n = tupleArr.length;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                tupleArr[i] = tupleArr[i].substring(1, tupleArr[i].length() - 1);
            } else {
                tupleArr[i] = tupleArr[i].substring(1, tupleArr[i].length());
            }
        }
        // 길이별로 정리하기
        Arrays.sort(tupleArr, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int countA = a.split(",").length;
                int countB = b.split(",").length;
                return countA - countB;
            }
        });
        // 배열 만들기
        Set<Integer> numSet = new HashSet<>();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            String[] tuple = tupleArr[i].split(",");
            for (String num : tuple) {
                if (!numSet.contains(Integer.parseInt(num))) {
                    numSet.add(Integer.parseInt(num));
                    result[i] = Integer.parseInt(num);
                    break;
                }
            }
        }
        return result;
    }
}