import java.util.*;

class Solution {
    public int[] solution(String msg) {
        // 사전
        Map<String, Integer> dict = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i <= 26; i++) {
            dict.put(alphabet.substring(i - 1, i), i);
        }
        
        char[] msgArr = msg.toCharArray();
        List<Integer> answer = new ArrayList<>();
        String check = "";
        String last = "";
        int index = 0;
        while (index < msgArr.length) {
            // 글자 하나씩 더해보고
            check += msgArr[index];
            // 이미 사전에 있는거면 가장 마지막에 나왔던 단어로 저장해두고
            if (dict.containsKey(check)) {
                last = check;
                index++;
            // 새로운 단어가 나왔을 때
            } else {
                // 새로운 단어를 저장하고
                int newWordIdx = dict.size() + 1;
                dict.put(check, newWordIdx);
                check = "";
                // 마지막에 나왔던 단어 출력하기
                answer.add(dict.get(last));
            }
        }
        answer.add(dict.get(last));
        return answer.stream().mapToInt(i -> i).toArray();
    }
}